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
    public static void main(String[] args){
        File randomFile = new File("C:\Users\User\Documents\College\Junior Year\CS 2400\Project 4\src\data_random.txt");
        File sortedFile = new File("C:\Users\User\Documents\College\Junior Year\CS 2400\Project 4\src\data_sorted.txt");

    }

    static void sequentialInsertion(File file) throws FileNotFoundException{

        int switchCount = 0;
        Scanner scanFile = new Scanner(file);
        MaxHeap<Integer> maxheap = new MaxHeap<Integer>(200);

        while (scanFile.hasNextLine()){

           int max = maxheap.getMax();

           int i = Integer.parseInt(scanFile.nextLine());
           maxheap.add(i);

           int newMax = maxheap.getMax();
           if (max != newMax){
               switchCount++;
           }
           
        }

        scanFile.close();
        
    }

    static void optimalMethod(){

    }
}