package Code;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Code.Admin;
import Code.Book;
import Code.User;
import Code.PersonDAO_Imp;
import Code.BookDatabaseObject;

public class Library {
	private static List<Book> books=new ArrayList<>();
	private static List<User>users=new ArrayList<>();
	private static List<Admin>admins=new ArrayList<>();
	private static LocalDate date= LocalDate.now();

	public static void syncData() {
	 PersonDAO_Imp personDAO=new PersonDAO_Imp();
	 BookDatabaseObject bookDAO=new BookDatabaseObject();
	 books=bookDAO.list();
	 users=personDAO.listUsers();
	 admins=personDAO.listAdmins();

	}
	public static Book getBookByISBN(String ISBN) {
		for(Book bk : books) {
			if(bk.getISBN().equals(ISBN)) {
				return bk;
			}
		}
		return null;
	}
	public static void checkFines(User user) {
		List<Date> deadlines = user.getDeadlines();
		for (Date deadline : deadlines) {
			if(deadline.compareTo(java.sql.Date.valueOf(date))<=0) {
				user.setHasFine(true);
			}
		}
	}

	public static List<Book> getBooks() {
		return books;
	}

	public static void setBooks(List<Book> bookList) {
		books = bookList;
	}


	public static LocalDate getDate() {
		return date;
	}

	public static void setDate(LocalDate date) {
		Library.date = date;
	}
	public static void nextDay() {
		date=date.plusDays(1);
		checkFines();
	}
	public static void checkFines() {
		for(User user: users) {
			List<Date> deadlines = user.getDeadlines();
			for (Date deadline : deadlines) {
				if(deadline.compareTo(java.sql.Date.valueOf(date))<=0) {
					user.setHasFine(true);
				}
			}
		}
	}
	public static User getUserByID(int ID) {
		for(User user: users) {
			if(user.getId() == ID) {
				return user;
			}
		}
		return null;
	}
	public static List<User> getUsers() {
		return users;
	}

	public static void setUsers(List<User> users) {
		Library.users = users;
	}

	public static List<Admin> getAdmins() {
		return admins;
	}

	public static void setAdmins(List<Admin> admins) {
		Library.admins = admins;
	}


}
