import java.util.Scanner;


public class driver {

	public static void main(String args[])
	{
		 System.out.println("Enter the key");
		 Scanner sc = new Scanner(System.in);
	     String input = sc.nextLine();
	    
	     aescipher as = new aescipher();
	     as.processInput(input);
	     
	     
	 
	}
	
	
}
