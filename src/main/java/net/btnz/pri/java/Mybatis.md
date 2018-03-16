##Mybatis缓存机制
<https://www.cnblogs.com/moongeek/p/7689683.html>
###一级缓存

MyBatis 默认开启了一级缓存，一级缓存是在SqlSession 层面进行缓
存的。即，同一个SqlSession ，多次调用同一个Mapper和同一个方法的
同一个参数，只会进行一次数据库查询，然后把数据缓存到缓冲中，以后直
接先从缓存中取出数据，不会直接去查数据库。

​但是不同的SqlSession对象，因为不用的SqlSession都是相互隔离的，
所以相同的Mapper、参数和方法，他还是会再次发送到SQL到数据库去执
行，返回结果。

###二级缓存

为了克服一级缓存所带来的问题，需要开启二级缓存，二级缓存在
SqlSessionFactory层面给各个SqlSession 对象共享。默认二级缓存
是不开启的，需要手动进行配置。

如果这样配置的话，很多其他的配置就会被默认进行，如：

* 映射文件所有的select 语句会被缓存
* 映射文件的所有的insert、update和delete语句会刷新缓存
* 缓存会使用默认的Least Recently Used（LRU，最近最少使用原则）
的算法来回收缓存空间
* 根据时间表，比如No Flush Interval，（CNFI，没有刷新间隔），
缓存不会以任何时间顺序来刷新
* 缓存会存储列表集合或对象（无论查询方法返回什么）的1024个引用
* 缓存会被视为是read/write（可读/可写）的缓存，意味着对象检索
不是共享的，而且可以很安全的被调用者修改，不干扰其他调用者或线程
所作的潜在修改

各个属性意义如下：

    eviction：缓存回收策略
        LRU：最少使用原则，移除最长时间不使用的对象
        FIFO：先进先出原则，按照对象进入缓存顺序进行回收
        SOFT：软引用，移除基于垃圾回收器状态和软引用规则的对象
        WEAK：弱引用，更积极的移除移除基于垃圾回收器状态和弱引用规则的对象
    flushInterval：刷新时间间隔，单位为毫秒，这里配置的100毫秒。如果不配置，那么只有在进行数据库修改操作才会被动刷新缓存区
    size：引用额数目，代表缓存最多可以存储的对象个数
    readOnly：是否只读，如果为true，则所有相同的sql语句返回的是同一个对象（有助于提高性能，但并发操作同一条数据时，可能不安全），如果设置为false，则相同的sql，后面访问的是cache的clone副本。

可以在Mapper的具体方法下设置对二级缓存的访问意愿：

    useCache配置

如果一条语句每次都需要最新的数据，就意味着每次都需要从数据库中查
询数据，可以把这个属性设置为false，如：

    <select id="selectAll" resultMap="BaseResultMap" 
    useCache="false">

刷新缓存（就是清空缓存）

​二级缓存默认会在insert、update、delete操作后刷新缓存，可以手
动配置不更新缓存，如下：

    <update id="updateById" parameterType="User" flushCa
    che="false" />

###自定义缓存

​ 自定义缓存对象，该对象必须实现 org.apache.ibatis.cache.Cache 
接口，如下：

​在Mapper文件里配置使用该自定义的缓存对象，如：

    <cache type="com.sanyue.utils.BatisCache"/>

####关闭一级缓存

1. 方法一
<http://blog.csdn.net/j7636579/article/details/73647885>
使用的是org.mybatis.spring.SqlSessionFactoryBean生
成的SqlSessionFactory默认配置的话,在对应Mapper的内使用
flushCache可以让mybatis进行查询时每次清空本地缓存,虽然他每次依
旧会读取和重新存进去~~

    @Options(timeout = 10000,  
    flushCache = FlushCachePolicy.TRUE)

2. 方法二

一个随机串到SQL里面。

    where #{randomString}=#{randomString}

3. 调用SqlSession.clearCache()