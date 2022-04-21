public final class MaxHeap<T extends Comparable<? super T>> implements MaxHeapInterface<T>{
    
    private final T[] heap;//the array representing the heap
    private int lastIndex;//the index of the last element
    private boolean initialized = false;//the status of the array
    private static final int DEFAULT_CAPACITY = 25;
    private static final int MAX_CAPACITY = 10000;

    private int swapCounter = 0;//the counter keeping track of swaps while heapifying

    /**
     * Default constructor for a heap
     */
    public MaxHeap(){
        this(DEFAULT_CAPACITY);
    }

    /**
     * Heap constructor with capacity
     * @param initialCapacity the capacity
     */
    public MaxHeap(int initialCapacity){
        if (initialCapacity < DEFAULT_CAPACITY)
            initialCapacity = DEFAULT_CAPACITY;
        else
            checkCapacity(initialCapacity);

        @SuppressWarnings("unchecked")
        T[] tempHeap = (T[]) new Comparable[initialCapacity + 1];
        heap = tempHeap;
        lastIndex = 0;
        initialized = true;
    }

    /**
     * Heap constructor that builds a heap from an existing array
     * @param entries the existing array
     */
    public MaxHeap(T[] entries){
        this(entries.length);
        lastIndex = entries.length;
        assert initialized = true;
 
        for(int index = 0; index < entries.length; index++)
            heap[index + 1] = entries[index];
        
        for(int rootIndex = lastIndex / 2; rootIndex > 0; rootIndex --)
            reheap(rootIndex);
    }

    /**
     * Adds a new entry to a heap at the proper index
     * @param newEntry the entry to be added
     */
    public void add(T newEntry){

        checkInitialization();
        int newIndex = lastIndex + 1;
        int parentIndex = newIndex / 2;
        
        while ( (parentIndex > 0) && newEntry.compareTo(heap[parentIndex]) > 0){
            heap[newIndex] = heap[parentIndex];
            newIndex = parentIndex;
            parentIndex = newIndex / 2;

            swapCounter++; //counts each time a swap has occured
        }

        heap[newIndex] = newEntry;
        lastIndex++;
        ensureCapacity();
    }

    /**
     * Removes the max element (root) of a heap and reheapifys
     */
    public T removeMax(){

        checkInitialization();
        T root = null;

        if (!isEmpty()){
            root = heap[1];
            heap[1] = heap[lastIndex];
            lastIndex--;
            reheap(1);
        }

        return root;
    }

    /**
     * Returns the nax element (root) of a heap
     */
    public T getMax(){

        checkInitialization();
        T root = null;
        if (!isEmpty())
            root = heap[1];

        return root;
    }

    /**
     * Returns if a heap has elements
     */
    public boolean isEmpty(){

        return lastIndex < 1;
    }

    /**
     * Returns the number of elements in the heap
     */
    public int getSize(){

        return lastIndex;
    }

    /**
     * Removes all elements from the heap
     */
    public void clear(){

        checkInitialization();
        while (lastIndex > -1){
            heap[lastIndex] = null;
            lastIndex--;
        }

        lastIndex = 0;
    }

    /**
     * checks if the heap array has exceeded its capacity
     * @param topIndex the highest index in the heap
     */
    public void checkCapacity(int topIndex){
        if (topIndex > MAX_CAPACITY)
            throw new SecurityException("Array MaxHeap has exceeded maximum capacity.");
    }

    /**
     * Ensures the heap array is initialized
     */
    public void checkInitialization(){
        if (!this.initialized){
            throw new SecurityException("Array MaxHeap is corrupt.");
        }
    }

    public void ensureCapacity(){
        /**
         * Ensures the heap has enough capacity
         */
        if ((lastIndex * 2) > heap.length)
            throw new SecurityException("Array MaxHeap not currently large enough.");
    }


    /**
     * Heapifys the heap, ensuring leafs are of lower numberic value than parents
     * @param rootIndex the index to start heapifying
     */
    private void reheap(int rootIndex) {
        boolean done = false;
        T orphan = heap[rootIndex];
        int leftChildIndex = 2 * rootIndex;

        while (!done && (leftChildIndex <= lastIndex)) {
            int largerChildIndex = leftChildIndex;
            int rightChildIndex = leftChildIndex + 1;
            if ((rightChildIndex <= lastIndex) && heap[rightChildIndex].compareTo(heap[largerChildIndex]) > 0) {
                largerChildIndex = rightChildIndex;
            }
            if (orphan.compareTo(heap[largerChildIndex]) < 0) {
                heap[rootIndex] = heap[largerChildIndex];
                rootIndex = largerChildIndex;
                leftChildIndex = 2 * rootIndex;
                swapCounter++;//counts each time a swap has occured
            } else
                done = true;
        }
        heap[rootIndex] = orphan;
    }

    /**
     * Prints the heap to screen
     * @param x the number of entries to print
     */
    public void toString(int x){
        for(int i = 1; i <= x; i++)
            System.out.print(heap[i] + ",");
    }

    /**
     * Counts the number of times a swap has occured while adding or heapifying.
     * @return the count of swaps
     */
    public int getSwap(){
        return swapCounter;
    }

}
