import java.io.File;
import java.util.Scanner;

public class Graph {
	private int[][] cityEdges; //  The 2D array that stores all the cities' edges
    private int numOfCities;  // Variable that stores how many cities there are
	private String[] cityArray;  //  Stores all city names
	private ArrayList<ArrayList<Integer>> neighborsArray = new ArrayList<>(); //  Stores all neighbors of each city

	public Graph(String filePath) {
		//  Reads the file
		File citiesFile = new File(filePath);

		//  Try catch block for if the file is not found
		try {
			//  Scanner to read the file
			Scanner scanner = new Scanner(citiesFile);
			String line = scanner.nextLine();

			//  Gets how many cities there are from the first line
			numOfCities = line.split(",").length - 1;

			//  Initializes the 2D array and the city array
			cityEdges = new int[numOfCities][numOfCities];
			cityArray = new String[numOfCities];
			String[] splittedLine = new String[0];

			//  Reads the file and stores the cities and their edges (O (n^2)), n = number of cities
			for(int i = 0; i<numOfCities; i++) {
				if(scanner.hasNextLine()) {
					splittedLine = scanner.nextLine().split(",");
				}
				cityArray[i] = splittedLine[0]; //  Get the first element of the line to fill the city array
				for(int j=0; j<numOfCities; j++) {
					addEdge(i, j, Integer.parseInt(splittedLine[j+1])); //  Fill the 2D array with the rest of the elements
				}
			}
		} catch (Exception e) { //  Catch block for if the file is not found
			System.out.println(e.getMessage());
		}
		for(int i = 0; i < numOfCities; i++) { //  Initializes the neighbors array (O (n)), n = number of cities
			neighborsArray.add(new ArrayList<>());
		}
		for(int i=0; i < numOfCities; i++) { //  If there is an edge between two cities, add the neighbor to the neighbors array (O (n^2)), n = number of cities
			for(int j=0; j < numOfCities; j++) {
				if(hasEdge(i,j)) {
					neighborsArray.get(i).add(j);
				}
			}
		}
	}

	//  Adds an edge between two cities, O(1)
	public void addEdge(int source, int destination, int valueToAdd) {
		cityEdges[source][destination] = valueToAdd;
	}

	//  Checks if there is an edge between two cities, O(1)
	public boolean hasEdge(int source, int destination) {
		return (cityEdges[source][destination] != 99999 && cityEdges[source][destination] != 0);
	}

	//  Gets the edge between two cities, O(1)
	public int getEdge(int source, int destination) {
		return cityEdges[source][destination];
	}

	//  Getters (O(1))
	// 	--------------------------------
	public String[] getCityArray() {
		return cityArray;
	}

	public ArrayList<ArrayList<Integer>> getNeighborsArray() {
		return neighborsArray;
	}

	public String getCity(int position) {
		return cityArray[position];
	}

	public int getNumOfCities() {
		return numOfCities;
	}
}
