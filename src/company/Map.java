package company;

public class Map {
    public Room currentRoom;
    private Room finalRoom;
    private Item finalItem;
    private Item finalItem2;

    public Map() {
        //Planeternes beskrivelser er hentet på https://solarsystem.nasa.gov/planets/overview/

        //De ønskede rum, beskrivelser og hjælpe-tekst.
        Room theEarth1 = new Room("the Earth (1)", """
                Earth — our home planet — is the only place we know of so far that’s inhabited by living things.
                It's also the only planet in our solar system with liquid water on the surface.""",
                "You are standing on Earth! There are two teleporters, one is to the west, and the other to the east. Try one of them and see where you land!");
        Room saturn2 = new Room("Saturn (2)", """
                Adorned with a dazzling, complex system of icy rings, Saturn is unique in our solar system.
                The other giant planets have rings, but none are as spectacular as Saturn's.""",
                "You can just pass by this one, there's nothing to see here, except for these spectacular rings!! Winter on Earth suddenly doesn't seem so bad anymore though!");
        Room neptune3 = new Room("Neptune (3)", """
                Neptune — the eighth and most distant major planet orbiting our Sun —
                is dark, cold and whipped by supersonic winds.
                It was the first planet located through mathematical calculations, rather than by telescope.""",
                "Hurry up and get out of here, before you get blown away by these powerful winds! Quick! Go south!");
        Room jupiter4 = new Room("Jupiter (4)", """
                Jupiter is more than twice as massive than the other planets of our solar system combined.
                The giant planet's Great Red spot is a centuries-old storm bigger than Earth.""",
                "Oh God! The storm! I can't see a thing! Hurry! Get out of here!");
        Room pluto5 = new Room("Pluto (5)", """
                YOU FOUND IT!! What a marvellous feat!
                                
                Pluto is a complex world of ice mountains and frozen plains.
                Still considered the ninth planet by some, Pluto is the largest member of the Kuiper Belt.
                It is the best known of a new class of worlds called dwarf planets.
                But now Pluto can be returned to its rightful place among the other planets, you just need to report back to headquarter.""",
                "We are finally here! Oh, it's so peaceful here. So quiet, let's stay here forever.");
        Room mercury6 = new Room("Mercury (6)", """
                Mercury — the smallest planet in our solar system and closest to the Sun —
                is only slightly larger than Earth's Moon.
                Mercury is the fastest planet, zipping around the Sun every 88 Earth days.""",
                "From freezing cold to flaming HOT! This is killing me! Hurry! Find your way to a safer place!");
        Room venus7 = new Room("Venus (7)", """
                Venus spins slowly in the opposite direction from most planets.
                A thick atmosphere traps heat in a runaway greenhouse effect, making it the hottest planet in our solar system.""",
                "If you want to continue getting my help, you better get your butt out of here, 'cause my motherboard is quickly melting :(!");
        Room mars8 = new Room("Mars (8)", """
                Mars is a dusty, cold, desert world with a very thin atmosphere.
                There is strong evidence Mars was — billions of years ago — wetter and warmer, with a thicker atmosphere.""",
                "I can sense it! WE ARE SO CLOSE!!! Don't lose it now! \nOH NO! Some aliens are blocking the portal, quick! Do something! ");
        Room uranus9 = new Room("Uranus (9)", """
                Uranus — seventh planet from the Sun — rotates at a nearly 90-degree angle from the plane of its orbit.
                This unique tilt makes Uranus appear to spin on its side.""",
                "BRRR! Not exactly Utopia, is it?");
        Room theSun10 = new Room("The Sun (10)", "you are dead", "I cant help you");


        //TODO: skal kortet ændres? og skal der tilføjes andre "rum"?

        //lav forbindelse mellem rummene
        theEarth1.createConnectionEast(mercury6);
        theEarth1.createConnectionWest(venus7);
        mercury6.createConnectionEast(theSun10);
        mercury6.createConnectionSouth(jupiter4);
        mercury6.createConnectionWest(venus7);
        venus7.createConnectionSouth(mars8);
        mars8.createConnectionEast(jupiter4);
        jupiter4.createConnectionSouth(uranus9);
        uranus9.createConnectionWest(saturn2);
        saturn2.createConnectionSouth(pluto5);
        uranus9.createConnectionSouth(neptune3);
        neptune3.createConnectionWest(pluto5);

        //Ifølge instruktionen skal bruger starte i rum 1, derfor er dette startværdien.
        this.currentRoom = theEarth1;
        //Det rummet hvor spillet slutter
        this.finalRoom = pluto5;

        //TODO: opret forskellige Item
        Item earthGrundstof = new Item("EarthGrundstof", "beskrivelse");
        Item lithium = new Item("Lithium","Lithium is a highly flammable element, which can be used to start a fire.");
        Item laserGun = new Item("Laser gun","A friendly entity has offered you this laser gun.");
        Item molbydenum = new Item("Molbydenum","At this point we're just making stuff up.");
        Item neptunium = new Item("Neptunium","Neptunium is an element that can be found on Neptune.");
        Item uran = new Item("Uran","Uran is an element that can be found on Uranus.");
        Item boron = new Item("Boron","Boron is an element that has been studied and experimented with, as a potential ingredient in organic medicine.");
        Item plutonium = new Item("Plutonium","Plutonium is found on Pluto. Plutonium is the element with the highest atomic number to occur in nature.");

        //TODO:fordel items på rum
        theEarth1.putItemInRoom(earthGrundstof);
        mercury6.putItemInRoom(lithium);
        mars8.putItemInRoom(boron);
        jupiter4.putItemInRoom(laserGun);
        neptune3.putItemInRoom(neptunium);
        uranus9.putItemInRoom(uran);
        venus7.putItemInRoom(molbydenum);
        pluto5.putItemInRoom(plutonium);
        //TODO: skal final item ligge på pluto, og spilleren skal samle det op, eller skal final item ligge et andet sted og spiller skal have det med?


        //TODO: spiller skal have den korrekte ting med ind i final-room før spillet slutter, hvad skal det være?
        this.finalItem = lithium;
        this.finalItem2 = boron;

    }

    public Room getFinalRoom() {
        return finalRoom;
    }

    public Item getFinalItem() {
        return finalItem;
    }
    public void checkIfFinal() {
        if (currentRoom.equals(getFinalRoom()) && (Adventure.findItemInInventory(getFinalItem().getItemName()))) {
            Adventure.gameRunning = false;
            System.out.println("\nYou have reached the end of the game. CONGRATULATIONS!!!\nThe End\nNow, go out and look at the sky.");
        }
    }
}
