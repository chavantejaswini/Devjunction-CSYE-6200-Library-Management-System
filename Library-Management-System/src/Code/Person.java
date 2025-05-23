package Code;

public abstract class Person {
	private int id;
	private String password;
	private String name;
	private String surname;
	public Person(int id, String password,String name, String surname) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.surname = surname;
	}
	public Person() {

	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
