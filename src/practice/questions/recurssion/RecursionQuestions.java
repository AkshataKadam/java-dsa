package practice.questions.recurssion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecursionQuestions {

    public static void printAllSubStrings(int[] arr) {  //{3, 1, 2}
        System.out.println("printAllSubStrings using recursion");
        List<Integer> res = new ArrayList<>();
        print(0, arr, res);
    }

    private static void print(int i, int[] arr, List<Integer> res) {
        if(i == arr.length) {
            for(int r: res) System.out.print(r + " ");
            if(res.isEmpty()) System.out.print("{}");
            System.out.println();
            return;
        }
        // take a particular index
        print(i+1, arr, res);
        res.add(arr[i]);
        // do not take a particular index
        print(i+1, arr, res);
//        res.removeLast();
    }

    public static void powerSet(int[] arr) {
        System.out.println("printAllSubStrings using PowerSet");
        int n = arr.length;

        List<List<Integer>> res = new ArrayList<>();
        for(int i=0; i < (1<<n); i++) {
            List<Integer> list = new ArrayList<>();
            for(int j=0; j < n; j++) {
                if((i & (1<<j)) != 0) {
                    System.out.println("---->>> " + arr[j]);
                    list.add(arr[j]);
                }

            }
            System.out.println(list);
            res.add(list);
        }
        res.forEach(System.out::println);
    }

    public static void printAllSubStrings(int[] arr, int k) {  //{1, 2, 1}
        System.out.println("printAllSubStrings using recursion");
        List<Integer> res = new ArrayList<>();
        print(0, arr, res, 0, k);
    }

    private static void print(int i, int[] arr, List<Integer> res, int sum, int k) {
        if(i == arr.length) {
            if(sum == k) {
                for(int r: res) System.out.print(r + " ");
                System.out.println();
            }
            return;
        }
        // take a particular index
        res.add(arr[i]);
        sum+=arr[i];
        print(i+1, arr, res, sum, k);

        // do not take a particular index
//        res.removeLast();
        sum-=arr[i];
        print(i+1, arr, res, sum, k);
    }

    public static int numberOfSubstrings(String s) {
        int i = 0, count = 0;
        StringBuilder sb = new StringBuilder();
        int res = printCount(s, sb.append(s.charAt(i)), i, count);
        System.out.println(res);
        return res;
    }

    private static int printCount(String s, StringBuilder sb, int i, int count) {
        System.out.println("i " + i + " sb " + sb.length());

        if(i == s.length()) {
            return 1;
        }

       int l = printCount(s, sb.append(s.charAt(i+1)), i+1, count++);
       int r = printCount(s, sb.append(s.charAt(i+1)), i+1, count);

        return l+r;
    }

    public static long getMaxArrayCorrelation(List<Integer> a, List<Integer> b) {
        List<Integer> sortedA = new ArrayList<>(a);
        List<Integer> sortedB = new ArrayList<>(b);

        Collections.sort(sortedA);
        Collections.sort(sortedB);

        long correlation = 0;
        int j = 0; // pointer for sortedB

        for (int i = 0; i < sortedA.size(); i++) {
            while (j < sortedB.size() && sortedB.get(j) <= sortedA.get(i)) {
                j++;
            }

            if (j < sortedB.size()) {
                correlation += sortedB.get(j);
                j++; // Move to next element in b
            }
        }

        return correlation;
    }


    public static List<Integer> countMinimumCharacters(String s, List<String> arr) {
        int n = s.length();
        int[][] pref = new int[n + 1][10];
        for (int i = 1; i <= n; i++) {
            int d = s.charAt(i - 1) - '0';
            System.arraycopy(pref[i - 1], 0, pref[i], 0, 10);
            pref[i][d]++;
        }
        List<Integer> ans = new ArrayList<>(arr.size());

        for (String t : arr) {
            int[] need = new int[10];
            for (int k = 0; k < t.length(); k++)
                need[t.charAt(k) - '0']++;

            int lo = 0, hi = n, best = -1;
            while (lo <= hi) {
                int mid = (lo + hi) >>> 1;          // candidate prefix length
                if (covers(pref[mid], need)) {
                    best = mid;
                    hi = mid - 1;                   // try shorter
                } else {
                    lo = mid + 1;                   // need longer
                }
            }
            ans.add(best);
        }
        return ans;
    }

    private static boolean covers(int[] prefixFreq, int[] need) {
        for (int d = 0; d < 10; d++)
            if (prefixFreq[d] < need[d]) return false;
        return true;
    }
}
