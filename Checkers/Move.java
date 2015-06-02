public class Move{
    private int x,y,newX,newY;
    public Move(int x, int y, int newX, int newY){
        this.x=x; this.y=y; this.newX = newX; this.newY = newY;
    }

    public int getX(){return x;}
    public int getY(){ return y;}
    public int getNewX(){return newX;}
    public int getNewY(){return newY;}
}