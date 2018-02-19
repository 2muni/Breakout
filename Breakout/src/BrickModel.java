import java.util.ArrayList;
import java.util.Random;

public class BrickModel {
	
	private final int BRICK_WIDTH = (new Brick(0,0,0)).getWidth();
	private final int BRICK_HEIGHT = (new Brick(0,0,0)).getHeight();
	
	private int score;
	private int layerOfBricks;
	private int numberOfAddedBall;
	private int numberOfBricksInRows;
	private int[] indexOfBrickInRows;
	
	ArrayList<Brick> bricks;
	ArrayList<AdditionalBallBrick> ballBricks;

	Random rand = new Random();
	
	public BrickModel() {
		
		score = 0;
		layerOfBricks = 1;
		numberOfAddedBall = 0;
		bricks = new ArrayList<Brick>();
		ballBricks = new ArrayList<AdditionalBallBrick>();
	}
	
	public int getNumberOfAddedBall() {		return numberOfAddedBall;	}
	public int getScore() {		return score;	}
	
	//벽돌 생성
	public void setBricks() {
		
		score++;
		
		//랜덤한 갯수의 벽돌을 생성하기 위한 인덱스 배열 초기화
		numberOfBricksInRows = rand.nextInt(5)+2;
		indexOfBrickInRows = new int[numberOfBricksInRows];
		indexOfBrickInRows[0] = rand.nextInt(6);
	
		//랜덤한 위치에 벽돌을 생성하기 위한 벽돌의 위치 인덱스 생성
		for(int i = 1; i < numberOfBricksInRows; i++) {
			indexOfBrickInRows[i] = rand.nextInt(numberOfBricksInRows);
			//중복된 위치인덱스 제거
			for(int j = 0; j < i; j++) {
				if(indexOfBrickInRows[j] == indexOfBrickInRows[i])
					i--;
			}
		}
		//벽돌 리스트에서 남은 공간에 AdditionalBallBrick 생성
		ballBricks.add(new AdditionalBallBrick(indexOfBrickInRows[0] * BRICK_WIDTH + 20,
				layerOfBricks * BRICK_HEIGHT + 10));
		
		//벽돌 리스트에 랜덤한 위치정보 입력
		for(int i = 1; i < numberOfBricksInRows; i++) {
			int index = indexOfBrickInRows[i];
			bricks.add(new Brick(index * BRICK_WIDTH,
					layerOfBricks * BRICK_HEIGHT, score));
		}
	}
	
	//벽돌의 y값 재설정
	public void resetBricks() {
		
		//일반 벽돌
		for(int i = 0; i < bricks.size(); i ++) {
			bricks.get(i).setY(bricks.get(i).getY() + BRICK_HEIGHT);
		}
		//AdditionalBallBrick
		for(int i = 0; i < ballBricks.size(); i ++) {
			ballBricks.get(i).setY(ballBricks.get(i).getY() + BRICK_HEIGHT);
		}
	}
	
	//리스트에서 파괴된 벽돌 삭제
	public void deleteBricks() {
		
		//일반 벽돌
		for(int i = 0; i < bricks.size(); i ++) {
			if(bricks.get(i).isDestroyed()) {
				bricks.remove(i);
			}
		}
		//AdditionalBallBrick
		for(int i = ballBricks.size()-1; i >= 0; i--) {
			if(ballBricks.get(i).isDestroyed()
					|| ballBricks.get(i).getY() > Properties.HEIGHT - 20) {
				ballBricks.remove(i);
				numberOfAddedBall++;
			}
		}		
	}
	
	//NumberOfAddedBall 
	public void setNumberOfAddedBallToZero() {
		this.numberOfAddedBall = 0;	
	}
}
