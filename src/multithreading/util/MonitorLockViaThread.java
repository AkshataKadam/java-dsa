package multithreading.util;

public class MonitorLockViaThread {

    public synchronized void task1() throws InterruptedException {
        System.out.println("Task 1 started");
        Thread.sleep(10000);
        System.out.println("Task 1 completed");
    }

    public synchronized void task2() {
        System.out.println("Task 2 started");
        synchronized (this) {
            System.out.println("Task 2 completed");
        }
    }

    public void task3() {
        System.out.println("Task 3 started");
        System.out.println("Task 3 completed");
    }
}
