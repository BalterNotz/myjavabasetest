阻塞队列，用来存储等待执行的任务，对线程池的运行过程产生重大影响，一般有以下几种选择：
ArrayBlockingQueue：其于数组的先进先出队列，此队列创建时必须指定大小
LinkedBlockingQueue：其于链表的先进先出队列，如果创建时没有指定此队列大小，则默认为Integer.MAX_VALUE;
SynchronousQueue：这个队列比较特殊，它不会保存提交的任务，而是将直接新建一个线程来执行新来的任务
PriorityBlockingQueue
ArrayBlockingQueue和PriorityBlockingQueue使用较少，一般使用LinkedBlockingQueue和SynchronousQueue。
线程池的排队策略与BlockingQueue有关。

任务拒绝策略：
当线程池的任务缓存队列已满并且线程池中的线程数目达到maximumPoolSize，如果还有任务到来就会采取任务拒绝策略，通常有以下四种策略：
ThreadPoolExecutor.AbortPolicy:丢弃任务并抛出RejectedExecutionException异常。
ThreadPoolExecutor.DiscardPolicy：也是丢弃任务，但是不抛出异常。
ThreadPoolExecutor.DiscardOldestPolicy：丢弃队列最前面的任务，然后重新尝试执行任务（重复此过程）
ThreadPoolExecutor.CallerRunsPolicy：由调用线程处理该任务

线程池的关闭：
shutdown()
shutdownNow()

线程池动态调整
setCorePoolSize()
setMaximumPoolSize()

BlockingQueue:
当队列中没有数据时，消费者将处于阻塞状态，直到有数据放入队列。
