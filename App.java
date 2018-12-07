import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
	public static void main(String args[]) {
		System.out.println("<<Welcome to Library Application!>>");
		Library library = new Library("Libary Application");
		Scanner scan = new Scanner(System.in);
		int number;
		String studentName;
		int studentNumber = 0;
		String title;
		String author;
		int catalogueNumber = 0;
		try {
			library.loadFile();
		}catch (Exception e) {
			// TODO: handle exception
		}
		while(true) {
			try {
				System.out.print("1 : Register one borrower \n"
									+ "2 : Register one book\n"
									+ "3 : Display books for loan\n"
									+ "4 : Display books on loan\n"
									+ "5 : Lend one book\n"
									+ "6 : Return one book\n"
									+ "7 : Delete One Borrower\n"
									+ "8 : Delete One Book\n"
									+ "0 : Close\n"
									+ "Enter the desired number>>");
				number = scan.nextInt();
				
				if(number >= 0 && number < 9) {
					if(number == 1) {
						System.out.print("Enter your name, your student number>>");
						try {
							studentName = scan.next();
							studentNumber = scan.nextInt();
							library.registerOneBorrower(studentName, studentNumber);
						}catch (InputMismatchException e) {
							System.out.println("The input value was entered incorrectly.");
							scan.nextLine();
						}
					}
					else if(number == 2) {
						System.out.print("Enter the book's title, book's author and book's catalogue number>>");
						title = scan.next();
						author = scan.next();
						try {
							catalogueNumber = scan.nextInt();
							library.registerOneBook(title, author, catalogueNumber);
						}catch (InputMismatchException e) {
							System.out.println("The input value was entered incorrectly.");
							scan.nextLine();
						}
					}
					else if(number == 3) {
						library.displayBooksForLoan();
						System.out.println("All the books for loan are display.");
					}
					else if(number == 4) {
						library.displayBooksOnLoan();
						System.out.println("All the books on loan are display.");
					} 
					else if(number == 5) {
						System.out.print("Enter the book's catalogue number and student number to borrow>>");
						try {
							catalogueNumber = scan.nextInt();
							studentNumber = scan.nextInt();
							library.lendOneBook(catalogueNumber, studentNumber);
						} catch (InputMismatchException e) {
							System.out.println("The input value was entered incorrectly.");
							scan.nextLine();
						}
					}
					else if(number == 6) {
						System.out.print("Enter the book's catalogue number to return>>");
						try {
							catalogueNumber = scan.nextInt();
							library.returnOneBook(catalogueNumber);
						} catch (InputMismatchException e) {
							System.out.println("The input value was entered incorrectly.");
							scan.nextLine();
						}
					}
					else if(number == 7) {
						System.out.print("Enter borrower's name and borrower's student number to delete>>");
						try {
							studentName = scan.next();
							studentNumber = scan.nextInt();
							library.deleteOneBorrower(studentName,	studentNumber);
						}catch (InputMismatchException e) {
							System.out.println("The input value was entered incorrectly.");
							scan.nextLine();
						}	
					}
					else if(number == 8) {
						System.out.print("Enter book's catalogue number to delete>>");
						try {
							catalogueNumber = scan.nextInt();
							library.deleteOneBook(catalogueNumber);
						}catch (InputMismatchException e) {
							System.out.println("The input value was entered incorrectly.");
							scan.nextLine();
						}
					}
					else if(number == 0) {
						System.out.println("<<Thanks you for using Library Application!>>");
						library.saveFile();
						break;
					}
				}
				else {
					System.out.println("The input value was entered incorrectly.");
				}
			}catch (InputMismatchException e) {
				System.out.println("The input value was entered incorrectly.");
				scan.nextLine();
			}
		}
	}
}
