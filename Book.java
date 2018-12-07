import java.io.Serializable;
import java.util.LinkedList;

public class Book implements Serializable{
	private String title;
	private String author;
	private int catalogueNumber;
	private Borrower borrower;
	private LinkedList<Borrower> bookBorrower;
	
	public Book(String title, String author, int catalogueNumber) {
		this.title = title;
		this.author = author;
		this.catalogueNumber = catalogueNumber;
		bookBorrower = new LinkedList<Borrower>();
	}
	
	public String getTitle() {
		return title;
	}
	public String getAuthor() {
		return author;
	}
	public int getCatalogueNumber() {
		return catalogueNumber;
	}
	public void attachBorrower(Borrower borrower) {
		this.borrower=borrower;
	}
	public void detachBorrower() {
		this.borrower=null;
	}
	public Borrower getBorrower() {
		return borrower;
	}
	public void setBorrower(Borrower borrower) {
		this.borrower = borrower;
	}
	public LinkedList<Borrower> getBookBorrower(){
		return bookBorrower;
	}
}
