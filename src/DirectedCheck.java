import java.util.List;

public class DirectedCheck {

    public static int[] computeInDegrees(ListGraph a) {
        int[] inDeg = new int[a.getNumVertices()];
        for (int i = 0; i < a.getNumVertices(); i++) {
            ListGraph.LinkedList incomingFromVertice = a.getAdjacencyList(i);
            ListGraph.Node current = incomingFromVertice.head;
            while(current != null) {
                inDeg[current.data]++;
                current = current.next;
            }
        }
        return inDeg;
    }

    public static int[] computeOutDegrees(ListGraph a) {
        int[] outDeg = new int[a.getNumVertices()];
        for (int i = 0; i < a.getNumVertices(); i++) {
            ListGraph.LinkedList outgoingFromVertice = a.getAdjacencyList(i);
            ListGraph.Node current = outgoingFromVertice.head;
            while(current != null) {
                outDeg[i]++;
                current = current.next;
            }
        }
        return outDeg;
    }
    public static int[] dagCheck(ListGraph a) {
        int numberSources = 0;
        int numberSinks = 0;
        int[] inDegreeList = computeInDegrees(a);
        int[] outDegreeList = computeOutDegrees(a);
        int[] topologicalSorted = new int[a.getNumVertices()];
        int index = 0;
        ListGraph.LinkedList degreeZero = new ListGraph.LinkedList();
        for (int i = 0; i < inDegreeList.length; i++) {
            if (inDegreeList[i] == 0) {
                numberSources++;
                ListGraph.Node node = new ListGraph.Node(i);
                node.next = degreeZero.head;
                degreeZero.head = node;
            }
        }
        for (int j = 0; j < outDegreeList.length; j++) {
            if (outDegreeList[j] == 0) {
                numberSinks++;
            }
        }

        while (degreeZero.head != null) {
            int currentVertex = degreeZero.head.data;
            topologicalSorted[index] = currentVertex;
            index++;
            degreeZero.head = degreeZero.head.next;

            ListGraph.LinkedList currentList = a.getAdjacencyList(currentVertex);
            ListGraph.Node currentNode = currentList.head;
            while (currentNode != null) {
                inDegreeList[currentNode.data]--;
                if (inDegreeList[currentNode.data] == 0) {
                    ListGraph.Node temp = new ListGraph.Node(currentNode.data);
                    temp.next = degreeZero.head;
                    degreeZero.head = temp;
                }
                currentNode = currentNode.next;
            }
        }
        if (index == a.getNumVertices()) {
            return new int[]{numberSources, numberSinks};
        }
        else {
            return new int[]{-1, -1};
        }
    }
}
