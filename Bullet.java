public class Bullet extends Sprite {
    private int speed = 5; // 속도 조정 (너무 빠르면 충돌 판정이 안 됨)

    public Bullet(int x, int y) {
        super(x, y, 2, 5, "bullet.png");
    }

    public int getSpeed() { return speed; }
    public void updateY(int y) { super.updateY(y); }
}
