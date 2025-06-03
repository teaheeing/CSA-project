public class Bullet extends Sprite{
   int width = 10;
   int height = 10;
    
    public Bullet(int xPosition, int yPosition){
        super(xPosition, yPosition, 20, "bullet.png");
    }

    public boolean hit(Player p){;
        //finish this code pls

        return false; //ignore this
    }
    public int getWidth() {
      return width;
    }
    public int getHeight() {
      return height;
    }

    @Override
    public void updateY(int dy) {
        setY(getY() + dy);
        System.out.println("Bullet:" + getY());

    }


}
