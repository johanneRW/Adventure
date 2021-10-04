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

    //Vi opretter et rum der har mulighed for fire forbindelser, uden at lave forbindelserne endnu, derfor er de sat til null
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
    }
    // Opret en forbindelse til et andet rum, når der bliver oprettet en forbindelse i en retning,
    // Bliver der automatisk oprettet en forbindelse i modsat retning tilbage til rummet, så man ikke glemmer en forbindelse
    //Der oprettes en for hver kompasretning.
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

    // Vi laver get-sætninger til at flytte rundt mellem rum, gemme de forbindelser vi har lavet i createDoor...
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

    //Der skal laves beskrivelser af alle rum, så der kan printes en beskrivelse ud ved kommando look
    public String getROOM_DESCRIPTION() {
        return ROOM_DESCRIPTION;
    }

    public String getROOM_NAME() {
        return ROOM_NAME + ".\n";
    }

    public String getROOM_HELP() {
        return ROOM_HELP;
    }

    public void putItemInRoom(Item item) {
        Room currentRoom = this;
        currentRoom.items.add(item);
    }

    public void upDateRoomCount() {
        this.roomCount++;
    }

    public int getRoomCount() {
        return roomCount;
    }
    @Override
    public String toString() {
        return ROOM_NAME;
    }

    // Har lavet en toString for at kunne dobbelt tjekke at dørene er oprettet korrekt.Den er ikke nødvendig når kortet er oprettet.
    //denne override er kommenteret ud, i stedet for at være slettet, da det kan være der bliver brug for den under opbygning af nyt kort;
   /* @Override
    public String toString() {
        String north, south, east, west;
        if (connectionNorth == null) {
            north = "none";
        } else {
            north = connectionNorth.ROOM_NAME;
        }
        if (connectionEast == null) {
            east = "none";
        } else {
            east = connectionEast.ROOM_NAME;
        }
        if (connectionWest == null) {
            west = "none";
        } else {
            west = connectionWest.ROOM_NAME;
        }
        if (connectionSouth == null) {
            south = "none";
        } else {
            south = connectionSouth.ROOM_NAME;
        }
        return "RoomName=" + ROOM_NAME +
                ", doorNorth=" + north +
                ", doorEast=" + east +
                ", doorWest=" + west +
                ", doorSouth=" + south;
    }*/
}


