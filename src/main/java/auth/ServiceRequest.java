package auth;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceRequest {
    private static final String REQUESTS_FILE = "service_requests.txt";

    private String userEmail;
    private String address;
    private String serviceType;
    private String appointmentTime;

    public ServiceRequest(String userEmail, String address, String serviceType, String appointmentTime) {
        this.userEmail = userEmail;
        this.address = address;
        this.serviceType = serviceType;
        this.appointmentTime = appointmentTime;
    }

    // دالة لحفظ طلب الخدمة في ملف
    public static boolean saveServiceRequest(ServiceRequest request) {
        String requestInfo = request.userEmail + ":" + request.address + ":" + request.serviceType + ":" + request.appointmentTime;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(REQUESTS_FILE, true))) {
            writer.write(requestInfo);
            writer.newLine();
            return true;
        } catch (IOException e) {
            System.out.println("Error saving service request: " + e.getMessage());
            return false;
        }
    }

    // دالة لقراءة جميع طلبات الخدمة من الملف
    public static List<ServiceRequest> loadServiceRequests() {
        List<ServiceRequest> requests = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(REQUESTS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] requestInfo = line.split(":");
                if (requestInfo.length == 4) {
                    String userEmail = requestInfo[0];
                    String address = requestInfo[1];
                    String serviceType = requestInfo[2];
                    String appointmentTime = requestInfo[3];
                    requests.add(new ServiceRequest(userEmail, address, serviceType, appointmentTime));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading service requests: " + e.getMessage());
        }
        return requests;
    }
}
