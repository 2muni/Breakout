import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Ball extends Sprite{
	
    private Double dirX;
    private Double dirY;
	private boolean inBoard;
	
	public Ball() {
		
		dirX = .0;
		dirY = .0;
		inBoard = false;
		
        ImageIcon ii = new ImageIcon(getClass().getClassLoader().getResource("ball.png"));
        
        image = ii.getImage();

        i_width = image.getWidth(null);
        i_heigth = image.getHeight(null);
        
        x = Properties.INIT_BALL_X;
        y = Properties.INIT_BALL_Y;
    }
	
	public void setinBoard(boolean inBoard) {		this.inBoard = inBoard;	}
	public boolean inBoard() {		return inBoard;	}
	public void setDirX(Double dirX) {		this.dirX = dirX;	}
	public void setDirY(Double dirY) {		this.dirY = dirY;	}
	public double getDirX() {		return dirX;	}
	public double getDirY() {		return dirY;	}
	public void setReDirX() {        dirX = dirX*-1.0;    }
    public void setReDirY() {        dirY = dirY*-1.0;    }
    
    @Override
    Rectangle getRect() {
        return new Rectangle((int)x-image.getWidth(null)/2, (int)y-image.getWidth(null)/2,
                image.getWidth(null), image.getHeight(null));
    } 
    
	public void move(CollisionModel collisionM) {
        if(inBoard) {
        	if(dirY == 0) {
        		dirY = 0.1;
        	}
        	x += dirX;
        	y += dirY;
        	
        	if (x < 0 || x > Properties.WIDTH) {
        		setReDirX();        
        	}
        	if (y < 0) {
        		setReDirY();
        	}
			collisionM.checkCollision(this);
        }
	}
}
