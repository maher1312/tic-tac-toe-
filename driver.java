import java.util.Scanner;
public class driver {
    
    public static void main(String[] args) {
        Map map= new Map();
        Game game =new Game(map);
        String playchoice = "";
        do{
        System.out.println("Welcome to Tic Tac Toe!");
        System.out.println("do you want to play 1v1 or 1vAI?");
        System.out.println("1. 1v1");
        System.out.println("2. 1vAI");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        
        if(choice == 1){
            game.play1v1();
            playchoice="valid";
        }
        else if(choice == 2){
            System.out.println("chooose the difficulty level");
            System.out.println("1. Easy");
            System.out.println("2. hard");
            int level = scanner.nextInt();
            if(level == 1){
                game.play1vAI_Easy();
                playchoice="valid";
            }
            else if(level == 2){
                game.play1vAI_Hard();
                playchoice="valid";
            }
            else{
                System.out.println("Invalid choice");
                playchoice="invalid";
            }
            
        }
        else{
            System.out.println("Invalid choice");
            playchoice="invalid";
        }
        
       
       } while(playchoice == "invalid");
    }
}

