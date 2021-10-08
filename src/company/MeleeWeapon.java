package company;

public class MeleeWeapon extends Weapon{

    public MeleeWeapon(String itemName, String itemDescription, int damage, int ammo) {
        super(itemName,itemDescription,damage,ammo);
    }

    @Override
    public boolean isMeleeWeapon() {
        if (isMeleeWeapon == false) {
            setAmmo(12);
        } else if (isMeleeWeapon == true) {
            setAmmo(99999);
        }
        return true;
    }
}
