import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
/**
 * A driver class to show the MaxHeap class in action as we intake two text
 * files and run them each on both of our methods used to build a max heap.
 * We will intake data_random.txt and data_sorted.txt then use them each in 
 * both methods to build max heaps. The program is based on the array implementation
 * of max heaps and makes use of generics.
 */
public class DriverMaxHeap{

    public static void main(String[] args) throws FileNotFoundException{

        try(FileWriter fw = new FileWriter("output.txt", true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw))
        {

            System.out.println("\n\n****************** Heap Generation ******************");//header
            out.println("****************** Heap Generation ******************");

            String fileName = "data_sorted.txt";//the name of a file containing integers
        
            generateHeap(fileName, out);//generates a heap using both sequential and optimal methods

            fileName = "data_random.txt";//the name of a file containing integers

            generateHeap(fileName, out);//generates a heap using both sequential and optimal methods
        } catch (IOException e){
        }

        
    }

    /**
     * Wrapper function for generating a heap using sequential and optimal methods
     * @param fileName the name of a file containing integers
     * @throws FileNotFoundException standard exception
     */
    static void generateHeap(String fileName, PrintWriter out) throws FileNotFoundException{
        System.out.println("\nFile name: " + fileName);
        out.println("\nFile name: " + fileName);
        sequentialInsertion(fileName, out);
        optimalMethod(fileName, out);
    }

    /**
     * Prints out a heap generated through sequential add method
     * @param fileName the name of a file containing integers
     * @param out write to file
     * @throws FileNotFoundException standard exception
     */
    static void sequentialInsertion(String fileName, PrintWriter out) throws FileNotFoundException{
        //add integers from the file into the heap sequentially
        Scanner scan = new Scanner(new File(fileName));
        MaxHeap<Integer> heap = new MaxHeap<>(200);
        while(scan.hasNextInt())
            heap.add(scan.nextInt());
        scan.close();

        //the number of iterations based on project parameters (output first x elements, remove max x times)
        int iterations = 10;

        //prints the generated heap to console
        System.out.print("\n\tHeap built using sequential insertions: ");
        out.print("\n\tHeap built using sequential insertions: ");
        heap.toString(iterations, out);
        System.out.print("...\n\n");
        out.print("...\n\n");

        //prints the number of swaps to the console
        System.out.println("\tNumber of swaps in the heap creation: " + heap.getSwap() + "\n");
        out.println("\tNumber of swaps in the heap creation: " + heap.getSwap() + "\n");

        //removes max element from heap x times based on project parameters
        for(int i = 0; i < iterations; i++){
            heap.removeMax();
        }

        //prints heap after removals
        System.out.print("\tHeap after " + iterations + " removals: ");
        out.print("\tHeap after " + iterations + " removals: ");
        heap.toString(iterations, out);
        System.out.print("...");
        out.print("...");
        System.out.println("\n\n");
        out.println("\n\n");
    }

    /**
     * Creates a heap using the optimal method
     * @param fileName the name of a file containing integers
     * @param out write to file
     * @throws FileNotFoundException standard exception
     */
    static void optimalMethod(String fileName, PrintWriter out) throws FileNotFoundException{
        //creates an array based on integers in file
        File file = new File(fileName);
        Scanner scan = new Scanner(file);
        Integer[] arr = new Integer[100];
        int counter = 0;//helps create array from integers in file
        int iterations = 10;//number of iterations based on project parameters

        while(scan.hasNextInt())
            arr[counter++] = scan.nextInt();
        scan.close();

        //creates a heap using the optimal method by heapifying an existing array
        MaxHeap<Integer> heap = new MaxHeap<>(arr);

        //output elements of the heap
        System.out.print("\n\tHeap built using optimal method: ");
        out.print("\n\tHeap built using optimal method: ");
        heap.toString(iterations, out);
        System.out.print("...\n\n");
        out.print("...\n\n");

        //outputs number of swaps
        System.out.println("\tNumber of swaps in the heap creation: " + heap.getSwap() + "\n");
        out.println("\tNumber of swaps in the heap creation: " + heap.getSwap() + "\n");

        //removes max x elements
        for(int i = 0; i < iterations; i++){
            heap.removeMax();
        }

        //outputs heap after removal
        System.out.print("\tHeap after " + iterations + " removals: ");
        out.print("\tHeap after " + iterations + " removals: ");
        heap.toString(iterations, out);
        System.out.print("...");
        out.print("...");
        System.out.println("\n");
        out.println("\n");
    }
}
