package com.Jvnyor.passwords;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class App {

	public static void main(String[] args) throws IOException {
		
		try (Scanner scanner = new Scanner(System.in)) {
			
			long leftLimit = 1L;
		    long rightLimit = 1000000000L;
		    long generatedLong = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
			
			String filename = "password_generated_"+generatedLong+".txt";
			
//			System.out.println(filename);
			
			File file = new File(filename);
			
			try {
					
				Integer value;
					
				do {
					System.out.print("How many characters you want for the password? Insert a number: ");
					value = scanner.nextInt();
				} while (value != 0 || value != null || !value.toString().isBlank() || !value.toString().isEmpty() && !(value <= 50));
						
				System.out.println("\nGenerating password...\n");
			
				String password = PasswordGenerator.generateSecureRandomPassword(value);
					
//				System.out.println(password);
				FileWriter myWriter = null;
				try {
					try {
						if (!file.exists()) {
							file.createNewFile();
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					myWriter = new FileWriter(filename, StandardCharsets.UTF_8);
						
					if (myWriter != null) {
							myWriter.write(password);
							System.out.println("\nFile created! "+filename);
					}
						
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} finally {
					myWriter.flush();
					myWriter.close();
				}
					
				StringSelection stringSelection = new StringSelection(password);
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				clipboard.setContents(stringSelection, null);
					
				System.out.println("\nCopied to clipboard!");
					
			} catch (RuntimeException e) {
				e.printStackTrace();
			} finally {
				scanner.close();
			}
		}
		
	}
}
