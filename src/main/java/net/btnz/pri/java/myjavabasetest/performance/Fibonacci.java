package net.btnz.pri.java.myjavabasetest.performance;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.math.BigInteger;

public class Fibonacci {

    public static BigInteger fibLoop(Integer n) {
        if (n == 2 | n == 1) {
            return BigInteger.valueOf(1);
        } else {
            BigInteger first = BigInteger.valueOf(1);
            BigInteger second = BigInteger.valueOf(1);
            BigInteger result = BigInteger.valueOf(0);
            while(n >= 3){
                result = new BigInteger(first.toByteArray()).add(second);
                first = new BigInteger(second.toByteArray());
                second = new BigInteger(result.toByteArray());
                n--;
            }
            return result;
        }
    }

    public static BigInteger fibLoop2(Integer n) throws Exception {
        if (n == 2 | n == 1) {
            return BigInteger.valueOf(1);
        } else {
            BigInteger first = BigInteger.valueOf(1);
            BigInteger second = BigInteger.valueOf(1);
            Kryo kryo = new Kryo();

//            File file = new File("fibsbuffer.tmp");
//            BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file));
//            BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file));

            byte[] buffer = new byte[1000000];
            Output output = new Output(buffer);
            Input input = new Input(buffer);

            while(n >= 3){
                kryo.writeObject(output, second);
                second = second.add(first);
                first = kryo.readObject(input, BigInteger.class);
                n--;
            }
            output.close();
            input.close();

            return second;
        }
    }

    public static void main(String...args) throws Exception {
        long begin = System.currentTimeMillis();
//        System.out.println(fibLoop(1000000));
        System.out.println(fibLoop2(1000000000));
        long end = System.currentTimeMillis();
        System.out.print("运行时间为（秒）：" + (end - begin)/1000);
    }
}
