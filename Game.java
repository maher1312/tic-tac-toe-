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
    public int[] getCoordinates(String input) { //get the coordinates of the input cell
        int x = -1, y = -1;
        switch (input) {
            case "A1":
                x = 0; y = 0;
                break;
            case "A2":
                x = 1; y = 0;
                break;
            case "A3":
                x = 2; y = 0;
                break;
            case "B1":
                x = 0; y = 1;
                break;
            case "B2":
                x = 1; y = 1;
                break;
            case "B3":
                x = 2; y = 1;
                break;
            case "C1":
                x = 0; y = 2;
                break;
            case "C2":
                x = 1; y = 2;
                break;
            case "C3":
                x = 2; y = 2;
                break;
        }
        return new int[]{x, y};
    }



    public void setCellOnMap(String input){//set the cell on the map X or O depending on the turn
        int x = getCoordinates(input)[0];
        int y = getCoordinates(input)[1];
        nmbOfMoves++;
        if (turn==user1.getName()){
           map.setCell(new X_cell(x,y));
        }
        else{
            if(turn==user2.getName()){
                map.setCell(new O_cell(x,y));
            }
            else if(turn==ai.getName()){
                map.setCell(new O_cell(x,y));
            }
        }
    }    


    public boolean isFeasable(String input){//check if the cell is empty or not
        int x = getCoordinates(input)[0];
        int y= getCoordinates(input)[1];
        return (map.getCell(x, y) instanceof Empty_cell);
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
    
        public void autoGenerateO() {// this method is for the hard level of the AI player 
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



        public void autoGenerateO_Easy(){//generate O randomly in the empty cell
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
           
            if (turn == user1.getName()){
                handleInput();
                setCellOnMap(input);
                win();
                flipTurnAi(turn);
            }
            else{
                
                autoGenerateO();
                win();
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
        
        if (turn == user1.getName()){
            handleInput();
            setCellOnMap(input);
            win();
            flipTurnAi(turn);
        }
        else{
            
            autoGenerateO_Easy();
            win();
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
                setCellOnMap(input);
                win();
                flipTurn(turn);
            }
            else{
                setCellOnMap(input);
               win();
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
        String input_tmp="XX";
        
boolean finish = false;
       if( turn == ai.getName())
       { System.out.println("AI's turn");}
       else{
            input = " ";
            if (turn == user1.getName()){
                System.out.println("X's turn");
            }
            else{
                System.out.println("O's turn");
            }
        }
            
        
        while (!finish){
            Scanner scanner = new Scanner(System.in);
        do {
                System.out.print("Enter box index (eg:A1, A2, B1...): ");
            if (scanner.hasNext()) {
               
                input_tmp = scanner.next().toUpperCase();
        } }while (!input_tmp.equals(Input.A1) && !input_tmp.equals(Input.A2) && !input_tmp.equals(Input.A3) && !input_tmp.equals(Input.B1) && !input_tmp.equals(Input.B2) && !input_tmp.equals(Input.B3) && !input_tmp.equals(Input.C1) && !input_tmp.equals(Input.C2) && !input_tmp.equals(Input.C3));
        if (isFeasable(input_tmp)){
            input = new String(input_tmp);
            System.out.println(input); 
            finish = true;
          }else {
           finish = false;
          }
        }
    
    }    

public void win(){
    if (map.getCell(0, 0).sameClassType(map.getCell(1, 0)) && map.getCell(1, 0).sameClassType(map.getCell(2, 0)) && !(map.getCell(2, 0) instanceof Empty_cell)) {
        gameState = map.getCell(0, 0) instanceof X_cell ? "x wins" : "o wins";
    } else if (map.getCell(0, 1).sameClassType(map.getCell(1, 1)) && map.getCell(1, 1).sameClassType(map.getCell(2, 1)) && !(map.getCell(2, 1) instanceof Empty_cell)) {
        gameState = map.getCell(0, 1) instanceof X_cell ? "x wins" : "o wins";
    } else if (map.getCell(0, 2).sameClassType(map.getCell(1, 2)) && map.getCell(1, 2).sameClassType(map.getCell(2, 2)) && !(map.getCell(2, 2) instanceof Empty_cell)) {
        gameState = map.getCell(0, 2) instanceof X_cell ? "x wins" : "o wins";
    } else if (map.getCell(0, 0).sameClassType(map.getCell(0, 1)) && map.getCell(0, 1).sameClassType(map.getCell(0, 2)) && !(map.getCell(0, 2) instanceof Empty_cell)) {
        gameState = map.getCell(0, 0) instanceof X_cell ? "x wins" : "o wins";
    } else if (map.getCell(1, 0).sameClassType(map.getCell(1, 1)) && map.getCell(1, 1).sameClassType(map.getCell(1, 2)) && !(map.getCell(1, 2) instanceof Empty_cell)) {
        gameState = map.getCell(1, 0) instanceof X_cell ? "x wins" : "o wins";
    } else if (map.getCell(2, 0).sameClassType(map.getCell(2, 1)) && map.getCell(2, 1).sameClassType(map.getCell(2, 2)) && !(map.getCell(2, 2) instanceof Empty_cell)) {
        gameState = map.getCell(2, 0) instanceof X_cell ? "x wins" : "o wins";
    } else if (map.getCell(0, 0).sameClassType(map.getCell(1, 1)) && map.getCell(1, 1).sameClassType(map.getCell(2, 2)) && !(map.getCell(2, 2) instanceof Empty_cell)) {
        gameState = map.getCell(0, 0) instanceof X_cell ? "x wins" : "o wins";
    } else if (map.getCell(0, 2).sameClassType(map.getCell(1, 1)) && map.getCell(1, 1).sameClassType(map.getCell(2, 0)) && !(map.getCell(2, 0) instanceof Empty_cell)) {
        gameState = map.getCell(0, 2) instanceof X_cell ? "x wins" : "o wins";
    }
        // Add your logic here
    }

    public void tie(){
        if (nmbOfMoves==9 && gameState!="x wins" && gameState!="o wins"){
            gameState="tie";
        }
    }

   
}