package com.Jvnyor.passwords;

import java.security.SecureRandom;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PasswordGenerator {

	static Random random = new SecureRandom();
	
	public static Stream<Character> getRandomSpecialChars(int count) {
	    Random random = new SecureRandom();
	    IntStream specialChars = random.ints(count, 33, 45);
	    return specialChars.mapToObj(data -> (char) data);
	}

	public static Stream<Character> getRandomAlphabets(int count, boolean upperCase) {
        IntStream characters = null;
        if (upperCase) {
            characters = random.ints(count, 65, 90);
        } else {
            characters = random.ints(count, 97, 122);
        }
        return characters.mapToObj(data -> (char) data);
    }

	public static Stream<Character> getRandomNumbers(int count) {
        IntStream numbers = random.ints(count, 48, 57);
        return numbers.mapToObj(data -> (char) data);
    }
	
	public static String generateSecureRandomPassword(int value) {
	    Stream<Character> pwdStream = Stream.concat(getRandomNumbers(value/4), 
	      Stream.concat(getRandomSpecialChars(value/4), 
	      Stream.concat(getRandomAlphabets(value/4, true), getRandomAlphabets(value/4, false))));
	    List<Character> charList = pwdStream.collect(Collectors.toList());
	    Collections.shuffle(charList);
	    String password = charList.stream()
	        .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
	        .toString();
	    return password;
	}
}
