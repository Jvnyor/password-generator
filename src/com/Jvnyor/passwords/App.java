package com.Jvnyor.passwords;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		
		try (Scanner scanner = new Scanner(System.in)) {
			String filename = "passwords.txt";
			
			try {
				if (!new File(filename).exists()) {
					new File(filename).createNewFile();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Scanner content = null;
			try {
				content = new Scanner(new File(filename));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String data = null;
			
			do {
				data = content.nextLine();
				System.out.println(data);
			} while (content.hasNextLine());
				
			content.close();
			
				try {
					
					Integer value;
					
					do {
						System.out.print("\nHow many characters you want for the password? Insert a number: ");
						value = scanner.nextInt();
					} while (value == 0 || value == null || value.toString().isBlank() || value.toString().isEmpty());
						
					System.out.print("\nFor what platform is the password? Insert the name: ");

					String namePlatform;
					
					do {
						namePlatform = scanner.nextLine();
					} while (namePlatform == null || namePlatform.isBlank() || namePlatform.isEmpty());
					
					System.out.println("\nGenerating password...\n");
			
					String password = PasswordGenerator.generateSecureRandomPassword(value);
					
					System.out.println(password);
					
					try {
						FileWriter myWriter = new FileWriter(filename);
						
						if(data == null || data.isBlank() || data.isEmpty()){
							myWriter.write(password+" - "+namePlatform+"\n");
							System.out.println("\nFirst password!");
							myWriter.close();
						} else {
							myWriter.write(data+"\n\n"+password+" - "+namePlatform+"\n");
							System.out.println("\nFile updated!");
							myWriter.close();
						}
						
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					StringSelection stringSelection = new StringSelection(password);
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents(stringSelection, null);
					
					System.out.println("\nCopied to clipboard!");
					
				} catch (RuntimeException e) {
					e.printStackTrace();
				}
		}
		
	}
}
