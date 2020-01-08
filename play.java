package nuiro1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

import javax.management.timer.Timer;
import javax.swing.JFrame;

public class play {

	public play() throws InterruptedException {

		JFrame frame = new JFrame();
		frame.setSize(1050,1050);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		final BoardFrame board = new BoardFrame();
		frame.add(board);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		board.repaint();

		for(int i = 0;  i < 5; i++) {
			for(int j = 0 ; j < 10; j++)
			{
				Thread.sleep(100);
				board.move();
			}
			board.repaint();
		}			
	}
	public static void main(String[] args) throws InterruptedException {
		new play();
	}
}
