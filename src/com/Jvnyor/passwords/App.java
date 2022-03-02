package com.Jvnyor.passwords;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		
		int nextInt = 0;
		
		Scanner scanner = new Scanner(System.in);
		
		do {
				
			try {
				System.out.print("\nHow many characters you want for the password? Insert a number: ");
				
				int value = scanner.nextInt();
					
				System.out.println("\nGenerating password...\n");
		
				String password = PasswordGenerator.generateSecureRandomPassword(value);
				
				System.out.println(password);
				
				StringSelection stringSelection = new StringSelection(password);
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				clipboard.setContents(stringSelection, null);
				
				System.out.print("\nDo you wants new password? 1 - Yes | 2 - No: ");
				
				nextInt = scanner.nextInt();
			} catch (RuntimeException e) {
				e.printStackTrace();
			}
			
			if (nextInt == 2 || nextInt == 2) {
				scanner.close();
				break;
			}
		
		} while (nextInt == 1 || nextInt == 1);
		
	}
}
