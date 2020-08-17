package com.gqx.jdk8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 *
 * @author gqx
 * @date 2020/8/13 15:13
 *
 * stream  流由三部分构成
 * 1：源
 * 2：零个或多个中间操作
 * 3：终止操作
 * 流操作分类
 * 1：惰性求值
 *  stream.xxx().yyyy().zzz()
 *  当没有终止操作时。，是不会去执行的
 * 2：及早求值
 *  stream.xxx().yyyy().zzz().count()
 *
 *
 *  1.Collection提供了新的stream()方法
 *  2.流不储存值，通过管道的方法获取值
 *  3.本质是函数式的，对流的操作会生成一个结果，不过并不会修改底层的数据源，集合可以作为流的底层数据源
 *  4.延迟查找，很多流操作(过滤、映射、排序等)都可以延迟实现
 *
 *
 *
 *  1.和迭代器不同的是，Stream可以并行化操作，迭代器只能命令式的、穿行操作
 *  2.当使用穿行方式去遍历时，每个item 读完后在度下一个item
 *  3.使用并行去遍历时，数据会被分成多个段，其中每个都在不同的线程中处理，然后将结果一起输出
 *  4.Stream的并行操作依赖于JAVA7 中引入的Fork/Join框架
 *
 *
 *  中间操作都会返回一个Stream 对象。
 *   终止操作则不会返回Stream对象，可能不返回值，也可能返回其他类型的单个值
 *  内部迭代
 *
 *  外部迭代
 *  <pre>
 *      {@code
 *      for(i=0;i<list.length;i++){
 *          A a = list.get(i);
 *          if(......){
 *              .........
 *          }
 *      }
 *      Collection.sort(list,Comparator()........);
 *      for(int i;i<list.length;i++){
 *          ........................
 *      }
 *      }
 *  </pre>
 *  集合关注数据和数据存储本身
 *  流关注的则是对数据的计算
 *
 *  流和迭代器类似的一点是，流是无法重复使用或者消费的
 *
 *
 *  流的执行策略为 List 取出其中一个元素按照 方法顺序执行map.filter 等等。。。。
 *
 *  当已经执行的数据满足结果时。，就不会迭代集合中后续的元素 。。称为短路运行类似
 *
 *  <pre>
 *      {@code
 *          for(A a:list){
 *              map..
 *              filter...
 *              .........
 *              if(满足特定条件)
 *                  return;
 *
 *          }
 *      }
 *  </pre>
 *
 *  Intermediate: 一个流可以后面跟随零个或多个intermediate操作。其目的的主要是打开流
 *  ，做出某种程度的数据映射/过滤，然后返回一个新的流，交给下一个操作使用，这类操作都是延迟
 *  （lazy）,就是说，仅仅调用到这类方法，并没有真正开始流的遍历
 *
 *
 *  Terminal: 一个流只能有一个terminal操作，当这个操作执行后，流就被使用“光”了,无法在被操作。
 *  所以这必定是流的最后一个操作。Terminal 操作的执行，才会真正开始流的遍历，并且会生成一个结果。
 *
 *
 *
 */
public class TestStream1 {
    public static void main(String[] args) {
        /**
         * 得到 stream 的三种方式
         */
        Stream stream1 = Stream.of("hello","word","hello word");

        String[] myArr = new String[]{"hello","word","hello word"};
        Stream stream2 = Stream.of(myArr);
        Stream stream3 = Arrays.stream(myArr);

        List<String> list = Arrays.asList(myArr);
        Stream stream4 = list.stream();


    }
}
