import java.util.Scanner;
import java.util.regex.*;


class CheckTheEssay {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String text = scanner.nextLine();
        int nrReplacements = 5;
        final String[] regex = new String[] {"Franse", "Eifel tower", "19th", "20th", "21st"} ;
        final String[] replacement = new String[] {"France", "Eiffel Tower", "XIXth", "XXth", "XXIst"};
        
        
       for (int i = 0; i < nrReplacements; i++)  {
           Pattern pattern = Pattern.compile(regex[i]);
           Matcher matcher = pattern.matcher(text);
           text = matcher.replaceAll(replacement[i]);
       }
        

        System.out.println(text);

    }
}
