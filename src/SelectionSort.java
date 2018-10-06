import java.util.ArrayList;
import java.util.List;

public class SelectionSort {
    public static void main(String [] args) {
        List<Integer> my_list = new ArrayList<>();
        my_list.add(5);
        my_list.add(3);
        my_list.add(6);
        my_list.add(2);
        my_list.add(10);
        System.out.println(selectionSort(my_list));
    }

    private static List<Integer> selectionSort(List<Integer> my_list) {
        List<Integer> newList = new ArrayList<>();
        int size = my_list.size();

        for (int i = 0; i < size; i++) {
            int smallestIndex = findSmallestIndex(my_list);
            newList.add(my_list.remove(smallestIndex));
        }

        return newList;
    }

    private static int findSmallestIndex(List<Integer> my_list) {
        int smallest = my_list.get(0);
        int smallest_index = 0;

        for (int i = 1; i < my_list.size(); i++) {
            if (my_list.get(i) < smallest) {
                smallest = my_list.get(i);
                smallest_index = i;
            }
        }

        return smallest_index;
    }
}
