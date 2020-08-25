#Stream
>
> * 是一个元素的序列，同时支持穿行和并行的聚合操作
>      
>   > 通过 Collection<Widget> widgets 的stream方法生成 stream对象
>   > 在使用filter 过滤出只有 RED 的 widgets stream，mapToInt 将 
>   > widgets stream 转换为 IntStream 然后进行求和
>   >``` 
>   > int sum = widgets.stream()
>   >                 .filter(w -> w.getClolor() == RED)
>   >                  .mapToInt(w -> w.getWeight)
>   >                  .sum();
>   >```
> * stream 本身是一个对象引用流，还有一些原生特化的版本，
>   如：IntStream,LongStream,DoubleStream 主要是减少 装箱、拆箱的操作
> 
> * 为了执行计算，流操作会被组合到一个流管道(stream pipeline)当中；一个流管道
>    包含一个源(流中数据来自的地方，array(集合),collection(数组)，generator function
>     (生成器函数),I/O channel (IO通道))；0个或多个中间操作(intermediate operations)，每一个中间操作会将一个流转换
>   换为另一个流；和一个终止操作(terminal operation),终止操作会生成一个结果(side-effect 有副作用的)。
>   stream 是 lazy(延迟的)，对于源数据的计算只有在终止操作发起的时候才会执行；
>   stream 里面的数据只有在需要的时候才会被消费。 
>   
>   > side-effect 副作用,对象应用的传递，Consumer中的accept方法可能会修改引用对象的一些状态，称之为有副作用的。
>   >  ```
>   >      void forEach(Consumer<? super T> action);
>   >   ```                                                                                                                                                                                                                                                             
> * 集合和流有一些相似性，但是他们有不同的目标；集合主要考虑对元素的管理和访问；
>   流并不会提供直接访问或者操做元素的方式，关注以一个声明性的去描述源和计算操作，计算操作可以以聚合的方式在源上去执行；
>   如果所提供流操作并没有满足要求，可以通过 ``` iterator()``` 和 ```spliterator()```进行迭代操作
>
> * 一个流管道可以看作是对流源的一种查询，除非这个流被显示的设计成可以并发修改的(如：```ConcurrentHashMap```)，
>   一个线程修改一个线程查询 就可能出现一些意料之外的情况
> 
> * 一个流只能被操作一次。
>
> * stream 都有一个close 方法，并且实现AutoCloseable,但是大部分流不需要关闭的，只有对I/O 操作的需要关闭。
>
> * 流管道可以以穿行或者并行方式执行，这种执行模式只是流中的一种属性。 
>  
#Collection 
> ##spliterator() 分割迭代器
> * 创建一个针对这个集合元素的分割迭代器
> 
> * 实现应该对spliterator 返回的特性值进行文档化的说明(记录下来)；
>
> * 默认的实现应该被子类重写，返回一个更加高效的分割迭代器，为了保留流(```stream() parallelStream()```)的延迟行为，
>   分割迭代器需要有 不可变的(IMMUTABLE)、并发的(CONCURRENT)或者是延迟绑定的(late-binding)；如果这些都无法实现，
>   重写的类应该用文档化的描述分割迭代器的绑定和结构上出现的修改和行为(用分割迭代器去描述一个流的时候，在进行分割和迭代的时候，
>   流可能被外界进行修改，需要将这种修改描述出来)，并且应该重写 ``` stream() parallelStream() ``` 来创建流 使用 spliterator 
>   的 ```Supplier```来创建，如下：
>   ```Stream<E> s = StreamSupport.stream(()->splitertor(),spliteratorCharacteristics) ```
>   
>
>
>