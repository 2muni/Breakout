import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class ScoreBoard extends JPanel {

	JLabel score;
	JLabel numberOfBall;
	
	GameBoard gBoard;
	Runnable setScore;
	Thread scoreThread;
	ScoreBoard(GameBoard gBoard){
		setBounds(5,5, Properties.WIDTH, 50);
		this.gBoard = gBoard;
		setLayout(new GridLayout(2,1));
		score = new JLabel();
		score.setFont(new Font("Dialog", Font.BOLD, 24));
		score.setHorizontalAlignment(JLabel.CENTER);
		add(score);
		numberOfBall = new JLabel();
		numberOfBall.setFont(new Font("Dialog", Font.BOLD, 16));
		numberOfBall.setHorizontalAlignment(JLabel.CENTER);
		add(numberOfBall);
		
		setScore = new SetScore();
		scoreThread = new Thread(setScore);
		scoreThread.start();
	}
	
	private class SetScore implements Runnable{
		@Override
		public void run() {
			try {
				while(true) {
					Thread.sleep(10);
					score.setText("SCORE: "+
							Integer.toString(gBoard.control.brickM.getScore()));
					numberOfBall.setText("Ball Count: "+
							Integer.toString(gBoard.control.ballM.getNumberOfBall()));
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
