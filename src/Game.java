public class Game {
    private Room currentRoom;

    public Game(){
    }

    public void creatRooms(){
        Room centerGarden= new Room("Short describe");
        Room northGarden= new Room("Short describe");
        Room southGarden= new Room("Short describe");
        Room eastGarden= new Room("Short describe");
        Room westGarden= new Room("Short describe");
    }

    public void play() {
        printWelcome();
        boolean finished = false;
        while(!finished){
        }
        System.out.println("Thanks for playing");
    }

    private void printWelcome(){
        System.out.println();
        System.out.println("welcome to my game");
        System.out.println("You will find your self in a garden");
        System.out.println("Type help if you need help");
        System.out.println();
        System.out.println();
    }
}


