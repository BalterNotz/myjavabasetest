#Iterable

实现该接口，表示该对类可以被for-each loop语句遍历

##函数

1. Iterator<T> iterator(): 返回一个迭代器；
2. default void forEach(Consumer<? super T> action): 对集全中的每一个元素使用action函数进行一次应用；
3. 