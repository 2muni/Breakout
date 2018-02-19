
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class GameBoard extends JPanel{

	Font font;
	Graphics g;
	
	GameControl control;
	
	ArrayList<Ball> balls;
	ArrayList<Brick> bricks;
	ArrayList<AdditionalBallBrick> ballBricks;

	Runnable setBallDirection;
	Thread directionThread;
	Runnable objectsRefresh;
	Thread refreshThread;

	GameBoard(){
		
		setBounds(5,60, Properties.WIDTH, Properties.HEIGHT);
		
		font = new Font("Dialog", Font.BOLD, 24);
		setFont(font);

		control = new GameControl(this);

		setFocusable(true);
		
		addMouseMotionListener(control);
		addMouseListener(control);
		
		
		balls = control.ballM.balls;
		bricks = control.brickM.bricks;
		ballBricks = control.brickM.ballBricks;
		

		//�ʱ� ������ ����
		setBallDirection = new SetBallDirection();
		directionThread = new Thread(setBallDirection);
		directionThread.start();
		
		objectsRefresh = new ObjectsRefresh();
		refreshThread = new Thread(objectsRefresh);
		refreshThread.start();
	}
	
	private class ObjectsRefresh implements Runnable {
		@Override
		public void run() {
			try {
				while(true) {
					Thread.sleep(5);
					//ȭ�� �� �����ִ� ���� ���� ������ �۵�
						if(control.remainedNumberOfBall()==0){
							control.objectsRefresh();
							control.checkGameover();
						}
						repaint();
					
				} 
			}catch (InterruptedException ie) {}
		}
	}
	
	private class SetBallDirection implements Runnable {
		@Override
		public void run() {
			try {
				while(true) {
					Thread.sleep(5);
					//���콺 Ŭ���� �������� ���� �۵�
					if(control.startBallDirectionSetting()) {
						for(int i = 0; i < balls.size(); i++) {
							control.setBallDirection(i);
							Thread.sleep(40);
						}
					}
				}
			} catch (InterruptedException ie) {}
		}
	}
	
	//������Ʈ ���
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(control.isGameover()) {
			gameFinished(g);
			directionThread.interrupt();
			refreshThread.interrupt();
			
		}else {
			drawObjects(g);
		}
    }	
	
	private void gameFinished(Graphics g) {
		g.drawString("GAME OVER",80,140);
	}

	private void drawObjects(Graphics g) {
		//�ʱ� �� �˵� ���
		if(!control.existenceOfBall()) {
			g.drawLine(balls.get(balls.size()-1).getX(), balls.get(balls.size()-1).getY(),
					control.getEndX(), control.getEndY());
		}
		//�� �̹��� ���
		for(int i = 0; i < balls.size(); i++) {
			if(balls.get(i).inBoard()) {
				g.drawImage(balls.get(i).getImage(), 
						balls.get(i).getX()-(balls.get(i).getWidth()/2), 
						balls.get(i).getY()-(balls.get(i).getHeight()/2),
						balls.get(i).getWidth(), balls.get(i).getHeight(), this);
			}
		}
		//�߰� �� �̹��� ���
		for (int i = 0; i < ballBricks.size(); i++) {
			if(!ballBricks.get(i).isDestroyed()) {
				g.drawImage(ballBricks.get(i).getImage(),
						ballBricks.get(i).getX(), ballBricks.get(i).getY(),
						ballBricks.get(i).getWidth(), ballBricks.get(i).getHeight(), this);
			}
		}
		//���� �̹��� ���
		for (int i = 0; i < bricks.size(); i++) {
			if (!bricks.get(i).isDestroyed()) {
                g.drawImage(bricks.get(i).getImage(), bricks.get(i).getX(),
                		bricks.get(i).getY(), bricks.get(i).getWidth(),
                		bricks.get(i).getHeight(), this);                
                //breakCnt ���ڿ� ���
                if(bricks.get(i).getBreakCnt()<10) {
                	g.drawString(Integer.toString(bricks.get(i).getBreakCnt()),
                			bricks.get(i).getX()+18, 
                			bricks.get(i).getY()+bricks.get(i).getHeight()-5);
                }else if(bricks.get(i).getBreakCnt()<100) {
                	g.drawString(Integer.toString(bricks.get(i).getBreakCnt()),
                			bricks.get(i).getX()+12, 
                			bricks.get(i).getY()+bricks.get(i).getHeight()-5);
                }else {
                	g.drawString(Integer.toString(bricks.get(i).getBreakCnt()),
                			bricks.get(i).getX()+6, 
                			bricks.get(i).getY()+bricks.get(i).getHeight()-5);
                }
			}       
        }
	}
}