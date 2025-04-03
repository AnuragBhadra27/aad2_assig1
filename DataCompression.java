import java.util.*;

// Node class for Huffman tree
class HuffmanNode {
    char ch;
    int freq;
    HuffmanNode left, right;

    HuffmanNode(char ch, int freq) {
        this.ch = ch;
        this.freq = freq;
    }
}

// Comparator for priority queue
class HuffmanComparator implements Comparator<HuffmanNode> {
    public int compare(HuffmanNode a, HuffmanNode b) {
        return a.freq - b.freq;
    }
}

public class DataCompression {

    // Brute-force compression: Simple Run-Length Encoding (RLE)
    public static String bruteForceCompress(String input) {
        StringBuilder compressed = new StringBuilder();
        int n = input.length();
        
        for (int i = 0; i < n; i++) {
            int count = 1;
            while (i < n - 1 && input.charAt(i) == input.charAt(i + 1)) {
                count++;
                i++;
            }
            compressed.append(input.charAt(i)).append(count);
        }
        return compressed.toString();
    }

    // Huffman Encoding Compression
    public static void huffmanEncode(String input) {
        // Frequency map
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char ch : input.toCharArray()) {
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
        }

        // Priority queue
        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>(new HuffmanComparator());
        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            pq.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }

        // Building Huffman Tree
        while (pq.size() > 1) {
            HuffmanNode left = pq.poll();
            HuffmanNode right = pq.poll();
            HuffmanNode newNode = new HuffmanNode('-', left.freq + right.freq);
            newNode.left = left;
            newNode.right = right;
            pq.add(newNode);
        }

        // Generating Huffman Codes
        HuffmanNode root = pq.poll();
        Map<Character, String> huffmanCodes = new HashMap<>();
        generateHuffmanCodes(root, "", huffmanCodes);

        // Encoding the input
        StringBuilder encodedString = new StringBuilder();
        for (char ch : input.toCharArray()) {
            encodedString.append(huffmanCodes.get(ch));
        }

        // Display results
        System.out.println("Huffman Codes: " + huffmanCodes);
        System.out.println("Encoded Data: " + encodedString);
    }

    // Helper method to generate Huffman Codes
    private static void generateHuffmanCodes(HuffmanNode root, String code, Map<Character, String> huffmanCodes) {
        if (root == null) return;
        if (root.ch != '-') {
            huffmanCodes.put(root.ch, code);
        }
        generateHuffmanCodes(root.left, code + "0", huffmanCodes);
        generateHuffmanCodes(root.right, code + "1", huffmanCodes);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the string to compress:");
        String input = scanner.nextLine();
        
        System.out.println("Choose compression method:\n1. Brute Force (RLE)\n2. Huffman Coding");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline
        
        switch (choice) {
            case 1:
                System.out.println("Compressed using Brute Force (RLE): " + bruteForceCompress(input));
                break;
            case 2:
                huffmanEncode(input);
                break;
            default:
                System.out.println("Invalid choice!");
        }

        scanner.close();
    }
}
