package multithreading;

import multithreading.util.MultiThreadingLearning;

public class MultiThreadingConcepts {

    static void threadBasics() throws InterruptedException {
        MultiThreadingLearning obj = new MultiThreadingLearning();

//        obj.task1();
//        obj.task2();
//        obj.task3();

        System.out.println("MultiThreading");

        Thread t1 = new Thread(() -> {
            try {
                obj.task1();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread t2 = new Thread(() -> obj.task2());
        Thread t3 = new Thread(() -> obj.task3());

        t1.start();
        t2.start();
        t3.start();

    }
}
