
import java.awt.Image;
import java.awt.Rectangle;

public class Sprite {

	//�ﰢ�� ���� ��ġ����� ����ϱ� ���� ��ǥ�� �ڷ����� double�� ����
	//�ٸ� �޼��忡�� ����ϱ� ���� getter�� ���, ��ǥ���� int������ ����Ѵ�.
    protected double x;
    protected double y;
    protected int i_width;
    protected int i_heigth;
    protected Image image;

    public void setX(double x) {
        this.x = x;
    }

    public int getX() {
        return (int)x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getY() {
        return (int)y;
    }

    public int getWidth() {
        return i_width;
    }

    public int getHeight() {
        return i_heigth;
    }

    Image getImage() {
        return image;
    }

    Rectangle getRect() {
        return new Rectangle((int)x, (int)y,
                image.getWidth(null), image.getHeight(null));
    }
}
