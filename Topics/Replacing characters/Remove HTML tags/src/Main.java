import java.util.Scanner;
import java.util.regex.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String stringWithHtmlTags = scanner.nextLine();

        String regex = "<[^>]*>";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(stringWithHtmlTags);

        String output;
        output = matcher.replaceAll("");

        System.out.println(output);
    }
}