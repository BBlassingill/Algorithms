import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Suppose you’re starting a radio show. You want to
 reach listeners in a specific set of states. You have to decide what
 stations to play on to reach all those listeners. It costs
 money to be on each station, so you’re trying to minimize the
 number of stations you play on.

 This is an example of an approximation algorithm that will work
 faster than the exact algorithm that gives the most optimal
 solution. The exact algorithm to give the most optimal solution
 is to calculate every possible subset of states which is NP-complete.
 */
public class SetCovering {
    public static void main(String[] args) {
        Set<String> states_needed = Stream.of("Montana", "Washington", "Oregon", "Idaho", "Nevada", "Utah", "California", "Arizona").collect(Collectors.toSet());
        Map<String, Set> stations = new HashMap<>();
        Set<String> final_stations = new HashSet<>();

        stations.put("kone", Stream.of("Idaho", "Nevada", "Utah").collect(Collectors.toSet()));
        stations.put("ktwo", Stream.of("Washington", "Idaho", "Montana").collect(Collectors.toSet()));
        stations.put("kthree", Stream.of("Oregon", "Nevada", "California").collect(Collectors.toSet()));
        stations.put("kfour", Stream.of("Nevada", "Utah").collect(Collectors.toSet()));
        stations.put("kfive", Stream.of("California", "Arizona").collect(Collectors.toSet()));

        while (!states_needed.isEmpty()) {
            String best_station = null;
            Set<String> states_covered = new HashSet<>();

            for (Map.Entry<String, Set> entry : stations.entrySet()) {
                String station = entry.getKey();
                Set states_for_station = entry.getValue();

                Set<String> covered = new HashSet<>(states_needed);
                covered.retainAll(states_for_station); //covered is the set of uncovered states that this station covers!

                //now check whether this station covers more states than the current best_station
                if (covered.size() > states_covered.size()) {
                    best_station = station;
                    states_covered = covered;
                }
            }

            final_stations.add(best_station);
            states_needed.removeAll(states_covered);
        }

        System.out.println(final_stations);
    }
}
