package company;

public class Formatting {
    public String getStringsCapitalized(String string) {
        if (string.length()>0){
        String firstLetter = string.substring(0, 1);
        String capitalLetter = firstLetter.toUpperCase();
        String restOfWord = string.substring(1);
        string = capitalLetter + restOfWord;

        return string;}
        else return string;
    }

}
