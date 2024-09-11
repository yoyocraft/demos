package org.example.algo.prac;

/**
 * @author yoyocraft
 * @date 2024/09/11
 */
public class MyCircularQueue {

    private int front;
    private int rear;
    private final int capacity;
    private final int[] data;

    /**
     * @param k queue size
     */
    public MyCircularQueue(int k) {
        capacity = k + 1;
        data = new int[capacity];
        front = rear = 0;
    }

    /**
     * 向循环队列中插入一个元素，如果成功插入返回 true
     */
    public boolean enQueue(int value) {
        if (isFull()) {
            return false;
        }

        data[rear] = value;
        rear = (rear + 1) % capacity;
        return true;
    }

    /**
     * 从循环队列中删除一个元素。 如果成功删除返回 true
     */
    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }
        front = (front + 1) % capacity;
        return true;
    }

    /**
     * 从队首获取元素。如果队列为空，返回 -1
     */
    public int Front() {
        if (isEmpty()) {
            return -1;
        }
        return data[front];
    }

    /**
     * 获取队尾元素。如果队列为空，返回 -1
     */
    public int Rear() {
        if (isEmpty()) {
            return -1;
        }
        return data[(rear - 1 + capacity) % capacity];
    }

    /**
     * 判断队列是否为空
     */
    public boolean isEmpty() {
        return rear == front;
    }

    /**
     * 判断队列是否已满
     */
    public boolean isFull() {
        return (rear + 1) % capacity == front;
    }
}
