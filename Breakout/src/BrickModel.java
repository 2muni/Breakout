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
	
	//���� ����
	public void setBricks() {
		
		score++;
		
		//������ ������ ������ �����ϱ� ���� �ε��� �迭 �ʱ�ȭ
		numberOfBricksInRows = rand.nextInt(5)+2;
		indexOfBrickInRows = new int[numberOfBricksInRows];
		indexOfBrickInRows[0] = rand.nextInt(6);
	
		//������ ��ġ�� ������ �����ϱ� ���� ������ ��ġ �ε��� ����
		for(int i = 1; i < numberOfBricksInRows; i++) {
			indexOfBrickInRows[i] = rand.nextInt(numberOfBricksInRows);
			//�ߺ��� ��ġ�ε��� ����
			for(int j = 0; j < i; j++) {
				if(indexOfBrickInRows[j] == indexOfBrickInRows[i])
					i--;
			}
		}
		//���� ����Ʈ���� ���� ������ AdditionalBallBrick ����
		ballBricks.add(new AdditionalBallBrick(indexOfBrickInRows[0] * BRICK_WIDTH + 20,
				layerOfBricks * BRICK_HEIGHT + 10));
		
		//���� ����Ʈ�� ������ ��ġ���� �Է�
		for(int i = 1; i < numberOfBricksInRows; i++) {
			int index = indexOfBrickInRows[i];
			bricks.add(new Brick(index * BRICK_WIDTH,
					layerOfBricks * BRICK_HEIGHT, score));
		}
	}
	
	//������ y�� �缳��
	public void resetBricks() {
		
		//�Ϲ� ����
		for(int i = 0; i < bricks.size(); i ++) {
			bricks.get(i).setY(bricks.get(i).getY() + BRICK_HEIGHT);
		}
		//AdditionalBallBrick
		for(int i = 0; i < ballBricks.size(); i ++) {
			ballBricks.get(i).setY(ballBricks.get(i).getY() + BRICK_HEIGHT);
		}
	}
	
	//����Ʈ���� �ı��� ���� ����
	public void deleteBricks() {
		
		//�Ϲ� ����
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
