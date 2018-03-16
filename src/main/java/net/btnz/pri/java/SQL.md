##SQL调优

<http://blog.csdn.net/jie_liang/article/details/77340905>

1. 对查询进行优化，应尽量避免全表扫描，首先应考虑在 where 及 
order by 涉及的列上建立索引。	

2. 应尽量避免在 where 子句中对字段进行 null 值判断，否则将导致
引擎放弃使用索引而进行全表扫描，如：	
select id from t where num is null	
可以在num上设置默认值0，确保表中num列没有null值，然后这样查询：	
select id from t where num=0	

3. 应尽量避免在 where 子句中使用!=或<>操作符，否则将引擎放弃
使用索引而进行全表扫描。	

4. 应尽量避免在 where 子句中使用 or 来连接条件，否则将导致引
擎放弃使用索引而进行全表扫描，如：	
select id from t where num=10 or num=20	
可以这样查询：	
select id from t where num=10	
union all	
select id from t where num=20	

5. in 和 not in 也要慎用，否则会导致全表扫描，如：	
select id from t where num in(1,2,3)	
对于连续的数值，能用 between 就不要用 in 了：	
select id from t where num between 1 and 3	

6. 下面的查询也将导致全表扫描：	
select id from t where name like '%abc%'	

7. 应尽量避免在 where 子句中对字段进行表达式操作，这将导致引
擎放弃使用索引而进行全表扫描。如：	
select id from t where num/2=100	
应改为:	
select id from t where num=100*2	

8. 应尽量避免在where子句中对字段进行函数操作，这将导致引擎放弃
使用索引而进行全表扫描。如：	
select id from t where substring(name,1,3)='abc'
--name以abc开头的id	
应改为:	
select id from t where name like 'abc%'	

9. 不要在 where 子句中的“=”左边进行函数、算术运算或其他表达式
运算，否则系统将可能无法正确使用索引。	

10. 在使用索引字段作为条件时，如果该索引是复合索引，那么必须使用
到该索引中的第一个字段作为条件时才能保证系统使用该索引，	
否则该索引将不会被使用，并且应尽可能的让字段顺序与索引顺序相一致。

11. 不要写一些没有意义的查询，如需要生成一个空表结构：	
select col1,col2 into #t from t where 1=0	
这类代码不会返回任何结果集，但是会消耗系统资源的，应改成这样：	
create table #t(...)	

12. 很多时候用 exists 代替 in 是一个好的选择：	
select num from a where num in(select num from b)	
用下面的语句替换：	
select num from a where exists(select 1 from b
 where num=a.num)		

13. 并不是所有索引对查询都有效，SQL是根据表中数据来进行查询
优化的，当索引列有大量数据重复时，SQL查询可能不会去利用索引，	
如一表中有字段sex，male、female几乎各一半，那么即使在sex上
建了索引也对查询效率起不了作用。	

14. 索引并不是越多越好，索引固然可以提高相应的 select 的效率，
但同时也降低了 insert 及 update 的效率，	
因为 insert 或 update 时有可能会重建索引，所以怎样建索引需
要慎重考虑，视具体情况而定。	
一个表的索引数最好不要超过6个，若太多则应考虑一些不常使用到的
列上建的索引是否有必要。	
	
15. 尽量使用数字型字段，若只含数值信息的字段尽量不要设计为字符
型，这会降低查询和连接的性能，并会增加存储开销。	
这是因为引擎在处理查询和连接时会逐个比较字符串中每一个字符，而
对于数字型而言只需要比较一次就够了。	
	
16. 尽可能的使用 varchar 代替 char ，因为首先变长字段存储空间
小，可以节省存储空间，	
其次对于查询来说，在一个相对较小的字段内搜索效率显然要高些。	
	
17. 任何地方都不要使用 select * from t ，用具体的字段列表代替
“*”，不要返回用不到的任何字段。	
	
18. 避免频繁创建和删除临时表，以减少系统表资源的消耗。

19. 临时表并不是不可使用，适当地使用它们可以使某些例程更有效，
例如，当需要重复引用大型表或常用表中的某个数据集时。但是，对于
一次性事件，最好使用导出表。

20. 在新建临时表时，如果一次性插入数据量很大，那么可以使用 
select into 代替 create table，避免造成大量 log ，	
以提高速度；如果数据量不大，为了缓和系统表的资源，应先
create table，然后insert。

21. 如果使用到了临时表，在存储过程的最后务必将所有的临时表显式
删除，先 truncate table ，然后 drop table ，这样可以避免系
统表的较长时间锁定。

22. 尽量避免使用游标，因为游标的效率较差，如果游标操作的数据超过
1万行，那么就应该考虑改写。	
	
23. 使用基于游标的方法或临时表方法之前，应先寻找基于集的解决方案
来解决问题，基于集的方法通常更有效。

24. 与临时表一样，游标并不是不可使用。对小型数据集使用 
FAST_FORWARD 游标通常要优于其他逐行处理方法，尤其是在必须引用
几个表才能获得所需的数据时。

25. 尽量避免大事务操作，提高系统并发能力。

26. 尽量避免向客户端返回大数据量，若数据量过大，应该考虑相应需求
是否合理。




###索引

####索引类型
<https://www.cnblogs.com/lushilin/p/6093793.html>
(1).system

这是const联接类型的一个特例。表仅有一行满足条件.如下(t3表上的id是 primary key)

(2).const

表最多有一个匹配行，它将在查询开始时被读取。因为仅有一行，在这行的列值可被优化器剩余部分认为是常数。const表很快，因为它们只读取一次！


(3). eq_ref

对于每个来自于前面的表的行组合，从该表中读取一行。这可能是最好的联接类型，除了const类型。它用在一个索引的所有部分被联接使用并且索引是UNIQUE或PRIMARY KEY。

eq_ref可以用于使用= 操作符比较的带索引的列。比较值可以为常量或一个使用在该表前面所读取的表的列的表达式。


(4).ref

对于每个来自于前面的表的行组合，所有有匹配索引值的行将从这张表中读取。如果联接只使用键的最左边的前缀，或如果键不是UNIQUE或PRIMARY KEY（换句话说，如果联接不能基于关键字选择单个行的话），则使用ref。如果使用的键仅仅匹配少量行，该联接类型是不错的。

ref可以用于使用=或<=>操作符的带索引的列。


(5).  ref_or_null

该联接类型如同ref，但是添加了MySQL可以专门搜索包含NULL值的行。在解决子查询中经常使用该联接类型的优化。


(6). index_merge

该联接类型表示使用了索引合并优化方法。在这种情况下，key列包含了使用的索引的清单，key_len包含了使用的索引的最长的关键元素。


(7). unique_subquery
子查询使用了unique或者primary key

8).index_subquery
子查询使用了普通索引
该联接类型类似于unique_subquery。可以替换IN子查询，但只适合下列形式的子查询中的非唯一索引：

(9).range

只检索给定范围的行，使用一个索引来选择行。key列显示使用了哪个索引。key_len包含所使用索引的最长关键元素。在该类型中ref列为NULL。

当使用=、<>、>、>=、<、<=、IS NULL、<=>、BETWEEN或者IN操作符，用常量比较关键字列时，可以使用range


(10).index

该联接类型与ALL相同，除了只有索引树被扫描。这通常比ALL快，因为索引文件通常比数据文件小。

当查询只使用作为单索引一部分的列时，MySQL可以使用该联接类型。


(11). ALL

对于每个来自于先前的表的行组合，进行完整的表扫描。如果表是第一个没标记const的表，这通常不好，并且通常在它情况下很差。通常可以增加更多的索引而不要使用ALL，使得行能基于前面的表中的常数值或列值被检索出。


####索引失效
<http://blog.csdn.net/wuseyukui/article/details/72312574>

1. 不要在索引列上执行任何操作计算、函数、自动/手动类型转换），不然会
导致索引失效而转向全表扫描
2. 组合索引，如（index1, index2, index3）在使用时带头索引不能死，
中间索引不能断，查询时，where index1 = ? / where index1 = ? and
index2 = ? / where index1 = ? and index2 = ? and index3 = ?
 会生效，但 where index2 = ? / where index3 = ? 不会生效，
 where index1 = ? and index3 = ? 只有index1 生效
3. 在索引列上进行 != / <> （不等）判断时将转换为全表扫描
4. 在索引列上执行 is null / is not null / like '%...' (以%开
头）将转换为全表扫描，若 like '...%' 将转换为 range 扫描
5. 索引表是字符串但sql语句中不加单引号，索引将失效，转为全表扫描
6. 索引字段使用 or 操作，索引将失效，转为全表扫描
7. 若只查询索引的列，不要写select * 最好将查询的列写出来

