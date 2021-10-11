package company;

import java.sql.SQLOutput;
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
            System.out.println(getBeginningTekst());
        } else {
            System.out.println(getIntroTekst());
            System.out.println(getCommandOptions());
            System.out.println(getBeginningTekst());
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
                    // jeg har ladet vores første udgave af take og drop blive, så man kan samle op og efterlade items på flere måder.
                } else if ((itemName == null) && (command.equals("take") || command.equals("t"))) {
                    String question = askPlayer("take");
                    System.out.println(question);
                    itemName = getPlayerReply();
                    String result = pickUpItem(itemName);
                    System.out.println(result);
                } else if ((itemName == null) && command.equals("drop") || (command.equals("d"))) {
                    String question = askPlayer("drop");
                    System.out.println(question);
                    itemName = getPlayerReply();
                    String result = dropItem(player.inventory, itemName);
                    System.out.println(result);
                    //har fjernet "go" fra equals, da substring nu sortere ordet fra, og det derfor aldrig vil blive brugt.
                } else if (command.equals("n") || command.equals("north")) {
                    String result = requestDirection(player.currentRoom.getConnectionNorth(), "north");
                    System.out.println(result);
                } else if (command.equals("w") || command.equals("west")) {
                    String result = requestDirection(player.currentRoom.getConnectionWest(), "west");
                    System.out.println(result);
                } else if (command.equals("e") || command.equals("east")) {
                    String result = requestDirection(player.currentRoom.getConnectionEast(), "east");
                    System.out.println(result);
                } else if (command.equals("s") || command.equals("south")) {
                    String result = requestDirection(player.currentRoom.getConnectionSouth(), "south");
                    System.out.println(result);
                } else if (command.equals("look") || command.equals("l")) {
                    String result = requestLook();
                    System.out.println(result);
                } else if (command.equals("inventory") || command.equals("i") || command.equals("in") || command.equals("inv")) {
                    System.out.println(getInventoryWhitDescription());
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
                } else if (command.equals("attack")) {
                    attack();
                } else if (command.equals("equip")) {
                    System.out.println("which weapon do you want to use?");
                    String weaponName = getPlayerReply();
                    equipPlayer(weaponName);
                } else if (command.equals("eat")) {
                    System.out.println("What do you want to eat?");
                    String foodToEat = getPlayerReply();
                    System.out.println(eat(foodToEat));
                } else if (command.equals("health")) {
                    System.out.println("You have " + player.getHealth() + "healthpoints right now.");
                } else {
                    //hvis spilleren taster en ugyldig kommando, beder spillet om en ny, dette er for at Adventure ikke crasher ved ugyldigt indput.
                    System.out.println("I don't know how to \"" + command + "\", try typing something else");
                }
            }


            catch(java.lang.ClassCastException e) {
                System.out.println("You can't do that, try again with something else.");
            }
        }

    }

    public String requestDirection(Room requestedRoom, String directionName) {
        if (requestedRoom != null) {
            player.currentRoom = requestedRoom;
            player.currentRoom.upDateRoomCount();
            enteringRoom();
            return "Teleporting " + directionName + "...." + "\n\n" + enteringRoom();
        } else return "You can't go that way - there isn't a teleporter.";
    }

    private String askPlayer(String command) {
        if (command.equals("drop") || command.equals("take")) {
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
                player.currentRoom.getROOM_DESCRIPTION() + getRoomItemDescription();
    }

    private String requestHelp(String answer) {
        if (answer.equals("yes") || answer.equals("y")) {
            return "Okay, I'll scan the planet and see if I can help you.....\n" + player.currentRoom.getROOM_HELP();
        }
        if (answer.equals("no") || answer.equals("n")) {
            return "Okay, suit yourself! :)";
        }
        if (answer.equals("commands") || (answer.equals("c"))) {
            return getCommandOptions();
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

    public boolean checkIfFinal() {
        if (player.currentRoom.equals(spaceMap.getFinalRoom()) && (findItemInInventory(spaceMap.getFinalItem().getItemName()))) {
            gameRunning = false;
            return true;
        } else return false;
    }

    public boolean checkIfGameOver() {
        if (player.currentRoom.equals(spaceMap.getGameOverRoom())) {
            gameRunning = false;
            return true;
        } else return false;
    }

    public String enteringRoom() {
        String roomIntroduction = "You are on " + player.currentRoom.getROOM_NAME() + player.currentRoom.getROOM_DESCRIPTION();
        if (checkIfGameOver()) {
            return roomIntroduction + "\nUnfortunately you burned up and therefore the GAME is OVER.";
        } else if (checkIfFinal()) {
            return roomIntroduction + "\n\nYou have reached the end of the game. CONGRATULATIONS!!!\nThe End\nNow, go out and look at the sky.";
        } else if ((player.currentRoom.getRoomCount() == 0) || (player.currentRoom.getRoomCount() == 1) || (player.currentRoom.getRoomCount() == 4)) {
            return roomIntroduction + getRoomItemDescription();
        } else
            return descriptionRandomizer();
    }

    public String descriptionRandomizer() {
        Random random = new Random();
        int answerRandomizer = random.nextInt(7) + 1;
        if (answerRandomizer == 1) {
            return "Back on " + player.currentRoom.getROOM_NAME();
        }
        if (answerRandomizer == 2) {
            return "... And we're back on " + player.currentRoom.getROOM_NAME() + getRoomItemDescription();
        }
        if (answerRandomizer == 3) {
            return "Once again we are back on " + player.currentRoom.getROOM_NAME();
        }
        if (answerRandomizer == 4) {
            return "Blah. blah. blah. " + player.currentRoom.getROOM_NAME() + getStringsCapitalized(getRoomItemsName()) + " blah. blah.";
        }
        if (answerRandomizer == 5) {
            return "Keep getting back to " + player.currentRoom.getROOM_NAME() + "Maybe we should just stay here?";

        } else return "You are on " + player.currentRoom.getROOM_NAME() + player.currentRoom.getROOM_DESCRIPTION() +
                getRoomItemDescription();
    }

    public String getStringsCapitalized(String string) {
        if (string.length() > 0) {
            String firstLetter = string.substring(0, 1);
            String capitalLetter = firstLetter.toUpperCase();
            String restOfWord = string.substring(1);
            string = capitalLetter + restOfWord;

            return string;
        } else return string;
    }

    private String getIntroTekst() {
        return """
                                 
                In the year 2006 we lost our smallest planet. It was demoted, and categorised as a dwarf planet.
                But we believe that Pluto belongs to the real planets, so your mission is:
                To rediscover our small friend Pluto, and report its position back to headquarters once it is found.
                On your quest you will find a number of teleporters that will send you to other planets.
                With you, you'll have a device to tell about the planets, track items, and helping you on your way.
                """;
    }

    public String getCommandOptions() {
        return """
                How to play:
                You can move around in the game by typing "go" followed by "north", "south", "east", "west".
                You can also type the first letter of the direction you want to move.
                You can pick items up by typing "take" followed by the name of the item, you can only carry five items at the time.
                Likewise you can leave items on planets by typing "drop" followed by the name og teh item.
                If you want to se your inventory - type "inventory or i.
                If you want to look around, just type "look" or 'l'. Type "help" or 'h' for help.
                If you want to quit the game, type "exit" or 'q'.

                If you want to combine two items, type "combine".""";
    }

    public String getBeginningTekst() {
        return "\nAt this moment you are on Earth. Take a look around.\n" +
                player.currentRoom.getROOM_DESCRIPTION() + "\n\n" + getInventoryWhitDescription() + "\nWhat do you want to do first?";
    }

    public String ItemName(ArrayList<Item> list) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            Item currentItem = list.get(i);
            stringBuilder.append(currentItem.getItemName());
            stringBuilder.append(", ");
        }
        return stringBuilder.toString();
    }

    public Item findItemByName(ArrayList<Item> list, String itemName) {
        for (int i = 0; i < list.size(); i++) {
            Item currentItem = list.get(i);
            if (currentItem.getItemName().equals(itemName)) {
                return currentItem;
            }
        }
        return null;
    }

    public String getRoomItemsName() {
        if (player.currentRoom.items.size() > 0) {
            String items = ItemName(player.currentRoom.items);
            return items;
        } else return "\nItems: There doesn't seem to be anything to take on this planet";
    }

    public String getInventory() {
        if (player.inventory.size() > 0) {
            String inventory = ItemName(player.inventory);
            return "These items are currently in your inventory: " + getStringsCapitalized(inventory);
        } else return "Your inventory seems to be empty";
    }

    public String getInventoryWhitDescription() {
        if (player.inventory.size() > 0) {
            String inventory = ItemNameAndDescription(player.inventory);
            return "These items are currently in your inventory: \n" + inventory;
        } else return "Your inventory seems to be empty";

    }

    public boolean findItemInInventory(String itemName) {
        boolean found = false;
        for (int i = 0; i < player.inventory.size(); i++) {
            Item currentItem = player.inventory.get(i);
            if (currentItem.getItemName().equals(itemName)) {
                found = true;
            }
        }
        return found;

    }

    public String ItemNameAndDescription(ArrayList<Item> list) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            Item currentItem = list.get(i);
            stringBuilder.append(getStringsCapitalized(currentItem.getItemName()) + ", " + currentItem.getItemDescription());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public String getRoomItemDescription() {
        if (player.currentRoom.items.size() > 0) {
            String inventory = ItemNameAndDescription(player.currentRoom.items);
            return "\nItems on this planet: \n" + inventory;
        } else return "\nItems: There doesn't seem to be anything to take on this planet";
    }

    public void removeItem(ArrayList<Item> list, String itemName) {
        for (int i = 0; i < list.size(); i++) {
            Item currentItem = list.get(i);
            if (currentItem.getItemName().equals(itemName)) {
                list.remove(i);
            }
        }
    }

    public String dropItem(ArrayList<Item> list, String itemName) {
        Item foundItem = findItemByName(list, itemName);
        if (foundItem == null) {
            return "Can't find a \"" + itemName + "\" in your inventory";
        } else {
            removeItem(list, itemName);
            player.currentRoom.items.add(foundItem);
            return getStringsCapitalized(itemName) + " is placed carefully on " + player.currentRoom.getROOM_NAME() + "\n" + getInventory();
        }
    }

    public String pickUpItem(String itemName) {
        //der må max være 5 Items i en spillers inventory ad gangen.
        if (player.inventory.size() <= 4) {
            Item foundItem = findItemByName(player.currentRoom.items, itemName);
            if (foundItem == null) {
                return "Can't find a \"" + itemName + "\" on this planet";
            } else {
                removeItem(player.currentRoom.items, itemName);
                player.inventory.add(foundItem);
                return getStringsCapitalized(itemName) + " is added to your inventory.\n" + getStringsCapitalized(getInventory());
            }
        } else
            return "You can't have more than 5 items in your inventory\nYou'll have to drop something\n" + getStringsCapitalized(getInventory());
    }

    public String combineItems(Item item1, Item item2) {
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
                    + getStringsCapitalized(item1.getCombination().getItemDescription());
        }

        return result;
    }

    public void equipPlayer(String weaponName) {
        Item foundItem = findItemByName(player.inventory, weaponName);
        if (foundItem == null) {
            foundItem = findItemByName(player.currentRoom.items, weaponName);
        }
        player.setCurrentWeapon((Weapon) foundItem);
        System.out.println("weapons in hands: " + foundItem);
    }

    public void unequipPlayer(String weaponName) {
        Weapon currentweapon = null;
        player.setCurrentWeapon(null);

    }

    public void attack() {
        if (player.getCurrentWeapon() != null) {
            if (player.currentRoom.getEnemy() != null) {
                Enemy enemy = player.currentRoom.getEnemy();

                System.out.println("hitting enemy");
                boolean enemyDead = hitEnemy(player.getCurrentWeapon(), enemy);
                if (!enemyDead) {
                    Weapon weaponName = enemy.getWeaponName();
                    playerTakeHit(weaponName);
                }
            } else System.out.println("ther doesn't seems to be anyone to attack");
        } else System.out.println("you haven't got any weapons");
    }

    public boolean hitEnemy(Weapon weapon, Enemy enemy) {
        int damage = weapon.getDamage();
        enemy.loseHealth(damage);
        //checkIsEnemyDead();
        if (checkIsEnemyDead() == true) {
            System.out.println("you won the battle!");
            return true;
        } else {
            enemy.getEnemyHealth();
            System.out.println("enemys health: " + enemy.getEnemyHealth());
            return false;
        }
    }


    public void playerTakeHit(Weapon weapon) {
        if (player.currentRoom.getEnemy() != null) {
            int damage = weapon.getDamage();
            player.changeInHealth(damage);
            player.getHealth();
            isPlayerDead();
            System.out.println("enemy hit you, your health is: " + player.getHealth());
        } else System.out.println("you have won");


    }


    public boolean checkIsEnemyDead() {
        boolean isDead;
        Enemy enemy = player.currentRoom.getEnemy();
        if (enemy.getEnemyHealth() < 1) {
            Weapon weapon = enemy.getWeaponName();
            dropItem(enemy.enemyInventory, weapon.getItemName());
            findItemByName(enemy.enemyInventory, weapon.getItemName());
            player.currentRoom.removeEnemyFromRoom();
            isDead = true;
        } else {
            isDead = false;
        }
        return isDead;
    }

    public boolean isPlayerDead() {
        boolean isDead;
        if (player.getHealth() < 0) {
            gameRunning = false;
            isDead = true;
        } else {
            isDead = false;
        }
        return isDead;
    }

    public Food findFood(String foodName){
        Food foundFood = (Food) findItemByName(player.inventory, foodName);
        if (foundFood == null) {
            foundFood = (Food) findItemByName(player.currentRoom.items, foodName);
        }
        return foundFood;
    }

    public String eat(String foodToEat) {
        Food food = findFood(foodToEat);

        if (food == null) {
            return "There is nothing you can eat here.";
        } else
            removeItem(player.currentRoom.items, foodToEat);
            return player.changeInHealth(food.getHealthPoints());
    }
}








