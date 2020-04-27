public class Solution {

    public int binarySearch(int array[], int key) {
        //TODO:: implement binary search
        // Corner case:
        if(array == null || array.length == 0) {
          return -1;
        }

        int left = 0;
        int right = array.length - 1;

        while(left <= right) {
          
          int mid = left + (right - left) / 2;

          if(array[mid] > key) {
            left = mid + 1;
          } else if(array[mid] < key) {
            right = mid - 1;
          } else {
            return mid;
          }
        }
        return -1;
    }
}
