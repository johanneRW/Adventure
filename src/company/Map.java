package company;

import java.util.Random;

public class Map {
    public Room currentRoom;
    private Room finalRoom;
    private Item finalItem;
    private Item secondFinalItem;
    private Item workingRadio;
    private Room gameOverRoom;

    public Map() {
        //Planeternes beskrivelser er hentet på https://solarsystem.nasa.gov/planets/overview/

        //De ønskede rum, beskrivelser og hjælpe-tekst.
        Room theEarth1 = new Room("the Earth", """
                Earth — our home planet — is the only place we know of so far that’s inhabited by living things.
                It's also the only planet in our solar system with liquid water on the surface.""",
                "You are standing on Earth! There are two teleporters, one is to the west, and the other to the east. Try one of them and see where you land!");
        Room saturn2 = new Room("Saturn", """
                Adorned with a dazzling, complex system of icy rings, Saturn is unique in our solar system.
                The other giant planets have rings, but none are as spectacular as Saturn's.""",
                "You can just pass by this one, there's nothing to see here, except for these spectacular rings!! Winter on Earth suddenly doesn't seem so bad anymore though!");
        Room neptune3 = new Room("Neptune", """
                Neptune — the eighth and most distant major planet orbiting our Sun —
                is dark, cold and whipped by supersonic winds.
                It was the first planet located through mathematical calculations, rather than by telescope.""",
                "Hurry up and get out of here, before you get blown away by these powerful winds! Quick! Go south!");
        Room jupiter4 = new Room("Jupiter", """
                Jupiter is more than twice as massive than the other planets of our solar system combined.
                The giant planet's Great Red spot is a centuries-old storm bigger than Earth.""",
                "Oh God! The storm! I can't see a thing! Hurry! Get out of here!");
        Room pluto5 = new Room("Pluto", """
                YOU FOUND IT!! What a marvellous feat!
                                
                Pluto is a complex world of ice mountains and frozen plains.
                Still considered the ninth planet by some, Pluto is the largest member of the Kuiper Belt.
                It is the best known of a new class of worlds called dwarf planets.
                But now Pluto can be returned to its rightful place among the other planets, you just need to report back to headquarter.""",
                "We are finally here! Oh, it's so peaceful here. So quiet, let's stay here forever. Maybe just call home first?");
        Room mercury6 = new Room("Mercury", """
                Mercury — the smallest planet in our solar system and closest to the Sun —
                is only slightly larger than Earth's Moon.
                Mercury is the fastest planet, zipping around the Sun every 88 Earth days.""",
                "From freezing cold to flaming HOT! This is killing me! Hurry! Find your way to a safer place!");
        Room venus7 = new Room("Venus", """
                Venus spins slowly in the opposite direction from most planets.
                A thick atmosphere traps heat in a runaway greenhouse effect, making it the hottest planet in our solar system.""",
                "If you want to continue getting my help, you better get your butt out of here, 'cause my motherboard is quickly melting :(!");
        Room mars8 = new Room("Mars", """
                Mars is a dusty, cold, desert world with a very thin atmosphere.
                There is strong evidence Mars was — billions of years ago — wetter and warmer, with a thicker atmosphere.""",
                "I can sense it! WE ARE SO CLOSE!!! Don't lose it now! \nOH NO! Some aliens are blocking the portal, quick! Do something! ");
        Room uranus9 = new Room("Uranus", """
                Uranus — seventh planet from the Sun — rotates at a nearly 90-degree angle from the plane of its orbit.
                This unique tilt makes Uranus appear to spin on its side.""",
                "BRRR! Not exactly Utopia, is it?");
        Room theSun10 = new Room("The Sun", """
                The Sun is a yellow dwarf star, a hot ball of glowing gases at the heart of our solar system.
                Its gravity holds everything from the biggest planets to tiny debris in its orbit.""",
                "You're burning up, I can't help you");

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
        mars8.createConnectionSouth(saturn2);

        //Definer start-, slut- og gameover-rum
        this.currentRoom = theEarth1;
        this.finalRoom = pluto5;
        this.gameOverRoom = theSun10;

        this.workingRadio = new Item("working radio", "you can call back to headquarters, now you have added batteries");

//Elementer og deres beskrivelse.
        Item water = new Item("water", "a very important substance");
        Item lithium = new Item("lithium", "a highly flammable element, which can be used to start a fire");
        Item laserGun = new Item("laser gun", "a friendly entity has offered you this laser gun");
        Item molbydenum = new Item("molybdenum", "honestly, at this point we're just making stuff up");
        Item neptunium = new Item("neptunium", "an element that can be found on Neptune");
        Item uran = new Item("uran", "an element that can be found on Uranus");
        Item boron = new Item("boron", "an element that has been studied and experimented with, as a potential ingredient in organic medicine\n" +
                "-sounds like something worth having in your inventory");
        Item plutonium = new Item("plutonium", "found on Pluto. Plutonium is the element with the highest atomic number to occur in nature");
        Item batteries = new Item("batteries", "just some batteries, I wonder how they got on this planet", workingRadio);
        Item nothing = new Item("nothing", "(comes from nothing)");
        Item cat = new Item("cat", "a four-legged creature far far away from home");
        Item book = new Item("book", "richly illustrated, this volume contains information on all the planets in the Solar System");
        Item tea = new Item("tea", "an oddly satisfying warm drink");

        //fordel de forskellige items på rum
        theEarth1.putItemInRoom(water);
        theEarth1.putItemInRoom(tea);
        mercury6.putItemInRoom(lithium);
        mars8.putItemInRoom(boron);
        jupiter4.putItemInRoom(laserGun);
        neptune3.putItemInRoom(neptunium);
        uranus9.putItemInRoom(uran);
        neptune3.putItemInRoom(molbydenum);
        venus7.putItemInRoom(cat);
        pluto5.putItemInRoom(plutonium);
        saturn2.putItemInRoom(nothing);
        jupiter4.putItemInRoom(book);

        //Items der skal være i spillerens inventory før spillet kan slutte.
        this.finalItem = workingRadio;
        this.secondFinalItem = boron;

        //put batteries in random room
        //batterierne skal aldring ligge på solen ellers saturn, derfor er de ikke med i denne ligning
        Random random = new Random();
        int randomRoom = random.nextInt(8) + 1;
        if (randomRoom == 1) {
            theEarth1.putItemInRoom(batteries);
        } else if (randomRoom == 2) {
            neptune3.putItemInRoom(batteries);
        } else if (randomRoom == 3) {
            jupiter4.putItemInRoom(batteries);
        } else if (randomRoom == 4) {
            pluto5.putItemInRoom(batteries);
        } else if (randomRoom == 5) {
            mercury6.putItemInRoom(batteries);
        } else if (randomRoom == 6) {
            venus7.putItemInRoom(batteries);
        } else if (randomRoom == 7) {
            mars8.putItemInRoom(batteries);
        } else if (randomRoom == 8) {
            uranus9.putItemInRoom(batteries);
        }
    }

    public Room getGameOverRoom(){
        return gameOverRoom;
    }

    public Room getFinalRoom() {
        return finalRoom;
    }

    public Item getSecondFinalItem() {
        return secondFinalItem;
    }

    public Item getFinalItem() {
        return finalItem;
    }

    public Item getWorkingRadio() {
        return workingRadio;
    }

}

