#Iterator

迭代器接口，用于遍历集合，用于代替Enumeration接口。

与Enumeration接口相比有两个改进：

1. Iteratiors允许在遍历的过和事删除集合中的元素；
2. 改良的方法名称。

##函数

##next

1. E next() 取得下一个元素。
2. default remove() 删除集合中的元素，默认实现为抛出异常（UnsupportedOperationException("remove")），从集合中删除最近一个返回的元素，该方法只能在每次调用完next()函数时调用一次，使用Iterator对集合进行遍历的同时使用除remove()之外的其它方法对集合进行的修改，Iterator没有明确指明会有什么影响。
3. default void forEachRemaining(Consumer<? super E> action) 该函数的意义是：使用参数action对集合中剩余的每一个元素进行一次处理。