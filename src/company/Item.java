package company;

public class Item {
    //private Formatting formatting = new Formatting();
    private String itemName;
    public String itemDescription;
    private Item combination;

    public Item(String itemName, String itemDescription, Item combination) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.combination = combination;
    }

    public Item(String itemName, String itemDescription) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
    }

    public Item(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public String getItemNameAndDescription() {
        return "\n" + itemName + ", " + itemDescription;
    }

    public Item getCombination() {
        return combination;
    }

}






