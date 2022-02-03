import java.util.Scanner;
import java.util.regex.*;

class RemoveExtraSpacesProblem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String text = scanner.nextLine();

        Pattern pattern = Pattern.compile("\\s{2,}");
        Matcher matcher = pattern.matcher(text);
        System.out.println(matcher.replaceAll(" "));
    }
}
