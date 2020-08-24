package com.gqx.jdk8.stream2;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

/**
 * @author gqx
 * @date 2020/8/17 16:15
 * 1.collect : 收集器
 * 2.Collector作为collect 方法的参数
 * 3.Collector 是一个接口，他是一个可变的汇聚操作，将输入元素累积到一个可变的结果容器中；
 * 它会在所有元素都处理完毕后，将累积的结果转换为一个最终的表示（这是一个可选操作），
 * 它支持串行和并行两种方式执行。
 * 4.Collectors本身提供了关于Collector的常见汇聚实现，Collectors本省实际上是一个实例工厂。
 * 5.Collector 本身是由四个函数构成的，这四个函数会将条目汇聚到结果容器中，并且可以可选的对结果执行最终的转换
 *      <ul>
 *          <li>supplier()创建并返回一个新的结果容器 ； 返回一个Supplier 的对象，Supplier是一个不接受参数返回一个结果的函数式接口</li>
 *          <li>accumulator()"累加器" 将新的数据元素（Stream 指流中的每一个元素）合并到结果容器；返回一个BiConsumer的对象，BiConsumer接收两个参数，不返回值 </li>
 *          <li>combiner() "和并行流紧密相关的"  将两个结果容器合并为一个，接收两个部分结果，并且将其合并起来，它可能将一个结果合并到另一个结果容器中，并且返回它，也有可能返回一个新的结果容器；
 *          返回一个 BinaryOperator 对象, 接收两个相同类型的参数，返回一个相同类型的结果</li>
 *          <li>finisher() 执行一个可选的最终转换，充中间的累积类型转换为最终结果类型；
 *          返回一个Function 对象，接收一个对象返回另外一个对象</li>
 *      </ul>
 * 6.Collectors 会有一个 characteristics 的集合； Characteristics表示一个Collect
 * 的属性，可以用于优化汇聚操作, 这个集合告诉收集器可以对目标执行什么样的处理动作
 *      <ul>
 *          <li>CONCURRENT</li>
 *          <li>UNORDERED</li>
 *          <li>IDENTITY_FINISH</li>
 *      </ul>
 * 7. 使用Collector 执行串行的汇聚操作，会使用supplier()创建一个唯一的结果容器，
 *    并且每一个输入元素都会调用accumulator()一次。一个并行操作，会对输入进行分区，
 *    每一个分区都会创建一个结果容器，累计每一个分区的元素到该分区的结果中(子结果)，使用
 *    combiner() 函数将多个分区的结果合并为一个结果
 * 8. 为了确保穿行和并行执行能够生成等价的结果，
 *    collector函数必须满足两个条件：identity(同一性) 、associativity(结合性)
 *      identity：将中间任意一个部分结果和一个空结果 combiner(合并)。得到的结果要和 对应的部分结果相等
 *      即：a == combiner.apply(a.supplier.get());
 *      associativity:  串行计算和并行计算必须得到一个等价的结果。
 * 9. 对于不包含UNORDERED(无序)特性,即有序的collector,穿行的结果a1 和 并行的结果 a2
 *    finisher.apply(a1).equals(finisher.apply(a2)) 成立才为等价的；而包含UNORDERED
 *    (无序)特性的collector,等价的条件为，包含相同的元素就是等价的，会忽略元素的顺序。
 *
 * 10. 基于collector实现了汇聚操作的库，如Stream.collect()，需要遵守下面的限制
 *      Collector<T,A,R>
 *          T: 进行 collector reduction（汇聚操作） 中的元素的类型，Stream.collect 就是流中元素的类型
 *          A: reduction（汇聚操作） 操作可变的累积类型(中间操作生成结果容器的类型)，通常作为一个实现细节进行隐藏
 *          R：reduction(汇聚操作) 的结果类型
 *      <ul>
 *          <li>
 *              传递给finisher(第一个参数)，accumulator(第一个参数)，combiner(两个参数)，函数的参数，需要时上一次函数调用返回的结果；
 *          </li>
 *          <li>
 *              对于具体的实现，不能对supplier，accumulator，combiner返回的结果做其他的操作，除了将
 *              结果传递给 accumulator，combiner，finisher或者返回给调用者。
 *          </li>
 *          <li>
 *              如果一个结果被传递给combiner，finisher,但是相同的对象并没有从函数中返回，将再也不会去使用它
 *          </li>
 *          <li>
 *              一旦将一个结果传递给 finisher，combiner函数，那么将再也不会传递给accumulator函数
 *          </li>
 *          <li>
 *              对于非并发的收集器，从supplier，accumulator，combiner返回的结果限制在当前线程中，
 *              这使得在并行情况下，无需Collector 去实行额外的同步操作。并行下每个线程都是独立的，只有当
 *              每个线程的accumulator 完成之后， 才会去执行 combiner 合并操作
 *          </li>
 *          <li>
 *              对于并发的收集器，可以只有选择并发的去执行reduction(聚合操作)，它表示的时，多线程公用一个
 *              结果收集器。一个并发的收集器应该在 拥有 UNORDERED(无序)特性，或者原始的数据是无序的。
 *          </li>
 *      </ul>
 *
 *  11. 除了在Collectors 中预定义的实现之外，Supplier,BiConsumer,BinaryOperator,Characteristics ...的静态工厂
 *      方法也可以用来构建收集器
 *
 * 12. Collector被设计成可以组合的，ollector中很多的方法都是一个函数，接受一个collector返回一个新的collector
 *      比如： 计算员工工资的和
 *
 *      <pre>
 *          {@code
 *              Collector<Employee,?,Integer> summingSalaries =
 *                  Collectors.summingInt(EmployeeEmployee::getSalary)
 *          }
 *      </pre>
 *
 *      如果对部门进行分组求和，就可以复用上面的那个collector
 *         <pre>
 *             {@code
 *                  Collector<Employee,?,Map<Department,Integer>>
 *                      summingSalariesByDept = Collectors.groupingBy(Employee::getDepartment,summingSalaries);
 *             }
 *         </pre>
 *
 *
 *
 *
 *
 *  特性：
 *      CONCURRENT : 支持累加器可以并发调用，与并行的区别为，并行的多线程有自己的结果容器，并发调用公用一个结果容器
 *                    如果没有和UNORDERED 同时使用，则数据源必须是无序的
 *      UNORDERED ： 收集操作是无序的
 *
 *      IDENTITY_FINISH : 标识 finisher 函数就是 identity 类型 A->R 是成立的，才能配置为IDENTITY_FINISH,且可以忽略 finisher 函数
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
public class StreamTest1 {
    public static void main(String[] args) {
        Student s1 = Student.builder().name("zhangsan").score(80).build();
        Student s2 = Student.builder().name("lisi").score(90).build();
        Student s3 = Student.builder().name("wangwu").score(100).build();
        Student s4 = Student.builder().name("zhaoliu").score(90).build();
        Student s5 = Student.builder().name("zhaoliu").score(90).build();

        List<Student> list = Arrays.asList(s1, s2, s3, s4,s5);
        list.stream().collect(Collectors.toList()).forEach(System.out::println);
        System.out.println("-----------------------------");
        System.out.println("count:"+list.stream().collect(counting()));
        System.out.println("count:"+list.stream().count());
        System.out.println("-----------------------------");

        list.stream().collect(minBy(Comparator.comparingInt(Student::getScore))).ifPresent(System.out::println);
        list.stream().collect(
                maxBy(Comparator.comparingInt(Student::getScore))).ifPresent(System.out::println);
        System.out.println("-----------------------------");

        System.out.println(list.stream().collect(averagingInt(Student::getScore)));
        System.out.println(list.stream().collect(summingInt(Student::getScore)));
        System.out.println("-----------------------------");
        IntSummaryStatistics intSummaryStatistics = list.stream().collect(summarizingInt(Student::getScore));
        System.out.println(intSummaryStatistics);
        System.out.println("-----------------------------");
        System.out.println(list.stream().map(Student::getName).collect(joining()));
        System.out.println(list.stream().map(Student::getName).collect(joining(",")));
        System.out.println(list.stream().map(Student::getName).collect(joining(",","<begin>","<end>")));
        System.out.println("-----------------------------");
        System.out.println(list.stream().collect(groupingBy(Student::getScore,groupingBy(Student::getName))));
        System.out.println("-----------------------------");
        System.out.println(list.stream().collect(partitioningBy(student -> student.getScore() > 80)));
        System.out.println("-----------------------------");
        System.out.println(list.stream().collect(partitioningBy(student -> student.getScore() > 80,groupingBy(Student::getScore,groupingBy(Student::getName)))));
        System.out.println(list.stream().collect(partitioningBy(student -> student.getScore()>80,counting())));
        System.out.println("-----------------------------");
        System.out.println(list.stream().collect(groupingBy(Student::getName,
                collectingAndThen(
                        minBy(Comparator.
                                comparingInt(Student::getScore)),
                        Optional::get))));
    }

}
