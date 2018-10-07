import java.util.*;

public class BreadthFirstSearch {
    public static void main(String [] args) {
        HashMap<String, List<String>> graph = new HashMap<>();
        graph.put("Thom", null);
        graph.put("Jonny", null);
        graph.put("Claire", Arrays.asList("Thom", "Jonny"));
        graph.put("Anuj", null);
        graph.put("Peggy", null);
        graph.put("Bob", Arrays.asList("Anuj", "Peggy"));
        graph.put("Me", Arrays.asList("Claire", "Bob"));

        bfs(graph);
    }

    /**
     * Find someone who sells melon. A person sells melon if their name ends in m.
     * @param graph
     */
    private static void bfs(HashMap<String, List<String>> graph) {
        Queue<String> queue = new LinkedList<>();
        queue.addAll(graph.get("Me"));
        
        List<String> searchedNeighbors = new ArrayList<>();
        
        while (!queue.isEmpty()) {
            String person = queue.remove();
            
            if (!searchedNeighbors.contains(person)) {
                if (person_is_seller(person)) {
                    System.out.println(person + " is a seller!");
                }

                else {
                    List<String> neighbors = graph.get(person);

                    if (neighbors != null) {
                        queue.addAll(graph.get(person));
                    }

                    searchedNeighbors.add(person);
                }
            }
        }
    }

    private static boolean person_is_seller(String person) {
        return person.endsWith("m");
    }
}

