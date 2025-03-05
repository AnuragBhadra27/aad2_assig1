import java.util.*;

class Graph {
    private int vertices;
    private int[][] adjMatrix;
    private List<List<Integer>> adjList;
    private boolean directed;

    public Graph(int vertices, boolean directed) {
        this.vertices = vertices;
        this.directed = directed;
        adjMatrix = new int[vertices][vertices];
        adjList = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    public void addEdge(int src, int dest) {
        adjMatrix[src][dest] = 1;
        adjList.get(src).add(dest);
        if (!directed) {
            adjMatrix[dest][src] = 1;
            adjList.get(dest).add(src);
        }
    }

    public void printMatrix() {
        System.out.println("Adjacency Matrix:");
        for (int[] row : adjMatrix) {
            System.out.println(Arrays.toString(row));
        }
    }

    public void printAdjList() {
        System.out.println("Adjacency List:");
        for (int i = 0; i < adjList.size(); i++) {
            System.out.println(i + " -> " + adjList.get(i));
        }
    }

    public void DFS(int start, boolean[] visited) {
        visited[start] = true;
        System.out.print(start + " ");
        for (int neighbor : adjList.get(start)) {
            if (!visited[neighbor]) {
                DFS(neighbor, visited);
            }
        }
    }

    public void DFSUtil(int start) {
        boolean[] visited = new boolean[vertices];
        System.out.print("DFS Traversal: ");
        DFS(start, visited);
        System.out.println();
    }

    public boolean isDirected() {
        for (int i = 0; i < vertices; i++) {
            for (int j : adjList.get(i)) {
                if (!adjList.get(j).contains(i)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void topologicalSort() {
        if (!isDirected()) {
            System.out.println("Graph is not directed. Topological sorting not applicable.");
            return;
        }
        int[] inDegree = new int[vertices];
        for (List<Integer> edges : adjList) {
            for (int v : edges) {
                inDegree[v]++;
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < vertices; i++) {
            if (inDegree[i] == 0) queue.add(i);
        }
        System.out.print("Topological Order: ");
        while (!queue.isEmpty()) {
            int node = queue.poll();
            System.out.print(node + " ");
            for (int neighbor : adjList.get(node)) {
                if (--inDegree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }
        System.out.println();
    }

    public void BFS(int start) {
        boolean[] visited = new boolean[vertices];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;
        System.out.print("BFS Traversal: ");
        while (!queue.isEmpty()) {
            int node = queue.poll();
            System.out.print(node + " ");
            for (int neighbor : adjList.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
        System.out.println();
    }

    public void findShortestPath(int src, int dest) {
        int[] dist = new int[vertices];
        Arrays.fill(dist, -1);
        Queue<Integer> queue = new LinkedList<>();
        queue.add(src);
        dist[src] = 0;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int neighbor : adjList.get(node)) {
                if (dist[neighbor] == -1) {
                    dist[neighbor] = dist[node] + 1;
                    queue.add(neighbor);
                }
            }
        }
        System.out.println("Distance from " + src + " to " + dest + " is: " + dist[dest]);
    }

    public boolean isConnected() {
        boolean[] visited = new boolean[vertices];
        BFS(0);
        for (boolean v : visited) {
            if (!v) return false;
        }
        return true;
    }
}

public class GraphMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of vertices: ");
        int vertices = scanner.nextInt();
        System.out.print("Is the graph directed? (true/false): ");
        boolean directed = scanner.nextBoolean();
        Graph graph = new Graph(vertices, directed);

        System.out.print("Enter number of edges: ");
        int edges = scanner.nextInt();
        System.out.println("Enter edges (src dest):");
        for (int i = 0; i < edges; i++) {
            int src = scanner.nextInt();
            int dest = scanner.nextInt();
            graph.addEdge(src, dest);
        }

        while (true) {
            System.out.println("\n1. Print Matrix\n2. Print List\n3. DFS\n4. Check Directed & Topological Sorting\n5. BFS\n6. Shortest Path\n7. Check Connectivity\n8. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> graph.printMatrix();
                case 2 -> graph.printAdjList();
                case 3 -> {
                    System.out.print("Enter start vertex for DFS: ");
                    graph.DFSUtil(scanner.nextInt());
                }
                case 4 -> {
                    if (graph.isDirected()) graph.topologicalSort();
                    else System.out.println("Graph is undirected.");
                }
                case 5 -> {
                    System.out.print("Enter start vertex for BFS: ");
                    graph.BFS(scanner.nextInt());
                }
                case 6 -> {
                    System.out.print("Enter source and destination: ");
                    graph.findShortestPath(scanner.nextInt(), scanner.nextInt());
                }
                case 7 -> System.out.println("Graph is " + (graph.isConnected() ? "Connected" : "Not Connected"));
                case 8 -> System.exit(0);
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}
 