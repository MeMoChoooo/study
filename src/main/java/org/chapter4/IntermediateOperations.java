package org.chapter4;

import java.util.List;
import java.util.stream.Stream;

public class IntermediateOperations {
    public void excute() {
        List<Integer> inputList = List.of(3,2,1,1,2,3,4);
        distinct(inputList);
        filter(inputList);
        limit(inputList);
        map(inputList);
        peek(inputList);
        skip(inputList);
        sorted(inputList);
    }

    private void distinct(List<Integer> list){
        /* distinct */
        System.out.println("=== distinct ===");
        Stream<Integer> distinctStream = list.stream().distinct();
        distinctStream.forEach(i -> System.out.println(i));
    }

    private void filter(List<Integer> list){
        /* filter */
        System.out.println("=== filter ===");
        Stream<Integer> filterStream = list.stream().filter(i -> i < 3);
        filterStream.forEach(i -> System.out.println(i));
    }

    private void limit(List<Integer> list){
        /* limit */
        System.out.println("=== limit ===");
        Stream<Integer> limitStream = list.stream().limit(3);
        limitStream.forEach(i -> System.out.println(i));
    }

    private void map(List<Integer> list){
        /* map */
        System.out.println("=== map ===");
        Stream<Integer> mapStream = list.stream().map(i -> i+1);
        mapStream.forEach(i -> System.out.println(i));
    }

    private void peek(List<Integer> list){
        /* peek */
        System.out.println("=== peek ===");
        Stream<Integer> peekStream = list.stream().peek(i -> System.out.println(i+"peek"));
        peekStream.forEach(i -> System.out.println(i));
    }

    private void skip(List<Integer> list){
        /* skip */
        System.out.println("=== skip ===");
        Stream<Integer> skipStream = list.stream().skip(3);
        skipStream.forEach(i -> System.out.println(i));
    }

    private void sorted(List<Integer> list){
        /* sorted */
        System.out.println("=== sorted ===");
        Stream<Integer> sortedStream = list.stream().sorted();
        sortedStream.forEach(i -> System.out.println(i));
    }
}