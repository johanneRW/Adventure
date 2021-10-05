package company;

import java.util.Random;
import java.util.Scanner;

public class Adventure {

    public static boolean gameRunning = true;
    private static Map spaceMap = new Map();
    private static Player player = new Player();
    private static Scanner input = new Scanner(System.in);
    private static Formatting formatting = new Formatting();

    public static void main(String[] args) {

        System.out.println(getIntroTekst());
        while (gameRunning) {
            String command;
            String itemName = null;

            command = input.nextLine();
            command = command.toLowerCase();

            int firstSpace = command.indexOf(" ");
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
                String result = dropItem(itemName);
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
                String result = dropItem(itemName);
                System.out.println(result);
                //har fjernet "go" fra equals, da substring nu sortere ordet fra, og det derfor aldrig vil blive brugt.
            } else if (command.equals("n") || command.equals("north")) {
                String result = requestDirection(spaceMap.currentRoom.getConnectionNorth(), "north");
                System.out.println(result);
            } else if (command.equals("w") || command.equals("west")) {
                String result = requestDirection(spaceMap.currentRoom.getConnectionWest(), "west");
                System.out.println(result);
            } else if (command.equals("e") || command.equals("east")) {
                String result = requestDirection(spaceMap.currentRoom.getConnectionEast(), "east");
                System.out.println(result);
            } else if (command.equals("s") || command.equals("south")) {
                String result = requestDirection(spaceMap.currentRoom.getConnectionSouth(), "south");
                System.out.println(result);
            } else if (command.equals("look") || command.equals("l")) {
                String result = requestLook();
                System.out.println(result);
            } else if (command.equals("inventory") || command.equals("i") || command.equals("in")) {
                System.out.println(player.getInventoryWhitDescription());
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
                //TODO:combine mangler stadig nogle tweaks og at blive pakket det ind i en metode
            } else if (command.equals("combine")) {
                System.out.println("Type the name of the first item, which you want to combine:");
                String itemName1 = getPlayerReply();
                Item item1 = findItemByName(itemName1);
                System.out.println("Type the name of the item you want to combine it with?");
                String itemName2 = getPlayerReply();
                Item item2 = findItemByName(itemName2);
                String result = combineItems(item1, item2);
                System.out.println(result);
                //String isThisTheEnd = enteringRoom();
                //System.out.println(isThisTheEnd);
            } else {
                //hvis spilleren taster en ugyldig kommando, beder spillet om en ny, dette er for at Adventure ikke crasher ved ugyldigt indput.
                System.out.println("I don't know how to \"" + command + "\", try typing something else");
            }
        }
    }

    private static String getIntroTekst() {
        return """
                Welcome to The Rediscovering of Pluto.
                                
                In the year 2006 we lost our smallest planet. It was demoted, and categorised as a dwarf planet.
                But we believe that Pluto belongs to the real planets, so your mission is:
                To rediscover our small friend Pluto, and report its position back to headquarters once it is found.
                On your quest you will find a number of teleporters that will send you to other planets.
                With you, you'll have a device to tell about the planets, track items, and helping you on your way.
                                
                How to play:
                You can move around in the game by typing "go" followed by "north", "south", "east", "west".
                You can also type the first letter of the direction you want to move.
                You can pick items up by typing "take" followed by the name of the item, you can only carry five items at the time.
                Likewise you can leave items on planets by typing "drop" followed by the name og teh item.
                If you want to se your inventory - type "inventory or i.
                If you want to look around, just type "look" or 'l'. Type "help" or 'h' for help.
                If you want to quit the game, type "exit" or 'q'.
                                
                If you want to combine two items, type "combine".
                                
                At this moment you are on Earth. Take a look around.\n""" +
                spaceMap.currentRoom.getROOM_DESCRIPTION() + "\n\n" + player.getInventoryWhitDescription() +
                "\nWhat do you want to do first?";
    }

    public static String requestDirection(Room requestedRoom, String directionName) {
        if (requestedRoom != null) {
            spaceMap.currentRoom = requestedRoom;
            spaceMap.currentRoom.upDateRoomCount();
            enteringRoom();
            return "Teleporting " + directionName + "...." + "\n\n" + enteringRoom();
        } else return "You can't go that way - there isn't a teleporter.";
    }

    public static String pickUpItem(String itemName) {
        //der må max være 5 Items i en spillers inventory ad gangen.
        if (player.inventory.size() <= 4) {
            boolean found = false;
            for (int i = 0; i < spaceMap.currentRoom.items.size(); i++) {
                Item currentItem = spaceMap.currentRoom.items.get(i);
                if (currentItem.getItemName().equals(itemName)) {
                    spaceMap.currentRoom.items.remove(i);
                    found = true;
                    player.inventory.add(currentItem);
                }
            }
            if (!found) {
                return "Can't find a \"" + itemName + "\" on this planet";
            } else
                return formatting.getStringsCapitalized(itemName) + " is added to your inventory.\n" + player.getInventory();
        } else
            return "You can't have more than 5 items in your inventory\nYou'll have to drop something\n" + player.getInventory();
    }

    public static String dropItem(String itemName) {
        boolean found = false;
        for (int i = 0; i < player.inventory.size(); i++) {
            Item currentItem = player.inventory.get(i);
            if (currentItem.getItemName().equals(itemName)) {
                player.inventory.remove(i);
                found = true;
                spaceMap.currentRoom.items.add(currentItem);
            }
        }
        if (!found) {
            return "Can't find a \"" + itemName + "\" in your inventory";
        } else
            return formatting.getStringsCapitalized(itemName) + " is placed carefully on " + spaceMap.currentRoom.getROOM_NAME() + "\n" + player.getInventory();
    }

    public static boolean findItemInInventory(String itemName) {
        boolean found = false;
        for (int i = 0; i < player.inventory.size(); i++) {
            Item currentItem = player.inventory.get(i);
            if (currentItem.getItemName().equals(itemName)) {
                found = true;
            }
        }
        return found;
    }

    private static String askPlayer(String command) {
        if (command.equals("drop") || command.equals("take")) {
            return "Ok, " + command + " what?";
        }
        if (command.equals("exit")) {
            return "Are you sure you want to leave the game? \"yes\" or \"no\"";
        }
        if (command.equals("help")) {
            return "Want help? Type \"yes\" or \"no\"";
        } else return null;
    }

    private static String getPlayerReply() {
        String reply = input.nextLine();
        reply = reply.toLowerCase();
        return reply;
    }

    private static String requestLook() {
        return "Looking around...\n" +
                spaceMap.currentRoom.getROOM_DESCRIPTION() + spaceMap.currentRoom.getRoomItemDescription();
    }

    private static String requestHelp(String answer) {
        if (answer.equals("yes") || answer.equals("y")) {
            return "Okay, I'll scan the planet and see if I can help you.....\n" + spaceMap.currentRoom.getROOM_HELP();
        }
        if (answer.equals("no") || answer.equals("n")) {
            return "Okay, suit yourself! :)";
        } else return null;
    }

    private static String requestExit(String answer) {
        if (answer.equals("yes") || answer.equals("y")) {
            gameRunning = false;
            return "Sending you back to your home planet - Goodbye!!!";
        }

        if (answer.equals("no") || answer.equals("n")) {
            return "Alright! Let's keep going then :)";
        } else return null;
    }


    public static boolean checkIfFinal() {
        if (spaceMap.currentRoom.equals(spaceMap.getFinalRoom()) && (Adventure.findItemInInventory(spaceMap.getFinalItem().getItemName()))
                && (Adventure.findItemInInventory(spaceMap.getSecondFinalItem().getItemName()))) {
            Adventure.gameRunning = false;
            return true;
        } else return false;
    }


    public static boolean checkIfGameOver() {
        if (spaceMap.currentRoom.equals(spaceMap.getGameOverRoom())) {
            Adventure.gameRunning = false;
            return true;
        } else return false;
    }

    public static String enteringRoom() {
        if (checkIfGameOver()) {
            return "You are on " + spaceMap.currentRoom.getROOM_NAME() + spaceMap.currentRoom.getROOM_DESCRIPTION() +
                    "\nUnfortunately you burned up and therefore the GAME is OVER.";
        } else if (checkIfFinal()) {
            return "You are on " + spaceMap.currentRoom.getROOM_NAME() + spaceMap.currentRoom.getROOM_DESCRIPTION() +
                    "\n\nYou have reached the end of the game. CONGRATULATIONS!!!\nThe End\nNow, go out and look at the sky.";
        } else if ((spaceMap.currentRoom.getRoomCount() == 0) || (spaceMap.currentRoom.getRoomCount() == 1) || (spaceMap.currentRoom.getRoomCount() == 4)) {
            return "You are on " + spaceMap.currentRoom.getROOM_NAME() + spaceMap.currentRoom.getROOM_DESCRIPTION()
                    + spaceMap.currentRoom.getRoomItemDescription();
        } else
            return descriptionRandomizer();
    }

    public static String descriptionRandomizer() {
        Random random = new Random();
        int answerRandomizer = random.nextInt(7) + 1;
        if (answerRandomizer == 1) {
            return "Back on " + spaceMap.currentRoom.getROOM_NAME();
        }
        if (answerRandomizer == 2) {
            return "... And we're back on " + spaceMap.currentRoom.getROOM_NAME() + spaceMap.currentRoom.getRoomItemDescription();
        }
        if (answerRandomizer == 3) {
            return "Once again we are back on " + spaceMap.currentRoom.getROOM_NAME();
        }
        if (answerRandomizer == 4) {
            return "Blah. blah. blah. " + spaceMap.currentRoom.getROOM_NAME() + formatting.getStringsCapitalized(spaceMap.currentRoom.getRoomItemsName()) + " blah. blah.";
        }
        if (answerRandomizer == 5) {
            return "Keep getting back to " + spaceMap.currentRoom.getROOM_NAME() + "Maybe we should just stay here?";

        } else return "You are on " + spaceMap.currentRoom.getROOM_NAME() + spaceMap.currentRoom.getROOM_DESCRIPTION() +
                spaceMap.currentRoom.getRoomItemDescription();
    }


    public static String combineItems(Item item1, Item item2) {
        String result = null;
        if ((item1 == null) || (item2 == null)) {
            result = " you haven't given me to item names, try combine again";
        } else if ((item1.getCombination() == null) || (item2.getCombination() == null)) {
            result = "You can't combine " + item1.getItemName() + " and " + item2.getItemName();
        } else if (item1.getCombination().getItemName().equals(item2.getCombination().getItemName())) {
            String itemName1 = item1.getItemName();
            String itemName2 = item2.getItemName();
            removeItemForCombination(itemName1);
            removeItemForCombination(itemName2);
            player.inventory.add(item1.getCombination());
            String newItemName = item1.getCombination().getItemName();
            result = "You have combined " + itemName1 + " and " + itemName2 + " to \"" + newItemName + "\"-"
                    + item1.getCombination().getItemNameAndDescription() + "\n" + player.getInventory();
        }

        return result;
    }

    public static Item findItemByName(String itemName) {
        for (int i = 0; i < player.inventory.size(); i++) {
            Item currentItem = player.inventory.get(i);
            if (currentItem.getItemName().equals(itemName)) {
                return currentItem;
            }
        }
        return null;
    }

    public static boolean removeItemForCombination(String itemName) {
        boolean found = false;
        for (int i = 0; i < player.inventory.size(); i++) {
            Item currentItem = player.inventory.get(i);
            if (currentItem.getItemName().equals(itemName)) {
                player.inventory.remove(i);
                found = true;
            }
        }
        return found;
    }

}







