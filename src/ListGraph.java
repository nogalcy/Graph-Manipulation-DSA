import java.io.InputStreamReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Objects;

public class ListGraph {
    public int vertices;
    public LinkedList[] adjacencyList;

    public ListGraph(int vertices) {
        this.vertices = vertices;
        this.adjacencyList = new LinkedList[vertices];
        for (int i = 0; i < vertices; i++) {
            this.adjacencyList[i] = new LinkedList();
        }
    }

    public int getNumVertices() {
        return this.vertices;
    }

    public LinkedList getAdjacencyList(int vertex) {
        return this.adjacencyList[vertex];
    }
    public static ListGraph read(String filepath) throws IOException {
        InputStream istr = new FileInputStream(filepath);
        BufferedReader br = new BufferedReader(new InputStreamReader(istr));

        // TODO: Implement

        int vertice_number = Integer.parseInt(br.readLine());
        ListGraph graph = new ListGraph(vertice_number);
        for (int i = 0; i < vertice_number; i++) {
            String line = br.readLine();
            if (!line.equals("")) {
                String[] edgesInString = line.split(" ");
                for (String edge : edgesInString) {
                    int adjacentVertice = Integer.parseInt(edge);
                    graph.addEdge(i, adjacentVertice);
                }
            }
        }
        br.close();
        istr.close();
        return graph;
    }

    public void addEdge(int sourceNode, int toNode) {
        this.adjacencyList[sourceNode].insert(toNode);
    }


    public static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static class LinkedList {
        Node head;

        public void insert(int data) {
            Node newNode = new Node(data);
            if (head == null) {
                head = newNode;
                newNode.next = null;
            }
            else {
                newNode.next = head;
                head = newNode;
            }
        }
    }
}

