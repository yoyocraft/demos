package org.example.algo.prac;

/**
 * @author yoyocraft
 * @date 2024/09/11
 */
public class MyCircularDeque {

    private final int capacity;
    private final int[] data;
    private int front, rear;


    public MyCircularDeque(int k) {
        capacity = k + 1;
        data = new int[capacity];
        front = rear = 0;
    }

    /**
     * 将一个元素添加到双端队列头部。 如果操作成功返回 true，否则返回 false。
     */
    public boolean insertFront(int value) {
        if (isFull()) {
            return false;
        }
        front = (front - 1 + capacity) % capacity;
        data[front] = value;
        return true;
    }

    /**
     * 将一个元素添加到双端队列尾部。如果操作成功返回 true，否则返回 false。
     */
    public boolean insertLast(int value) {
        if (isFull()) {
            return false;
        }
        data[rear] = value;
        rear = (rear + 1) % capacity;
        return true;
    }

    /**
     * 从双端队列头部删除一个元素。 如果操作成功返回 true ，否则返回 false。
     */
    public boolean deleteFront() {
        if (isEmpty()) {
            return false;
        }
        front = (front + 1) % capacity;
        return true;
    }

    /**
     * 从双端队列尾部删除一个元素。如果操作成功返回 true ，否则返回 false。
     */
    public boolean deleteLast() {
        if (isEmpty()) {
            return false;
        }
        rear = (rear - 1 + capacity) % capacity;
        return true;
    }

    /**
     * 从双端队列头部获得一个元素。如果双端队列为空，返回 -1。
     */
    public int getFront() {
        if (isEmpty()) {
            return -1;
        }
        return data[front];
    }

    /**
     * 获得双端队列的最后一个元素。 如果双端队列为空，返回 -1。
     */
    public int getRear() {
        if (isEmpty()) {
            return -1;
        }
        return data[(rear - 1 + capacity) % capacity];
    }

    /**
     * 若双端队列为空，则返回 true ，否则返回 false。
     */
    public boolean isEmpty() {
        return front == rear;
    }

    /**
     * 若双端队列满了，则返回 true，否则返回 false。
     */
    public boolean isFull() {
        return (rear + 1) % capacity == front;
    }
}
