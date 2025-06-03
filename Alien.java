public class Alien extends Sprite{
    
    int width = 10;
    int height = 10;
    int hp = 10;
    public Alien(int xPosition, int yPosition){
        super(xPosition, yPosition, 40, "alien.png");
    }
    public int getHp() {
      return hp;
    }
    public void updateHp(int hp) {
      this.hp = hp;
    }
    public int getWidth() {
      return width;
    }
    public int getHeight() {
      return width;
    }

    public boolean checkHit(int bulletX, int bulletY){
        if (Math.abs(bulletX - super.getX()) < 30 && Math.abs(bulletY - super.getY()) < 30){
            return true;
        }
        return false;
    }
}
