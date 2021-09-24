package company;

public class Room {
//ved ikke om dette giver mening i stidste ende, men har forsøgt at lave en omskrivning af "adam og eva" koden til at oprette døre
    private String roomName;
    private Room doorNorth;
    private Room doorEast;
    private Room doorWest;
    private Room doorSouth;
    private Room door;


    //vi opretter et rum der har mulighed for fire døre, uden at lave dørerne endu, derfor er de sat til null
    public Room(String roomName) {
        this.roomName = roomName;
        this.doorNorth = null;
        this.doorEast = null;
        this.doorWest = null;
        this.doorSouth = null;
    }

// opret en forbindlse til et andet rum
    public void createDoor(Room anotherRoom) {
        if (door == null) {
            door = anotherRoom;
            anotherRoom.createDoor(this);
        }
    }
// string til at tjekke at der er lavet døre.
    @Override
    public String toString() {
        return  roomName + "har en dør til " + door.roomName;
    }
}

