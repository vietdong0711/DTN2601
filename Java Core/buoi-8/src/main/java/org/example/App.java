package org.example;

import org.example.entity.Account;
import org.example.entity.Department;
import org.example.frontend.DepartmentFunction;
import org.example.frontend.Menu;

import java.util.*;

/**
 * Hello world!
 *
 */
public class App {
    // Visibility example flag
    private static volatile boolean running = true;
    // Non-atomic example counter
    private static volatile int counter = 0;

    public static void main(String[] args) throws InterruptedException {
//        visibilityExample();
//        nonAtomicExample();
        byte b = 10;
        b = b + 10; // Statement 1
        b += 10; // Statement 2
    }
    // số row thanh cong
    // ro row that bai
    // 1 dọc file csv 10k dòng
    // số row thanh cong    departments.size()
    // ro row that bai      importErrors.size()

    // đếm xem có bao nhieu row dc chạy      count = 1k  phải dc 10k
    // 10  luồng mỗi luồng 1k dòng  chạy với nhau cùng 1 lúc

        private synchronized void runImport() {

        }

    // countSuccess
    // countError
    // sua khi xu ly du lieu xong 1 row   countSuccess++  hoac countError++


    // Shows that changing a volatile flag in one thread is seen by another thread
    static void visibilityExample() throws InterruptedException {
        running = true;
        Thread worker = new Thread(() -> {
            System.out.println("Worker started, waiting for stop signal...");
            while (running) {
                // busy-wait; volatile read ensures we see updates to 'running'
            }
            System.out.println("Worker stopped.");
        });
        worker.start();

        Thread.sleep(100); // let worker run
        System.out.println("Main: requesting stop");
        running = false; // volatile write visible to worker
        worker.join();
    }

    // Shows that volatile does NOT make increment atomic
    static void nonAtomicExample() throws InterruptedException {
        counter = 0;
        int threads = 20;
        int incrementsPerThread = 1000;
        Thread[] ts = new Thread[threads];

        for (int i = 0; i < threads; i++) {
            ts[i] = new Thread(() -> {
                for (int j = 0; j < incrementsPerThread; j++) {
                    counter++; // read-modify-write is not atomic even though 'counter' is volatile
                }
            });
        }

        for (Thread t : ts) t.start();
        for (Thread t : ts) t.join();

        System.out.println("Expected: " + (threads * incrementsPerThread) + ", actual: " + counter);
        // Actual will usually be less than expected due to lost updates
    }
//    public static void main(String[] args) {
////        Menu menu = new Menu();
////        menu.run();
//        System.out.println(1/0);
//
//
//
//
//
//    }
}
