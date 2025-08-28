package multithreading;

public class MultiThreadingMain {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Main Thread: " + Thread.currentThread().getName());

        MultiThreadingConcepts.threadBasics();
    }
}
