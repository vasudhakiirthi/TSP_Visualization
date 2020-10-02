package AOI;

public class tsp_solution {
	static int[] route;
	static double[] cost;
	public static int[] returnroute() {
		return route;
	}
	public static int[] start(String starting_point)
	{		
		int n = Data_Preprocessor.cities();
		cost = new double[n + 1];
		int[] visited = new int[n+1];
	    route = new int[n+2];
	    int chosen = n - Integer.parseInt(starting_point) + 1;
	    System.out.println(chosen);
	    for(int h = 0; h<=n; h++)
	    {
	    	cost[h] = 0;
	    }
	    
	    for(int h = 1; h<=n; h++)
	    {
	    	visited[h] = 0;
	    }
	    visited[chosen] = 1;
	    route[1] = chosen;
	    int selected = 1;
	    int i = 1;
	    double x[] = new double[2];
	    while(selected < n)
	    {
	    	
	    	x = distance.findmin_sym(chosen, visited);
	    	
	    	if(x[0] != '\0')
	    	{
	    		cost[i] = cost[i-1] + x[1];
	    		i++;
	    		selected++;
	    		route[selected] = (int)x[0];
	    		chosen = (int)x[0];
	    		visited[(int)x[0]] = 1;
	    	}
	    }

	  
	    
	   
	    
	    return route;
	}
	
	public static double getCost(int i)
	{
		System.out.println("cost and limit is: " + i + " and " + cost[i]);
		return cost[i];
	}
}
