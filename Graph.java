import java.util.*;

public class Graph {
    private int vertices;
    private int[][] adjacencyMatrix;
    private List<List<Integer>> adjacencyList;
    private boolean isDirected;

    public Graph(int vertices, boolean isDirected) {
        this.vertices = vertices;
        this.isDirected = isDirected;
        adjacencyMatrix = new int[vertices][vertices];
        adjacencyList = new ArrayList<>(vertices);
        for (int i = 0; i < vertices; i++) {
            adjacencyList.add(new ArrayList<>());
        }
    }

    public void addEdgeMatrix(int src, int dest) {
        adjacencyMatrix[src][dest] = 1;
        if (!isDirected) {
            adjacencyMatrix[dest][src] = 1;
        }
    }

    public void addEdgeList(int src, int dest) {
        adjacencyList.get(src).add(dest);
        if (!isDirected) {
            adjacencyList.get(dest).add(src);
        }
    }

    public void dfsMatrix(int vertex, boolean[] visited) {
        visited[vertex] = true;
        System.out.print(vertex + " ");
        for (int i = 0; i < vertices; i++) {
            if (adjacencyMatrix[vertex][i] == 1 && !visited[i]) {
                dfsMatrix(i, visited);
            }
        }
    }

    public void dfsList(int vertex, boolean[] visited) {
        visited[vertex] = true;
        System.out.print(vertex + " ");
        for (int neighbor : adjacencyList.get(vertex)) {
            if (!visited[neighbor]) {
                dfsList(neighbor, visited);
            }
        }
    }

    public void bfsMatrix(int start) {
        boolean[] visited = new boolean[vertices];
        Queue<Integer> queue = new LinkedList<>();
        visited[start] = true;
        queue.add(start);

        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            System.out.print(vertex + " ");
            for (int i = 0; i < vertices; i++) {
                if (adjacencyMatrix[vertex][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }
    }

    public void bfsList(int start) {
        boolean[] visited = new boolean[vertices];
        Queue<Integer> queue = new LinkedList<>();
        visited[start] = true;
        queue.add(start);

        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            System.out.print(vertex + " ");
            for (int neighbor : adjacencyList.get(vertex)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
    }

    public boolean isDirected() {
        return isDirected;
    }

    public void topologicalSortUtil(int v, boolean[] visited, Stack<Integer> stack) {
        visited[v] = true;
        for (int i = 0; i < vertices; i++) {
            if (adjacencyMatrix[v][i] == 1 && !visited[i]) {
                topologicalSortUtil(i, visited, stack);
            }
        }
        stack.push(v);
    }

    public void topologicalSort() {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[vertices];

        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                topologicalSortUtil(i, visited, stack);
            }
        }

        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }

    public boolean isConnected() {
        boolean[] visited = new boolean[vertices];
        bfsList(0); // Start BFS from vertex 0
        for (boolean v : visited) {
            if (!v) {
                return false;
            }
        }
        return true;
    }

    public int findPathAndDistance(int src, int dest) {
        boolean[] visited = new boolean[vertices];
        Queue<Integer> queue = new LinkedList<>();
        Map<Integer, Integer> distanceMap = new HashMap<>();
        queue.add(src);
        visited[src] = true;
        distanceMap.put(src, 0);

        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            if (vertex == dest) {
                return distanceMap.get(vertex);
            }
            for (int neighbor : adjacencyList.get(vertex)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                    distanceMap.put(neighbor, distanceMap.get(vertex) +  1);
                }
            }
        }
        return -1; // No path found
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Graph graph = null;

        System.out.println("Enter number of vertices:");
        int vertices = scanner.nextInt();
        System.out.println("Is the graph directed? (true/false):");
        boolean isDirected = scanner.nextBoolean();

        graph = new Graph(vertices, isDirected);

        System.out.println("Enter edges (src dest), -1 to stop:");
        while (true) {
            int src = scanner.nextInt();
            if (src == -1) break;
            int dest = scanner.nextInt();
            graph.addEdgeMatrix(src, dest);
            graph.addEdgeList(src, dest);
        }

        System.out.println("DFS Traversal (Matrix):");
        boolean[] visitedMatrix = new boolean[vertices];
        graph.dfsMatrix(0, visitedMatrix);
        
        System.out.println("\nDFS Traversal (List):");
        boolean[] visitedList = new boolean[vertices];
        graph.dfsList(0, visitedList);
        
        System.out.println("\nBFS Traversal (Matrix):");
        graph.bfsMatrix(0);
        
        System.out.println("\nBFS Traversal (List):");
        graph.bfsList(0);
        
        if (graph.isDirected()) {
            System.out.println("\nTopological Sort:");
            graph.topologicalSort();
        } else {
            System.out.println("\nThe graph is undirected.");
        }

        System.out.println("\nIs the graph connected? " + (graph.isConnected() ? "Yes" : "No"));
        
        System.out.println("Enter the source vertex for path finding:");
        int source = scanner.nextInt();
        System.out.println("Enter the destination vertex for path finding:");
        int destination = scanner.nextInt();
        int distance = graph.findPathAndDistance(source, destination);
        if (distance != -1) {
            System.out.println("Path found with distance: " + distance);
        } else {
            System.out.println("No path found from " + source + " to " + destination);
        }
        
        scanner.close();
    }
}