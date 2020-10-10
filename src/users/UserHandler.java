package users;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class UserHandler {

	String[] subStr;
	int numberUsers = 0;
	ArrayList<User> u = new ArrayList<User>();

	//encryption
	public static String encryption(String str) {
		char[] chars = str.toCharArray();
		StringBuilder str2 = new StringBuilder();
		for (byte b = 0; chars.length > b; b++) {
			chars[b] = (char) (chars[b] + 2);
			str2.append(chars[b]);
		}
		return str2.toString();
	}
	
	//decryption
	public static String decryption(String str) {
		char[] chars = str.toCharArray();
		StringBuilder str2 = new StringBuilder();
		for (byte b = 0; chars.length > b; b++) {
			chars[b] = (char) (chars[b] - 2);
			str2.append(chars[b]);
		}
		return str2.toString();
	}

    //file reading and creating an array of users
	public int readingFile(File file) {
		try (BufferedReader is = new BufferedReader(new FileReader(file))) {
			String s;
			while ((s = is.readLine()) != null) {
				subStr = s.split(" ");
				u.add(new User(Integer.parseInt(subStr[0]), subStr[1], decryption(subStr[2])));
				numberUsers++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return numberUsers;
	}

	
	public User getUserByName(String name) {
		for (User user : u)
			if (user.getName().equals(name))
				return user;
		return null;
	}

	//adding a new user to the file
	public void addNewUser(User newUser, File file) {
		try (FileWriter os = new FileWriter(file, true)) {
			os.write(newUser.toString());
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//displaying the file content on the screen (with decryption)
	public void readingAllFile(File file) {
		try (BufferedReader is = new BufferedReader(new FileReader(file))) {
			String s;
			while ((s = is.readLine()) != null) {
				subStr = s.split(" ");
				subStr[2] = decryption(subStr[2]);
				for (int i = 0; i < subStr.length; i++) {
					System.out.print(subStr[i] + " ");
				}
				System.out.println("");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
