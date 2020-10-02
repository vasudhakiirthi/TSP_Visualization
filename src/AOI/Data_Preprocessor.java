package AOI;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class Data_Preprocessor {
	
		protected static int sym_len = 0;
		protected static double[][] symmetric_data;
		protected static double[][] points;

		protected static int cities = 0;
		protected static double min_value_x ;
		protected static double max_value_x ;
		protected static double min_value_y ;
		protected static double max_value_y ;
		public static double[][] sorted2d(double[][] A)
		{
			double t;
			int m = cities;
	        for(int x=1;x<m;x++)
	        {
	            
	                for(int i=x+1;i<=m;i++)
	                {
	                   
	                        if(A[i][1]>A[x][1])
	                        {
	                        	
	                            t = A[x][1];
	                            A[x][1] = A[i][1];
	                            A[i][1] = t;
	                            t = A[x][0];
	                            A[x][0] = A[i][0];
	                            A[i][0] = t;
	                        }
	                        else if(A[i][1]==A[x][1])
	                        {
	                        	if(A[i][0]>A[x][0])
		                        {
		                            t = A[x][1];
		                            A[x][1] = A[i][1];
		                            A[i][1] = t;
		                            t = A[x][0];
		                            A[x][0] = A[i][0];
		                            A[i][0] = t;
		                        }
	                        }
	                        
	                        
	                    }  
	        }
	        
	        return A;
		}
		
		// reads input from the file.
		public static void  read_input(File filename)
		{
			try {
				
			      Scanner myReader = new Scanner(filename);
			      int i =1;
			      symmetric_data = new double[80000][2];  
			      while (myReader.hasNextLine()) {
			        String line = myReader.nextLine();				
					  if(!(Character.isDigit(line.charAt(0))))
						  continue;
					  else
					  {
						 String a[] = line.split(" ", 3);
						 symmetric_data[i][0] = Double.parseDouble(a[1]);
						 symmetric_data[i][1] = Double.parseDouble(a[2]);
						 i++;
					  }
				     sym_len = i;
			      }
			      myReader.close();
			      cities = sym_len - 1;
			      Score_x();
			      Score_y();
			      symmetric_data = sorted2d(symmetric_data);
			      points = normalizedPoints(symmetric_data);
			     
				
				
			    } catch (FileNotFoundException e) {
			     
			      e.printStackTrace();
			    }
		
			return;
			
		}
		
	// returns 2d array containing points
	public static double[][] returnPoints(){
		return points;
	}
		
	// returns cities
	public static int cities()
    {
    	return cities;
    }
	
//	Returns score of x to aid in sorting
	
	public static void Score_x()
	{
		max_value_x = symmetric_data[1][0];
		min_value_x = symmetric_data[1][0];
		for(int i = 2; i <= cities; i++)
		{
			if(symmetric_data[i][0]>max_value_x)
			{
				max_value_x = symmetric_data[i][0];
			}
			else if(symmetric_data[i][0] < min_value_x)
			{
				min_value_x = symmetric_data[i][0];
			}
		}
	}
	
	
//	normailizes the points to fit in the given pannel
	
	public static double[][] normalizedPoints(double[][] symmetric_data) 
	{
		
		points = new double[80000][2]; 
		for(int i = 1; i<=cities; i++)
		{
			points[i][0] = (((symmetric_data[i][0] - min_value_x) * (1100 - 50))/(max_value_x - min_value_x)) + 10;
			points[i][1] = (((symmetric_data[i][1] - min_value_y)/(max_value_y - min_value_y)) * (500 - 50)) + 10;
		}
		
        return points;
    }
	
//	Returns score of y to aid in sorting
	
	public static void Score_y()
	{
		max_value_y = symmetric_data[1][1];
		min_value_y = symmetric_data[1][1];
		for(int i = 1; i <= cities; i++)
		{
			if(symmetric_data[i][1]>max_value_y)
			{
				max_value_y = symmetric_data[i][1];
			}
			else if(symmetric_data[i][1] < min_value_y)
			{
				min_value_y = symmetric_data[i][1];
			}
		}
	
	}


//	Returns co-ordinates 
	
	public static double[] data_sym(int a, int b)
	{
		double[] cord = new double[4];
		cord[0] = symmetric_data[a][0];
		cord[1] = symmetric_data[a][1];
		cord[2] = symmetric_data[b][0];
		cord[3] = symmetric_data[b][1];
		return cord;
	}
	
}