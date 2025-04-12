package Code;

public class LogInScreen {
	static String token = "deniz";//change

	static public boolean checkLogin(int id, String password,boolean isAdmin) {
		PersonRepository_Imp personDB=new PersonRepository_Imp();
		Person person=personDB.get(id);
		if(person!=null && person.getPassword().equals(password) && (isAdmin? person instanceof Admin: person instanceof User)) {
			return true;
		} else {
			return false;
		}
	}

	static public boolean checkRegisterForUser(int id, String password) {
		PersonRepository_Imp personDB=new PersonRepository_Imp();
		Person person=personDB.get(id);
		if (person==null && password.length()>=4) {
			return true;
		}
		return false;
	}
	static public void addPerson(int id, String password, String name, String surname,boolean isAdmin) {
		Person person= isAdmin? new Admin(id,password,name,surname):new User(id,password,name,surname);
		PersonRepository_Imp personDB=new PersonRepository_Imp();
		personDB.add(person);
	}
	static public boolean checkRegisterForAdmin(int ID,String password, String tokenUser) {
		PersonRepository_Imp personDB=new PersonRepository_Imp();
		Person person=personDB.get(ID);
		if (person==null && password.length()>=4 && token.equals(tokenUser)) {
			return true;
		}
		return false;
	}
	static public boolean changePassword(int ID, String oldPassword, String newPassword) {
		PersonRepository_Imp personDB=new PersonRepository_Imp();
		Person user=personDB.get(ID);
		if((user != null && user.getPassword().equals(oldPassword)) && newPassword.length()>=4) {
			if(!(user instanceof Admin)) {
				User userr = (User)user;
				userr.syncData(ID);
			}

			user.setPassword(newPassword);
			personDB.update(user);
			return true;
		}
		return false;
	}

}
