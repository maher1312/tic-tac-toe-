public class Map {
   public final int x=3;
    public final int y=3;
    public Cell[][] grid;
    
        
    
    public Map(){
        
        this.grid = new Cell[x][y];
        for(int i = 0; i < x; i++){
            for(int j = 0; j < y; j++){
                this.grid[i][j] = new Empty_cell(i,j);
            }
        }
    }

    public void setCell(Cell cell){
        this.grid[cell.getX()][cell.getY()] = cell;
    }

    public Cell getCell(int x, int y){
        return this.grid[x][y];
    }
    @Override
    public String toString(){
        System.out.println("   1    2    3 ");
        for (int i = 0; i<x; i++){
            if(i==0){
                System.out.println(" +---++---++---+");
                System.out.print("A");

                for(int j = 0; j<y;j++){
                    
                    System.out.print("| "+grid[j][i]+" |");
                }
               
                System.out.println();
            }
            if(i==1){
                System.out.println(" +---++---++---+");
                System.out.print("B");
                for(int j = 0; j<y;j++){
                   
                    System.out.print("| "+grid[j][i]+" |");
                }
                
                System.out.println();
            }
            if(i==2){
                System.out.println(" +---++---++---+");
                System.out.print("C");
                for(int j = 0; j<y;j++){
                    System.out.print("| "+grid[j][i]+" |");
                }
                
                System.out.println();
                System.out.println(" +---++---++---+");
            }
        }
        return "";
    }

    public int getx() {
        return x;
    }

    public int gety() {
        return y;
    }

    
}
