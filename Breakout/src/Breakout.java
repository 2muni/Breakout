import java.awt.Color;
import java.awt.Container;

import javax.swing.JFrame;

public class Breakout extends JFrame {
	
	GameBoard gBoard = new GameBoard();
	ScoreBoard sBoard = new ScoreBoard(gBoard);
	Container cp = getContentPane();
	
	Breakout() {
		
        setTitle("Breakout");
        setBounds(5,5, Properties.WIDTH+15, Properties.HEIGHT+100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
		setVisible(true);
		setResizable(false);

		cp.setBackground(Color.BLACK);
		cp.add(gBoard);
		cp.add(sBoard);
	}

}
