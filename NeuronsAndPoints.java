package nuiro1;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Arrays;


public class NeuronsAndPoints {
	double minDistance = Integer.MAX_VALUE;
	ArrayList<Point2D.Double>  neurons;
	ArrayList<Point2D.Double> data;

	public NeuronsAndPoints() {
		this.neurons = new ArrayList<Point2D.Double>();
		this.data = new ArrayList<Point2D.Double>();

	}

	public void initializeUuniformValuesToNuoroAndData(int numOfNurons, int numOfData){

		for(int i = 0 ; i < numOfData ; i++)
		{	
			data.add(new Point2D.Double(Math.random()*1000,Math.random()*1000));
		}
		for(int i = 0 ; i < numOfNurons ; i++)
		{
			neurons.add(new Point2D.Double(Math.random()*1000,0));
		}
	}

	public int chackClosest(ArrayList<Point2D.Double> neurons, Point2D.Double data) {
		minDistance = Integer.MAX_VALUE;
		int indexOfNeurons = 0;
		for(int i = 0; i< neurons.size(); i++)
		{
			double distance = neurons.get(i).distance(data);
			if(minDistance > distance)
			{
				minDistance = distance;
				indexOfNeurons = i;
			}
		}
		return indexOfNeurons;
	}
	public void updateNeurons(ArrayList<Point2D.Double> neurons,Point2D.Double data, int currentNeuron) {
		if(currentNeuron == 0) {//first neuron
			neurons.set(currentNeuron, new Point2D.Double(((neurons.get(currentNeuron).x + data.x)/2),
				                              	          ((neurons.get(currentNeuron).y + data.y)/2)));
			neurons.set(currentNeuron + 1, new Point2D.Double((((((neurons.get(currentNeuron +1).x) - data.x )*2)/3)+ data.x),
				                                              (((((neurons.get(currentNeuron +1).y) - data.y )*2)/3)+ data.y)));
		}
		else if(currentNeuron == neurons.size()-1) {
			neurons.set(currentNeuron, new Point2D.Double(((neurons.get(currentNeuron).x + data.x)/2),
					                                      ((neurons.get(currentNeuron).y + data.y)/2)));
			neurons.set(currentNeuron - 1, new Point2D.Double((((((neurons.get(currentNeuron -1).x) - data.x )*2)/3)+ data.x),
					                                          (((((neurons.get(currentNeuron -1).y) - data.y )*2)/3)+ data.y)));
		}
		else {
			neurons.set(currentNeuron, new Point2D.Double(((neurons.get(currentNeuron).x + data.x)/2),
					((neurons.get(currentNeuron).y + data.y)/2)));
			neurons.set(currentNeuron + 1, new Point2D.Double((((((neurons.get(currentNeuron +1).x) - data.x )*2)/3)+ data.x),
														      (((((neurons.get(currentNeuron +1).y) - data.y )*2)/3)+ data.y)));
			
			neurons.set(currentNeuron - 1, new Point2D.Double((((((neurons.get(currentNeuron -1).x) - data.x )*2)/3)+ data.x),
															  (((((neurons.get(currentNeuron -1).y) - data.y )*2)/3)+ data.y)));
		}
	}


	public ArrayList<Point2D.Double> sortX(ArrayList<Point2D.Double> neurons){
		Collections.sort(neurons, new Comparator<Point2D.Double>() {
			public int compare(Point2D.Double p1, Point2D.Double p2) {
				return Double.compare(p1.getX(), p2.getX());
			}
		});
		return neurons;
	}
//	public static void main(String[] args) {
//		NeuronsAndPoints n = new NeuronsAndPoints();
//		n.initializeUuniformValuesToNuoroAndData(10, 10);
//		n.sortX(n.neurons);
//
//		ArrayList<Point2D.Double> testN = new ArrayList<Point2D.Double>();
//		testN.add(0,new Point2D.Double(0.0,0.0));
//		testN.add(1,new Point2D.Double(100.0,0.0));
//		testN.add(2,new Point2D.Double(200.0,0.0));
//		testN.add(3,new Point2D.Double(300.0,0.0));
//		testN.add(4,new Point2D.Double(400.0,0.0));
//
//		ArrayList<Point2D.Double> testD = new ArrayList<Point2D.Double>();
//		testD.add(0,new Point2D.Double(200,200));
//
//		n.neurons = testN;
//		n.data = testD;
//		int index = n.chackClosest(n.neurons, n.data.get(0));
//		System.out.println(n.neurons);
//		n.updateNeurons(n.neurons, n.data.get(0),index);
//		System.out.println(n.neurons);
//
//	}
}
