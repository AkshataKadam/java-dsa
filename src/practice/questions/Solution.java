package practice.questions;

import java.util.*;

public class Solution {

    public long getMaximumEfficiency(int connect_nodes, ArrayList<Integer> connect_from,
                                     ArrayList<Integer> connect_to, ArrayList<Integer> computer_val, int k) {

        // FIX: Use List<List<Integer>> instead of ArrayList array
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= connect_nodes; i++) {
            adj.add(new ArrayList<>());
        }

        // Add edges to adjacency list (undirected)
        for (int i = 0; i < connect_from.size(); i++) {
            adj.get(connect_from.get(i)).add(connect_to.get(i));
            adj.get(connect_to.get(i)).add(connect_from.get(i));
        }

        // Start DFS from root (node 1)
        boolean[] visited = new boolean[connect_nodes + 1];
        return dfs(1, adj, computer_val, k, visited);
    }

    private long dfs(int node, List<List<Integer>> adj, ArrayList<Integer> computer_val, int k, boolean[] visited) {
        visited[node] = true;

        // Calculate sum if we keep this entire subtree
        long subtreeSum = computer_val.get(node - 1); // Current node's value

        // Process all children and add their optimal contributions
        for (int child : adj.get(node)) {
            if (!visited[child]) {
                long childOptimalContribution = dfs(child, adj, computer_val, k, visited);
                subtreeSum += childOptimalContribution;
            }
        }

        // Decision: keep this entire subtree OR remove it completely
        return Math.max(subtreeSum, -k);
    }
}
