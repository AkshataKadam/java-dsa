package dsa.stream;


import dsa.stream.util.Utility;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamConcepts {
    static void streamTerminalOps() {
        int[] arr = Utility.fetchArray();
        List<Integer> intList = Utility.fetchList();
        List<List<Integer>> listOfList = Utility.fetchListOfList();

//        collectionReduction(intList);
//        aggregationOps(arr);
//        matchAndFindOps(intList);

//        intermediateOps(listOfList);

    }

    static void parallelStream() {
        List<List<Integer>> listOfList = Utility.fetchListOfList();
        List<Integer> intList = Utility.fetchList();
        List<Integer> longList = Utility.fetchLongList();
        List<Integer> bigList = IntStream.rangeClosed(1, 10_000_000)
                .boxed()
                .toList();
        System.out.println();

        long streamStartTime1 = System.currentTimeMillis();
        double streamListMap1 = bigList.stream().filter(val -> val % 2 == 0).mapToDouble(Math::sqrt).sum();
        System.out.println("Stream processing time: for list " + (System.currentTimeMillis() - streamStartTime1));

        long parallelStreamStartTime1 = System.currentTimeMillis();
        double streamListFlatMap1 = bigList.parallelStream().filter(val -> val % 2 == 0).mapToDouble(Math::sqrt).sum();
        System.out.println("Parallel Stream processing time for list: " + (System.currentTimeMillis() - parallelStreamStartTime1));

        long streamStartTime = System.currentTimeMillis();
        List<List<Integer>> streamListMap = listOfList.stream().map(val -> val.stream().map(val1 -> val1 * 2).toList()).peek(val -> System.out.print(val + " ")).toList();
        System.out.println("Stream processing time for list of list: " + (System.currentTimeMillis() - streamStartTime));

        long parallelStreamStartTime = System.currentTimeMillis();
        List<Integer> streamListFlatMap = listOfList.parallelStream().flatMap(val -> val.stream().map(val1 -> val1 * 2)).peek(val -> System.out.print(val + " ")).toList();
        System.out.println("Parallel Stream processing time list of list: " + (System.currentTimeMillis() - parallelStreamStartTime));

    }


    private static void intermediateOps(List<List<Integer>> listOfList) {
        System.out.println("Map of list of list");
        List<List<Integer>> streamListMap = listOfList.stream().map(val -> val.stream().map(val1 -> val1 * (-1)).toList()).peek(val -> System.out.print(val + " ")).toList();
        System.out.println();

        System.out.println("FlatMap of list of list");
        List<Integer> streamListFlatMap = listOfList.stream().flatMap(val -> val.stream().map(val1 -> val1 * (-1))).peek(val -> System.out.print(val + " ")).toList();
        System.out.println();


    }

    private static void collectionReduction(List<Integer> list) {
        System.out.println("collectionReduction start");
        Optional<Integer> sq = list.stream().reduce(Integer::sum);
        System.out.println(sq.get());

        Stream<Integer> streamList = list.stream().filter(val -> val>2);
        List<Integer> streamToList = streamList.toList();

        streamToList.forEach(val -> System.out.print(val + " "));

        System.out.println("collectionReduction end");
        System.out.println();
    }

    private static void aggregationOps(int[] arr) {
        System.out.println("aggregationOps start");
        System.out.println("sum: " + Arrays.stream(arr).sum());

        long listCount = Arrays.stream(arr).filter(val -> val>2).peek(val -> System.out.print(val + " ")).count();
        System.out.println("listCount: " + listCount);

        OptionalInt listMin = Arrays.stream(arr).filter(val -> val>2).peek(val -> System.out.print(val + " ")).min();
        System.out.println("listMin: " + listMin.getAsInt());

        OptionalInt listMax = Arrays.stream(arr).filter(val -> val>2).peek(val -> System.out.print(val + " ")).max();
        System.out.println("listMax: " + listMax.getAsInt());

        long listSum = Arrays.stream(arr).filter(val -> val>2).peek(val -> System.out.print(val + " ")).sum();
        System.out.println("listSum: " + listSum);

        OptionalDouble listAvg = Arrays.stream(arr).filter(val -> val>2).peek(val -> System.out.print(val + " ")).average();
        System.out.println("listAvg: " + listAvg.getAsDouble());

        IntSummaryStatistics listStat = Arrays.stream(arr).filter(val -> val>2).peek(val -> System.out.print(val + " ")).summaryStatistics();
        System.out.println("listStat: " + listStat);
        System.out.println("aggregationOps end");
        System.out.println();
    }

    private static void matchAndFindOps(List<Integer> list) {
        System.out.println("matchAndFindOps start");   // 2,4,8,1,5,9,3,1

        boolean anyMatch = list.stream().peek(val -> System.out.print(val + " ")).anyMatch(val-> val > 7);
        System.out.println("anyMatch: " + anyMatch);

        boolean anyMatchAll = list.stream().peek(val -> System.out.print(val + " ")).allMatch(val-> val > 0);
        System.out.println("allMatch: " + anyMatchAll);

        boolean noneMatch = list.stream().peek(val -> System.out.print(val + " ")).noneMatch(val-> val < 0);
        System.out.println("noneMatch: " + noneMatch);

        Optional<Integer> findFirst = list.stream().findFirst();
        System.out.println("findFirst: " + findFirst.get());

        Optional<Integer> findAny = list.stream().findAny();
        System.out.println("findAny: " + findAny.get());

        System.out.println("matchAndFindOps end");
        System.out.println();
    }
}
