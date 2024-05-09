package org.chapter4;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class TerminalOperation {
    public void excute() {
        List<Integer> inputList = List.of(3,2,1,1,2,3,4);
        allMatch(newStream(inputList));
        anyMatch(newStream(inputList));
        collect(newStream(inputList));
        count(newStream(inputList));
        findAny(newStream(inputList));
        findFirst(newStream(inputList));
        forEach(newStream(inputList));
        max(newStream(inputList));
        min(newStream(inputList));
        noneMatch(newStream(inputList));
        reduce(newStream(inputList));
        toArray(newStream(inputList));
    }

    private Stream<Integer> newStream(List<Integer> list){
        return list.stream();
    }

    private void allMatch(Stream<Integer> stream){
        /* allMatch */
        System.out.println("=== allMatch ===");
        System.out.println("4以上の値が含まれない:"+stream.allMatch(i -> i < 4));
    }

    private void anyMatch(Stream<Integer> stream){
        /* anyMatch */
        System.out.println("=== anyMatch ===");
        System.out.println("4以上の値が含まれる:"+stream.anyMatch(i -> i >= 4));
    }

    private void collect(Stream<Integer> stream){
        /* collect */
        System.out.println("=== collect ===");
        System.out.println(stream.collect(Collectors.toList()));
    }

    private void count(Stream<Integer> stream){
        /* count */
        System.out.println("=== count ===");
        System.out.println(stream.count());
    }

    private void findAny(Stream<Integer> stream){
        /* findAny */
        System.out.println("=== findAny ===");
        Optional<Integer> list = stream.findAny();
        System.out.println(list.isPresent()? list.get() :"中身なし");
    }

    private void findFirst(Stream<Integer> stream){
        /* findFirst */
        System.out.println("=== findFirst ===");
        Optional<Integer> list = stream.findFirst();
        System.out.println(list.isPresent()? list.get() :"中身なし");
    }

    private void forEach(Stream<Integer> stream){
        /* forEach */
        System.out.println("=== forEach ===");
        stream.forEach(i -> System.out.println(i));
    }

    private void max(Stream<Integer> stream){
        /* max */
        System.out.println("=== max ===");
        Optional<Integer> list = stream.max(Comparator.naturalOrder());
        System.out.println("最大値:" + (list.isPresent()? list.get() :"中身なし"));
    }

    private void min(Stream<Integer> stream){
        /* min */
        System.out.println("=== min ===");
        Optional<Integer> list = stream.min(Comparator.naturalOrder());
        System.out.println("最小値:" + (list.isPresent()? list.get() :"中身なし"));
    }

    private void noneMatch(Stream<Integer> stream){
        /* noneMatch */
        System.out.println("=== noneMatch ===");
        System.out.println("4以上の値が含まれない:"+stream.noneMatch(i -> i >= 4));
    }

    private void reduce(Stream<Integer> stream){
        /* reduce */
        System.out.println("=== reduce ===");
        Optional<Integer> list = stream.reduce((i1,i2) -> i1+i2);
        System.out.println("合計値:" + (list.isPresent()? list.get() :"中身なし"));
    }

    private void toArray(Stream<Integer> stream){
        /* toArray */
        System.out.println("=== toArray ===");
        Integer[] intList = stream.toArray(Integer[]::new);
        for(Integer i:intList) {
            System.out.println(i);
        }
    }
}
