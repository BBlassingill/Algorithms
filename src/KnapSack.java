import java.util.HashMap;
import java.util.Map;

/**
 * Suppose you’re going camping. You have a knapsack that will hold
 6 lb, and you can take the following items. Each has a value, and the
 higher the value, the more important the item is:
 • Water, 3 lb, 10
 • Book, 1 lb, 3
 • Food, 2 lb, 9
 • Jacket, 2 lb, 5
 • Camera, 1 lb, 6
 What’s the optimal set of items to take on your camping trip?
 */
public class KnapSack {
    private static final int NUM_ITEMS = 5;
    private static final int MAX_WEIGHT = 6;

    private static Map<String, Integer> item_values;
    public static void main(String[] args) {

        /*associations
        Water - 0
        Book - 1
        Food - 2
        Jacket - 3
        Camera - 4
        */
        Map<Integer, String> index_associations = new HashMap<>();
        index_associations.put(0, "Water");
        index_associations.put(1, "Book");
        index_associations.put(2, "Food");
        index_associations.put(3, "Jacket");
        index_associations.put(4, "Camera");

        item_values = setStringIntegerMap();

        Map<String, Integer> item_weights = new HashMap<>();
        item_weights.put("Water", 3);
        item_weights.put("Book", 1);
        item_weights.put("Food", 2);
        item_weights.put("Jacket", 2);
        item_weights.put("Camera", 1);



        //Create a 2D grid
        int pound_index = 0;
        int item_index = 0;
        //Tuple<Integer, String>[][] grid2 = new Tuple<Integer, String>[NUM_ITEMS][MAX_WEIGHT];
        Tuple [][] grid = new Tuple[NUM_ITEMS][MAX_WEIGHT];
        //int[][] grid = new int[NUM_ITEMS][MAX_WEIGHT];

        for (String item : item_weights.keySet()) {
            for (int j = 0; j < grid[0].length; j++) {
                int pounds = j + 1;

                if (item_weights.get(item) <= pounds) {
                    //grid[item_index][j] = item_values.get(item);
                    if (item_index == 0){
                        grid[item_index][j] = new Tuple(item_values.get(item), item);
                    }

                    else if (j == 0) {
                        //grid[item_index][j] = Math.max(grid[item_index-1][j], item_values.get(item));
                        grid[item_index][j] = new Tuple(Math.max(grid[item_index-1][j].value, item_values.get(item)), item);
                    }

                    else {
                        //int remaining_weight = MAX_WEIGHT - j;
                        //grid[item_index][j] = Math.max(grid[item_index-1][j], item_values.get(item) + grid[item_index-1][j-1]);
                        int remaining_weight = pounds-item_weights.get(item);

                        if(remaining_weight == 0) {
                            //compare current item to previous max
                            grid[item_index][j] = maxTuple(grid[item_index-1][j], item);
                        }

                        else {
                            int remaining_weight_index = remaining_weight-1;
                            Tuple tupleAbove = grid[item_index-1][j];

                            //int remaining_weight = pounds-item_weights.get(item);
                            Tuple maxTupleForRemainingWeight = grid[item_index-1][remaining_weight_index];

                            Tuple newTup = maxTupleWithRemainingWeight(tupleAbove, maxTupleForRemainingWeight, item);
                            grid[item_index][j] = newTup;
                        }
                    }
                }

                else {
                    if (item_index == 0) {
                        grid[item_index][j] = new Tuple(0, "");
                    }

                    else {
                        grid[item_index][j] = grid[item_index-1][j];
                    }

                }
            }
            item_index++;
        }

        String finalAnswer = grid[NUM_ITEMS-1][MAX_WEIGHT-1].item;
        System.out.println(finalAnswer);
        //process grid in 1 1b increments

//        for(int i = 0; i < grid.length; i++) {
//            for (int j = 0; j < grid[0].length; j++) {
//                int pounds = i + 1;
//
//                for (String item : item_weights.keySet()) {
//                    if (item_weights.get(item) <= pounds) {
//
//                    }
//                }
//            }
//        }
    }

    private static Map<String, Integer> setStringIntegerMap() {
        Map<String, Integer> item_values = new HashMap<>();

        item_values.put("Water", 10);
        item_values.put("Book", 3);
        item_values.put("Food", 9);
        item_values.put("Jacket", 5);

        item_values.put("Camera", 6);
        return item_values;
    }

    private static Tuple maxTupleWithRemainingWeight(Tuple tupleAbove, Tuple maxTupleForRemainingWeight, String currentItem) {
        int max_value = Math.max(tupleAbove.value, item_values.get(currentItem) + maxTupleForRemainingWeight.value);

        //item in above row wins so return it
        if (tupleAbove.value == (max_value)) {
            return tupleAbove;
        }

        //return combination of items
        else {
            return new Tuple(max_value, maxTupleForRemainingWeight.item + ", " + currentItem);
        }
    }

    private static Tuple maxTuple(Tuple tuple, String currentItem) {
        int max_value = Math.max(tuple.value, item_values.get(currentItem));

        //item in above row wins so return it
        if (item_values.get(currentItem).equals(max_value)) {
            return new Tuple(max_value, currentItem);
        }

        //return combination of items
        else {
            return tuple;
        }
    }
}

class Tuple {
    public final int value;
    public final String item;

    public Tuple(int value, String item) {
        this.value =  value;
        this.item = item;
    }
}
