// Students : 
// Name1 - Nada Rayyan  , Username - nadarayyan , ID - 318173606
// Name2 - Mohammad Khimel, Username - khimel , ID - 207001702


import java.util.concurrent.ThreadLocalRandom;
import javafx.util.Pair;
/**
*
*
*
* An implementation of algorithms for selection problem
* 
*
*/

public class selectProblems
{
	public static int COUNTS1 = 0;
	public static int COUNTS2 = 0;
	public static int COUNTS3 = 0;
	public static int COUNTS4 = 0;
	public static int COUNTS5 = 0;
	public static int COUNTS6 = 0;
	
	/**
	 *
	 * public Pair<Integer, Integer> selectRandQuickSort(int [] array, int k)
	 *  
	 *
	 *returns the k-th smallest number in array & # of comparisons
	 *
	 */	
  public Pair<Integer, Integer> selectRandQuickSort(int [] array, int k)
  {
	  randQSortREC(array,0,array.length-1);
	  int comps = COUNTS1;
	  COUNTS1 = 0;
    return new Pair<Integer, Integer>(array[k-1],comps);
  }
  /**
	 *
	 * public Pair<Integer, Integer> selectInsertionSort(int [] array, int k)
	 *
	 *returns the k-th smallest number in array & # of comparisons
	 *
	 */	
  
  public Pair<Integer, Integer> selectInsertionSort(int [] array, int k)
  {	
	  int n = array.length;
	  int count=0;
      for (int i = 1; i < n; ++i) { 
          int key = array[i]; 
          int j = i - 1; 
          
          while (j >= 0 && mySmall(key,array[j],2)) {
        	  count++;
              array[j + 1] = array[j]; 
              j = j - 1; 
          } 
          array[j + 1] = key; 
      }
    return new Pair<Integer, Integer>(array[k-1],count);
  }
  
  /**
	 *
	 *  public Pair<Integer, Integer> selectHeap(int [] array, int k)
	 *
	 *returns the k-th smallest number in array & # of comparisons
	 *
	 */
  public Pair<Integer, Integer> selectHeap(int [] array, int k)
  {
	  minHeap myHeap = new minHeap(array,array.length,3);
	  for(int i = 0 ; i<k-1 ; i++) {
		  myHeap.deleteMin1();
	  }
	  int count = COUNTS3;
	  COUNTS3 = 0;
    return new Pair<Integer, Integer>(myHeap.heapArr[0].key,count); 
  }
  
  
  /**
	 *
	 *  public Pair<Integer, Integer> selectDoubleHeap(int [] array, int k)
	 *
	 *returns the k-th smallest number in array & # of comparisons
	 *
	 */
  public Pair<Integer, Integer> selectDoubleHeap(int [] array, int k)
  {
	  int[] kMin = new int[k];
	  minHeap myHeap = new minHeap(array,array.length,4);
	  minHeap secHeap = new minHeap(new int[array.length], 0,4 );
	  secHeap.insert(myHeap.heapArr[0]);
	  for(int i =0 ; i<k ; i++) {
		  
		  heapNode node = secHeap.deleteMin2();
		  kMin[i] = node.key;
		  
		  if(myHeap.size > 1 && myHeap.left(node) <myHeap.size) {
		  secHeap.insert(myHeap.heapArr[myHeap.left(node)]);
		  }
		  if(myHeap.size > 2 && myHeap.right(node) <myHeap.size) {
		  secHeap.insert(myHeap.heapArr[myHeap.right(node)]);
		  }
	  }
	  int count = COUNTS4;
	  COUNTS4 = 0;
     return new Pair<Integer, Integer>(kMin[k-1],count);
  }
  
  /**
	 *
	 *  public Pair<Integer, Integer> randQuickSelect(int [] array, int k)
	 *
	 *returns the k-th smallest number in array & # of comparisons
	 */  
  
  public Pair<Integer, Integer> randQuickSelect(int [] array, int k)
  {
	  randQSelectREC(array,0,array.length-1,k-1);
	  int comps = COUNTS5;
	  COUNTS5 = 0;
    return new Pair<Integer, Integer>(array[k-1],comps); 
  }

  /**
	 *
	 *  public Pair<Integer, Integer> medOfMedQuickSelect(int [] array, int k)
	 * 
	 *returns the k-th smallest number in array & # of comparisons
	 *
	 */ 
  
  public Pair<Integer, Integer> medOfMedQuickSelect(int [] array, int k)
  {		
	 medOfMedREC(array,0,array.length-1,k-1);
	 int comps = COUNTS6 + COUNTS5;
	  COUNTS6 = 0;
	  COUNTS5=0;
    return new Pair<Integer, Integer>(array[k-1],comps);
  }
  
  
  
  
////////////////////////// All Of Our New Functions ////////////////////////
  
  /**
	 *
	 *  public void randQSortREC(int [] array, int min,int max)
	 * 
	 * recursive function used in randQuickSort
	 *	 
	 */
  public void randQSortREC(int [] array, int min,int max) { // calls RandPartition
	  int p =0;
	  if(min< max) {
		  p = RandPartition(array,min,max,1);
		  randQSortREC(array,min,p-1);
		  randQSortREC(array,p+1,max); 
	  }
  }
  
  /**
	 *
	 *  public void randQSelectREC(int[] array , int left, int right , int k)
	 * 
	 * recursive function used in randQuickSelect
	 *	 
	 */
  public void randQSelectREC(int[] array , int left, int right , int k){ // calls RandPartition
	  int p=0;
	  if(left<right) {
		  p = RandPartition(array,left,right,5);
		  if(p>k) {
			  randQSelectREC(array,left,p-1,k);
		  }
		  else {
		  randQSelectREC(array,p+1,right,k);
		  }
	  }
	  
  }
  
  /**
	 *
	 *  public void medOfMedREC(int[] array , int left, int right,int k)
	 * 
	 * recursive function used in medOfMedQuickSelect
	 *	
	 */
  public void medOfMedREC(int[] array , int left, int right,int k) { //calls medOfMedPartition
	  int p=0;
	  if(left<right) {
		  p = medOfMedPartition(array,left,right);
		  if(p>k) {
			  medOfMedREC(array,left,p-1,k);
		  }
		  else {
		  medOfMedREC(array,p+1,right,k);
		  }
	  }
  }
  
  /**
	 *
	 *  public int medOfMedPartition(int[] array, int left, int right)
	 * 
	 *
	 *	used for getting median of medians and does a partition by it as a pivot 
	 */
  public int medOfMedPartition(int[] array, int left, int right) { //calls getMedOfMed
	int medOfMed = getMedOfMed(array,left,right);
	return partitionByValue(array,left,right,medOfMed);
  }
 
  /**
	 *
	 *  public int getMedOfMed(int[] array, int left, int right)
	 * 
	 * , gets median of medians by splitting to tuples of 5's,
	 *  uses randQuickSelect for getting the median of array of length <=5
	 *	 
	 */
  public int getMedOfMed(int[] array, int left, int right) { 
	  
	  
	        if(right-left+1 <= 5)
	        {	
	        	randQSelectREC(array,left,right,(right-left)/2);
	            return array[(right-left)/2 + left ];
	        }
	        
	        int temp[] = null;
	        
	        int medians[] = new int[(int)Math.ceil((double)(right-left+1)/5)];
	        int medianIndex = 0;
	        
	        while(left <= right)
	        {
	            temp = new int[Math.min(5,right-left+1)];
	            
	            for(int j=0;j<temp.length && left <= right;j++)
	            {
	                temp[j] = array[left];
	                left++;
	            }
	            
	            medians[medianIndex] = randQuickSelect(temp, temp.length/2 +1).getKey();
	            medianIndex++;
	        }
	        
	        return getMedOfMed(medians,0,medians.length-1);
	    }
  
  /**
	 *
	 *  public int RandPartition(int []array, int min ,int max,int type)
	 * 
	 *  , does random partition on given array and (left,right)
	 *  
	 *	 
	 */
  public int RandPartition(int []array, int min ,int max,int type) {
	  int i = ThreadLocalRandom.current().nextInt(min,max+1); 
	  int temp = array[i];
	  array[i] = array[max];
	  array[max] = temp;
	  return partition(array,min,max,type);
  }
  
  /**
	 *
	 *  public int partition(int []array, int min ,int max,int type)
	 * 
	 *  does the actual partition given a pivot
	 *  
	 *	 
	 */
  public int partition(int []array, int min ,int max,int type) {
	  int i = min-1;
	
	  for(int j=min;j<=max-1;j++) {
		  if(mySmall(array[j],array[max],type)) {
			  i++;
			  int temp = array[i];
			  array[i] = array[j];
			  array[j] = temp;
		  }
	  }
		  int temp = array[i+1];
		  array[i+1] = array[max];
		  array[max] = temp;
		  return i+1;
  }
  
  /**
	 *
	 *  public int partitionByValue(int[] array,int low, int high,int pivot)
	 * 
	 *  does actual partition given the pivot as a value and not an index
	 *  
	 *	 
	 */
  public int partitionByValue(int[] array,int low, int high,int pivot) { /// partition used for medianQS
	  while(low < high)
      {
          while(mySmall(array[low], pivot,6))
          {
              low ++;
          }
          
          while(mySmall(pivot,array[high],6))
          {
              high--;
          }
          
          if(array[low] == array[high])
          {
              low ++;
          }
          else if(low < high)
          {
              int temp = array[low];
              array[low] = array[high];
              array[high] = temp;
          }
              
      }
      return high;
  
  }
  
/// simple Comparing method that increments the static comparison counter 
	public boolean mySmall(int a,int b, int m) { 
		  switch(m) {
		  case 1:
			  COUNTS1++;
			  return (a<b);
		  case 2:
			  COUNTS2++;
			  return (a<b);
		  case 3:
			  COUNTS3++;
			  return (a<b);
		  case 4:
			  COUNTS4++;
			  return (a<b);
		  case 5:
			  COUNTS5++;
			  return (a<b);
		  case 6:
			  COUNTS6++;
			  return (a<b);
		  }
		  return (a<b);
  }
	
	/**
	 *
	 *  public class minHeap
	 * 
	 *  implementation of minimun Heap
	 *  
	 *	 
	 */
	public class minHeap{ 
		private heapNode[] heapArr;
		private int size = 0;
		private int typez;
		
		public minHeap(int[] arr,int m , int type) {
			heapArr = new heapNode[arr.length];
			size = m;
			typez = type;
			
			for(int i = 0 ; i<m ; i++) { /////// converting each integer to a node
				heapArr[i] = new heapNode(arr[i],i);
			}
			if(size==0) {
				return;
			}
			for(int i = size/2  ; i>=0 ; i--) {
				heapfyDown(i);
			}
		}
		
		public int left(heapNode node) {
			return 2*(node.pos)+1;
		}
		public int right(heapNode node) {
			return 2*(node.pos) +2;
		}
		public int parent(heapNode node) {
			return (node.pos+1)/2 ;
		}
		
		public void heapfyDown(int i) {
			heapNode node = new heapNode(heapArr[i].key,i);
			int left = left(node) ;
			int right = right(node) ;
			int n = size;
			int smallest = i;
			if(left< n && mySmall(heapArr[left].key, heapArr[i].key , typez)) {
				smallest = left;
			}
			if(right<n && mySmall(heapArr[right].key , heapArr[smallest].key,typez)) {
				smallest = right;
			}
			if(smallest != i) { // swap
				int temp= (heapArr[i].key);
				heapArr[i] = new heapNode(heapArr[smallest].key,i);
				heapArr[smallest] = new heapNode(temp, smallest);
				heapfyDown(smallest);
			}
		}
		
		public void heapfyDown2(int i) {
			heapNode node = new heapNode(heapArr[i].key,i);
			int left = left(node) ;
			int right = right(node) ;
			int n = size;
			int smallest = i;
			if(left< n && mySmall(heapArr[left].key, heapArr[i].key , typez)) {
				smallest = left;
			}
			if(right<n && mySmall(heapArr[right].key , heapArr[smallest].key,typez)) {
				smallest = right;
			}
			if(smallest != i) { // swap
				heapNode temp= heapArr[i];
				heapArr[i] = heapArr[smallest];
				heapArr[smallest] = temp;
				heapfyDown2(smallest);
			}
		}
		
		public void heapfyUp(int i) {
			heapNode node = new heapNode(heapArr[i-1].key,i-1);
			int parent = parent(node) -1;
			int smallest = i-1;
			if(parent >=0 &&  mySmall(heapArr[i-1].key, heapArr[parent].key, typez)) {
				smallest = parent;
			}
			if(smallest != i-1) { // swap				
				heapNode temp= heapArr[i-1];
				heapArr[i-1] = heapArr[smallest];
				heapArr[smallest] = temp;
				heapfyUp(smallest+1);
			}	
		}
		
		public heapNode deleteMin1() {
			if(size<1) {
				return new heapNode(0,0);
			}
			heapNode min = heapArr[0];
			heapArr[0] = new heapNode(heapArr[size-1].key,0);
			size = size-1;
			heapfyDown(0);
			return min;
		}
		
		public heapNode deleteMin2() {
			if(size<1) {
				return new heapNode(0,0);
			}
			heapNode min = heapArr[0];
			heapArr[0] = new heapNode(heapArr[size-1].key,heapArr[size-1].pos);
			size = size-1;
			heapfyDown2(0);
			return min;
		}
		
		public void insert(heapNode node) {
			size++;
			heapArr[size-1] = node;
			heapfyUp(size);
		}
	}
	/**
	 *
	 *  public class heapNode
	 * 
	 *  implementation of heap node, 
	 *  used for maintaing the left and right in old tree (used in double heap )
	 *  
	 *	 
	 */
/////////////// class heap node ///////////////////////// 
//////// implemented  for getting left and right of node in doubleheap algorithm [by maintaining old position])////////
	
	public class heapNode{ 
		private int key;
		private int pos;

		public heapNode(int key , int pos) {
			this.key = key;
			this.pos = pos;
		}
	}
	
}