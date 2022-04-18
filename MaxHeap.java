public final class MaxHeap<T extends Comparable<? super T>> implements MaxHeapInterface<T>{
    
    private final T[] heap;
    private int lastIndex;
    private boolean initialized = false;
    private static final int DEFAULT_CAPACITY = 25;
    private static final int MAX_CAPACITY = 10000;

    private int swapCounter = 0;

    public MaxHeap(){
        this(DEFAULT_CAPACITY);
    }

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

    public MaxHeap(T[] entries){
        this(entries.length);
        lastIndex = entries.length;
        assert initialized = true;
 
        for(int index = 0; index < entries.length; index++)
            heap[index + 1] = entries[index];
        
        for(int rootIndex = lastIndex / 2; rootIndex > 0; rootIndex --)
            reheap(rootIndex);
    }

    // the sequential add method
    public void add(T newEntry){

        checkInitialization();
        int newIndex = lastIndex + 1;
        int parentIndex = newIndex / 2;
        
        while ( (parentIndex > 0) && newEntry.compareTo(heap[parentIndex]) > 0){
            heap[newIndex] = heap[parentIndex];
            newIndex = parentIndex;
            parentIndex = newIndex / 2;

            swapCounter++;
        }

        heap[newIndex] = newEntry;
        lastIndex++;
        ensureCapacity();
    }

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

    public T getMax(){

        checkInitialization();
        T root = null;
        if (!isEmpty())
            root = heap[1];

        return root;
    }

    public boolean isEmpty(){

        return lastIndex < 1;
    }

    public int getSize(){

        return lastIndex;
    }

    public void clear(){

        checkInitialization();
        while (lastIndex > -1){
            heap[lastIndex] = null;
            lastIndex--;
        }

        lastIndex = 0;
    }

    public void checkCapacity(int topIndex){
        if (topIndex > MAX_CAPACITY)
            throw new SecurityException("Array MaxHeap has exceeded maximum capacity.");
    }

    public void checkInitialization(){
        if (!this.initialized){
            throw new SecurityException("Array MaxHeap is corrupt.");
        }
    }

    public void ensureCapacity(){
        /* Are we using this to make sure we have enough room for another child on a certain leaf?
        ** If so should we use the formula to make sure that there is enough room in the array
        ** to add a child based on the parents position in the array?
        */
        if ((lastIndex * 2) > heap.length)
            throw new SecurityException("Array MaxHeap not currently large enough.");
    }


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
                swapCounter++;
            } else
                done = true;
        }
        heap[rootIndex] = orphan;
    }

    public void toString(int x){
        for(int i = 1; i <= x; i++)
            System.out.print(heap[i] + ",");
    }

    public int getSwap(){
        return swapCounter;
    }

}
