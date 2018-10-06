import java.util.ArrayList;
import java.util.List;

public class QuickSort {
    public static void main(String [] args) {
        List<Integer> my_list = new ArrayList<>();
        my_list.add(10);
        my_list.add(5);
        my_list.add(2);
        my_list.add(30);
        my_list.add(4);
        System.out.println(quickSort(my_list));
    }

    private static List<Integer> quickSort(List<Integer> my_list) {
        if (my_list.size() < 2) {
            return my_list;
        }

        else {
            int pivot = my_list.get(0);
            List<Integer> less = new ArrayList<>();
            List<Integer> greater = new ArrayList<>();

            for (int i : my_list.subList(1, my_list.size())) {
                if (i <= pivot) {
                    less.add(i);
                }

                else {
                    greater.add(i);
                }
            }

            List<Integer> result = quickSort(less);
            result.add(pivot);
            result.addAll(quickSort(greater));

            return result;
        }
    }
}
