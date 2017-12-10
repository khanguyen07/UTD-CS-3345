import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Kruskals 
{
	//Edge class
	public static class Edge implements Comparable<Edge>
	{
		int distance ;
		String v1 , v2 ;
		
		Edge(int d , String s1 , String s2)
		{
			this.distance = d ;
			this.v1 = s1 ;
			this.v2 = s2 ;
		}
      
		//compare two edges
		public int compareTo(Edge e)
		{
			if (this.distance < e.distance)
				return -1 ;
			else if (this.distance > e.distance)
				return 1 ;
			
			else 
				return 0 ;
		}
	}
   
   //implementation of kruskal algorithm
	public void kruskal()
	{
		ArrayList<Edge> edgelist = new ArrayList<>();
		ArrayList<String> VertexList = new ArrayList<>();
      
      //create string of file name
    	String fileToParse = "assn9_data.csv";
    	BufferedReader fileReader = null;
        int countVertex = 0;	 
        int sumDistance = 0;
         
        final String DELIMITER = ",";
        try
        {
            String line = "";
            
            //read from file
            fileReader = new BufferedReader(new FileReader("assn9_data.csv"));
           
            while ((line = fileReader.readLine()) != null)
            {
                
                String[] tokens = line.split(DELIMITER);
                VertexList.add(tokens[0]);
                	edgelist.add( new Edge(Integer.parseInt(tokens[2]),tokens[0],tokens[1] ));
                	for (int i =3 ; i<tokens.length ; i++)
                	{
                		edgelist.add(new Edge(Integer.parseInt(tokens[i+1]),tokens[ 0],tokens[ i]));
                		i++ ;
                	} 
                	countVertex ++ ;             
            }
        }
        
        catch (Exception e) {
            e.printStackTrace();
        }
        
        finally
        {
            try {
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }        

		int foundEdge = 0 ;
		DisjSets ds = new DisjSets(VertexList.size());
      
      //using java priority queue
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		for (Edge e : edgelist)
		{
			pq.add(e);							
		}
		
		while (foundEdge < VertexList.size()-1 )
		{
			Edge	e = pq.poll();	
         
         			
			 
			if (ds.find(VertexList.indexOf(new String(e.v1))) != ds.find(VertexList.indexOf(new String(e.v2))))
			{
				foundEdge++ ;
				ds.union(ds.find(VertexList.indexOf(new String(e.v1))),ds.find(VertexList.indexOf(new String(e.v2))));
				sumDistance = sumDistance + e.distance ;
				System.out.println("City 1: " + e.v1 + " ---> City 2: " + e.v2 + ". Distance = " + e.distance);
			}
		
		}
      
      System.out.println();
		System.out.println("Sum of all distance = " + sumDistance);
		

	}
   
    public static void main(String[] args)
    {
    	
    	    	Kruskals kr = new Kruskals();
            //call kruskal method
            System.out.println("-- Minium spanning tree --");
            System.out.println();
    	    	kr.kruskal();   	

    }

}