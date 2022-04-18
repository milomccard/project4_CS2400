import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * A driver class to show the MaxHeap class in action as we intake two text
 * files and run them each on both of our methods used to build a max heap.
 * We will intake data_random.txt and data_sorted.txt then use them each in 
 * both methods to build max heaps. The program is based on the array implementation
 * of max heaps and makes use of generics.
 */
public class DriverMaxHeap{

    
    
    public static void main(String[] args) throws FileNotFoundException{


        String fileName = "data_sorted.txt";

        System.out.println("\n\n****************** Heap Generation ******************");
        
        generateHeap(fileName);

        fileName = "data_random.txt";

        generateHeap(fileName);


        

        

    }

    static void generateHeap(String fileName) throws FileNotFoundException{
        System.out.println("\nFile name: " + fileName);
        sequentialInsertion(fileName);
        optimalMethod(fileName);
    }

    static void sequentialInsertion(String fileName) throws FileNotFoundException{
        Scanner scan = new Scanner(new File(fileName));
        MaxHeap<Integer> heap = new MaxHeap<>(200);
        while(scan.hasNextInt())
            heap.add(scan.nextInt());
        scan.close();

        int iterations = 10;

        System.out.print("\n\tHeap built using sequential insertions: ");
        heap.toString(iterations);
        System.out.print("...\n\n");

        System.out.println("\tNumber of swaps in the heap creation: " + heap.getSwap() + "\n");

        for(int i = 0; i < iterations; i++){
            heap.removeMax();
        }

        System.out.print("\tHeap after " + iterations + " removals: ");
        heap.toString(iterations);
        System.out.print("...");
        System.out.println("\n\n");
    }

    static void optimalMethod(String fileName) throws FileNotFoundException{
        File file = new File(fileName);
        Scanner scan = new Scanner(file);
        Integer[] arr = new Integer[100];
        int counter = 0;
        int iterations = 10;

        while(scan.hasNextInt())
            arr[counter++] = scan.nextInt();
        scan.close();

        MaxHeap<Integer> heap = new MaxHeap<>(arr);

        System.out.print("\n\tHeap built using optimal method: ");
        heap.toString(iterations);
        System.out.print("...\n\n");

        System.out.println("\tNumber of swaps in the heap creation: " + heap.getSwap() + "\n");

        for(int i = 0; i < iterations; i++){
            heap.removeMax();
        }

        System.out.print("\tHeap after " + iterations + " removals: ");
        heap.toString(iterations);
        System.out.print("...");
        System.out.println("\n");
    }
}
