# 从接口定义找到实现类

使用反射

1. Object ——> getClass();
1. 任何数据类型（包括基本数据类型）都有一个“静态”的class属性
1. 通过Class类的静态方法：forName（String  className）(常用)