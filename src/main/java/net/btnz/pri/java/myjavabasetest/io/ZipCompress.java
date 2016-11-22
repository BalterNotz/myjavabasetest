package net.btnz.pri.java.myjavabasetest.io;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipOutputStream;

/**
 * Created by zhangsongwei on 2016/11/22.
 */
public class ZipCompress {
    public static void main(String[] args) {
        try{
            FileOutputStream f = new FileOutputStream("test.zip");
            CheckedOutputStream csum = new CheckedOutputStream(f, new Adler32());
            ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(csum));
            out.setComment("A test of Java Zipping");
            //Can't read the above comment, though
            for(int i = 0; i < args.length; i++) {
                System.out.println();
            }
        } catch (Throwable t) {

        }
    }
}
