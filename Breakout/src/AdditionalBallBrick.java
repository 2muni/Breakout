import javax.swing.ImageIcon;

public class AdditionalBallBrick extends Sprite {
	
    private boolean destroyed;
    
    public AdditionalBallBrick(int x, int y) {
        
        this.x = x;
        this.y = y;

        ImageIcon ii = new ImageIcon(getClass().getClassLoader().getResource("addBall.png"));
        image = ii.getImage();
	    
        i_width = image.getWidth(null);
        i_heigth = image.getHeight(null);
        
        destroyed = false;
    }
    
    public boolean isDestroyed() {
        
    	return destroyed;
    }
    public void setDestroyed(boolean destroyed) {
        
        this.destroyed = destroyed;
    }
}
