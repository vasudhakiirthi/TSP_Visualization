package AOI;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import javax.swing.filechooser.FileFilter;

//import AOI.DrawLines.MyFrame;

public class UI_Main extends JFrame{
	private static final long serialVersionUID = -5191807029537701007L;
	JPanel contentPane = new JPanel();
	private JButton open;
    private JTextField startingPoint;
    private JButton run;
    private JLabel distanceLabel;
    private JLabel iterationLabel;
    private JLabel distance;
    private JLabel iteration;
    private DrawLines drawLine;
    final JLabel label = new JLabel();
    private JFrame frame;
    private JLabel startingLabel;
    private Boolean opened = false;
    private Thread thread;
    private static int[] route;
    private static int limit =2;
    public UI_Main() 
    {	
        this.open = new JButton("Open");
        this.run = new JButton("Run");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(open);
        buttonPanel.add(run);
        JPanel outputPanel = new JPanel();
        distanceLabel = new JLabel("Best Distance: ");
        iterationLabel = new JLabel("Iteration: ");
        distance = new JLabel();
        iteration = new JLabel();
        outputPanel.add(distanceLabel);
        outputPanel.add(distance);
        outputPanel.add(iterationLabel);
        outputPanel.add(iteration);
        JPanel inputPanel = new JPanel();
        startingLabel = new JLabel("Starting Point: ");
        startingPoint = new JTextField("1");
        startingPoint.setPreferredSize(new Dimension( 200, 24 ));
        inputPanel.add(startingLabel);
        inputPanel.add(startingPoint);
        JPanel topPanel = new JPanel();
        topPanel.add(buttonPanel, BorderLayout.WEST);
        topPanel.add(outputPanel, BorderLayout.EAST);
        topPanel.add(inputPanel, BorderLayout.SOUTH);
        //drawingPanel = new Drawing_Area();
        drawLine = new DrawLines();
        contentPane.add(topPanel);
        contentPane.add(drawLine);
        setContentPane(contentPane);
        this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        open.addActionListener(e -> onOpen());
        run.addActionListener(e -> onRun());
    }    
    private void onOpen() {
    	JFileChooser fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(new FileFilter() {
            public String getDescription() {
                return "TSP files (*.tsp)";
            }
         
            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                }
                else {
                    return f.getName().toLowerCase().endsWith(".tsp");
                }
            }
        });
        fileChooser.setAcceptAllFileFilterUsed(false);
        int option = fileChooser.showOpenDialog(frame);
        if(option == JFileChooser.APPROVE_OPTION){
           File file = fileChooser.getSelectedFile();
           Data_Preprocessor.read_input(file);
           double[][]points = Data_Preprocessor.returnPoints();
           this.drawLine.setPoints(points);
           opened = true;
        }
        else{
           label.setText("Open command canceled");
        }
     }
    

    @SuppressWarnings({ "deprecation" })
	private void onRun() {
        if(run.getText() == "Run"){
        	if(opened == false) {
            JOptionPane.showMessageDialog(null, "Open a file before running the solution.",
                    "Information ", JOptionPane.INFORMATION_MESSAGE);
        	} else if(run.getText().equals("Run")) {
        		run.setText("Pause");
               
                String starting_point = startingPoint.getText();            
              route = new int [Data_Preprocessor.cities + 1];
              route =	tsp_solution.start(starting_point);
              
          
              thread = new Thread(() ->{
            	  while(route!=null && limit<route.length) {
            		  double dist = 0;
            		  
            		  System.out.println(dist);
            		  drawLine.lineExecution(route);
	                  try {
	    				drawLine.setLimit(limit);
	    				this.distance.setText("" + AOI.tsp_solution.getCost((limit - 2)));
	    				System.out.println(limit + " " + AOI.tsp_solution.getCost((limit-1)));
	    				this.iteration.setText(""+(limit-1));
	    				Thread.sleep(1000);
	    				
	    			} catch (InterruptedException e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			}
	                  limit++;
            	  }
                  run.setText("Run");
              });
              thread.start();
        		
        	}
        } else if(run.getText().equals("Pause")) {
    		thread.stop();
        	run.setText("Run");
    	} else {
        	run.setText("Run");          
        }
    }

    public static void main(String[] args) {    	
        UI_Main dialog = new UI_Main();
        dialog.setVisible(true);
        dialog.pack();
        dialog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
