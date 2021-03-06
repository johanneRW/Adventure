package company;

import java.util.ArrayList;

public class Room {

    // rummets navn og beskrivelse ændres sig ikke undervejs, derfor er de final.
    private final String ROOM_NAME;
    private final String ROOM_DESCRIPTION;
    private final String ROOM_HELP;
    private Room connectionNorth;
    private Room connectionEast;
    private Room connectionWest;
    private Room connectionSouth;
    public ArrayList<Item> items;
    private int roomCount;
    private Enemy enemy;

    // Vi opretter et rum der har mulighed for fire forbindelser, uden at lave forbindelserne endnu,
    // derfor er de sat til null
    public Room(String roomName, String roomDescription, String roomHelp) {
        this.ROOM_DESCRIPTION = roomDescription;
        this.ROOM_NAME = roomName;
        this.ROOM_HELP = roomHelp;
        this.connectionNorth = null;
        this.connectionEast = null;
        this.connectionWest = null;
        this.connectionSouth = null;
        this.items = new ArrayList<>();
        this.roomCount = 0;
        this.enemy = null;
    }

    // Opret en forbindelse til et andet rum, når der bliver oprettet en forbindelse i en retning,
    // Bliver der automatisk oprettet en forbindelse i modsat retning tilbage til rummet, så man ikke glemmer en forbindelse
    // Der oprettes en for hver kompasretning.
    public void createConnectionNorth(Room anotherRoom) {
        if (connectionNorth == null) {
            connectionNorth = anotherRoom;
            anotherRoom.createConnectionSouth(this);
        }
    }

    public void createConnectionEast(Room anotherRoom) {
        if (connectionEast == null) {
            connectionEast = anotherRoom;
            anotherRoom.createConnectionWest(this);
        }
    }

    public void createConnectionWest(Room anotherRoom) {
        if (connectionWest == null) {
            connectionWest = anotherRoom;
            anotherRoom.createConnectionEast(this);
        }
    }

    public void createConnectionSouth(Room anotherRoom) {
        if (connectionSouth == null) {
            connectionSouth = anotherRoom;
            anotherRoom.createConnectionNorth(this);
        }
    }

    public void putItemInRoom(Item item) {
        this.items.add(item);
    }

    public void increaseRoomCount() {
        this.roomCount++;
    }

    public int getRoomCount() {
        return roomCount;
    }

    public Room getConnectionNorth() {
        return connectionNorth;
    }

    public Room getConnectionSouth() {
        return connectionSouth;
    }

    public Room getConnectionEast() {
        return connectionEast;
    }

    public Room getConnectionWest() {
        return connectionWest;
    }

    public String getROOM_DESCRIPTION() {
        return ROOM_DESCRIPTION;
    }

    public String getROOM_NAME() {
        return ROOM_NAME + ".\n";
    }

    public String getROOM_HELP() {
        return ROOM_HELP;
    }

    public void putEnemyInRoom(Enemy enemy) {
        this.enemy = enemy;
    }

    public void removeEnemyFromRoom() {
        this.enemy = null;
    }

    public Enemy getEnemyInRoom() {
        return enemy;
    }
}
