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
> * 这些要求确保了 ```stream()```和```parallelStream()``` 生成的流，从终止流操作发起的时候会反应出集合的内容   
>
> * 默认的实现会从集合的```Iterator``` 迭代器中创建一个```late-binding```延迟绑定的分割迭代器(spliterator),
>   所创建出来分割迭代器会继承集合中迭代器的```fail-fast```快速失败属性
>
> * 所创建的 ```Spliterator```分割迭代器拥有 ```Spliterator#SIZED```(固定大小) 这个特性值
>
> * 所创建出来的```Spliterator```分割迭代器还会额外增加 ```Spliterator#SUBSIZED```(子大小固定) 这个特性值
>   分割迭代器分割之后会分成若干个块，每个块的大小是固定的。```SUBSIZED```
> 
> * 如果分割迭代器里面的元素是空的，除了 ```SIZED``` 和```SUBSIZED``` 的其他特性值对与客户的计算是不会有帮助作用的；
>   但是可以让分割迭代器这个实例得到重用
>
> * 返回一个针对集合元素的```Spliterator```分割迭代器
>
>
#Spliterator
>
> * 分割迭代器是一个对象，用于对源中的元素进行遍历和分区。源可以是 数组，集合，IO通道，生成器函数。
>
> * 一个分割迭代器可以单个的去遍历元素 ```tryAdvance()``` 也可以按照顺序成块的方式遍历 ```forEachRemaining()``` 。
>
> * 一个分割迭代器还可以对他的元素进行分区，使用 ```trySplit``` 分成另外一个分割迭代器，并且可能是并行的方式进行使用。
>   对一个Spliterator进行分区时，在一个高度不平衡或者效率非常低的时候进行分割，不太能从并行流中获益。
>
> * 遍历和分割都会消耗调元素，每个分割迭代器只对它所对应的元素起作用
> 
> * 分割迭代器还会去报告 源、源的结构、源的元素 特性值的集合；特性值有 ```ORDER```有序的、```DISTINCT```不同的
>   ```SORTED``` 带排序的、```SIZED```确定大小的、```NONNULL```不为空的、````IMMUTABLE``不可变的、
>   ```CONCURRENT```并发的、```SUBSIZED```子部分固定大小的。这些可以被一个分割迭代器的一个客户端来去使用，用来控制
>   具体化或者简化一些计算。比如: 针对 ```Collection``` 返回 ```SIZED```,针对```Set```返回```DISTINCT```,
>   针对```SortedSet```返回```STORED```。 Characteristics 会对这些特性值以```bit``` 位操作返回。
>
> * 有些特性值会额外的限制方法的行为，比如：```ORDERED``` 遍历方法需要遵循定义好的顺序。新的特新可能会在未来定义，因此
>   使用者不要进行自定义新的特性值。 
>
> * 分割迭代器不包含```IMMUTABLE```或者```CONCURRENT```期望有一个文档化的一个策略上的考量：
>   1. 当分割迭代器绑定到元素的源上时；并且在绑定之后，要对元素的源的结构上的修改进行检测。
>   2. ```late-binding```延迟绑定的分割迭代器会在第一次遍历、第一次分割、第一次查询大小的时候
>      进行源的绑定，而不是在分割迭代器创建的时候绑定。
>   3. 如果不是延迟绑定的分割迭代器，在分割迭代器创建的时候绑定源，或者在分割迭代器里面任意一个方法首次调用时绑定。
>   4. 对于源的修改发生在绑定之前，这种修改在分割迭代器遍历的时候就会被反映出来。
>   5. 对于源的修改发生在绑定之后，分割迭代器就会抛出```ConcurrentModificationException```这个异常。按照这种方式运作的分割迭代器称之为```fail-fast```快速失败。
>   6. 块遍历方法```forEachRemaining```在所有元素遍历完之后进行检查，而不是在每一个遍历的时候都进行检查。
> 
> * 分割迭代器可以提供一个还没遍历的元素的大小的估算，通过```estimateSize```方法。在包含```SIZED```这个特性值的时候
>   这个值就等于接下来需要遍历的数量。即便不知道精确的值，一个估算的值对源的操作也是有帮助的。比如可以帮助你确定是否需要分割,
>   是不是以穿行的方式进行遍历剩下的元素。
>
> * 分割迭代器并不要求是线程安全的，相反并行算法使用了分割迭代器的实现，应该确保分割迭代器在某一个时刻只能被一个线程去使用。
>   这个很容易的通过```serial thread-confinement```模式(穿行线程约束)去执行，通常是一种 典型的并行算法递归解耦 自然的结果。
>   一个线程调用了 ```trySplit()``` 返回来的分割迭代可以交由另外一个线程接管，另一个线程可能去遍历，或者进一步分割这个迭代器。
>   分割和遍历行为是不确定的，如果两个或多个线程并行操作同一个分割迭代器。
>   原来的线程将分割迭代器交由另外一个线程进行处理，尽量在开始遍历之前进行处理，因为有些保证只在遍历开始之前才会有效，比如```SIZED```和```estimateSize()```只在遍历开始之前才受到保证。
>   
> * 提供了原生子类型的特化，```ofInt int; ofLong long; ofDouble double```;
>   默认的实现```Spliterator#tryAdvance(java.util.function.Consumer)``` 和 ```Spliterator#forEachRemaining(java.util.function.Consume)```
>   会将原生的值会包装成对应的包装类的实例，这种包装会影响性能上的优势，为了避免装箱的这种操作，相应的原生的方法就应该被使用；
>   比如：```Spliterator.ofInt#tryAdvance(java.util.function.IntConsumer)``` 和 ```Spliterator.ofInt#forEachRemaining(java.util.function.IntConsumer)``` 
>   使用的是原生类型，应该优先使用原生类型的特化版本。
>   即使使用了通用版本，遇到了原生的类型，出现装箱操作，也并不会影响执行的顺序。
>
> * Spliterator 就像 Iterator一样，就是用来遍历源当中的元素的。
>   Spliterator 被设计成支持高效的并行操作，通过支持分解和单元素的迭代来实现。
>   此外，通过Spliterator这种方式去访问每个元素施加了更小的负担相比于Iterator,避免了像
>   Iterator 使用```hasNext(),next()```这两个方法遍历下一个元素，而是使用一个方法。
>   hasNext() Next() 搭配使用可能会出现竞争。
>
> * 对于可变的源来说，源的结构被修改了(元素的添加，替换，删除)，在Spliterator绑定源之后和遍历完成之前，可能会发生任意不确定的行为。
>   比如这种修改在使用 ```java.util.stream```框架的时候就可能产生不确定的结果
>
> * 源的结构上的修改可以通过下面几种方式来修改：
>   > 1. 源的结构不能被修改；比如：```java.util.CopyOnWriteArrayList``` CopyOnWriteArrayList 是针对ArrayList的并发实现，
>   >    通过这个源创建的Spliterator就会包含一个```IMMUTABLE```的特性值。
>   > 2. 源自己管理并发；比如：```java.util.ConcurrentHashMap``` 他的键的集合就是一个并发的源，
>   >    通过这个源创建的Spliterator包含```CONCURRENT```这个特性
>   > 3. 可变的源提供了一种延迟绑定和快速失败的Spliterator，延迟绑定会减少或限制修改影响计算的时间窗口(计算时才进行绑定)。
>   >    快速失败会在遍历已经开始检测源的结构是否已经变化，并且会抛出```ConcurrentmodificationException()```。
>   >    比如： JDK中```ArrayList```和其他的非并发的集合，他们都提供个一种延迟绑定，快速失败的分割迭代器。
>   > 4. 可变的源不是延迟绑定，但是是快速失败的，增加了抛出 ```ConcurrentmodificationExcetion```，因为潜在的可修改的时间窗口被放大了(绑定-----修改时间窗口被放大----->计算)，抛出异常的可能性就更大。
>   > 5. 可变的源提供了延迟绑定，但是不是快速失败的，会出现任意的，不确定的计算结果，在遍历开始之后进行了修改的话。
>   > 6. 可变的源提供了一个非延迟绑定，且不是快速失败的，出现不确定计算结果的可能性更大。
>   
> * tryAdvance(Consumer<? super T> action): 如果有下一个元素，去执行action这个方法，并且返回true,没有下一个元素，返回False,如果时ORDERED的，则会按照顺序执行，产生的异常会抛给调用者。
> 
> * forEachRemaining(Consumer<? super T> action): 针对剩余的元素进行指定的动作，在当前线程使用串行的方式执行，知道所有元素被处理，或者抛出了异常；
>   如果时ORDERED的，则会按照顺序执行，产生的异常会传递给调用者。默认的实现会重复调用tryAdvance这个方法，直到返回false。在必要的情况下，应该被重写。
> 
> * trySplit():返回一个新的Spliterator对；如果这个Spliterator能够进行分割，就会返回一个Spliterator，返回的Spliterator包含的元素不会被当前的Spliterator包含。
>   如果这个Spliterator是一个ORDERED的，那么返回的Spliterator也应该是个ORDERED的。
>   除非当前的Spliterator包含的是个无限的元素的源，否则重复调用这个方法最终会返回一个null，表示无法在继续分割。
>   当返回的结果不为null时：
>   > 1. 在分割之前，```estimateSize()```估算大小返回的值，必须大于或等于分割之后当前Spliterator的```estimateSize()```返回的值，同时大于或等于 ```trySplit()```返回的Spliterator的```estimateSize()```返回的值。
>   > 2. 如果这个Spliterator是一个SUBSIZED的，那么在分割之前的```estimateSize()```返回的值等于，分割之后返回的Spliterator和当前的Spliterator ```estimateSize()```的和。
>
>   这个方法出于任何一个原因都有可能返回一个null, 原因包括：原来的Spliterator就是空的，遍历已经开始，数据结构存在限制，还有性能上的考量。
>   
>   理想的这个方法，在不需要遍历的时候，会将元素分成两半，平衡的并行计算；大多数情况偏离了这个做法仍然有效，比如：对一个近似平衡的树进行近似平衡的分割。或者这个树只有一两个元素，不能在对这个树进行分割。
>   然而很不平衡的分割，或者没有效率的trySplit的这种机制会让并发效率急剧的降低。
>
> * estimateSize()：估算大小，返回一个会被```forEachRemaining()```遍历的元素数量的估算值，如果时无限的元素，未知的元素，或者计算成本特别高的元素，会返回一个```Long#MAX_VALUE```值，Long的最大值。
>   如果这个Spliterator包含SIZED，并且它没有被部分的遍历和分割，或者这个Spliterator包含SUBSIZED,并且没有被部分的进行遍历，那么这个```estimateSize()```就是一个精确的会被完整遍历所遇到元素的个数的值，
>   否则，这个估算得到的值是不精确的，并且随着```trySplit()```的调用会逐渐的减少。
>   一个不太精确的计算是有用的且成本不高的。
>
> * getExactSizeIfKnow():如果知道返回一个确定的大小，如果Spliterator()包含一个SIZED会直接返回一个```estinateSize()```否则直接返回一个-1；
>   默认实现如果Spliterator()包含SIZED,直接返回```estimateSize()```的结果，否则返回 -1。
>   
> 
> * characteristics()：返回一个Spliterator包含特性值的集合，这个结果表示为ORed values 的值从 
>   ```ORDERED、DISTINCT、SORTED、SIZED、NONNULL、IMMUTABLE、CONCURRENT、SUBSIZED```八个中获取，
>   对于一个Spliterator，在trySplit调用之前和调用时，重复调用characteristics()会返回一个相同的值。
>   如果Spliterator返回了一个不同的特性值集合(单个的多次调用当中)，对于使用这个Spliterator的任何计算都是不被保证的。
>   对于一个Spliterator在分割之前和分割之后的特性值可能不同。
>
> * hasCharacteristics(int characteristics)：判断是否包含指定的 characteristics(特性值)。
>
> * getComparator()：如果这个源是有序的，返回一个用于排序的```Comparator```,如果这个源就是自然顺序的，就返回null，如果
>   这个源是无序的，就抛出 ```IllegalStateException()```异常。
>
> * #OfPrimitive
>    ```
>       public interface OfPrimitive<T,T_CONS,T_SPLITR extends Spliterator.OfPrimitive<T,T_CONS,T_SPLITR>> 
>           extends Spliterator<T>    
>   ```
>   * 这个一个Spliterator为原生值而设计的分割迭代器
>   * T         :由Spliterator返回的元素的类型，类型必须要是原生类型的一个包装类型，比如```int```的```Integer```
>   * T_CONS    :必须是原生的Consumer,位于```java.util.Consumer```对于T的原生类型的Consumer，比如```T:Integer,T_CONS:IntConsumer```
>   * T_SPLITR  :原生的分割迭代器的类型，必须是针对T的原生特化,比如```T:Integer,T_CONS:IntConsumer,T_SPLITR:Spliterator.ofInt```
>   
>   
# StreamSupport 
> * 底层的辅助方法，用于创建和操纵流。
>
> * 用于以流的方式展现数据结构。
> 
> * ## stream(Spliterator<T> sp, boolean parallel)
>   * 从一个Spliterator 创建一个穿行或者并行的流
>   
>   * 这个Spliterator仅在终止操作之后进行遍历，分割，查询，进行大小的修改。
>   
>   * 强烈建议，spliterator 包含 IMMUTABLE、CONCURRENT这两个特性值或者延迟绑定这些特性，
>     否着，stream和Spliterator应该去减少潜在的修改时间窗口。
>
# ReferencePipeline 
> * 
> * 这是一个抽象的基类，用来描述一个中间的管道阶段，或者管道源阶段。
> * 
> * ## Head 
>   * 表示引用管道的源阶段
>   * 
>  
# AbstractPipeline 
>
> * 它对于管道类的基类，管道类是Stream和Stream原生特化的实现，他会管理流管道的构建和计算。
>
> * AbstractPipeline表示流管道初始的部分，他封装了流的源，以及零个或者多个中间操作；每个AbstractPipeline
>   通常被称为stages(阶段)，每一个阶段描述的是流的源或者为中间操作。
>
> * 一个具体的中间阶段，通常是从一个AbstractPipeline。原生类型的特化版也是继承于AbstractPipeline。
>   AbstractPipeline包含了大多数评价，计算管道的机制，并且实现了方法，这些方法会被实际操作所使用；
>   原生特定的类添加了一些辅助方法用来将集合添加到特定的容器(实际就是避免了自动拆箱和装箱)。
>
> * 当链接完一个新的中间操作之后，或者是执行了一个终止操作之后，这个流就被认为被消费掉，再也不会有新的中间操作或者终止操作添加上去。
> 
> * 对于串行流以及中间操作都是无状态的并行流，管道的计算是在一个传递中完成的，
>   他将所有的操作“阻塞”在一起(在一次遍历中执行所有操作);
>   eg：stream->map->filter  类似于 for(){map;filter}。
>   对于中间操作是有状态的并行流，执行被分为多个段来执行，其中有状态的操作会被表示为一个段的结尾，每一个段会被单独计算；
>   而且上一个段的计算结果会被当做下一个段的输入元素；
>   eg: stream->map->map->filter..  类似于for(){map;map;}->for(){filter} 将上一段的结果作为下一个段的输入；
>   在所有的情况中，源数据都不会被消费，知道中止操作开始才会被消费。
> * 属性：
>   * previousStage：“上游(upstream)”的pipeline,为源阶段时值为null。
>   * sourceSpliterator：源分割迭代器,只对头阶段有效，
>   * sourceSupplier:
>   * sourceStage：指向pipeline的头阶段，如果为头阶段的时候为自身。
>   * depth：穿行时在当前管道对象到pipeline源的中间操作个数，并行时为有状态的操作个数，在流管道准备计算的时候是有效的(准备工作时)。
>   * parallel：是否为并行的。
>
>  
>   
>