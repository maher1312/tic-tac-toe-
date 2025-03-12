public abstract class Cell {
    int x;
    int y;  

    public Cell(){
        this.x = 0;
        this.y = 0;

    }

    public Cell(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public boolean equals(Cell cell){
        return this.x == cell.getX() && this.y == cell.getY();
    }
public boolean sameClassType(Cell cell){
    return this.getClass() == (cell.getClass());
}
    public boolean isAdjacent(Cell cell){
        return Math.abs(this.x - cell.getX()) <= 1 && Math.abs(this.y - cell.getY()) <= 1;
    }

    public boolean cellIsEmpty(Cell cell){
        return cell instanceof Empty_cell;
    }
}

