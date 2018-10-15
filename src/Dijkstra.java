import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Dijkstra {
    public static void main(String [] args) {
        HashMap<String, HashMap> graph = getGraphMap();
        HashMap<String, Integer> costs = getCostMap();
        HashMap<String, String> parents = getParentsMap();

        List<String> processed = new ArrayList<>();
        processed.add("start");
        String node = find_lowest_cost_node(costs, processed);

        while (!node.equals("none")) {
            int cost = costs.get(node);
            HashMap<String, Integer> neighbors = graph.get(node);

            for (String n : neighbors.keySet()) {
                int new_cost = cost + neighbors.get(n);
                if (new_cost < costs.get(n)) {
                    costs.put(n, new_cost);
                    parents.put(n, node);
                }
            }

            processed.add(node);
            node = find_lowest_cost_node(costs, processed);
        }


        System.out.println(processed);
    }

    private static String find_lowest_cost_node(HashMap<String, Integer> costs, List<String> processed) {
        int lowest_cost = Integer.MAX_VALUE;
        String lowest_cost_node = "none";

        for (String node : costs.keySet()) {
            int cost = costs.get(node);

            if (cost < lowest_cost && !processed.contains(node)) {
                lowest_cost = cost;
                lowest_cost_node = node;
            }
        }

        return lowest_cost_node;
    }

    private static HashMap<String, String> getParentsMap() {
        HashMap<String, String> parents = new HashMap<>();
        parents.put("A", "start");
        parents.put("B", "start");
        parents.put("fin", "none");
        return parents;
    }

    private static HashMap<String, Integer> getCostMap() {
        HashMap<String, Integer> costs = new HashMap<>();
        costs.put("A", 6);
        costs.put("B", 2);
        costs.put("fin", Integer.MAX_VALUE);
        return costs;
    }

    private static HashMap<String, HashMap> getGraphMap() {
        HashMap<String, HashMap> graph = new HashMap<>();

        graph.put("start", new HashMap<String, Integer>() {
            {
                put("A", 6);
                put("B", 2);
            }
        });

        graph.put("A", new HashMap<String, Integer>() {
            {
                put("fin", 1);
            }
        });

        graph.put("B", new HashMap<String, Integer>() {
            {
                put("A", 3);
                put("fin", 5);
            }
        });

        graph.put("fin", new HashMap<String, Integer>());
        return graph;
    }
}
