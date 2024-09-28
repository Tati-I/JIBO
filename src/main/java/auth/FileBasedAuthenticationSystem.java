package auth;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FileBasedAuthenticationSystem {
    private static final String USER_FILE = "users.txt";
    private static final Map<String, String> userCredentials = new HashMap<>();
    private static User currentUser = null;


    public static boolean registerUser(String username, String password, String email, String userType,String numPhone) {
        if (userCredentials.containsKey(email)) {
            return false;
        }
        String userInfo = username + ":" + password + ":" + email + ":" + userType + ":" + numPhone;
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
                    return currentUser;                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}