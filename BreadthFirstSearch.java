public class BreadthFirstSearch {
    //  Declare necessary variables
    Graph graph;
    ArrayList<Integer> solution = null;
    int minimumDistance = 99999;  //  Initialize to a high number, update when a better path is found

    //  Constructor with graph parameter
    public BreadthFirstSearch(Graph graph) {
        this.graph = graph;
    }

    //  The BFS algorithm itself. It finds the shortest path between two cities. Time complexity is O(2^n) for the worst case, but this never happens in our dataset. n = number of cities
    public void findCityDistance(int city1, int city2) {
        //  Create a queue to store the paths
        Queue<ArrayList<Integer>> open = new Queue<>();

        //  Create a list to store the current path, begin with the starting city
        ArrayList<Integer> current = new ArrayList<>();
        current.add(city1);
        open.enqueue(current);

        //  While there are still paths to explore, keep looking
        while (!open.isEmpty()) {
            //  Get the first path in the queue
            current = open.dequeue();

            //  Get the last city in the path
            Integer lastCity = current.get(current.size()-1);

            //  Loop through the neighbors of the last city
            for (int i = 0; i<graph.getNeighborsArray().get(lastCity).size(); i++) {  //  Worst case: O(n * k), n = number of neighbors of the last city in the current path, k = number of cities in the path newCurrent
                Integer neighbor = graph.getNeighborsArray().get(lastCity).get(i);
                //  Create a new path with the neighbor
                ArrayList<Integer> newCurrent = new ArrayList<>(current);
                newCurrent.add(neighbor);

                //  Calculate the distance of the new path, if it is greater than the currently found minimum distance, discard it. Otherwise, add it to the queue.
                //  This is what makes the algorithm finish in a reasonable time, otherwise it would take O(2^n) time.
                int temp = 99999;
                if(newCurrent.size() > 1) {
                    temp = 0;
                    for(int j = 0; j<newCurrent.size()-1; j++) {
                        temp += graph.getEdge(newCurrent.get(j),newCurrent.get(j+1));  //  Calculate the distance of the path
                    }
                }
                if (temp >= minimumDistance) {
                    continue;  //  If the distance is greater than the minimum distance, discard the path
                }

                //  If the neighbor is the destination city, update the minimum distance and the solution
                if (neighbor.equals(city2)) {
                    minimumDistance = temp;
                    solution = newCurrent;
                } else if (!current.contains(neighbor)) {  //  If the neighbor is not in the path, add it to the queue. Time complexity: O(n), n = number of cities in the path (we are not using a hash map or something similar)
                    open.enqueue(newCurrent);
                }
            }
        }
    }

    //  Returns the path found by the algorithm (O(n), n = number of cities in the solution path)
    public String path() {
        if(solution != null) {
            //  Create a string to store the path
            String output = "Path found by BFS: " + graph.getCity((solution.get(0)));
            for(int i=1; i<solution.size(); i++) { //  Loop through the solution path and add the cities to the string
                output = output + " -> " + graph.getCity((solution.get(i)));
            }
            //  Add the distance to the string
            output = output + ", with a distance of " + minimumDistance + " km!\n";
            return output;
        } else return "No path found!"; //  If no path is found, return this string
    }
}