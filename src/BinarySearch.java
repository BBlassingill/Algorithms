public class BinarySearch {
    public static void main(String[] args) {
        int[] my_list = {1, 3, 5, 7, 9};
        System.out.println(binary_search(my_list, 3));
        System.out.println(binary_search(my_list, 8));
    }

    /**
     * The binary_search function takes a sorted array and an item. If the item is in
     * the array, the function returns its position.
     * @param my_list
     * @param element
     * @return
     */
    private static Integer binary_search(int[] my_list, int element) {
        int low = 0;
        int high = my_list.length - 1;

        while (low <= high) {
            int mid = (low + high)/2;
            int guess = my_list[mid];

            if (guess == element) {
                return mid;
            }

            if (guess > element) {
                high = mid - 1;
            }else {
                low = mid + 1;
            }
        }

        return null;
    }
}
