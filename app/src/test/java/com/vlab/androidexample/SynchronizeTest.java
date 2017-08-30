package com.vlab.androidexample;

import org.junit.Test;

/**
 * Created by Vinh.Tran on 3/1/17.
 */

class PrintDemo {

    private int mValue;

    public PrintDemo(int value){
        mValue = value;
    }

    public void printCount() {
        try {
            for(int i = 5; i > 0; i--) {
                System.out.println("Counter   ---   "  + mValue  + " ; " + Thread.currentThread());
                mValue = mValue + 1;
            }
        }catch (Exception e) {
            System.out.println("Thread  interrupted.");
        }
    }
}

class ThreadDemo extends Thread {
    private Thread t;
    private String threadName;
    PrintDemo  PD;

    ThreadDemo(String name,  PrintDemo pd) {
        super(name);
        threadName = name;
        PD = pd;
    }

    public void run() {
        synchronized (PD){
            PD.printCount();
        }
        System.out.println("Thread " + threadName + " exiting.");
    }
}

class ThreadDemo1 implements Runnable {

    public void run() {

        Thread t = Thread.currentThread();
        System.out.print(t.getName());
        //checks if this thread is alive
        System.out.println(", status isAlive = " + t.isAlive());

        for (int i = 1; i < 10; i++) {
            System.out.println(">>> ThreadDemo1 -> run : " + i);
        }
    }
}

public class SynchronizeTest {

    @Test
    public void test() throws Exception {

        PrintDemo PD = new PrintDemo(0);

        ThreadDemo T1 = new ThreadDemo( "Thread - 1 ", PD );
        ThreadDemo T2 = new ThreadDemo( "Thread - 2 ", PD );

        T1.start();
        T2.start();
        try {
            T1.join();
            T2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(">>> Finish");


        /*Thread t = new Thread(new ThreadDemo1());
        // this will call run() function
        t.start();
        // waits for this thread to die
        t.join();
        //System.out.print(t.getName());
        //checks if this thread is alive
        System.out.println(t.getName() + ", status isAlive = " + t.isAlive());*/


    }
}
