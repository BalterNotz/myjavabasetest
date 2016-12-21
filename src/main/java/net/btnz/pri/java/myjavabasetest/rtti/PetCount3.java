package net.btnz.pri.java.myjavabasetest.rtti;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

/**
 * Created by zhangsongwei on 2016/11/29.
 */

class Pet {
}

class Dog extends Pet {
}

class Pug extends Dog {
}

class Cat extends Pet {
}

class Rodent extends Pet {
}

class Gerbil extends Rodent {
}

class Hamster extends Rodent {
}

class Counter {
    int i;
}

public class PetCount3 {
    public static void main(String[] args) {
        Vector pets = new Vector();
        Class[] petTypes = {Pet.class, Dog.class, Pug.class, Cat.class, Rodent.class, Gerbil.class, Hamster.class};
        try {
            for (int i = 0; i < 15; i++) {
                // Offset by one to eliminate Pet.class:
                int rnd = 1 + (int) (Math.random() * (petTypes.length - 1));
                pets.addElement(petTypes[rnd].newInstance());
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        Hashtable h = new Hashtable();
        for (int i = 0; i < petTypes.length; i++) {
            h.put(petTypes[i].toString(), new Counter());
        }
        for (int i = 0; i < pets.size(); i++) {
            Object o = pets.elementAt(i);
            //Using isInstance to eliminate individual
            //instanceof expressions:
            for (int j = 0; j < petTypes.length; ++j) {
                if (petTypes[j].isInstance(o)) {
                    String key = petTypes[j].toString();
                    ((Counter) h.get(key)).i++;
                }
            }
        }
        for (int i = 0; i < pets.size(); i++) {
            System.out.println(pets.elementAt(i).getClass().toString());
        }
        Enumeration keys = h.keys();
        while (keys.hasMoreElements()) {
            String nm = (String) keys.nextElement();
            Counter cnt = (Counter) h.get(nm);
            System.out.println(nm.substring(nm.lastIndexOf('.') + 1) + " quantity: " + cnt.i);
        }
    }
}