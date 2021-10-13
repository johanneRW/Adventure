package company;

public class Item {
    private String itemName;
    private String itemDescription;
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

    public String getItemName() {
        return itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public Item getCombination() {
        return combination;
    }

}






