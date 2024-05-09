package org.chapter4;

import java.util.Optional;
import java.util.function.Consumer;

public class OptionalTest {

    public void excute(){
        checkCase(1,i -> test1(i));
        checkCase(2,i -> test2(i));
        checkCase(3,i -> test3(i));
    }

    private void checkCase(int number, Consumer<Integer> method){
        System.out.println("=== ケース"+number+" ===");
        System.out.println("[NULL]");
        method.accept(null);
        System.out.println("[100]");
        method.accept(100);
    }

    private void test1(Integer integer){
        Optional<Integer> test = Optional.ofNullable(integer);
        if(test.isEmpty()){
            System.out.println("値が入っていません！");
        } else {
            System.out.println(test.get().toString());
        }
    }

    private void test2(Integer integer){
        Optional<Integer> test = Optional.ofNullable(integer);
        test.ifPresentOrElse(
                i -> System.out.println(i.toString()),
                () -> System.out.println("値が入っていません！")
        );
    }

    private void test3(Integer integer){
        System.out.println(integer.toString());
    }
}
