import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Set<String> dictionary = new HashSet<>();
        Set<String> erroneousWords = new HashSet<>();
        List<String> lines = new ArrayList<>();

        int numberOfRecords = scanner.nextInt();
        scanner.nextLine();
        // create dictionary
        for (int i = 0; i < numberOfRecords; i++) {
            String word = scanner.next();
            if (word != null) {
                dictionary.add(word.toLowerCase());
            }
        }
        //System.out.println(dictionary.toString());

        int numberOfLines = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < numberOfLines; i++) {
            String line = scanner.nextLine();
            lines.add(line.toLowerCase());
        }
        //System.out.println(lines);



        for (String line : lines) {
            String[] samples = line.split("\\s");
            //System.out.println(Arrays.toString(samples));
            for (int i = 0; i < samples.length; i++) {
                if (!dictionary.contains(samples[i])) {
                    erroneousWords.add(samples[i]);
                }
            }
        }

        for (String word : erroneousWords) {
            System.out.println(word);
        }
    }
}