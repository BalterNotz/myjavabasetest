package net.btnz.pri.java.myjavabasetest.transparams;

/**
 * Created by zhangsongwei on 2016/11/30.
 */

import java.nio.DoubleBuffer;

/**
 * Cloning a composed object
 */
class DepthReading implements Cloneable {
    protected double depth;
    public DepthReading(double depth){
        this.depth = depth;
    }
    public Object clone() {
        Object o = null;
        try{
            o = super.clone();
        } catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
        return o;
    }
    public String toString() {
        return "{" +
                "depth:" + depth +
                "}";
    }
}
class TemperatureReading implements Cloneable {
    protected long time;
    protected double temperature;
    public TemperatureReading(double temperature) {
        time = System.currentTimeMillis();
        this.temperature = temperature;
    }
    public Object clone() {
        Object o = null;
        try{
            o = super.clone();
        }catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return o;
    }
    public String toString() {
        return "{" +
                "time:" + time +
                ",temperature:" + temperature +
                "}";
    }
}
class OceanReading implements Cloneable {
    protected DepthReading depthReading;
    protected TemperatureReading temperatureReading;
    public OceanReading(double depth, double temper){
        depthReading = new DepthReading(depth);
        temperatureReading = new TemperatureReading(temper);
    }
    public Object clone(){
        OceanReading o = null;
        try{
            o = (OceanReading) super.clone();
        } catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
        o.depthReading = (DepthReading) o.depthReading.clone();
        o.temperatureReading = (TemperatureReading) o.temperatureReading.clone();
        return o;
    }
    public String toString() {
        return "{" +
                "depthReading:" + depthReading.toString() +
                ",temperatureReading:" + temperatureReading.toString() +
                "}";
    }
}
public class DeepCopy {
    public static void main(String[] args){
        OceanReading reading = new OceanReading(33.9, 100.5);
        //Now clone it:
        OceanReading r = (OceanReading) reading.clone();
        System.out.println(reading);
        reading.depthReading.depth += 1000;
        reading.temperatureReading.temperature += 1000;
        System.out.println(reading);
        System.out.println(r);
    }
}
