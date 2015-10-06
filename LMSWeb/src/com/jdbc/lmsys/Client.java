/**
 * Client.java
 * @author Ziyi Chen 
 * <br> Email: <a href="mailto:ziyi_chen@gcitsolutions.com">ziyi_chen@gcitsolutions.com</a>
 * <br> Created on Sep 29, 2015
 */
package com.jdbc.lmsys;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class Client {

	public static void main(String [ ] args) {
		try {
			start();
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
	}

	public static void start () throws IOException, SQLException {
		boolean run = true;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (run) {
			System.out.println("Welcome to Library Management System. Which category of a user are you");
			System.out.println("1)\tLibrarian");
			System.out.println("2)\tAdministrator");
			System.out.println("3)\tBorrower");
			System.out.println("4)\tQuit");
			int in1 = Integer.parseInt(br.readLine());
			switch (in1) {
			case 1:
				LibrarianManagementSys lmSys = new LibrarianManagementSys(br);
				lmSys.start();
				break;
			case 2:
				//AdministratorManagementSys amSys = new AdministratorManagementSys(br);
				//amSys.start();
				break;
			case 3:
				BorrowerManagementSys brSys = new BorrowerManagementSys(br);
				brSys.start();
				break;
			case 4:
				run = false;
				break;
				default: System.out.println("Invalid input, Please try again");
			}
		}
		br.close();
	}
}
