import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainFrame extends JFrame implements ActionListener{
	
	MainFrame(){
		
		setTitle("Breakout");
        setBounds(5,5, Properties.WIDTH+15, Properties.HEIGHT+100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		
		JPanel panel = new JPanel();
		
		JLabel logo = new JLabel("Breakout!");
		logo.setFont(new  Font("Comic Sans MS Bold",Font.PLAIN,50));
		logo.setBounds(40, 50, 280, 100);
		
		JButton startButton = new JButton("게임 시작");
		startButton.setBounds(100,280,100, 30);
		startButton.addActionListener(this);
		
		panel.setLayout(null);
		panel.add(logo);
		panel.add(startButton);
		add(panel);
		
	}
	
	public void actionPerformed(ActionEvent e){
		new Breakout();
		setVisible(false);
	}
	
	public static void main(String[] args) {
		new MainFrame();
	}
}
