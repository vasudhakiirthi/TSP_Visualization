package AOI;

public class distance {
	public static double dist_symm(int city1, int city2)
	{
		double[] city = new double[4];
		city = Data_Preprocessor.data_sym(city1, city2);
		double xcity1 = city[0];
		double ycity1 = city[1];
		double xcity2 = city[2];
		double ycity2 = city[3];
		return (Math.sqrt((ycity2 - ycity1) * (ycity2 - ycity1) + (xcity2 - xcity1) * (xcity2 - xcity1)));
	}
	
	public static double[] findmin_sym(int city, int[] visited)
	{
		int n = Data_Preprocessor.cities();
		int city2 = -1;
		double temp_dist = -1;
		double min_dist = 1000000.00;
		double[] val = new double[2];
		val[0] = '\0';
		for(int i = 1; i <= n; i++)
		{
			if( i == city)
			{
				continue;
			}
				temp_dist = dist_symm(city, i);
				if(temp_dist<min_dist && visited[i] == 0)
				{
					city2 = i;
					min_dist = temp_dist;
				}	
		}
		val[0] = city2;
		val[1] = min_dist;
		return val;
	}

}
