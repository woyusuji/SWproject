import java.io.Serializable;
import java.util.Comparator;

public class Comp implements Comparator<Book>,Serializable{
	public int compare(Book o1, Book o2){
		return o1.getCatalogueNumber() < o2.getCatalogueNumber() ? -1 : (o1== o2 ? 0 : 1);
	}
}
