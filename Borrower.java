import java.io.Serializable;
import java.util.LinkedList;

public class Borrower implements Serializable{
	private String studentName;
	private int studentNumber;
	private LinkedList<Book> borrowBook;
	
	public Borrower(String studentName, int studentNumber){
		this.studentName = studentName;
		this.studentNumber = studentNumber;
		borrowBook = new LinkedList<Book>();
	}
	public String getStudentName() {
		return studentName;
	}
	public int getStudentNumber() {
		return studentNumber;
	}
	public void attachBook(Book book) {
		borrowBook.add(book);
	}
	public void detachBook(int catalogueNumber) {
		for(Book book : borrowBook) {
			if(book.getCatalogueNumber() == catalogueNumber) {
				borrowBook.remove(catalogueNumber-1);
			}
		}
	}
	public LinkedList<Book> getBorrowBook(){
		return borrowBook;
	}
}
