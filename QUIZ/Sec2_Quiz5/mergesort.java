import java.io.*;
import java.util.Scanner;

public class MedianSort{
    private int[] a;
    private int[] tempA;
    private int length;

    public MedianSort(int[] array) {
    		this.a = array;
    		this.length = array.length;
    		this.tempA = new int[length];
    }

     /* The following mergeSort part has been already implemented. You just need to
     * implement merge part.
     *
     * Some clarifications:
     * low: represents the index of the first element to be sorted
     * high: represents the index of the last element to be sorted
    */
    public void mergeSort(int low, int high) {
		    // Base case:
        if(low >= high) {
            return;
        }
        int mid = low + (high - low) / 2;
        mergeSort(low, mid);
        mergeSort(mid + 1, high);
        merge(low, mid, high);
     }

     //First Solution of merge: [Textbook]
     public void merge_1(int low, int mid, int high){

		     //TODO:: Implement merge
        for(int k = low; k <= high; k++) {
            tempA[k] = a[k];
        }

        int left = low;
        int right = mid + 1;

        for(int k = low; k <= high; k++){
            // Nothing left on the left
            if (left > mid) {
                a[k] = tempA[right++];
            // Nothing left on the right
            } else if (right > high) {
                a[k] = tempA[left++];
            // Right element is smaller
            } else if (tempA[right] < tempA[left]) {
                a[k] = tempA[right++];
            // Left element is smaller than or equals to the right element
            }else {
                a[k] = tempA[left++];
            }
        }
	   }


     //Second Solution of merge:
     public void merge_2(int low, int mid, int high){

		     //TODO:: Implement merge
        for(int k = low; k <= high; k++) {
            tempA[k] = a[k];
        }

        int left = low;
        int right = mid + 1;

        while (left <= mid && right <= high) {
            if (tempA[left] <= tempA[right]) {
                a[low++] = tempA[left++];
            } else {
                a[low++] = tempA[right++];
            }
        }
        // Nothing left on the right
        while (left <= mid) {
            a[low++] = tempA[left++];
        }
        // We do not have to copy the rest element on the right since at this moment there are no elements
        // on the left and all rest elements on the right are already ordered in the array.
	   }


    public double findMedian(int low, int high){
        double median = 0.0;
        if (length == 0) {
            return median;
        }
        mergeSort(low, high);
        if (length % 2 == 0){
            median = (a[length / 2] + a[length / 2 - 1]) / 2.0;
        } else {
            median = a[length / 2];
        }
        return median;
    }

    public static void main(String args[] ) throws Exception {
        Scanner in = new Scanner(System.in);
        int sz = in.nextInt();
        int[] distribution = new int[sz];
        for (int i = 0; i < sz; i++) {
            distribution[i] = in.nextInt();
        }
        MedianSort ms = new MedianSort(distribution);
        System.out.println(ms.findMedian(0,distribution.length-1));
      }
    }
