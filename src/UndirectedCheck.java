import java.util.List;

public class UndirectedCheck {
    public static boolean treeCheck(ListGraph a) {
        int verticeCount = a.getNumVertices();
        boolean[] visited = new boolean[verticeCount];
        if (!undirectedDfs(a, 0, -1, visited)) {
            return false;
        }
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                return false;
            }
        }
        return true;
    }

    public static boolean undirectedDfs(ListGraph graph, int u, int parent, boolean[] visited) {
        if (visited[u]) {
            return false;
        }
        visited[u] = true;
        ListGraph.LinkedList children = graph.getAdjacencyList(u);
        ListGraph.Node currentChild = children.head;
        while (currentChild != null) {
            if (currentChild.data != parent) {
                boolean result = undirectedDfs(graph, currentChild.data, u, visited);
                if (result == false) {
                    return false;
                }
            }
            currentChild = currentChild.next;
        }
        return true;
    }

    public static int countTriangles(MatrixGraph a) {
        int triangle_count = 0;
        for (int u = 0; u < a.adjacencyMatrix.length; u++) {
            for (int v = u + 1; v < a.adjacencyMatrix.length; v++ ) {
                for (int z = v + 1; z < a.adjacencyMatrix.length; z++) {
                    if (a.adjacencyMatrix[u][v] && a.adjacencyMatrix[v][z] && a.adjacencyMatrix[u][z]) {
                        triangle_count++;
                    }
                }
            }
        }
        return triangle_count;
    }

    public static double vertexClusterCoeff(ListGraph a, int u) {
        ListGraph.LinkedList neighbors = a.getAdjacencyList(u);
        int connectionCount = 0;
        int degree = 0;

        ListGraph.Node currentNode = neighbors.head;
        while (currentNode != null) {
            ListGraph.Node nextNode = currentNode.next;
            while (nextNode != null) {
                if (triangle(a, currentNode.data, nextNode.data)) {
                    connectionCount++;
                }
                nextNode = nextNode.next;
            }
            degree++;
            currentNode = currentNode.next;
        }

        if (degree <= 1) {
            return 0.0;
        }

        return (2.0 * connectionCount) / (degree * (degree - 1));
    }

    public static boolean triangle(ListGraph graph, int u, int v) {
        ListGraph.LinkedList list1 = graph.getAdjacencyList(u);
        ListGraph.LinkedList list2 = graph.getAdjacencyList(v);

        ListGraph.Node currentNode = list1.head;
        while (currentNode != null) {
            if (currentNode.data == v) {
                return true;
            }
            currentNode = currentNode.next;
        }

        ListGraph.Node currentNode2 = list2.head;
        while (currentNode2 != null) {
            if (currentNode2.data == u) {
                return true;
            }
            currentNode2 = currentNode2.next;
        }
        return false;
    }

    public static double graphClusterCoeff(ListGraph a) {
        double collectiveCoeff = 0.0;
        int totalVertices = a.getNumVertices();
        for (int i = 0; i < totalVertices; i++) {
            double currentCoeff = vertexClusterCoeff(a, i);
            collectiveCoeff += currentCoeff;
        }
        return collectiveCoeff / totalVertices;
    }
}
