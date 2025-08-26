package practice.questions;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InterviewQuestions {

    public void dictionary(String dictionary, String message) {
        String[] dictWords = dictionary.split(" ") ;
        String[] msgWords = message.split(" ") ;

        Set<String> dictSet = new HashSet<>(Arrays.asList(
                Arrays.stream(dictWords).map(String::toLowerCase).toArray(String[]::new)));

        for(int i=0; i< msgWords.length; i++) {
            if(!dictSet.contains(msgWords[i].toLowerCase())) {
                msgWords[i] = "###";
            }
        }
        System.out.println(String.join(" ", msgWords));
    }

    public boolean ocrMatch(String S, String T) {
        List<Map<String, Object>> tokenizedS = parseOCRPattern(S);
        List<Map<String, Object>> tokenizedT = parseOCRPattern(T);

        Stack<Map<String, Object>> stackS = new Stack<>();
        Stack<Map<String, Object>> stackT = new Stack<>();

        for(int i=tokenizedS.size() - 1; i >= 0; i--) { stackS.push(tokenizedS.get(i)); }

        for(int i=tokenizedT.size()-1; i >= 0; i--) { stackT.push(tokenizedT.get(i)); }

        while(!stackS.isEmpty() && !stackT.isEmpty()) {
            Map<String, Object> currentTokenS = stackS.pop();
            Map<String, Object> currentTokenT = stackT.pop();

            char typeS = (char) currentTokenS.get("type");
            char typeT = (char) currentTokenT.get("type");
            int lengthS = (int) currentTokenS.get("length");
            int lengthT = (int) currentTokenT.get("length");

            if(typeS == 'L' && typeT == 'L') {
                if(!currentTokenS.get("value").equals(currentTokenT.get("value"))) {
                    return false;
                }
            } else if(typeS == 'L' && typeT == 'N') {
                currentTokenT.put("length", --lengthT);
                if(lengthT > 0) stackT.push(currentTokenT);
            } else if(typeS == 'N' && typeT == 'L') {
                currentTokenS.put("length", --lengthS);
                if(lengthS > 0) stackS.push(currentTokenS);
            } else if(typeS == 'N' && typeT == 'N') {
                int min = Math.min(lengthT, lengthS);
                lengthT-=min;
                lengthS-=min;
                currentTokenT.put("length", lengthT);
                if(lengthT > 0) stackT.push(currentTokenT);
                currentTokenS.put("length", lengthS);
                if(lengthS > 0) stackS.push(currentTokenS);
            } else {
                return false;
            }
        }
        return stackT.isEmpty() && stackS.isEmpty();
    }

    private List<Map<String, Object>> parseOCRPattern(String str) {
        List<Map<String, Object>> tokens = new ArrayList<>();
        Pattern pattern = Pattern.compile("([a-zA-z])|(\\d+)");
        Matcher matcher = pattern.matcher(str);

        while(matcher.find()) {
            String letterMatch = matcher.group(1);
            String numberMatch = matcher.group(2);
            Map<String, Object> token = new HashMap<>();

            if(letterMatch != null) {
                token.put("type",'L');
                token.put("value", letterMatch.charAt(0));
                token.put("length", 1);
            } else if(numberMatch != null) {
                token.put("type", 'N');
                token.put("value",'?');
                token.put("length", Integer.parseInt(numberMatch));
            }
            tokens.add(token);
        }
        return tokens;
    }


    public int solution(String S, int[] X, int[] Y) {
        int N = S.length();
        long[][] pointData = new long[N][2];

        for (int i = 0; i < N; i++) {
            long x = X[i];
            long y = Y[i];
            pointData[i][0] = x * x + y * y;
            pointData[i][1] = S.charAt(i);
        }
        Arrays.sort(pointData, Comparator.comparingLong(a -> a[0]));
        Set<Character> usedTags = new HashSet<>();
        int count = 0;
        int i = 0;

        while (i < N) {
            long currentDist = pointData[i][0];
            Set<Character> seenAtThisDist = new HashSet<>();
            int j = i;

            while (j < N && pointData[j][0] == currentDist) {
                char tag = (char) pointData[j][1];
                if (usedTags.contains(tag) || seenAtThisDist.contains(tag)) {
                    return count;
                }
                seenAtThisDist.add(tag);
                j++;
            }

            usedTags.addAll(seenAtThisDist);
            count = usedTags.size();
            i = j;
        }
        return count;
    }

    public int solution1(String S, int[] X, int[] Y) {
        int N = S.length();
        long[][] pointData = new long[N][2];

        for (int i = 0; i < N; i++) {
            long x = X[i];
            long y = Y[i];
            pointData[i][0] = x * x + y * y;
            pointData[i][1] = S.charAt(i);
        }
        Arrays.sort(pointData, Comparator.comparingLong(a -> a[0]));
        Set<Character> usedTags = new HashSet<>();
        int count = 0;

        for(int i=0; i<N; i++) {
            char tag = (char) pointData[i][1];
            if (usedTags.contains(tag)) {
                return count;
            }
            usedTags.add(tag);
            count = usedTags.size();
        }
        return count;
    }

}
