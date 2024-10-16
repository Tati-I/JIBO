package auth;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FileBasedAuthenticationSystem {
    private static final String USER_FILE = "users.txt";
    private static final String LOGIN_FILE = "login.txt";
    private static final Map<String, String> userCredentials = new HashMap<>();
    private static final Map<String, User> users = new HashMap<>();
    private static User currentUser = null;


    public static void loadUsers() {
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userInfo = line.split(":");
                if (userInfo.length == 5) {
                    String username = userInfo[0];
                    String email = userInfo[2];
                    String userType = userInfo[3];
                    String numPhone = userInfo[4];

                    // تخزين البيانات في الذاكرة
                    userCredentials.put(email, null);
                    users.put(email, new User(username, null, email, userType, numPhone));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading users: " + e.getMessage());
        }
    }

    public static boolean registerUser(String username, String password, String email, String userType, String numPhone) {
        if (userCredentials.containsKey(email)) {
            return false; // البريد الإلكتروني مسجل مسبقًا
        }
        String userInfo = username + ":" + password + ":" + email + ":" + userType + ":" + numPhone;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_FILE, true))) {
            writer.write(userInfo);
            writer.newLine();
            userCredentials.put(email, password);
            users.put(email, new User(username, password, email, userType, numPhone));
            return true;
        } catch (IOException e) {
            System.out.println("Error registering user: " + e.getMessage());
            return false;
        }
    }

    public static User loginUser(String email, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userInfo = line.split(":");
                String storedUsername = userInfo[0];
                String storedPassword = userInfo[1];
                String storedEmail = userInfo[2];
                String storedUserType = userInfo[3];
                String storedNumPhone = userInfo[4];

                // التحقق من صحة الإيميل وكلمة المرور
                if (storedEmail.equals(email) && storedPassword.equals(password)) {
                    // إنشاء كائن User بناءً على المعلومات المخزنة
                    currentUser = new User(storedUsername, storedPassword, storedEmail, storedUserType, storedNumPhone);
                    saveLoginInfo(email);
                    return currentUser;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static Map<String, User> getUsers() {
        return users;
    }

    // حفظ معلومات تسجيل الدخول
    private static void saveLoginInfo(String email) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOGIN_FILE))) {
            writer.write(email);
        } catch (IOException e) {
            System.out.println("Error saving login info: " + e.getMessage());
        }
    }

    // التحقق من وجود تسجيل دخول سابق
    public static User checkPreviousLogin() {
        try (BufferedReader reader = new BufferedReader(new FileReader(LOGIN_FILE))) {
            String email = reader.readLine();
            if (email != null && !email.isEmpty()) {
                loadUsers();
                System.out.println("Read email: [" + email + "]");
                if (users.containsKey(email)) {
                    System.out.println("User found: " + email);
                    return users.get(email);
                } else {
                    System.out.println("User not found in the map.");
                }
            }
        } catch (IOException e) {
            System.out.println("No previous login found.");
        }
        return null;
    }

    // تسجيل الخروج
    public static void logout() {
        currentUser = null;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOGIN_FILE))) {
            writer.write("");
        } catch (IOException e) {
            System.out.println("Error clearing login info: " + e.getMessage());
        }
    }
}