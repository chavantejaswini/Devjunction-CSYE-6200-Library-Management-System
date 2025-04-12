package Code;

import java.util.List;
public interface BookRepository { //Data Access Object interface for books
	 	public void add(Book book);
	    public void update(Book book);
	    public void delete(String ISBN);
	    public Book get(String ISBN);
	    public List<Book> list();
}