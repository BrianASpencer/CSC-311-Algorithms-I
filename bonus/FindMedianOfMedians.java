import java.util.*;

public class FindMedianOfMedians { 

    // Function that sorts array and returns middle element. 
    static int findMedian(int a[], int i, int n) { 
    	Arrays.sort(a, i, i + n);
    	return a[i + n / 2]; // Return middle element
    } 
    
    // Function to find smallest element at index
    static int smallestAtIndex(int a[], int left, int right, int index) { 
    	// If index is smaller than 
    	// number of elements in array 
    	if (index > 0 && index <= right - left + 1) { 
    		int n = right - left + 1 ; // Number of elements in a[left..right] 
    
    		int i; 
    		
    		int []median = new int[(n + 4) / 5]; 
    
    		for (i = 0; i < n / 5; i++) {
    			median[i] = findMedian(a, left + (i * 5), 5); 
    		}	
    
    		if (i * 5 < n) { 
    			median[i] = findMedian(a, left + (i * 5), n % 5); 
    			i++; 
    		} 
    
            	int medOfMed = median[i - 1];
    
    		if (i != 1)
    		     medOfMed = smallestAtIndex(median, 0, i - 1, i / 2);
    
    		int pos = partition(a, left, right, medOfMed); 
    
    		if (pos - left == index - 1) 
    			return a[pos]; 
    
    		if (pos - left > index - 1) // If position is more, recur for left 
    			return smallestAtIndex (a, left, pos - 1, index); 
    
    		return smallestAtIndex (a, pos + 1, right, index - pos + left - 1); 
    	} 
    
    	// If index is more than number of elements in array 
    	return -1234567; 
    } 
    
    
    // Function that searches for x in the array bounds of left to right and partitions the array around x. 
    static int partition(int[] a, int left, int right, int x) { 
    	int i; 
    
      	int temp;
    
    	for (i = left; i < right; i++) {
    		if (a[i] == x) 
    		  break; 
    	}
    
    	//swapping a[i] and a[right]
      	temp = a[i]; 
    	a[i] = a[right]; 
    	a[right] = temp; 
    
    	// Standard partition algorithm 
    	i = left; 
    	for (int j = left; j < right; j++) { 
    		if (a[j] <= x) { 
    		   //swapping a[i] and a[j]
              	   temp = a[i]; 
              	   a[i] = a[j]; 
              	   a[j] = temp; 
    		   i++; 
    		} 
    	} 
    
    	//swapping a[i] and a[right]
      	temp = a[i]; 
    	a[i] = a[right]; 
    	a[right] = temp;
    
    	return i; 
    } 
    public static void main(String[] args) { 
    
    	int testArray[] = {8, 1, 2, 45, 2, 11, 86, 21, 22, 11, 3, 4, 99, 34, 1294, 13, 1100};
    
    	int n = testArray.length; 
    
    	int medIndex = n / 2;
    	
    	if (n % 2 == 0) {
          	int x = smallestAtIndex(testArray, 0, n - 1, medIndex);
          	int y = smallestAtIndex (testArray, 0, n - 1, (medIndex + 1));
          	if (x != -1234567 && y != -1234567)
               System.out.println("Median is "+(double)(x + y)/2);
          	else 
              	System.out.println("Error -- list is empty!");
       	} else {
        	  medIndex = (n / 2) + 1;
          	  int median = smallestAtIndex (testArray, 0, n - 1, medIndex);
          	  if (median != -1234567)
                System.out.println("Median is "+median);
          	  else
                System.out.println("Error -- list is empty!"); 
        }
    
    }
 
}