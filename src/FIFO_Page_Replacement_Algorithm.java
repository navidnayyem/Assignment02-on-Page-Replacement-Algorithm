import java.io.*;
import java.util.Scanner;

public class FIFO_Page_Replacement_Algorithm {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int number_of_frames,length,i,j,pos = 0,totalhits = 0,totalmisses = 0;
		System.out.println("FIFO Page Replacement Algorithm");
		System.out.println("-------------------------------\n");
		System.out.print("Enter the Number of Page Frames: ");
		number_of_frames = Integer.parseInt(input.nextLine());
		System.out.print("Enter the Length of the Reference String: ");
		length = Integer.parseInt(input.nextLine());
		
		int referencestring[] = new int[length];
		System.out.println("Enter the Reference String: ");
		for (i = 0; i < length; i++) {
			referencestring[i] = Integer.parseInt(input.nextLine());
		}
		
		System.out.println();
		int pageframe[] = new int[number_of_frames]; //array of page frame
		int current_page_frame[][] = new int[length][number_of_frames]; //2D array of current page frame

		for (j = 0; j < number_of_frames; j++) {
			pageframe[j] = -1; //value of each frame is -1
		}
		//finding hit
		for (i = 0; i < length; i++) {
			int value = -1;
			for (j = 0; j < number_of_frames; j++) {
				if (pageframe[j] == referencestring[i]) {
					value = j;
					totalhits++;
					System.out.print("| H| ");
				}
			}
			//finding miss
			if (value == -1) {
				pageframe[pos] = referencestring[i];
				totalmisses++;
				System.out.print("| M| ");
				pos++;
				if (pos == number_of_frames)
					pos = 0;
			}
			for (j = 0; j < number_of_frames; j++)
				current_page_frame[i][j] = pageframe[j];
		}
		
		//print page frame
		System.out.println();
		for (i = 0; i < number_of_frames; i++) {
			for (j = 0; j < length; j++)
				System.out.printf("|%2d| ", current_page_frame[j][i]);
			System.out.println();
		}
		
		//print total number of Miss and Hit
		System.out.println("\nThe Number of Hits: " + totalhits);
		System.out.println("The Number of Misses: " + totalmisses);
	}
}