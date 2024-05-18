package chapter4;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Pipeline {
    public void excute() {
        List<Integer> inputList = List.of(3,2,1,1,2,3,4);
        test1(newStream(inputList));
        test2(newStream(inputList));
        test3(newStream(inputList));
        test4(newStream(inputList));
        test5(newStream(inputList), newStream(inputList));
    }

    private Stream<Integer> newStream(List<Integer> list){
        return list.stream();
    }
    private void test1(Stream<Integer> stream){
        System.out.println("=== case1 ===");
        stream.filter(i -> i < 3) // 中間操作
                .map(i -> i*10) // 中間操作
                .forEach(i -> System.out.println(i)); // 終端操作
    }

    private void test2(Stream<Integer> stream){
        System.out.println("=== case2 ===");
        Set<Integer> set = stream
                .map(i -> i*10) // 中間操作
                .collect(Collectors.toSet()); // 終端操作

        for(Integer i:set){
            System.out.println(i);
        }
    }

    private void test3(Stream<Integer> stream){
        System.out.println("=== case3 ===");
        String[] srtLList = stream
                .distinct() // 中間操作
                .sorted() // 中間操作
                .map(i -> i+"個") // 中間操作
                .toArray(String[]::new); // 終端操作

        for(String str:srtLList){
            System.out.println(str);
        }
    }

    private void test4(Stream<Integer> stream){
        System.out.println("=== case4 ===");
        final BinaryOperator<Integer> operation =
                (num1, num2) -> {
                    return num1 + num2;
                };
        Optional<Integer> sum = stream
                .reduce(operation); // 終端操作
        System.out.println("合計値:" + (sum.isPresent()? sum.get() :"中身なし"));
    }

    private void test5(Stream<Integer> stream1, Stream<Integer> stream2){
        System.out.println("=== case5 ===");
        Optional<Integer> result1 = stream1
                .parallel()
                .filter(i -> i>=1)
                .findFirst();

        Optional<Integer> result2 = stream2
                .parallel()
                .filter(i -> i>=1)
                .findAny();

        System.out.println("findFirst:"+(result1.isPresent()? result1.get() :"中身なし"));
        System.out.println("findAny  :"+(result2.isPresent()? result2.get() :"中身なし"));
    }
}
