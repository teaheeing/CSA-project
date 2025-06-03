public class Alien extends Sprite {
    private int hp;
    private int speed;
    private int level;

    public Alien(int x, int y, int level) {
        super(x, y, 5, 5, "alien.png");
        this.hp = 1 + level / 2;
        this.speed = 1 + level;
        this.level = level;
    }

    public int getHp() {
        return hp;
    }
    public void updateHp(int hp) {
        this.hp = hp;
    }
    public int getSpeed() {
        return speed;
    }
    public void updateY(int y) {
        super.updateY(y);
    }
}
