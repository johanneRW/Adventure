package company;

public class Room {

    //ved ikke om dette giver mening i sidste ende, men har forsøgt at lave en omskrivning af "adam og eva" koden til at oprette døre
    private String roomName;
    private String roomDesipciton;
    private Room doorNorth;
    private Room doorEast;
    private Room doorWest;
    private Room doorSouth;


    //vi opretter et rum der har mulighed for fire døre, uden at lave dørene endnu, derfor er de sat til null
    public Room(String roomName) {
        this.roomName = roomName;
        this.doorNorth = null;
        this.doorEast = null;
        this.doorWest = null;
        this.doorSouth = null;
    }

    // opret en forbindelse til et andet rum, når der bliver oprettet en dør i en retning,
    // bliver der automatisk oprettet en dør i modsat retning tilbage til rummet, så man ikke glemmer en forbindelse
    //Der oprettes en for hver kompasretning.
    public void createDoorNorth(Room anotherRoom) {
        if (doorNorth == null) {
            doorNorth = anotherRoom;
            anotherRoom.createDoorSouth(this);
        }
    }
    public void createDoorEast(Room anotherRoom) {
        if (doorEast == null) {
            doorEast = anotherRoom;
            anotherRoom.createDoorWest(this);
        }
    }
    public void createDoorWest(Room anotherRoom) {
        if (doorWest == null) {
            doorWest = anotherRoom;
            anotherRoom.createDoorEast(this);
        }
    }
    public void createDoorSouth(Room anotherRoom) {
        if (doorSouth == null) {
            doorSouth = anotherRoom;
            anotherRoom.createDoorNorth(this);
        }
    }
// Vi laver get-sætninger til at flytte rundt mellem rum, gemme de forbindelser vi har lavet i createDoor...

    public Room getdoorNorth() {
        return doorNorth;
    }
    public Room getDoorSouth() {
        return doorSouth;
    }
    public Room getDoorEast() {
        return doorEast;
    }
    public Room getDoorWest() {
        return doorWest;
    }

    //Der skal laves beskrivelser af alle rum, så der kan printes en beskrivelse ud ved kommando look
    public String getRoomDescription() {
        return "You are in " + roomName + roomDesipciton;
    }

    // Har lavet en toString for at kunne dobbelt tjekke at dørene er oprettet korrekt.
    @Override
    public String toString() {
        String north;
        if (doorNorth == null) {
            north = "ingen";
        } else {
            north = doorNorth.roomName;

        }
        String east;
        if (doorEast == null) {
            east = "ingen";
        } else {
            east = doorEast.roomName;

        }
        String west;
        if (doorWest == null) {
            west = "ingen";
        } else {
            west = doorWest.roomName;

        }
        String south;
        if (doorSouth == null) {
            south = "ingen";
        } else {
            south = doorSouth.roomName;
        }
        return "Room{" +
                "roomName='" + roomName + '\'' +
                ", doorNorth=" + north +
                ", doorEast=" + east +
                ", doorWest=" + west +
                ", doorSouth=" + south +
                '}';
    }
}

