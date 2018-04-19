package net.btnz.pri.java.myjavabasetest.java.util;/**
 * Created by zhangsongwei on 2017/1/22.
 *
 * @author: Balter Notz [BalterNotz@foxmail.com]
 * @Date: 2017/1/22 16:45
 */

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * source: https://www.mkyong.com/java8/java-8-flatmap-example/
 * flatMap实现的功能有点像Haskell中的concat函数
 * ghci> :type concat
 * concat :: Foldable t => t [a] -> [a]
 *
 In Java 8, Stream can hold different data types, for examples:

 Stream<String[]>
 Stream<Set<String>>
 Stream<List<String>>
 Stream<List<Object>>
 But, the Stream operations (filter, sum, distinct…) and collectors do not support it, so, we need flatMap() to do the following conversion :

 Stream<String[]>		-> flatMap ->	Stream<String>
 Stream<Set<String>>	-> flatMap ->	Stream<String>
 Stream<List<String>>	-> flatMap ->	Stream<String>
 Stream<List<Object>>	-> flatMap ->	Stream<Object>
 How flatMap() works :

 { {1,2}, {3,4}, {5,6} } -> flatMap -> {1,2,3,4,5,6}

 { {'a','b'}, {'c','d'}, {'e','f'} } -> flatMap -> {'a','b','c','d','e','f'}
 */
public class FlatMapTest {
    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
        test5();
    }

    public static void test1() {
        String[][] data = new String[][]{{"a", "b"}, {"c", "d"}, {"e", "f"}};
        //Stream<String[]>
        Stream<String[]> temp = Arrays.stream(data);
        //filter a stream of string[], and return a string[]?
        Stream<String[]> stream = temp.filter(x -> "a".equals(x.toString()));
        stream.forEach(System.out::println);
        //这个将输出。。。empty
        //应该使用flatMap()将Stream<String[]>转换成Stream<String>
    }

    public static void test2() {
        String[][] data = new String[][]{{"a", "b"}, {"c", "d"}, {"e", "f"}};
        //Stream<String[]>
        Stream<String[]> temp = Arrays.stream(data);
        //Stream<String>, GOOD!
        Stream<String> stringStream = temp.flatMap(x -> Arrays.stream(x));
        Stream<String> stream = stringStream.filter(x -> "a".equals(x.toString()));
        stream.forEach(System.out::println);
        /**
         * Stream<String> stream = Arrays.stream(data).flatMap(x->Arrays.stream(x)).filter(x->"a".equals(x.toString)));
         */
        //output: a
    }

    //flatMap() and Set example
    public static void test3() {
        Student obj1 = new Student();
        obj1.setName("mkyong");
        obj1.addBook("Java 8 in Action");
        obj1.addBook("Spring Boot in Action");
        obj1.addBook("Effective Java (2nd Edition)");

        Student obj2 = new Student();
        obj2.setName("zilap");
        obj2.addBook("Learning Python, 5th Edition");
        obj2.addBook("Effective Java (2nd Edition)");

        List<Student> list = new ArrayList<>();
        list.add(obj1);
        list.add(obj2);

        list.stream()
                .map(x -> x.getBook()) //Stream<Set<String>>
                .flatMap(x -> x.stream()) //Stream<String>
                .distinct().forEach(System.out::println);
    }

    //Stream primitive flatMapToInt
    public static void test4() {
        int[] intArray = {1,2,3,4,5,6};
        //1.Stream<int[]>
        Stream<int[]> streamArray = Stream.of(intArray);

        //2. Stream<int[]> -> flatMap -> IntStream
        IntStream intStream = streamArray.flatMapToInt(x -> Arrays.stream(x));
        intStream.forEach(x -> System.out.println(x));
    }

    public static void test5() {

    }
}

class Student {
    private String name;
    private Set<String> book;

    public void addBook(String book) {
        if (null == this.book) {
            this.book = new HashSet<>();
        }
        this.book.add(book);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getBook() {
        return book;
    }

    public void setBook(Set<String> book) {
        this.book = book;
    }
}
