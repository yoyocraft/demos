package org.example.juc;

/**
 * @author yoyocraft
 * @date 2024/08/27
 */
public class PrintObjectDemo {

    public static void main(String[] args) {
        final int FLAG_A = 1, FLAG_B = 2, FLAG_C = 3;
        PrintObject printObject = new PrintObject(FLAG_A, 10);
        new Thread(() -> printObject.print("A", FLAG_A, FLAG_B)).start();
        new Thread(() -> printObject.print("B", FLAG_B, FLAG_C)).start();
        new Thread(() -> printObject.print("C", FLAG_C, FLAG_A)).start();
    }

    static class PrintObject {

        private int flag;
        private final int loopNum;
        private int idx;

        public PrintObject(int flag, int loopNum) {
            this.flag = flag;
            this.loopNum = loopNum;
            this.idx = 0;
        }

        public void print(String s, int currFlag, int nextFlag) {
            while (idx < loopNum) {

                synchronized (this) {
                    while (currFlag != flag) {
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    if (idx < loopNum) {
                        System.out.println(s);
                        idx++;
                    }
                    flag = nextFlag;
                    notifyAll();
                }

            }
        }
    }
}
