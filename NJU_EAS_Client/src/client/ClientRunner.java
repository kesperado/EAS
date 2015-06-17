package client;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import presentation.loginui.LoginUI;

public class ClientRunner {

//	private static String ip="127.0.0.1";
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		new LoginUI();

	}
	
	public static String getIP(){
		File file= new File("EAS_IP.txt");
		String ip =null;
		try {
			Scanner scanner= new Scanner(file);
			ip=scanner.nextLine();
			scanner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ip;
	}

}
