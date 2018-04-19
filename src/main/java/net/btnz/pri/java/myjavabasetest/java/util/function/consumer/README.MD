Consumer表示，一个接收单一参数不返回任何结果的函数接口，用来表示具有副作用的操作。

具有两个方法：void accept(T t), default Consumer<T> andThen(Consumer<? super T> after);

accept函数，执行对参数的操作。
andThen函数，返回一个组合的操作函数，先执行本Consumer的accept函数，然后再执行参数after的accept函数。表示一系列串行的操作。