package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Checklogin {

  static String hashedpassword(String str) {
    String hashed = "";

    try {
      MessageDigest md5 = MessageDigest.getInstance("md5");
      //Returns a MessageDigest object that implements the specified digest algorithm
      //MD5 returns a hash of 128 bit

      md5.update(str.getBytes(StandardCharsets.UTF_8));
      ////Updates the digest using the specified array of bytes.

      byte[] bytes = md5.digest();
      //the array of bytes for the resulting hash value.

      StringBuilder stringBuilder = new StringBuilder();
      //Constructs a string builder with no characters in it

      // iterating through the array
      for (byte aByte : bytes) {
        stringBuilder.append(
          Integer
            .toHexString((aByte & 0xFF) | 0x100)
            //The characters of the String argument are appended, in order,
            //increasing the length of this sequence by the length of the argument.

            .substring(1, 3)
        );
        //Returns a string that is a substring of this string
      }
      hashed = stringBuilder.toString();
      //CharSequence Returns a string containing the characters in this sequence
      // in the same order as this sequence

    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
      //Prints this throwable and its backtrace to the standard error stream

    }

    return hashed;
    //return hashed

  }

  public static void main(String[] args) throws FileNotFoundException {
    //creates a new file
    File amuFile = new File("AMU.txt");

    //check if the file exists
    if (!amuFile.exists()) {
      System.out.println(
        amuFile + " not found, try running the first program first!"
      );
      System.exit(0);
    }

    //reading the amuFile
    Scanner scanner = new Scanner(amuFile);

    scanner.useDelimiter("\t");

    String user = scanner.next();

    String hash = scanner.next();

    scanner = new Scanner(System.in);

    boolean done = false;

    int attempts = 0;

    System.out.print("Enter username: ");

    String userName = scanner.nextLine();

    System.out.print("Enter password: ");

    String password = scanner.nextLine();

    //while loop to ask for user input
    while (!done) {
      //if the username equal user
      if (userName.equals(user)) {
        /// if the password equals hash
        if (hashedpassword(password).equals(hash)) {
          //done is true
          done = true;

          System.out.println("Access granted!");
          //else invalid password
        } else {
          System.out.println("Access denied! Invalid password");

          //increment the attempts
          attempts++;

          //iff user tries more than 3 times, block the account
          if (attempts == 3 && !done) {
            System.out.println(
              "You exceeded entering the allowable" +
              " number of incorrect passwords. " +
              "Your account is blocked, please " +
              "contact your system administrator."
            );

            done = true;
            break;
          }
          System.out.print("Enter password: ");

          password = scanner.nextLine();
        }
        //else wrong user name
      } else {
        System.out.println(
          "Access denied! " + "You are not a registered user!"
        );

        System.out.print("Enter username: ");

        userName = scanner.nextLine();

        System.out.print("Enter password: ");

        password = scanner.nextLine();
      }
    }
  }
}
