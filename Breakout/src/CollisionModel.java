import java.awt.Point;

public class CollisionModel {
	
	BallModel ballM;
	BrickModel brickM;
	
	Point pointLeft, pointRight, pointTop, pointBottom;
	
	private double lastBallX, lastBallY;
	
	CollisionModel(BallModel ballM, BrickModel brickM){
		this.ballM = ballM;
		this.brickM = brickM;
		brickM.setBricks();
		
	}
	public double getLastBallX() {		return lastBallX;	}
	public double getLastBallY() {		return lastBallY;	}
	
	public void setPoint(Ball ball, int accuracy) {
		pointLeft = new Point(ball.getX() - ball.getWidth()/2-accuracy,
				ball.getY());
		pointRight = new Point(ball.getX() + ball.getWidth()/2+accuracy,
				ball.getY());
		pointTop = new Point(ball.getX(),
				ball.getY() - ball.getHeight()/2-accuracy);
		pointBottom = new Point(ball.getX(),
				ball.getY() + ball.getHeight()/2+accuracy);
	}
	
	//공의 충돌 처리
	public void checkCollision(Ball ball) {

		//바닥에 닿았을 때
		if (ball.getY()> Properties.HEIGHT) {
			ball.setinBoard(false);
			ball.setDirX((double) 0);
			ball.setDirY((double) 0);
			ball.setY(Properties.INIT_BALL_Y);
			ballM.setRemainedNumberOfBall(ballM.getRemainedNumberOfBall()-1);
			lastBallX = ball.getX();
			lastBallY = ball.getY();
		}
		//추가공에 닿았을 때
		for (int j = 0; j < brickM.ballBricks.size(); j++) {
			
			if ((ball.getRect()).intersects(brickM.ballBricks.get(j).getRect())) {
				brickM.ballBricks.get(j).setDestroyed(true);

			}
		}
		//벽돌에 닿았을 때
		for (int j = 0; j < brickM.bricks.size(); j++) {
			
			if ((ball.getRect()).intersects(brickM.bricks.get(j).getRect())) {
				
				setPoint(ball, 1);
				
				if (!brickM.bricks.get(j).isDestroyed()) {
					
					brickM.bricks.get(j).setBreakCnt(brickM.bricks.get(j).getBreakCnt()-1);	
					if(brickM.bricks.get(j).getBreakCnt()==0)
						brickM.bricks.get(j).setDestroyed(true);
					
					if (brickM.bricks.get(j).getRect().contains(pointLeft)
							|| brickM.bricks.get(j).getRect().contains(pointRight)) {
						ball.setReDirX();
						break;
					}else if (brickM.bricks.get(j).getRect().contains(pointTop)
							|| brickM.bricks.get(j).getRect().contains(pointBottom)) { 
						ball.setReDirY();
						break;
					}else {
						ball.setReDirX();
						if(ball.getDirY()<0)
							ball.setReDirY();
						break;
					}
					
				}
			}		
		}
		
	}
}
