
import java.awt.Image;
import java.awt.Rectangle;

public class Sprite {

	//삼각비에 따른 위치결과를 출력하기 위해 좌표의 자료형을 double로 설정
	//다른 메서드에서 사용하기 위한 getter의 경우, 좌표값을 int값으로 출력한다.
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
