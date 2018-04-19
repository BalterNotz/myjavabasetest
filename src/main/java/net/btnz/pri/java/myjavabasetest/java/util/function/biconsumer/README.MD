BiConsumer与Consumer函数接口类似，只不过函数的参数变成两个。
有两个函数：void accept(T t, U u), default BiCnsumer<T,U> andThen(BiConsumer<? super T, ? super U> after);

accept:用于执行对参数t、u的操作。
andThen：用于组合本次BiConsumer的操作与下一步的操作，先对参数执行本次BiConsumer的accept函数，再对参数执行after参数的accept函数。