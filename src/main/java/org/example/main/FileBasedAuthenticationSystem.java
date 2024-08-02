package org.example.main;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FileBasedAuthenticationSystem {
    private static final String USER_FILE = "users.txt";
    private static Map<String, String> userCredentials = new HashMap<>();

    static {
        loadUsers();
    }

    private static void loadUsers() {
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 4) {
                    userCredentials.put(parts[2], parts[1]); // email:password
                }
            }
        } catch (IOException e) {
            System.out.println("No existing user file found. Will create new on first registration.");
        }
    }


    public static boolean registerUser(String username, String password, String email, String userType) {
        if (userCredentials.containsKey(email)) {
            return false; // User already exists
        }

        String userInfo = username + ":" + password + ":" + email + ":" + userType;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_FILE, true))) {
            writer.write(userInfo);
            writer.newLine();
            userCredentials.put(email, password);
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static boolean authenticateUser(String email, String password) {
        String storedPassword = userCredentials.get(email);
        if (storedPassword != null) {
            return storedPassword.equals(password);
        }
        return false;
    }
}