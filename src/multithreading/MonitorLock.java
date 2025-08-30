package multithreading;

import multithreading.util.MonitorLockViaRunnable;
import multithreading.util.MonitorLockViaThread;

public class MonitorLock {
    static void monitorLockViaThread() throws InterruptedException {
        MonitorLockViaThread obj = new MonitorLockViaThread();

//        obj.task1();
//        obj.task2();
//        obj.task3();

        System.out.println("MultiThreading Via Thread");

        Thread t1 = new Thread(() -> {
            try {
                obj.task1();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread t2 = new Thread(obj::task2);
        Thread t3 = new Thread(obj::task3);

        t1.start();
        t2.start();
        t3.start();
    }

    static void monitorLockViaRunnable() {
        MonitorLockViaThread obj = new MonitorLockViaThread();
        MonitorLockViaRunnable monitorLockViaRunnable = new MonitorLockViaRunnable(obj);

        System.out.println("MultiThreading Via Runnable");

        Thread tt1 = new Thread(monitorLockViaRunnable);
        Thread tt2 = new Thread(monitorLockViaRunnable);
        Thread tt3 = new Thread(monitorLockViaRunnable);

        tt1.start();
        tt2.start();
        tt3.start();
    }

}
