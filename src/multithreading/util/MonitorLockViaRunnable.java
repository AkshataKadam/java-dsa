package multithreading.util;

public class MonitorLockViaRunnable implements Runnable{

    MonitorLockViaThread obj;

    public  MonitorLockViaRunnable(MonitorLockViaThread obj) {
        this.obj = obj;
    }
    @Override
    public void run() {
        try {
            obj.task1();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        obj.task2();
        obj.task3();
    }
}
