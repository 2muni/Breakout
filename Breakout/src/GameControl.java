
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GameControl extends MouseAdapter{
	GameBoard board;

	BallModel ballM;
	BrickModel brickM;
	CollisionModel collisionM;

	Runnable moveBall;
	ArrayList<Thread> ballThreads;
	
	private boolean gameover;
	private boolean existenceOfBall;
	private boolean startBallDirectionSetting;
	private int endX, endY;
	private int dirX, dirY;
	private final int PERIOD;

	GameControl(GameBoard board){
		
		PERIOD = 3;
		
		this.board = board;
		
		existenceOfBall = false;
		startBallDirectionSetting = false;

		ballThreads = new ArrayList<Thread>();
		//�� �ʱ�ȭ
		ballM = new BallModel();
		brickM = new BrickModel();
		collisionM = new CollisionModel(ballM, brickM);
		
		addBallThread();
	}
	
	/*----------------------------------- getters -----------------------------------*/
	
	public boolean isGameover() {		return gameover;	}
	public boolean existenceOfBall() {		return existenceOfBall;	}
	public boolean startBallDirectionSetting() {		return startBallDirectionSetting;	}
	
	public int remainedNumberOfBall() {		return ballM.getRemainedNumberOfBall();	}
	
	public int getEndX() {		return endX;	}
	public int getEndY() {		return endY;	}
	
	/*--------------------------------------------------------------------------------*/
	
	//���� ���� ������ �ʱⰪ����, �߰��� ������ ���� ������ŭ �����带 �迭�� �߰��ϰ� ����
	public void addBallThread() {
		for(int i = ballThreads.size(); i < ballM.getNumberOfBall(); i++) {
			moveBall = new MoveBall(i);
			ballThreads.add(new Thread(moveBall));
			ballThreads.get(i).start();
		} 
	}
	
    private class MoveBall implements Runnable {
    	int index;
    	MoveBall(int index){
    		this.index = index;
    	}
    	@Override
		public void run() {
			try {
				while(true) {
					Thread.sleep(PERIOD);
					ballM.balls.get(index).move(collisionM);
    			}	    			
    		}catch(InterruptedException e) {}
		}
    }
    
	//���콺 Ŭ�� ��, ���� �̵� ���⿡ ���� �ӵ� ����
	public void setBallDirection(int index) {
		if(startBallDirectionSetting) {
			startBallDirectionSetting = false;
			existenceOfBall = true;
		}
		ballM.balls.get(index).setinBoard(true);
		ballM.setDirBall(index, dirX, dirY);				
	}
	
	public void checkGameover() {
		for(int i = 0; i < brickM.bricks.size()-1; i++) {
			if(brickM.bricks.get(i).getY()>Properties.HEIGHT - 60) {
				gameover = true;
				break;
			}
		}
	}
	
	
	//��� ���� �̵��� ������, ������Ʈ���� �缳��
	public void objectsRefresh() {
		
		existenceOfBall = false;
		
		brickM.resetBricks();
		brickM.deleteBricks();
		brickM.setBricks();
		
		
		ballM.setBalls(brickM.getNumberOfAddedBall());
		ballM.resetBalls(collisionM.getLastBallX(), collisionM.getLastBallY());
		addBallThread();

		ballM.setRemainedNumberOfBall(ballM.getNumberOfBall());
		brickM.setNumberOfAddedBallToZero();
	}
	
	//MouseListener
	@Override
	public void mouseClicked(MouseEvent e) {
		if(!existenceOfBall) {
			dirX = e.getX();
			dirY = e.getY();
			startBallDirectionSetting = true;
		}
	}
	
	//MouseMotionListener
	@Override
	public void mouseMoved(MouseEvent e) {
		endX = e.getX();
		endY = e.getY();
	}
}
