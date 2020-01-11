import java.io.*;
import java.util.*;

public class LRU_Page_Replacement_Algorithm {

	public static void main(String[] args) throws IOException {
		Scanner input = new Scanner(System.in);
		int number_of_frames,length,i,j,pos = 0,totalhits = 0,totalmisses = 0;
		System.out.println("LRU Page Replacement Algorithm");
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
		Boolean FullPageFrame = false;
		ArrayList<Integer> frame = new ArrayList<Integer>();
		int time[] = new int[number_of_frames]; //array of time
		
		for (j = 0; j < number_of_frames; j++) {
			pageframe[j] = -1; //value of each frame is -1
			time[j] = 0; //value of each time is 0
		}
		
		for (i = 0; i < length; i++) {
			if (frame.contains(referencestring[i])) { // if RefString already exists
				frame.remove(frame.indexOf(referencestring[i]));
			}
			frame.add(referencestring[i]);
			
			//finding hit
			int framevalue = -1;
			for (j = 0; j < number_of_frames; j++) {
				if (pageframe[j] == referencestring[i]) {
					framevalue = j;
					totalhits++;
					time[j] = 1;
					System.out.print("| H|| t| ");
				}
			}

			//finding miss
			if (framevalue == -1) {
				if (FullPageFrame) {
					int rslength = length;
					for (j = 0; j < number_of_frames; j++) {
						if (frame.contains(pageframe[j])) {  // if value of pageframe already exists
							int temp = frame.indexOf(pageframe[j]);
							if (temp < rslength) {
								rslength = temp;
								pos = j;
							}
						}
						time[j] = 0;
					}
				}
				pageframe[pos] = referencestring[i];
				totalmisses++;
				System.out.print("| M|| t| ");
				pos++;
				if (pos == number_of_frames) {
					pos = 0;
					FullPageFrame = true;
				}
			}
			for (j = 0; j < number_of_frames; j++)
				current_page_frame[i][j] = pageframe[j];
		}

		//print page frame
		System.out.println();
		for (i = 0; i < number_of_frames; i++) {
			for (j = 0; j < length; j++)
				System.out.printf("|%2d|| %d| ", current_page_frame[j][i],time[i]); //couldn't print the time correctly
			System.out.println();
		}

		//print total number of Miss and Hit
		System.out.println("\nThe Number of Hits: " + totalhits);
		System.out.println("The Number of Misses: " + totalmisses);
	}
}