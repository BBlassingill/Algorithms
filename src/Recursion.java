import java.util.ArrayList;
import java.util.List;

public class Recursion {
    public static void main(String[] args) {
        List<Integer> my_list = new ArrayList<>();
        my_list.add(5);
        my_list.add(3);
        my_list.add(6);
        System.out.println(sum(my_list));
        System.out.println(numItems(my_list));
        System.out.println(maxNumber(my_list));
    }

    private static int maxNumber(List<Integer> my_list) {
        if (my_list.size() == 1) {
            return my_list.get(0);
        }

        else {
            return Math.max(my_list.get(0), maxNumber(my_list.subList(1, my_list.size())));
        }
    }

    private static int numItems(List<Integer> my_list) {
        if (my_list.size() == 0) {
            return 0;
        }

        else {
            return 1 + numItems(my_list.subList(1, my_list.size()));
        }
    }

    private static int sum(List<Integer> my_list) {
        if (my_list.size() == 0) {
            return 0;
        }

        else {
            return my_list.get(0) + sum(my_list.subList(1, my_list.size()));
        }
    }
}
