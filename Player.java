public class Player extends Sprite {
    private int hp = 10;

    public Player(int x, int y) {
        super(x, y, 5, 8, "soldier.png");
    }

    public int getHp() { return hp; }
    public void updateHp(int hp) { this.hp = hp; }
}
