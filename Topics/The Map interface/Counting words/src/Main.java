import java.util.*;

class MapUtils {

    public static SortedMap<String, Integer> wordCount(String[] strings) {
        SortedMap<String, Integer> map = new TreeMap<>();
        for (int i = 0; i < strings.length; i++) {
            String nextWord = strings[i];
            if (map.get(nextWord) == null) {
                map.put(nextWord, 1);
            } else {
                map.put(nextWord, map.get(nextWord) + 1);
            }
        }
        return map;
    }

    public static void printMap(Map<String, Integer> map) {
        for (String key : map.keySet()) {
            System.out.println(key + " : " + map.get(key));
        }
    }

}

/* Do not change code below */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] words = scanner.nextLine().split(" ");
        MapUtils.printMap(MapUtils.wordCount(words));
    }
}