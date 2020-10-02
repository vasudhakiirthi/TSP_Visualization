package AOI;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;



public class DrawLines extends JPanel {
	private static final long serialVersionUID = -4519938482088942252L;
	private double[][] points;
	private int[] route;
	int coordinateX;
  	int coordinateY;
  	int city = 0;
  	static int limit;
	DrawLines(){
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(1200, 700));
    }
	
	public void drawpoints(Graphics g)
	{
		 points = new double[80000][2];
		points = Data_Preprocessor.returnPoints();
		//g.setColor(Color.BLACK);
	      city = Data_Preprocessor.cities();
	      
	      for(int i= 1; i <= city; i++) {
	    	  coordinateX = (int) points[i][0];
	          coordinateY = (int) points[i][1];
	          g.drawString(Integer.toString(city - i + 1), coordinateX + 5, coordinateY+5);
	    	  Pointer Pointer = new Pointer(points[i][0], points[i][1]);
	          Pointer.draw(g);
	      }
		
	}
	
	public void drawLine(Graphics g, int[] route)	{
		//g.drawOval(500, 500, 100, 100);
	        points = new double[80000][2];
		points = Data_Preprocessor.returnPoints();
		  route = tsp_solution.returnroute();	
		  int start;
	      int end;
	      double X1;
	      double Y1;
	      double X2;
	      double Y2;
	      double Xi;
	      double Yi;
	      
	      g.setColor(Color.BLACK);
	      Graphics2D g2 = (Graphics2D) g;
	      city = Data_Preprocessor.cities();
	      
	      for(int i= 1; i < limit; i++) {  	 
	          coordinateX = (int) points[i][0];
	          coordinateY = (int) points[i][1];
	          if (i != city) {
	      	  start = route[i];
	      	  end = route[i+1];
	      	  X1 =  points[start][0];
	      	  Y1= points[start][1];
	      	  X2 = points[end][0];
	      	  Y2 = points[end][1];
	      	  
	          
	          
	      	  g2.draw(new Line2D.Double(X1, Y1, X2, Y2)); 
	      	  
	      	}
	          else 
	          {
	        	  start = route[i];
	            	end = route[1];
	            	X1 = (int) points[start][0];
	              	Y1= (int) points[start][1];
	            	Xi = (int)points[end][0];
	            	Yi = (int)points[end][1];
	            	
	               	g2.draw(new Line2D.Double(X1, Y1, Xi, Yi)); 	        	  
	          }       
	          
		}
	}
		
	@Override
	protected void paintComponent(Graphics g) {
	  super.paintComponent(g);
	  drawpoints(g);
	  drawLine(g,this.route);
	  
	}
	
	protected void lineExecution(int[] route){
		
		this.route = route;
		repaint();
	}
	
	public void setPoints(double[][] points) {
		this.points = points;
		repaint();
	}
	
	public void setLimit(int limit) {
		DrawLines.limit = limit;
		repaint();
	}
	

}
