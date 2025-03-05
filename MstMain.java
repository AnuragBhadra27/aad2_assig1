import java.util.*;

class Graph {
    private int vertices;
    private List<int[]> edges;

    public Graph(int vertices) {
        this.vertices = vertices;
        edges = new ArrayList<>();
    }

    public void addEdge(int u, int v, int weight) {
        edges.add(new int[]{u, v, weight});
    }

    // Kruskal's Algorithm for MST
    public void kruskalMST() {
        edges.sort(Comparator.comparingInt(edge -> edge[2]));
        int[] parent = new int[vertices];
        for (int i = 0; i < vertices; i++) parent[i] = i;
        
        int mstWeight = 0;
        System.out.println("Edges in MST using Kruskal's Algorithm:");
        for (int[] edge : edges) {
            int u = find(parent, edge[0]);
            int v = find(parent, edge[1]);
            if (u != v) {
                System.out.println(edge[0] + " - " + edge[1] + " : " + edge[2]);
                mstWeight += edge[2];
                union(parent, u, v);
            }
        }
        System.out.println("Total Weight: " + mstWeight);
    }

    private int find(int[] parent, int i) {
        if (parent[i] == i) return i;
        return parent[i] = find(parent, parent[i]);
    }

    private void union(int[] parent, int x, int y) {
        parent[find(parent, x)] = find(parent, y);
    }

    // Prim's Algorithm for MST
    public void primMST() {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(edge -> edge[2]));
        boolean[] inMST = new boolean[vertices];
        pq.add(new int[]{0, 0, 0});
        int totalWeight = 0;

        System.out.println("Edges in MST using Prim's Algorithm:");
        while (!pq.isEmpty()) {
            int[] edge = pq.poll();
            int u = edge[1];
            if (inMST[u]) continue;
            inMST[u] = true;
            totalWeight += edge[2];
            if (edge[0] != edge[1])
                System.out.println(edge[0] + " - " + edge[1] + " : " + edge[2]);
            
            for (int[] e : edges) {
                if (e[0] == u && !inMST[e[1]]) pq.add(e);
                else if (e[1] == u && !inMST[e[0]]) pq.add(new int[]{e[1], e[0], e[2]});
            }
        }
        System.out.println("Total Weight: " + totalWeight);
    }

    // Dijkstra's Algorithm for Shortest Path
    public void dijkstra(int src) {
        int[] dist = new int[vertices];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.add(new int[]{src, 0});
        
        while (!pq.isEmpty()) {
            int[] node = pq.poll();
            int u = node[0];
            int d = node[1];
            if (d > dist[u]) continue;
            
            for (int[] edge : edges) {
                if (edge[0] == u || edge[1] == u) {
                    int v = edge[0] == u ? edge[1] : edge[0];
                    int newDist = dist[u] + edge[2];
                    if (newDist < dist[v]) {
                        dist[v] = newDist;
                        pq.add(new int[]{v, newDist});
                    }
                }
            }
        }
        
        System.out.println("Shortest distances from source " + src + ":");
        for (int i = 0; i < vertices; i++)
            System.out.println("To " + i + " : " + (dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]));
    }
}

public class MstMain   {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of vertices:");
        int v = sc.nextInt();
        Graph graph = new Graph(v);

        System.out.println("Enter number of edges:");
        int e = sc.nextInt();
        System.out.println("Enter edges (u v weight):");
        for (int i = 0; i < e; i++) {
            int u = sc.nextInt();
            int v1 = sc.nextInt();
            int w = sc.nextInt();
            graph.addEdge(u, v1, w);
        }
        
        System.out.println("Choose algorithm:\n1. Kruskal's MST\n2. Prim's MST\n3. Dijkstra's Shortest Path");
        int choice = sc.nextInt();
        
        switch (choice) {
            case 1:
                graph.kruskalMST();
                break;
            case 2:
                graph.primMST();
                break;
            case 3:
                System.out.println("Enter source vertex:");
                int src = sc.nextInt();
                graph.dijkstra(src);
                break;
            default:
                System.out.println("Invalid choice!");
        }
        sc.close();
    }
}
