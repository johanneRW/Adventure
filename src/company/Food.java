package company;

public class Food extends Item {

    private int healthPoints;


    public Food(String foodName, String itemDescription, int healthPoints) {
        super(foodName, itemDescription);
        this.healthPoints = healthPoints;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints() {
        this.healthPoints = healthPoints;
    }

}
