package users;

import java.io.File;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		String username, password;
		int numberUsers;

		File file = new File("DB.txt");
		if (!file.isFile()) {
			System.out.println("ERROR: Not a file!");
			return;
		}
		if (!file.canWrite()) {
			System.out.println("ERROR: Cannot write a file!");
			return;
		}

		UserHandler uh1 = new UserHandler();
		numberUsers = uh1.readingFile(file);

		Scanner scan = new Scanner(System.in);
		System.out.print("Enter your username:");
		username = scan.nextLine();

		User currentUser = uh1.getUserByName(username);
		if (currentUser == null) {
			System.out.print("The user was not found. Enter your username to register:");
			username = scan.nextLine();
			System.out.print("Enter your password:");
			password = scan.nextLine();
			User newUser = new User(numberUsers+1, username, password);
			uh1.addNewUser(newUser, file);
			System.out.println("User " + username + " added to the database.");
			scan.close();
		} else {
			System.out.print("Enter your password:");
			password = scan.nextLine();
			if (password.equals(currentUser.Password())) {
				System.out.println("Entry is possible");
			} else {
				System.out.println("Password is incorrect");
			}
		}

		System.out.println("");
		System.out.println("All Users:");
		uh1.readingAllFile(file);
	}
}    