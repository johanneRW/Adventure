package company;

public class Item {
    private Formating formating = new Formating();
    private String itemName;
    public String itemDescription;
    private Item combination;

    public Item(String itemName, String itemDescription, Item combination) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.combination=combination;
    }

    public Item(String itemName, String itemDescription) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
    }

    public Item(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        formating.getStringsCapitalized(itemName);
        return itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public String getItemNameAndDesription(){
        String itemNameCapitalized=formating.getStringsCapitalized(itemName);
        return "\n"+itemNameCapitalized+", "+itemDescription+".";
    }

    public Item getCombination(){
        return combination;
    }

}





