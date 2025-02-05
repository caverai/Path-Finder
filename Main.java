import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        //  Create a graph class and load "cities.csv" into it.
        Graph graph = new Graph("cities.csv");

        //  Create the window
        JFrame frame = new JFrame("Path Finder");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 300);
        frame.setLayout(new BorderLayout());

        //  Panel for the top (drop-downs and button)
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(3, 1, 5, 5));

        //  First city selection
        JComboBox<String> startCityBox = new JComboBox<>(graph.getCityArray());
        JLabel startLabel = new JLabel("Select Starting City:");
        topPanel.add(startLabel);
        topPanel.add(startCityBox);

        //  Second city selection
        JComboBox<String> endCityBox = new JComboBox<>(graph.getCityArray());
        JLabel endLabel = new JLabel("Select Destination City:");
        topPanel.add(endLabel);
        topPanel.add(endCityBox);

        frame.add(topPanel, BorderLayout.NORTH);

        //  Add the button
        JButton findPathButton = new JButton("Find Optimal Path");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Center the button
        findPathButton.setPreferredSize(new Dimension(160, 60)); // Set button size
        buttonPanel.add(findPathButton);
        frame.add(buttonPanel, BorderLayout.CENTER);

        //  Add the result display area
        JTextArea resultArea = new JTextArea();
        resultArea.setEditable(false); // Make the text area read-only
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        resultArea.setText("\n\n\n\n"); //  If we don't do this it starts out invisible

        //  Add result area to the frame in the bottom
        JScrollPane scrollPane = new JScrollPane(resultArea); // Add scroll pane for long results
        frame.add(scrollPane, BorderLayout.SOUTH);

        //  Action Listener for the button
        findPathButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int city1 = startCityBox.getSelectedIndex();
                int city2 = endCityBox.getSelectedIndex();

                if(city1 == city2) {
                    resultArea.setText("Select a different city!\n\n\n");
                } else {
                    //  Declare timing variables
                    long startTimeOfBFS;
                    long endTimeOfBFS;
                    long startTimeOfDFS;
                    long endTimeOfDFS;
                    double totalBFSTime = 0;
                    double totalDFSTime = 0;
                    //  Create new BFS and DFS objects
                    BreadthFirstSearch BFS = new BreadthFirstSearch(graph);
                    DepthFirstSearch DFS = new DepthFirstSearch(graph);
                    for(int i = 0; i<100; i++) { //  Do BFS and DFS 100 times for more accurate timing results
                        startTimeOfBFS = System.nanoTime();
                        //  Apply BFS algorithm
                        BFS.findCityDistance(city1,city2);

                        endTimeOfBFS = System.nanoTime();
                        //  Add measured time to the total
                        totalBFSTime += (endTimeOfBFS - startTimeOfBFS) / 1000 ;

                        startTimeOfDFS = System.nanoTime();
                        //  Apply DFS algorithm
                        DFS.findCityDistance(city1,city2);

                        endTimeOfDFS = System.nanoTime();
                        //  Add measured time to the total
                        totalDFSTime += (endTimeOfDFS - startTimeOfDFS) / 1000 ;
                    }

                    //  Display BFS and DFS timings
                    String bfsText = "BFS: " + totalBFSTime/100 + " microseconds (average over 100 runs)\n";
                    String dfsText = "DFS: " + totalDFSTime/100 + " microseconds (average over 100 runs)\n";

                    resultArea.setText(BFS.path() + DFS.path() + bfsText + dfsText);
                }
            }
        });
        frame.setVisible(true);
    }
}