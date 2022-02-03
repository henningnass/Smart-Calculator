import java.util.Scanner;
import java.util.regex.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String codeWithComments = scanner.nextLine();

        //codeWithComments = "/* new line */String /* we need String, not int */s /* bad name, I think */= \"123\";";

        String regex1 = "(?s)/\\*(.)*?\\*/";
        String regex2 = "//.*";

        Pattern pattern = Pattern.compile(regex1);
        Matcher matcher = pattern.matcher(codeWithComments);

        String output;

        output = matcher.replaceAll("");
        pattern = Pattern.compile(regex2);
        matcher = pattern.matcher(output);
        output = matcher.replaceAll("");

        System.out.println(output);
    }
}