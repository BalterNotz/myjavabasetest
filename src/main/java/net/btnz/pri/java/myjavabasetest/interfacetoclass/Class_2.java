package net.btnz.pri.java.myjavabasetest.interfacetoclass;

public class Class_2 implements InterfaceDemo {
    Number number = new Number() {
        @Override
        public int intValue() {
            return 0;
        }

        @Override
        public long longValue() {
            return 0;
        }

        @Override
        public float floatValue() {
            return 0;
        }

        @Override
        public double doubleValue() {
            return 0;
        }
    };

    @Override
    public String say() {
        return "I'm Class_2";
    }
}
