package nuiro1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.management.timer.Timer;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BoardFrame extends JComponent {

	public NeuronsAndPoints networks;
	int currentPointData = 0;
	public BoardFrame() {
		networks = new NeuronsAndPoints();
		networks.initializeUuniformValuesToNuoroAndData(100, 100);
		networks.sortX(networks.neurons);
		System.out.println(networks.neurons.toString());
		System.out.println(networks.data.toString());
	}

	public void paintComponent(Graphics g) {	
		super.paintComponent(g);
		this.setBackground(Color.WHITE);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.blue); //Data
		for(int i = 0; i< networks.data.size(); i++)
		{
			g2d.fillOval((int)(networks.data.get(i).x), 
					(int)(networks.data.get(i).y), 10, 10);
		}
		g2d.setColor(Color.red); //Neurons
		for (int i = 0; i < networks.neurons.size()-1; i++) 
		{
			g2d.fillOval((int)(networks.neurons.get(i).x),
					(int)(networks.neurons.get(i).y) , 10, 10);
			
			g2d.drawLine((int)networks.neurons.get(i).x,(int)networks.neurons.get(i).y, 
					(int)networks.neurons.get(i+1).x,(int)networks.neurons.get(i+1).y);
		}

	}
	public void move () throws InterruptedException {
		for(int i = 0; i< this.networks.data.size() ; i++)
		{
			int closestNeroToTargetIndex = this.networks.chackClosest(networks.neurons,networks.data.get(i));
			System.out.println(closestNeroToTargetIndex);
			System.out.println("------");
			this.networks.updateNeurons(networks.neurons, networks.data.get(i), closestNeroToTargetIndex);
		}


	}

}
