package dsa.stream.util;

import java.util.ArrayList;
import java.util.List;

public class Utility {
    public static int[] fetchArray() { return new int[] {2,4,8,1,5,9,3,1}; }

    public static List<Integer> fetchList() { return List.of(2,4,8,1,5,9,3,1); }


    public static List<Integer> fetchLongList() { return List.of(2,4,8,1,5,9,3,1,2,4,8,1,5,9,3,1,2,4,8,1,5,9,3,1,2,4,8,1,5,9,3,1,2,4,8,1,5,9,3,1,2,4,8,1,5,9,3,1); }

    public static List<List<Integer>> fetchListOfList() {
        List<List<Integer>> finalList = new ArrayList<>();

        List<Integer> list1 = List.of(1,2,3);
        List<Integer> list2 = List.of(4,5,6,7);
        List<Integer> list3 = List.of(11,22,33,44,99,55,99);

        finalList.add(list1);
        finalList.add(list2);
        finalList.add(list3);

        return finalList;
    }
}
