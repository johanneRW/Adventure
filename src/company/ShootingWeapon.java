package company;

public class ShootingWeapon extends Weapon{
    private int ammo;

    public ShootingWeapon(String itemName, String itemDescription, int damage, int ammo){
        super(itemName,itemDescription,damage);
        this.ammo = ammo;
    }


    public int getAmmo() {
        return ammo;

    }


    public void useAmmo(){
        ammo--;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }
}
