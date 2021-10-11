package company;

public abstract class Weapon extends Item {
    private int damage;
    protected boolean isMeleeWeapon;

    public Weapon(String itemName, String itemDescription, int damage) {
        super(itemName, itemDescription);
        this.damage = damage;
        this.isMeleeWeapon = false;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }


    @Override
    public String toString() {
        return getItemName();

    }
}
