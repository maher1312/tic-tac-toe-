import java.util.Scanner;
public class driver {
    
    public static void main(String[] args) {
        Map map= new Map();
        Game game =new Game(map);
        System.out.println("Welcome to Tic Tac Toe!");
        System.out.println("do you want to play 1v1 or 1vAI?");
        System.out.println("1. 1v1");
        System.out.println("2. 1vAI");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        if(choice == 1){
            game.play1v1();
        }
        else if(choice == 2){
            game.play1vAI();
        }
        else{
            System.out.println("Invalid choice");
        }
        
       
    }
    
}
