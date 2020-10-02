package AOI;



import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Pointer {

    private final int coordinateX;
    private final int coordinateY;
    int city;
 
    public Pointer(double point, double point2) {
    	this.coordinateX = (int) point;
        this.coordinateY = (int) point2;
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        Graphics2D g2 = (Graphics2D) g;
        city = Data_Preprocessor.cities();      
        g2.fill(new Ellipse2D.Double(coordinateX , coordinateY, 4, 4));              
    }
        
    
}
