public class Game {

    private Room currentRoom;
    private Parser parser;
    private Player player;


    public Game() {
        parser = new Parser();
        player = new Player();
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.createRooms();
        game.play();
    }

    private void createRooms() {
        Room Hallway = new Room("A long dark hallway with a scary sound and four old doors. ", "There are four "+
                "doors the one on north is for kitchen, the one on the south is the bedroom,and the one the the west is for the garden "+
                ",and the last the door on the east is for the basement ");
        Room kitchen = new Room("A kitchen a little messy. a pot is cooking something but nobody was in side.some food and snacks are out side",
                "The food in the pot is already burnt and the smoke are every where. maybe you should shut the fire and save the kitchen.");
        Room bedroom = new Room("you are at the bed room everything looks new.", "A double bed that looks very comfortable. There is a"+"" +
                " group photo of a couple on the wall, but I can't see their faces clearly. The bed and everything in the room look very new. Maybe you can sleep in bed to recover your energy.");
        Room garden = new Room("Garden with a dog seems gauard something", "A garden full of grass. A dog is in the garden Behind the dog, there seems to be a door to the outside." +
                "But the dog looks very strong. You need to have enough strength and at least one weapon to defeat it.");
        Room basement = new Room("A messy basement with a lot of boxes", "It looks messy. Several large boxes were placed in the center. It is "+
                "not clear what is in the box. There is a crowbar beside it that looks like it can be used to open a box or as a weapon.\n");

        Hallway.setExit("north", kitchen);
        Hallway.setExit("south", bedroom);
        Hallway.setExit("east", garden);
        Hallway.setExit("west", basement);
        basement.getItem(Crowbar);
        // ... continued

        garden.setExit("west", Hallway);

        Item Crowbar = new Item();
        Item obj2 = new Item();

        player.setItem("one", Crowbar);
        Hallway.setItem("two", obj2);

        currentRoom = Hallway;
    }

    public void play() {
        printWelcome();

        boolean finished = false;

        while(!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thanks for playing!");
    }

    private boolean processCommand(Command command) {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch(commandWord) {
            case UNKNOWN:
                System.out.println("I don't know what you mean");
                break;

            case HELP:
                printHelp();
                break;

            case GO:
                goRoom(command);
                break;

            case QUIT:
                wantToQuit = quit(command);
                break;

            case LOOK:
                look(command);
                break;

            case DROP:
                break;

            case GRAB:
                break;
        }

        return wantToQuit;
    }

    private void printHelp() {
        System.out.println("You are lost.  You are alone.  You wander");
        System.out.println("You are in a garden maze");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    private void look(Command command) {
        if(command.hasSecondWord()) {
            System.out.println("You can't look at " + command.getSecondWord());
            return;
        }

        System.out.println(currentRoom.getLongDescription());
        System.out.println(player.getItemString());
    }

    private void goRoom(Command command)
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getShortDescription());
        }
    }

    private boolean quit(Command command) {
        if(command.hasSecondWord()) {
            System.out.println("You can't quit " + command.getSecondWord());
            return false;
        }
        else {
            return true;
        }
    }

    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to my text adventure game!");
        System.out.println("You will find yourself in a garden maze, desperate to escape!");
        System.out.println("Type \"help\" if you need assistance");
        System.out.println();
        System.out.println("we will print a long room description here");
    }


}