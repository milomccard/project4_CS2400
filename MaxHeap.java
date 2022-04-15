public final class MaxHeap<T extends Comparable<? super T>> implements MaxHeapInterface<T>{
    
    private final T[] heap;
    private int lastIndex;
    private boolean initialized = false;
    private static final int DEFAULT_CAPACITY = 25;
    private static final int MAX_CAPACITY = 10000;

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

    // the sequential add method
    public void add(T newEntry){

        checkInitialization();
        int newIndex = lastIndex + 1;
        int parentIndex = newIndex / 2;
        
        while ( (parentIndex > 0) && ((Object) newEntry).compareTo(heap[parentIndex]) > 0){
            heap[newIndex] = heap[parentIndex];
            newIndex = parentIndex;
            parentIndex = newIndex / 2;
        }

        heap[newIndex] = newEntry;
        lastIndex++;
        ensureCapacity();
    }

    public void smartAdd(T newEntryT){

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
            throw new SecurityException("Array MaxHeap is corrupt");
    }

    public void checkInitialization(){
        if (!this.initialized){
            // throw new Exception();
        }
    }

    public void ensureCapacity(){

    }

    private void reheap(int rootIndex){

        boolean done = false;
        T orphan = heap[rootIndex];
        int leftChildIndex = 2 * rootIndex;

        while (!done && (leftChildIndex <= lastIndex)){
            int largerChildIndex = leftChildIndex;
            int rightChildIndex = leftChildIndex + 1;

            if ((rightChildIndex <= lastIndex) && heap[rightChildIndex].compareTo(heap[largerChildIndex]) < 0){
                largerChildIndex = rightChildIndex;
            }

            if (orphan.compareTo(heap[largerChildIndex]) < 0){
                heap[rootIndex] = heap[largerChildIndex];
                rootIndex = largerChildIndex;
                leftChildIndex = 2 * rootIndex;
            }
            else    
                done = true;
        }
        heap[rootIndex] = orphan;
    }
}
