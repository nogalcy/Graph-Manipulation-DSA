import java.io.InputStreamReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;

public class MatrixGraph {

    public int vertices;
    public boolean[][] adjacencyMatrix;
    public MatrixGraph(int vertices) {
        this.vertices = vertices;
        this.adjacencyMatrix = new boolean[vertices][vertices];
    }


    public static MatrixGraph read(String filepath) throws IOException {
        InputStream istr = new FileInputStream(filepath);
        BufferedReader br = new BufferedReader(new InputStreamReader(istr));
        // TODO: Implement

        int vertice_number = Integer.parseInt(br.readLine());
        MatrixGraph graph = new MatrixGraph(vertice_number);
        for (int i = 0; i < vertice_number; i++) {
            String line = br.readLine();
            if (!line.equals("")) {
                String[] edges = line.split(" ");
                for (String edge : edges) {
                    int adjacentVertex = Integer.parseInt(edge);
                    graph.addEdge(i, adjacentVertex);
                    graph.addEdge(adjacentVertex, i);
                }
            }
        }
        br.close();
        istr.close();
        return graph;
    }

    public void addEdge(int sourceVertex, int destVertex) {
        this.adjacencyMatrix[sourceVertex][destVertex] = true;
    }

}
