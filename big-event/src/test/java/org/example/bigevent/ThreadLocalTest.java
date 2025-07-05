package org.example.bigevent;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ThreadLocalTest {

    @Test
    public void test(){
        ThreadLocal<String> tl = new ThreadLocal<>();

        new Thread(() -> {
            tl.set("消炎");
            System.out.println(tl.get()+":"+Thread.currentThread().getName());
            System.out.println(tl.get()+":"+Thread.currentThread().getName());
            System.out.println(tl.get()+":"+Thread.currentThread().getName());
        },"线程1").start();

        new Thread(() -> {
            tl.set("药老");
            System.out.println(tl.get()+":"+Thread.currentThread().getName());
            System.out.println(tl.get()+":"+Thread.currentThread().getName());
            System.out.println(tl.get()+":"+Thread.currentThread().getName());
        },"线程2").start();
    }
}
