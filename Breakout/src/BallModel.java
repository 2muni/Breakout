import java.util.ArrayList;

public class BallModel {

	private int numberOfBall;
	private int remainedNumberOfBall;
	ArrayList<Ball> balls;
	
	public BallModel() {

		numberOfBall = 1;
		balls = new ArrayList<Ball>();
		balls.add(new Ball());
		remainedNumberOfBall = numberOfBall;
	}
	
	public int getNumberOfBall() {		return numberOfBall;	}
	public int getRemainedNumberOfBall() {		return remainedNumberOfBall;	}
	public void setRemainedNumberOfBall(int remainedNumberOfBall) {
		this.remainedNumberOfBall = remainedNumberOfBall;
	}
	
	//공 갯수 설정
	public void setBalls(int numberOfAddedBall) {
		//공의 갯수에 따른 BallThread 추가
		for(int i = 0; i < numberOfAddedBall; i++) {
			balls.add(new Ball());
			numberOfBall++;
		}
	}
	
	public void resetBalls(double lastBallX, double lastBallY) {

		for(int i = 0; i < balls.size(); i++) {
			
			balls.get(i).setX(lastBallX);
			balls.get(i).setY(lastBallY);
		}
	}
	
	//공 방향 설정
	public void setDirBall(int index, int x, int y) {
		//방향각 설정
		double angle = Math.atan2(Math.abs(y-balls.get(index).getY()),
					Math.abs(x-balls.get(index).getX()));
		
		//방향각에 따른 x축 이동 속도 설정
		if(x-balls.get(index).getX() > 0)
			balls.get(index).setDirX(Math.cos(angle));
		else
			balls.get(index).setDirX(Math.cos(angle)*-1.0);
		
		//방향각에 따른 y축 이동 속도 설정
		if(y-balls.get(index).getY() > 0)
			balls.get(index).setDirY(Math.sin(angle));
		else
			balls.get(index).setDirY(Math.sin(angle)*-1.0);
	}
}

