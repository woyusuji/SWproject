import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

public class Library {
	private String name;
	private TreeSet<Book> registeredBook;
	private HashSet<Borrower> registeredBorrower;

	public Library(String name) {
		this.name = name;
		registeredBook = new TreeSet<Book>(new Comp());
		registeredBorrower = new HashSet<Borrower>();
	}

	// 이용자 등록
	public void registerOneBorrower(String studentName, int studentNumber) {
		if (checkDuplicate(studentName, studentNumber)) {
			Borrower borrower = new Borrower(studentName, studentNumber);
			registeredBorrower.add(borrower);
			System.out.println(
					"Student name :" + studentName + ", Student number :" + studentNumber + " has been registered.");
		} else {
			System.out.println("Registration failed due to duplicate name and catalogue number.");
		}
	}

	// 이용자 등록시 이름, 학번 중복확인
	public boolean checkDuplicate(String studentName, int studentNumber) {
		Iterator<Borrower> iterator = registeredBorrower.iterator();
		Borrower borrower;
		while (iterator.hasNext()) {
			borrower = iterator.next();
			if (borrower.getStudentName().equals(studentName) || borrower.getStudentNumber() == studentNumber)
				return false;
		}
		return true;
	}

	// 책 등록
	public void registerOneBook(String title, String author, int catalogueNumber) {
		if (checkDuplicate(catalogueNumber)) {
			Book book = new Book(title, author, catalogueNumber);
			registeredBook.add(book);
			System.out.println("Book's name :" + title + ", Book's author :" + author + ", Book's catalogue number :"
					+ catalogueNumber + " has been registered.");
		} else {
			System.out.println("Registertion failed due to duplicate book's catalogue number.");
		}
	}

	// 책 등록시 분류번호 중복확인
	public boolean checkDuplicate(int catalogueNumber) {
		for (Book book : registeredBook) {
			if (book.getCatalogueNumber() == catalogueNumber) {
				return false;
			}
		}
		return true;
	}

	// 대출가능한 책 전시
	public void displayBooksForLoan() {
		Iterator<Book> iter = registeredBook.iterator();
		while (iter.hasNext() == true) {
			Book book = (Book) iter.next();
			if (book.getBorrower() == null) {
				System.out.println("Book's title : " + book.getTitle() + ", Book's author : " + book.getAuthor()
						+ ", Book's catalogue number : " + book.getCatalogueNumber()
						+ "\n------------------------------");
			}
		}
	}

	// 대출중인 책 전시
	public void displayBooksOnLoan() {
		Iterator<Book> iter = registeredBook.iterator();
		while (iter.hasNext() == true) {
			Book book = (Book) iter.next();
			if (book.getBorrower() != null)
				System.out.println("Book's title : " + book.getTitle() + ", Book's author : " + book.getAuthor()
						+ ", Book's catalogue number : " + book.getCatalogueNumber()
						+ "\n------------------------------");
		}
	}

	// 책 찾기
	public Book findBook(int catalogueNumber) {
		Book target = null;
		for (Book book : registeredBook) {
			if (book.getCatalogueNumber() == catalogueNumber) {
				target = book;
				break;
			}
		}
		if (target.getBorrower() != null) {
			return target;
		} else {
			return target;
		}

	}

	// 이용자 찾기
	public Borrower findBorrower(int studentNumber) {
		Borrower target = null;
		for (Borrower borrower : registeredBorrower) {
			if (borrower.getStudentNumber() == studentNumber) {
				target = borrower;
				break;
			}
		}
		return target;
	}

	// 책 대출
	public void lendOneBook(int catalogueNumber, int studentNumber) {
		Book book = findBook(catalogueNumber);
		Borrower borrower = findBorrower(studentNumber);
		if (book != null) {
			if (borrower != null) {
				book.attachBorrower(borrower);
				borrower.attachBook(book);
				System.out.println(borrower.getStudentName() + " lent out " + book.getTitle());
			}
		}
	}

	// 책 반납
	public void returnOneBook(int catalogueNumber) {
		Book book = findBook(catalogueNumber);
		Borrower borrower = book.getBorrower();
		book.detachBorrower();
		borrower.detachBook(catalogueNumber);
		System.out.println(borrower.getStudentName() + " returned " + book.getTitle());
	}

	// 도서관에 저장되어있던 이용자정보들과 책정보들을 ObjectOutputStream으로 읽어들임.
	public void loadFile() {
		try {
			FileInputStream file = new FileInputStream("borrower.tmp");
			ObjectInputStream in = new ObjectInputStream(file);
			registeredBorrower = (HashSet<Borrower>) in.readObject();
			file = new FileInputStream("book.tmp");
			in = new ObjectInputStream(file);
			registeredBook = (TreeSet<Book>) in.readObject();
		} catch (Exception e) {
		}
	}

	// 현재 도서관에 저장되어있는 이용자정보들과 책정보들을 ObjectInputStream으로 저장함.
	public void saveFile() {
		try {
			FileOutputStream file = new FileOutputStream("borrower.tmp");
			ObjectOutputStream out = new ObjectOutputStream(file);
			out.writeObject(registeredBorrower);
			file = new FileOutputStream("book.tmp");
			out = new ObjectOutputStream(file);
			out.writeObject(registeredBook);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 이용자 삭제
	public void deleteOneBorrower(String studentName, int studentNumber) {
		for(Borrower borrower : registeredBorrower) {
			if(borrower.getStudentName().equals(studentName) && borrower.getStudentNumber() == studentNumber) {
				if(borrower.getBorrowBook().size() != 0) {
					System.out.println("Return books first.");
				} else{
					registeredBorrower.remove(borrower);
					System.out.println(studentName + " has been deleted.");
				}
			}
		}
	}
	
	// 책 삭제
	public void deleteOneBook(int catalogueNumber) {
		for(Book book : registeredBook) {
			if(book.getCatalogueNumber() == catalogueNumber) {
				if(book.getBookBorrower().size() != 0) {
					System.out.println("Can't delete books on loan.");
				} else{
					registeredBook.remove(book);
					System.out.println(catalogueNumber + " has been deleted.");
				}
			}
		}
	}
}
