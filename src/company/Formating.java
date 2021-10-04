package company;

public class Formating {
    public static String getStringsCapitalized(String string) {
        String firstLetter = string.substring(0, 1);
        String capitalLetter = firstLetter.toUpperCase();
        String restOfWord = string.substring(1);
        string = capitalLetter + restOfWord;

        return string;}
}
