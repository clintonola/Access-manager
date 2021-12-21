package com.company;
import java.io.PrintWriter;
import java.util.Scanner;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;

public class Main {

        //method to hash the password
    static String hashedpassword(String str) {

        String hashed = "";

        try {

            MessageDigest md5 = MessageDigest.getInstance("md5");
            //Returns a MessageDigest object that implements the specified digest algorithm
            //MD5 returns a hash of 128 bit

            md5.update(str.getBytes(StandardCharsets.UTF_8));
            //Updates the digest using the specified array of bytes.

            byte[] bytes = md5.digest();
            //the array of bytes for the resulting hash value.

            StringBuilder stringBuilder = new StringBuilder();
            //Constructs a string builder with no characters in it

            // iterating through the array
            for (byte aByte : bytes) {

                stringBuilder.append(Integer.toHexString((aByte & 0xFF) | 0x100)
                        //The characters of the String argument are appended, in order,
                        //increasing the length of this sequence by the length of the argument.

                        .substring(1, 3));
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

    //main method
    public static void main(String[] args) throws FileNotFoundException {

        //Scanner to read user input
        Scanner scanner = new Scanner(System.in);

        //enter username
        System.out.print("Enter a username: ");

        String user = scanner.nextLine();
        //enter passsword
        System.out.print("Enter a password: ");

        //store the password in pass
        String password = scanner.nextLine();

        //pass pass as an argument into hash method
        String HashedPassword = hashedpassword(password);

        //object to write to the amu file
        PrintWriter printWriter = new PrintWriter("AMU1.txt");

        printWriter.append(user);

        printWriter.append("\t");

        printWriter.append(HashedPassword);

        printWriter.append("\t");

        printWriter.append(password);

        printWriter.close();

        System.out.println("AMU file created successfully!");

    }

}


