import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<Integer, String> map = new HashMap<>();

        int fromInt = scanner.nextInt();
        int toInt = scanner.nextInt();
        scanner.nextLine();

        int numberOfLines = scanner.nextInt();
        scanner.nextLine();

        // read map data
        for (int i = 0; i < numberOfLines; i++) {
            int key = scanner.nextInt();
            String word = scanner.next();
            map.put(key, word);
        }

        // print data within range
        for (int i = fromInt; i <= toInt; i++) {
            String word = map.get(i);
            if (word != null) {
                System.out.println(i + " " + word);
            }
        }
    }
}