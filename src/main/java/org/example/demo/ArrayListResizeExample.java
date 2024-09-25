package org.example.demo;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;

public class ArrayListResizeExample {
    public static final int INIT_CAPACITY = 5;

    public static final int ADDED_ELEMENTS = 10;

    public static final int REMOVED_ELEMENTS = 3;

    public static final String ELEMENT_DATA = "elementData";

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>(INIT_CAPACITY);

        System.out.println("initial capacity: " + getListCapacity(list));

        for (int i = 0; i < ADDED_ELEMENTS; i++) {
            list.add(i);
        }

        System.out.println("after adding item, capacity:  " + getListCapacity(list));

        Iterator<Integer> iterator = list.iterator();
        for (int i = 0; i < REMOVED_ELEMENTS && iterator.hasNext(); i++) {
            iterator.next();
            iterator.remove();
        }

        System.out.println("after removing item, capacity: " + getListCapacity(list));

        list.trimToSize();

        System.out.println("after trimToSize, capacity: " + getListCapacity(list));
    }

    public static int getListCapacity(ArrayList<?> list) {
        try {
            Field field = ArrayList.class.getDeclaredField(ELEMENT_DATA);
            field.setAccessible(true);
            int capacity = ((Object[]) field.get(list)).length;
            field.setAccessible(false);
            return capacity;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
