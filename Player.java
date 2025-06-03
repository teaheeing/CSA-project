public class Player extends Sprite{
  int hp = 10;
      
    public Player(int xPosition, int yPosition){
        super(xPosition, yPosition, 40, "soldier.png");
    }

    public int getHp() {
      return hp;
    }

}
