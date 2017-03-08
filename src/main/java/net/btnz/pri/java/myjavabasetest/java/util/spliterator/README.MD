#Spliterator分裂迭代器  可分割迭代器

该接口是Java为了并行遍历数据源中的元素而设计的迭代器，可以类比顺序遍历迭代器Iterator。

对数据源而言，集合是最多的情况，所以Java已经默认在集合框架中为所有的数据结构提供了一个默认的Spliterator实现，相应的这个实现其实就是底层Stream如何并行遍历(Stream.isParallel())的实现，平常用到Spliterator的情况不多。

Spliterator接口的思想，应该要提到Java7的Fork/Join（分支/合并）框架，总得来说就是用递归的方式把并行的任务拆分成更小的任务，然后把每个子任务的结果合并起来生成整体结果。

##函数

1. boolean tryAdvance(Consumer<? super T> action):顺序处理每一个元素，类似Iterator，如果还有元素要处理，返回true，否则返回false；
2. Spliterator<T> trySplit():是为Spliterator专门设计的方法，区分普通的Iterator，该方法会把当前元素划分一部分出去创建一个新的Spliterator作为返回，两个Spliterator会并行执行，如果元素个数小到无法划分则返回null；
3. long estimateSime():该方法用于估算还剩下多少个元素需要遍历；
4. int characteristics():表示该Spliterator有哪些特性，用于可以更好控制和优化Spliterator的使用。

