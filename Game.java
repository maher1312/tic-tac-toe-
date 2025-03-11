import java.util.Scanner;
public class Game {
    String input;
    Map map;
    User user1 = new Human_user("User1");
    User user2 = new Human_user("User2");
    User ai = new Computer_user();
    //User turn ;
    public String gameState;
    public String turn ;
    public int nmbOfMoves;
    public String input_true;




    public Game(Map map) {
        map = new Map();
        gameState= "playing";
        turn = user1.getName() ;
        nmbOfMoves=0;
       this.map=map;
       
    }

    public void setCellToX(String input){
        
        switch(input)
        {
            case "A1":
                map.setCell(new X_cell(0,0));
                nmbOfMoves++;
                break;
            case "A2":
                map.setCell(new X_cell(1,0));
                nmbOfMoves++;
                break;
            case "A3":
                map.setCell(new X_cell(2,0));
                nmbOfMoves++;
                break;
            case "B1":
                map.setCell(new X_cell(0,1));
                nmbOfMoves++;
                break;  
            case "B2":
                map.setCell(new X_cell(1,1));
                nmbOfMoves++;
                break;  
            case "B3":
                map.setCell(new X_cell(2,1));
                nmbOfMoves++;
                break;
            case "C1":  
                map.setCell(new X_cell(0,2));
                nmbOfMoves++;
                break;
            case "C2":
                map.setCell(new X_cell(1,2));
                nmbOfMoves++;
                break;
            case "C3":
                map.setCell(new X_cell(2,2));
                nmbOfMoves++;
                break;
    } 
 
    
}

public void setCellToO(String input){
    
        switch(input)
        {
            case "A1":
                map.setCell(new O_cell(0,0));
                nmbOfMoves++;
                break;
            case "A2":
                map.setCell(new O_cell(1,0));
                nmbOfMoves++;
                break;
            case "A3":
                map.setCell(new O_cell(2,0));
                nmbOfMoves++;
                break;
            case "B1":
                map.setCell(new O_cell(0,1));
                nmbOfMoves++;
                break;  
            case "B2":
                map.setCell(new O_cell(1,1));
                nmbOfMoves++;
                break;  
            case "B3":
                map.setCell(new O_cell(2,1));
                nmbOfMoves++;
                break;
            case "C1":  
                map.setCell(new O_cell(0,2));
                nmbOfMoves++;
                break;
            case "C2":
                map.setCell(new O_cell(1,2));
                nmbOfMoves++;
                break;
            case "C3":
                map.setCell(new O_cell(2,2));
                nmbOfMoves++;
                break;
        }
    
}

    public boolean isFeasable(String input){
        switch(input)
        {
            case "A1":
                if(map.getCell(0,0) instanceof Empty_cell)
                    return true;
                break;
            case "A2":
                if(map.getCell(1,0) instanceof Empty_cell)
                    return true;
                break;
            case "A3":
                if(map.getCell(2,0) instanceof Empty_cell)
                    return true;
                break;
            case "B1":
                if(map.getCell(0,1) instanceof Empty_cell)
                    return true;
                break;  
            case "B2":
                if(map.getCell(1,1) instanceof Empty_cell)
                    return true;
                break;  
            case "B3":
                if(map.getCell(2,1) instanceof Empty_cell)
                    return true;
                break;
            case "C1":  
                if(map.getCell(0,2) instanceof Empty_cell)
                    return true;
                break;
            case "C2":
                if(map.getCell(1,2) instanceof Empty_cell)
                    return true;
                break;
            case "C3":
                if(map.getCell(2,2) instanceof Empty_cell)
                    return true;
                break;
        }
        return false;
    } 

    public void flipTurn(String turn){
        if (turn == user1.getName()){
            this.turn = user2.getName();
        }
        else{
            this.turn = user1.getName();
        }

       
    }
    public void flipTurnAi(String turn){
        if (turn == user1.getName()){
            this.turn = ai.getName();
        }
        else{
            this.turn = user1.getName();
        }
        
        }
    
        public void autoGenerateO() {
            // First, check if O can win in the next move
            if (tryToWinOrBlock('O')) {
                nmbOfMoves++;
                return;
            }
        
            // Then, check if X can win in the next move and block them
            if (tryToWinOrBlock('X')) {
                nmbOfMoves++;
                return;
            }
        
            // If no immediate winning or blocking moves, place O in the best available position
            // Priority: Center > Corners > Edges
            if (map.getCell(1, 1) instanceof Empty_cell) { // Center
                map.setCell(new O_cell(1, 1));
                nmbOfMoves++;
                return;
            }
        
            // Check corners
            int[][] corners = {{0, 0}, {0, 2}, {2, 0}, {2, 2}};
            for (int[] corner : corners) {
                if (map.getCell(corner[0], corner[1]) instanceof Empty_cell) {
                    map.setCell(new O_cell(corner[0], corner[1]));
                    nmbOfMoves++;
                    return;
                }
            }
        
            // Check edges
            int[][] edges = {{0, 1}, {1, 0}, {1, 2}, {2, 1}};
            for (int[] edge : edges) {
                if (map.getCell(edge[0], edge[1]) instanceof Empty_cell) {
                    map.setCell(new O_cell(edge[0], edge[1]));
                    nmbOfMoves++;
                    return;
                }
            }
        
            // If no moves are available (should not happen in a valid game), do nothing
        }
    
        private boolean tryToWinOrBlock(char player) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (map.getCell(i, j) instanceof Empty_cell) {
                        // Simulate placing the player's symbol 
                        if (player == 'O') {
                            map.setCell(new O_cell(i, j));
                        } else {
                            map.setCell(new X_cell(i, j));
                        }
        
                        // Check if this move results in a win
                        if (checkWin(player)) {
                            if (player == 'X') {
                                // If X was about to win, block by placing O
                                map.setCell(new O_cell(i, j));
                            }
                            return true; // Move was made
                        }
        
                        // Undo the move
                        map.setCell(new Empty_cell(i, j));
                    }
                }
            }
            return false; // No winning or blocking move found
        }
    
        private boolean checkWin(char player) {
            Class<?> cellClass = (player == 'O') ? O_cell.class : X_cell.class;
            return (map.getCell(0, 0).getClass() == cellClass && map.getCell(1, 0).getClass() == cellClass && map.getCell(2, 0).getClass() == cellClass) ||
                   (map.getCell(0, 1).getClass() == cellClass && map.getCell(1, 1).getClass() == cellClass && map.getCell(2, 1).getClass() == cellClass) ||
                   (map.getCell(0, 2).getClass() == cellClass && map.getCell(1, 2).getClass() == cellClass && map.getCell(2, 2).getClass() == cellClass) ||
                   (map.getCell(0, 0).getClass() == cellClass && map.getCell(0, 1).getClass() == cellClass && map.getCell(0, 2).getClass() == cellClass) ||
                   (map.getCell(1, 0).getClass() == cellClass && map.getCell(1, 1).getClass() == cellClass && map.getCell(1, 2).getClass() == cellClass) ||
                   (map.getCell(2, 0).getClass() == cellClass && map.getCell(2, 1).getClass() == cellClass && map.getCell(2, 2).getClass() == cellClass) ||
                   (map.getCell(0, 0).getClass() == cellClass && map.getCell(1, 1).getClass() == cellClass && map.getCell(2, 2).getClass() == cellClass) ||
                   (map.getCell(0, 2).getClass() == cellClass && map.getCell(1, 1).getClass() == cellClass && map.getCell(2, 0).getClass() == cellClass);
        }
        public void autoGenerateO_Easy(){
            int x, y;
            do {
                x = (int)(Math.random()*3);
                y = (int)(Math.random()*3);
            } while ( x >= map.getx() || y >= map.gety() || !(map.getCell(x,y) instanceof Empty_cell));
            map.setCell(new O_cell(x,y));
            nmbOfMoves++;
        }
    
    public void play1vAI_Hard(){
        while (gameState=="playing"){
            map.toString();
            handleInput();
            if (turn == user1.getName()){
                setCellToX(input);
                XWIns();
                flipTurnAi(turn);
            }
            else{
                
                autoGenerateO();
                OWIns();
                flipTurnAi(turn);
            }
            tie();
        }
        if (gameState=="x wins"){
            map.toString();
            System.out.println("X wins");
        }
        else if (gameState=="o wins"){
            map.toString();
            System.out.println("O wins");
        }
        else if (gameState=="tie"){
            map.toString();
            System.out.println("Tie");
        }
}
public void play1vAI_Easy(){
    while (gameState=="playing"){
        map.toString();
        handleInput();
        if (turn == user1.getName()){
            setCellToX(input);
            XWIns();
            flipTurnAi(turn);
        }
        else{
            
            autoGenerateO_Easy();
            OWIns();
            flipTurnAi(turn);
        }
        tie();
    }
    if (gameState=="x wins"){
        map.toString();
        System.out.println("X wins");
    }
    else if (gameState=="o wins"){
        map.toString();
        System.out.println("O wins");
    }
    else if (gameState=="tie"){
        map.toString();
        System.out.println("Tie");
    }
}


    public void play1v1(){
        gameState="playing";
        while (gameState=="playing"){
            
            map.toString();
            handleInput();
            if (turn == user1.getName()){
                setCellToX(input);
                XWIns();
                flipTurn(turn);
            }
            else{
                setCellToO(input);
                OWIns();
                flipTurn(turn);
            }
            tie();
        }
        
        if (gameState=="x wins"){
            map.toString();
            System.out.println("X wins");
        }
        else if (gameState=="o wins"){
            map.toString();
            System.out.println("O wins");
        }
        else if (gameState=="tie"){
            map.toString();
            System.out.println("Tie");
        }
    }

    
    public void handleInput() {
       if( turn == ai.getName())
       { System.out.println("AI's turn");}
       else{

        do {
        
            input = " ";
            Scanner scanner = new Scanner(System.in);
            if (turn == user1.getName()){
                System.out.println("X's turn");
            }
            else{
                System.out.println("O's turn");
            }
            System.out.print("Enter box index (eg:A1, A2, B1...): ");
            if (scanner.hasNext()) {
               String input_tmp;
               
               input_tmp = scanner.next().substring(0, 2).toUpperCase();
              if (isFeasable(input_tmp)){
                input = input_tmp;
                System.out.println(input); 
              }else {
                System.out.println("Invalid input please select an empty cell");
                handleInput();
              }

            } else {
                scanner.close();
                System.out.println("No input provided!");
            }
        } while (!input.equals(Input.A1) && !input.equals(Input.A2) && !input.equals(Input.A3) && !input.equals(Input.B1) && !input.equals(Input.B2) && !input.equals(Input.B3) && !input.equals(Input.C1) && !input.equals(Input.C2) && !input.equals(Input.C3));
           
    }
    }        

    public void XWIns() {
        if (map.getCell(0,0) instanceof X_cell && map.getCell(1,0) instanceof X_cell && map.getCell(2,0) instanceof X_cell){
            gameState="x wins";
            
        }
        else if (map.getCell(0,1) instanceof X_cell && map.getCell(1,1) instanceof X_cell && map.getCell(2,1) instanceof X_cell){
            gameState="x wins";
        }
        else if (map.getCell(0,2) instanceof X_cell && map.getCell(1,2) instanceof X_cell && map.getCell(2,2) instanceof X_cell){
            gameState="x wins";
        }
        else if (map.getCell(0,0) instanceof X_cell && map.getCell(0,1) instanceof X_cell && map.getCell(0,2) instanceof X_cell){
            gameState="x wins";
        }
        else if (map.getCell(1,0) instanceof X_cell && map.getCell(1,1) instanceof X_cell && map.getCell(1,2) instanceof X_cell){
            gameState="x wins";
        }
        else if (map.getCell(2,0) instanceof X_cell && map.getCell(2,1) instanceof X_cell && map.getCell(2,2) instanceof X_cell){
            gameState="x wins";
        }
        else if (map.getCell(0,0) instanceof X_cell && map.getCell(1,1) instanceof X_cell && map.getCell(2,2) instanceof X_cell){
            gameState="x wins";
        }
        else if (map.getCell(0,2) instanceof X_cell && map.getCell(1,1) instanceof X_cell && map.getCell(2,0) instanceof X_cell){
            gameState="x wins";
        }
        // No action needed here
    }

    public void OWIns() {
        if (map.getCell(0,0) instanceof O_cell && map.getCell(1,0) instanceof O_cell && map.getCell(2,0) instanceof O_cell){
            gameState="o wins";
        }
        else if (map.getCell(0,1) instanceof O_cell && map.getCell(1,1) instanceof O_cell && map.getCell(2,1) instanceof O_cell){
            gameState="o wins";
        }
        else if (map.getCell(0,2) instanceof O_cell && map.getCell(1,2) instanceof O_cell && map.getCell(2,2) instanceof O_cell){
            gameState="o wins";
        }
        else if (map.getCell(0,0) instanceof O_cell && map.getCell(0,1) instanceof O_cell && map.getCell(0,2) instanceof O_cell){
            gameState="o wins";
        }
        else if (map.getCell(1,0) instanceof O_cell && map.getCell(1,1) instanceof O_cell && map.getCell(1,2) instanceof O_cell){
            gameState="o wins";
        }
        else if (map.getCell(2,0) instanceof O_cell && map.getCell(2,1) instanceof O_cell && map.getCell(2,2) instanceof O_cell){
            gameState="o wins";
        }
        else if (map.getCell(0,0) instanceof O_cell && map.getCell(1,1) instanceof O_cell && map.getCell(2,2) instanceof O_cell){
            gameState="o wins";
        }
        else if (map.getCell(0,2) instanceof O_cell && map.getCell(1,1) instanceof O_cell && map.getCell(2,0) instanceof O_cell){
            gameState="o wins";
        }
        // No action needed here
    }

    public void tie(){
        if (nmbOfMoves==9 && gameState!="x wins" && gameState!="o wins"){
            gameState="tie";
        }
    }

   
}