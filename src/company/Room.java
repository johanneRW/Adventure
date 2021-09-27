package company;

public class Room {

    //ved ikke om dette giver mening i sidste ende, men har forsøgt at lave en omskrivning af "adam og eva" koden til at oprette døre
    // rummets navn og beskrivelse ændres sig ikke undervejs, derfor er de final.
    private final String ROOM_NAME;
    private final String ROOM_DESCRIPTION;
    private final String ROOM_HELP;
    private Room doorNorth;
    private Room doorEast;
    private Room doorWest;
    private Room doorSouth;
    //Det kan være at "help" skal afhænge af hvilket rum spilleren befinder sig i.
    private String help;
    private String finalRoom;

//TODO: hvordan skal spillet slutte i rum 5? Der skal formentlig laves en getter der siger "Tillykke" og kode der ændres gameRunning til false.
    //TODO: skal spillet vide hvad det "sidste" er, eller hvilken handling der slutter spillet? Måske skal rummet have en dør der leder til "slut"

    //Vi opretter et rum der har mulighed for fire døre, uden at lave dørene endnu, derfor er de sat til null
    public Room(String roomName,String roomDescription,String roomHelp) {
        this.ROOM_DESCRIPTION =roomDescription;
        this.ROOM_NAME = roomName;
        this.ROOM_HELP=roomHelp;
        this.doorNorth = null;
        this.doorEast = null;
        this.doorWest = null;
        this.doorSouth = null;
    }

    // Opret en forbindelse til et andet rum, når der bliver oprettet en dør i en retning,
    // Bliver der automatisk oprettet en dør i modsat retning tilbage til rummet, så man ikke glemmer en forbindelse
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
    public Room getDoorNorth() {
        return doorNorth ;
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
    public String getROOM_DESCRIPTION() {
        return "You are in " + ROOM_NAME +". "+ ROOM_DESCRIPTION;
    }

    public String getROOM_HELP(){return ROOM_HELP;}

    // Har lavet en toString for at kunne dobbelt tjekke at dørene er oprettet korrekt.Den er ikke nødvendig når kortet er oprettet.
    @Override
    public String toString() {
        String north, south, east, west;
        if (doorNorth == null) {
            north = "none";
        } else {
            north = doorNorth.ROOM_NAME;
        }
        if (doorEast == null) {
            east = "none";
        } else {
            east = doorEast.ROOM_NAME;
        }
        if (doorWest == null) {
            west = "none";
        } else {
            west = doorWest.ROOM_NAME;
        }
        if (doorSouth == null) {
            south = "none";
        } else {
            south = doorSouth.ROOM_NAME;
        }
        return "RoomName=" + ROOM_NAME +
                ", doorNorth=" + north +
                ", doorEast=" + east +
                ", doorWest=" + west +
                ", doorSouth=" + south;
    }
}

