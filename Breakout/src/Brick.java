
import javax.swing.ImageIcon;


public class Brick extends Sprite {
	
    private boolean destroyed;
    private int breakCnt;
    
    public Brick(int x, int y, int breakCnt) {
        
        this.x = x;
        this.y = y;
        
        ImageIcon ii = new ImageIcon(getClass().getClassLoader().getResource("brick.png"));
        image = ii.getImage();

        i_width = image.getWidth(null);
        i_heigth = image.getHeight(null);
        
        this.breakCnt = breakCnt;
        destroyed = false;
    }
    
    public void setBreakCnt(int breakCnt) {
    	this.breakCnt = breakCnt;
    }
    
    public int getBreakCnt() {
    	return breakCnt;
    }

    public boolean isDestroyed() {
        
        return destroyed;
    }

    public void setDestroyed(boolean destroyed) {
        
        this.destroyed = destroyed;
    }
}
