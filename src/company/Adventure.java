package company;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Adventure {


    public boolean gameRunning = true;
    private Map spaceMap = new Map();
    private Player player = new Player();
    private Scanner input = new Scanner(System.in);


    public void playGame() {
        player.setHealth(10);
        player.setCurrentRoom(spaceMap.getStartRoom());
        System.out.println("Welcome to The Rediscovering of Pluto.\n\nIf you want to skip the introduction, type \"skip\" otherwise pres any button followed by enter");
        String reply = getPlayerReply();
        if (reply.equals("skip")) {
            System.out.println(startTekst());
        } else {
            System.out.println(introTekst());
            System.out.println(commandOptions());
            System.out.println(startTekst());
        }

        while (gameRunning) {
            String command;
            String itemName = null;

            command = input.nextLine();
            command = command.toLowerCase();

            int firstSpace = command.indexOf(" ");

            try {
                if (firstSpace > 0) {
                    String firstWordOfCommand = command.substring(0, firstSpace);
                    String secondWordOfCommand = command.substring(firstSpace + 1);
                    command = firstWordOfCommand;
                    itemName = secondWordOfCommand;

                    if (firstWordOfCommand.equals("go")) {
                        command = secondWordOfCommand;
                    }
                }
                if ((itemName != null) && (command.equals("take") || command.equals("t"))) {
                    String result = pickUpItem(itemName);
                    System.out.println(result);
                } else if ((itemName != null) && (command.equals("drop") || command.equals("d"))) {
                    String result = dropItem(player.inventory, itemName);
                    System.out.println(result);

                    //har fjernet "go" fra equals, da substring nu sortere ordet fra, og det derfor aldrig vil blive brugt.
                } else if (command.equals("n") || command.equals("north")) {
                    String result = requestDirection(player.getCurrentRoom().getConnectionNorth(), "north");
                    System.out.println(result);
                } else if (command.equals("w") || command.equals("west")) {
                    String result = requestDirection(player.getCurrentRoom().getConnectionWest(), "west");
                    System.out.println(result);
                } else if (command.equals("e") || command.equals("east")) {
                    String result = requestDirection(player.getCurrentRoom().getConnectionEast(), "east");
                    System.out.println(result);
                } else if (command.equals("s") || command.equals("south")) {
                    String result = requestDirection(player.getCurrentRoom().getConnectionSouth(), "south");
                    System.out.println(result);

                } else if (command.equals("look") || command.equals("l")) {
                    String result = requestLook();
                    System.out.println(result);

                } else if (command.equals("inventory") || command.equals("i") || command.equals("in") || command.equals("inv")) {
                    System.out.println(getInventoryWhitDescription());

                } else if ((itemName != null) && (command.equals("attack"))) {
                    requestAttack(itemName);

                } else if ((itemName != null) && (command.equals("equip"))) {
                    String result = equipPlayer(itemName);
                    System.out.println(result);

                } else if ((itemName != null) && (command.equals("eat"))||(command.equals("drink"))) {
                   String result=requestEat(itemName);
                    System.out.println(result);

                } else if (command.equals("health")) {
                    System.out.println("You have " + player.getHealth() + "health points right now.");

                } else if (command.equals("help") || command.equals("h")) {
                    String question = askPlayer("help");
                    System.out.println(question);
                    String answer = getPlayerReply();
                    String help = requestHelp(answer);
                    System.out.println(help);

                } else if (command.equals("exit") || command.equals("q")) {
                    String question = askPlayer("exit");
                    System.out.println(question);
                    String answer = getPlayerReply();
                    String exit = requestExit(answer);
                    System.out.println(exit);

                } else if (command.equals("combine")) {
                    System.out.println(getInventory() +
                            "\nType in the name of the first item, which you want to combine:");
                    String itemName1 = getPlayerReply();
                    Item item1 = findItemByName(player.inventory, itemName1);
                    System.out.println("Type in the name of the item you want to combine it with?");
                    String itemName2 = getPlayerReply();
                    Item item2 = findItemByName(player.inventory, itemName2);
                    String result = combineItems(item1, item2);
                    System.out.println(result);
                    checkIfFinal();
                    if (checkIfFinal()) {
                        System.out.println("\n\nYou have reached the end of the game. CONGRATULATIONS!!!\nThe End\nNow, go out and look at the sky.");
                    }
                    if (!checkIfFinal()) {
                        System.out.println("\n" + getInventory());
                    }
                    //metode til at fange kommandoer der er skrevet med et ord ved en fejl.
                }else if ((itemName != null)&& command.equals("call")&&(itemName.equals("cat"))) {
                    System.out.println("MANDU!!!");}

                else if (itemName==null){oneWordCommandInstedOfTo(command);
                } else {
                    //hvis spilleren taster en ugyldig kommando, beder spillet om en ny, dette er for at Adventure ikke crasher ved ugyldigt indput.
                    System.out.println("I don't know how to \"" + command + "\", try typing something else");
                }
            } catch (java.lang.ClassCastException e) {
                System.out.println("You can't do that, try again with something else.");
            }
        }

    }

    private void oneWordCommandInstedOfTo(String command) {
        if (command.equals("eat")||command.equals("drink")){
            askPlayer("eat");
            String foodToEat = getPlayerReply();
            System.out.println(requestEat(foodToEat));
        } else if (command.equals("equip")) {
            System.out.println("which weapon do you want to use?");
            String weaponName = getPlayerReply();
            String result = equipPlayer(weaponName);
            System.out.println(result);
        } else if (command.equals("take") || command.equals("t")) {
            String question = askPlayer("take");
            System.out.println(question);
            String itemName = getPlayerReply();
            String result = pickUpItem(itemName);
            System.out.println(result);
        } else if (command.equals("drop") || (command.equals("d"))) {
            String question = askPlayer("drop");
            System.out.println(question);
            String itemName = getPlayerReply();
            String result = dropItem(player.inventory, itemName);
            System.out.println(result);
        } else if (command.equals("attack")) {
            String enemyName = getPlayerReply();
            System.out.println("Who do you want to attack?");
            requestAttack(enemyName);
        } else if (command.equals("equip")) {
            System.out.println("which weapon do you want to use?");
            String weaponName = getPlayerReply();
            String result = equipPlayer(weaponName);
            System.out.println(result);

        }else System.out.println("I don't know how to do that, try something else");
    }


    private String requestDirection(Room requestedRoom, String directionName) {
        if (requestedRoom != null) {
            player.setCurrentRoom(requestedRoom);
            player.getCurrentRoom().upDateRoomCount();
            enteringRoom();
            return "Teleporting " + directionName + "...." + "\n\n" + enteringRoom();
        } else return "You can't go that way - there isn't a teleporter.";
    }

    private String askPlayer(String command) {
        if (command.equals("drop") || command.equals("take") || command.equals("eat")||command.equals("drink")){
            return "Ok, " + command + " what?";
        }
        if (command.equals("exit")) {
            return "Are you sure you want to leave the game? \"yes\" or \"no\"";
        }
        if (command.equals("help")) {
            return "Want help? Type \"yes\" or \"no\"." +
                    "\nOr if you want to review the command options type \"commands\"";
        } else return null;
    }

    private String getPlayerReply() {
        String reply = input.nextLine();
        reply = reply.toLowerCase();
        return reply;
    }

    private String requestLook() {
        return "Looking around...\n" +
                player.getCurrentRoom().getROOM_DESCRIPTION() + getRoomItemDescription();
    }

    private String requestHelp(String answer) {
        if (answer.equals("yes") || answer.equals("y")) {
            return "Okay, I'll scan the planet and see if I can help you.....\n" + player.getCurrentRoom().getROOM_HELP();
        }
        if (answer.equals("no") || answer.equals("n")) {
            return "Okay, suit yourself! :)";
        }
        if (answer.equals("commands") || (answer.equals("c"))) {
            return commandOptions();
        } else return null;
    }

    private String requestExit(String answer) {
        if (answer.equals("yes") || answer.equals("y")) {
            gameRunning = false;
            return "Sending you back to your home planet - Goodbye!!!";
        }

        if (answer.equals("no") || answer.equals("n")) {
            return "Alright! Let's keep going then :)";
        } else return null;
    }

    private String getItemListAsString(ArrayList<Item> list) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            Item currentItem = list.get(i);
            stringBuilder.append(currentItem.getItemName());
            stringBuilder.append(", ");
        }
        return stringBuilder.toString();
    }

    private Item findItemByName(ArrayList<Item> list, String itemName) {
        for (int i = 0; i < list.size(); i++) {
            Item currentItem = list.get(i);
            if (currentItem.getItemName().equals(itemName)) {
                return currentItem;
            }
        }
        return null;
    }

    private String getRoomItemsName() {
        ArrayList<Item> itemList = player.getCurrentRoom().items;

        if (itemList.size() > 0) {
            String items = getItemListAsString(itemList);
            return items;
        } else return "\nItems: There doesn't seem to be anything to take on this planet";
    }

    private String getInventory() {
        if (player.inventory.size() > 0) {
            String inventory = getItemListAsString(player.inventory);
            return "These items are currently in your inventory: " + getStringsCapitalized(inventory);
        } else return "Your inventory seems to be empty";
    }

    private String getInventoryWhitDescription() {
        if (player.inventory.size() > 0) {
            String inventory = ItemNameAndDescription(player.inventory);
            return "These items are currently in your inventory: \n" + inventory;
        } else return "Your inventory seems to be empty";

    }

    private String ItemNameAndDescription(ArrayList<Item> list) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            Item currentItem = list.get(i);
            stringBuilder.append(getStringsCapitalized(currentItem.getItemName()) + ", " + currentItem.getItemDescription());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    private String getRoomItemDescription() {
        ArrayList<Item> itemList = player.getCurrentRoom().items;
        if (itemList.size() > 0) {
            String inventory = ItemNameAndDescription(itemList);
            if(player.getEnemy()!=null){
               String enemy= player.getEnemy().toString();
               return  "\n\nOther lifeforms on this planet:\n"+getStringsCapitalized(enemy)+ "\n\nItems on this planet: \n" + inventory;
            }else return "\n\nItems on this planet: \n" + inventory;
        } else return "\n\nItems: There doesn't seem to be anything to take on this planet";
    }

    private void removeItem(ArrayList<Item> list, String itemName) {
        for (int i = 0; i < list.size(); i++) {
            Item currentItem = list.get(i);
            if (currentItem.getItemName().equals(itemName)) {
                list.remove(i);
            }
        }
    }

    private String dropItem(ArrayList<Item> list, String itemName) {
        Item foundItem = findItemByName(list, itemName);
        Room room = player.getCurrentRoom();

        if (foundItem == null) {
            return "Can't find a \"" + itemName + "\" in your inventory";
        } else {
            removeItem(list, itemName);
            room.items.add(foundItem);
            return getStringsCapitalized(itemName) + " is placed carefully on " + room.getROOM_NAME() + "\n" + getInventory();
        }
    }

    private String pickUpItem(String itemName) {
        //der må max være 5 Items i en spillers inventory ad gangen.
        ArrayList<Item> itemList = player.getCurrentRoom().items;

        if (player.inventory.size() <= 4) {
            Item foundItem = findItemByName(itemList, itemName);
            if (foundItem == null) {
                return "Can't find a \"" + itemName + "\" on this planet";
            } else {
                removeItem(itemList, itemName);
                player.inventory.add(foundItem);
                return getStringsCapitalized(itemName) + " is added to your inventory.\n" + getStringsCapitalized(getInventory());
            }
        } else
            return "You can't have more than 5 items in your inventory\nYou'll have to drop something\n" + getStringsCapitalized(getInventory());
    }

    private String combineItems(Item item1, Item item2) {
        String result = null;
        if ((item1 == null) || (item2 == null)) {
            result = " you haven't given me to item names, try combine again";
        } else if ((item1.getCombination() == null) || (item2.getCombination() == null)) {
            result = "You can't combine " + item1.getItemName() + " and " + item2.getItemName();
        } else if (item1.getCombination().getItemName().equals(item2.getCombination().getItemName())) {
            String itemName1 = item1.getItemName();
            String itemName2 = item2.getItemName();
            removeItem(player.inventory, itemName1);
            removeItem(player.inventory, itemName2);
            player.inventory.add(item1.getCombination());
            String newItemName = item1.getCombination().getItemName();
            result = "You have combined " + itemName1 + " and " + itemName2 + " to \"" + newItemName + "\"-"
                    + getStringsCapitalized(newItemName);
        }

        return result;
    }

    private Item searchInRoomAndPlayerInventory(String itemName) {
        Item foundItem = findItemByName(player.inventory, itemName);
        if (foundItem == null) {
            ArrayList<Item> itemList = player.getCurrentRoom().items;
            foundItem = findItemByName(itemList, itemName);
            if (foundItem != null) ;
            itemList.remove(foundItem);
        }
        return foundItem;
    }

    private String equipPlayer(String weaponName) {
        Item foundItem = searchInRoomAndPlayerInventory(weaponName);

        if ((foundItem != null) && foundItem instanceof ShootingWeapon) {
            ShootingWeapon foundShootingWeapon = (ShootingWeapon) foundItem;
            if (foundShootingWeapon.getAmmo() <= 0) {
                return "There isn't any ammo left - you can't equip with this";
            }
        }

        if (player.getCurrentWeapon() == null) {
            player.setCurrentWeapon((Weapon) foundItem);
        } else {
            if (findItemByName(player.inventory, player.getCurrentWeapon().getItemName()) == null) {
                player.getCurrentRoom().putItemInRoom(player.getCurrentWeapon());
            }
            player.setCurrentWeapon((Weapon) foundItem);
        }
        if (player.getCurrentWeapon() == null) {
            return "Can't equip whit " + weaponName + " it is out of ammo.";
        } else return "Your weapon of choice: " + foundItem;

    }

    private void requestAttack(String enemyName) {
        Enemy enemy = null;
        enemy = player.getEnemy();
        if (enemy != null) {
            if (enemy.getEnemyName().equals(enemyName)) {
                beginFight(enemy);
            } else {
                System.out.println("I don't know that name");
            }
        } else {
            beginFight(null);
        }
    }

    private void playerAttack(Enemy enemy) {
        if (enemy != null) {
            boolean enemyDead = enemyTakesHit(player.getCurrentWeapon(), enemy);
            if (!enemyDead) {
                enemyResponds(enemy);
            }
        } else {
            System.out.println("You attacked the air...You hero!");
        }
    }

    private void beginFight(Enemy enemy) {
        if (player.getCurrentWeapon() != null) {
            Weapon playerWeapon = player.getCurrentWeapon();
            String enemyName = getStringsCapitalized(enemy.getEnemyName());
            if (playerWeapon instanceof MeleeWeapon) {
                System.out.println("Hitting " + enemyName + "...");
                playerAttack(enemy);
            } else if (playerWeapon instanceof ShootingWeapon) {
                ShootingWeapon shootingWeapon = ((ShootingWeapon) playerWeapon);
                if (shootingWeapon.getAmmo() > 0) {
                    System.out.println("Shooting at " + enemyName + "....");
                    playerAttack(enemy);
                    shootingWeapon.useAmmo();
                } else {
                    System.out.println("You're out of ammo.");
                    enemyResponds(enemy);
                }
            }
        } else System.out.println("you haven't got any weapons");
    }

    private void enemyResponds(Enemy enemy) {
        if (enemy != null) {
            Weapon weaponName = enemy.getWeaponName();
            playerTakeHit(weaponName);
        } else {
            System.out.println("You're attacking the empty void, whit an empty weapon...Stupid");
        }
    }

    private boolean enemyTakesHit(Weapon weapon, Enemy enemy) {
        int damage = weapon.getDamage();
        enemy.changeInHealth(damage);
        if (isEnemyDead() == true) {
            System.out.println("Your enemy is dead.\nYou won the battle!");
            return true;
        } else {
            enemy.getEnemyHealth();
            System.out.println("Enemys health: " + enemy.getEnemyHealth());
            return false;
        }
    }

    private void playerTakeHit(Weapon weapon) {
        boolean playerTakesHit = player.takeHit(weapon);
        if (playerTakesHit) {
            isPlayerDead();
            System.out.println("Enemy hit you back!\nYour health is: " + player.getHealth());
        } else System.out.println("You have won the battle!");
    }

    //player skal finde enemy.
    private boolean isEnemyDead() {
        boolean isDead;
        Enemy enemy = player.getEnemy();
        if (enemy.getEnemyHealth() < 1) {
            Weapon weapon = enemy.getWeaponName();
            dropItem(enemy.enemyInventory, weapon.getItemName());
            findItemByName(enemy.enemyInventory, weapon.getItemName());
            player.getCurrentRoom().removeEnemyFromRoom();
            isDead = true;
        } else {
            isDead = false;
        }
        return isDead;
    }

    private boolean isPlayerDead() {
        boolean isDead;
        if (player.getHealth() < 1) {
            gameRunning = false;
            isDead = true;
            System.out.println("You have lost all of your health points. \nThe GAME is OVER.");
        } else {
            isDead = false;
        }
        return isDead;
    }


 /*   private Item findFoodItem(String itemName) {
        Item foundFood = findItemByName(player.inventory, itemName);
        if (foundFood == null) {
            foundFood = findItemByName(player.getCurrentRoom().items, itemName);
        }
        return foundFood;
    }*/

    private String requestEat(String foodToEat) {
        Item item = searchInRoomAndPlayerInventory(foodToEat);
        String result = null;

        if (item == null) {
            return "There is nothing you can consume here.";
        } else {
            EatEnum eatResult = tryToEat(item);
            if (eatResult == EatEnum.BAD) {
                Food food = (Food) item;
                result = "You lost " + food.getHealthPoints() + " points\n" + "Now you have "
                        + player.getHealth() + " health points";
                isPlayerDead();
            } else if (eatResult == EatEnum.GOOD) {
                Food food = (Food) item;
                result = "You added " + food.getHealthPoints() + " points\n" + "Now you have "
                        + player.getHealth() + " health points";
            } else if (eatResult == EatEnum.INEDIBLE) {
                result = "You can't consume this";
            }
        }
        return result;
    }

    private EatEnum tryToEat(Item item) {
        if (item instanceof Food) {
            removeItem(player.getCurrentRoom().items, item.getItemName());
            removeItem(player.inventory, item.getItemName());
            Food food = (Food) item;
            player.changeInHealth(food.getHealthPoints());
            int healthPoint = food.getHealthPoints();
            if (food.getHealthPoints() < 0) {
                return EatEnum.BAD;
            } else if (healthPoint > 0) {
                return EatEnum.GOOD;
            }
        }
        return EatEnum.INEDIBLE;
    }


    private String enteringRoom() {
        Room room = player.getCurrentRoom();
        String roomIntroduction = "You are on " + room.getROOM_NAME() + room.getROOM_DESCRIPTION();
        if (checkIfGameOver()) {
            return roomIntroduction + "\nUnfortunately you burned up and therefore the GAME is OVER.";
        } else if (checkIfFinal()) {
            return roomIntroduction + "\n\nYou have reached the end of the game. CONGRATULATIONS!!!\nThe End\nNow, go out and look at the sky.";
        } else if ((room.getRoomCount() == 0) || (room.getRoomCount() == 1) || (room.getRoomCount() == 4)) {
            return roomIntroduction + getRoomItemDescription();
        } else
            return descriptionRandomizer();
    }

    private String descriptionRandomizer() {
        Random random = new Random();
        Room room = player.getCurrentRoom();
        int answerRandomizer = random.nextInt(7) + 1;

        if (answerRandomizer == 1) {
            return "Back on " + room.getROOM_NAME();
        }
        if (answerRandomizer == 2) {
            return "... And we're back on " + room.getROOM_NAME()+ getRoomItemDescription();
        }
        if (answerRandomizer == 3) {
            return "Once again we are back on " + room.getROOM_NAME();
        }
        if (answerRandomizer == 4) {
            return "Blah. blah. blah. " + room.getROOM_NAME() +getStringsCapitalized(getRoomItemsName()) + " blah. blah.";
        }
        if (answerRandomizer == 5) {
            return "Keep getting back to " + room.getROOM_NAME() + "Maybe we should just stay here?";

        } else return "You are on " + room.getROOM_NAME() + room.getROOM_DESCRIPTION() +
                getRoomItemDescription();
    }

    private boolean checkIfFinal() {
        Room room = player.getCurrentRoom();
        Item finalItem = findItemByName(player.inventory, spaceMap.getFinalItem().getItemName());
//man skal være i pluto, med working radio i sit inventory, og fjenden på pluto skal være besejret.
        if (room.equals(spaceMap.getFinalRoom()) && (finalItem != null)&& (player.getEnemy()==null)){
            gameRunning = false;
            return true;
        } else return false;
    }

    private boolean checkIfGameOver() {
        if (player.getCurrentRoom().equals(spaceMap.getGameOverRoom())) {
            gameRunning = false;
            return true;
        } else return false;
    }

    private String getStringsCapitalized(String string) {
        if (string.length() > 0) {
            String firstLetter = string.substring(0, 1);
            String capitalLetter = firstLetter.toUpperCase();
            String restOfWord = string.substring(1);
            string = capitalLetter + restOfWord;

            return string;
        } else return string;
    }

    private String introTekst() {
        return """
                                 
                In the year 2006 we lost our smallest planet. It was demoted, and categorised as a dwarf planet.
                But we believe that Pluto belongs to the real planets, so your mission is:
                To rediscover our small friend Pluto, and report its position back to headquarters once it is found.
                On your quest you will find a number of teleporters that will send you to other planets.
                With you, you'll have a device to tell about the planets, track items, and helping you on your way.
                """;
    }

    private String commandOptions() {
        return """
                How to play:
                You can move around in the game by typing "go" followed by "north", "south", "east", "west".
                You can also type the first letter of the direction you want to move.
                You can pick items up by typing "take" followed by the name of the item, you can only carry five items at the time.
                Likewise you can leave items on planets by typing "drop" followed by the name og teh item.
                If you want to se your inventory - type "inventory or i.
                If you want to look around, just type "look" or 'l'. Type "help" or 'h' for help.
                If you want to quit the game, type "exit" or 'q'.
                                
                If you want to equip your self whit a weapon type "equip" and the name of the weapon. Noteis that you can only equip your self whit a weapon in the room or in your inventory.
                If you want to eat or drink a item you can wright "eat" or "drink" and the name of teh item.
                                                
                Type "attack" and the name of the enemy you want to attack.
                For update on your health score type "health"

                If you want to combine two items, type "combine".""";
    }

    private String startTekst() {
        return "\nAt this moment you are on Earth. Take a look around.\n" +
                player.getCurrentRoom().getROOM_DESCRIPTION() + "\n\n" + getInventoryWhitDescription() + "\nWhat do you want to do first?";
    }

}










