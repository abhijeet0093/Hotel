import java.sql.*;
import java.util.Scanner;

public class HotelManager {

    static final String DB_URL = "jdbc:postgresql://localhost:5432/hotel_db";
    static final String USER = "postgres";
    static final String PASS = "abhi93"; 

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Scanner scanner = new Scanner(System.in)) {

            createTable(conn);
            insertSampleRooms(conn);

            while (true) {
                System.out.println("\n1. View Rooms\n2. Book Room\n3. Check Availability\n4. Delete Room\n5. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); 

                switch (choice) {
                    case 1 -> viewAllRooms(conn);
                    case 2 -> {
                        System.out.print("Enter room number to book: ");
                        int roomNo = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter customer name: ");
                        String name = scanner.nextLine();
                        bookRoom(conn, roomNo, name);
                    }
                    case 3 -> {
                        System.out.print("Enter room number: ");
                        int roomNo = scanner.nextInt();
                        scanner.nextLine();
                        checkAvailability(conn, roomNo);
                    }
                    case 4 -> {
                        System.out.print("Enter room number to delete: ");
                        int roomNo = scanner.nextInt();
                        scanner.nextLine();
                        deleteRoom(conn, roomNo);
                    }
                    case 5 -> {
                        System.out.println("Exiting...");
                        return;
                    }
                    default -> System.out.println("Invalid choice!");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Create table if not exists
    static void createTable(Connection conn) throws SQLException {
        String sql = """
            CREATE TABLE IF NOT EXISTS rooms (
                room_number INT PRIMARY KEY,
                is_booked BOOLEAN DEFAULT FALSE,
                customer_name VARCHAR(100)
            )""";
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("Table checked/created.");
        }
    }

    // Insert some rooms (only if not already there)
    static void insertSampleRooms(Connection conn) throws SQLException {
        String checkSql = "SELECT COUNT(*) FROM rooms";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(checkSql)) {
            rs.next();
            int count = rs.getInt(1);
            if (count == 0) {
                String insertSql = "INSERT INTO rooms (room_number, is_booked, customer_name) VALUES (?, ?, ?)";
                try (PreparedStatement ps = conn.prepareStatement(insertSql)) {
                    for (int i = 101; i <= 103; i++) {
                        ps.setInt(1, i);
                        ps.setBoolean(2, false);
                        ps.setNull(3, Types.VARCHAR);
                        ps.addBatch();
                    }
                    ps.executeBatch();
                    System.out.println("Sample rooms inserted.");
                }
            }
        }
    }

    // View all rooms
    static void viewAllRooms(Connection conn) throws SQLException {
        String sql = "SELECT * FROM rooms ORDER BY room_number";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int roomNo = rs.getInt("room_number");
                boolean booked = rs.getBoolean("is_booked");
                String name = rs.getString("customer_name");
                System.out.printf("Room %d | Booked: %s | Customer: %s%n",
                        roomNo, booked ? "Yes" : "No", (name != null ? name : "-"));
            }
        }
    }

    // Book a room
    static void bookRoom(Connection conn, int roomNumber, String customerName) throws SQLException {
        String checkSql = "SELECT is_booked FROM rooms WHERE room_number = ?";
        try (PreparedStatement check = conn.prepareStatement(checkSql)) {
            check.setInt(1, roomNumber);
            ResultSet rs = check.executeQuery();
            if (rs.next()) {
                if (rs.getBoolean("is_booked")) {
                    System.out.println("Room already booked.");
                    return;
                }
            } else {
                System.out.println("Room not found.");
                return;
            }
        }

        String sql = "UPDATE rooms SET is_booked = TRUE, customer_name = ? WHERE room_number = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, customerName);
            ps.setInt(2, roomNumber);
            ps.executeUpdate();
            System.out.println("Room booked successfully.");
        }
    }

    // Check availability
    static void checkAvailability(Connection conn, int roomNumber) throws SQLException {
        String sql = "SELECT is_booked FROM rooms WHERE room_number = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, roomNumber);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                boolean booked = rs.getBoolean("is_booked");
                System.out.println("Room " + roomNumber + " is " + (booked ? "booked" : "available") + ".");
            } else {
                System.out.println("Room not found.");
            }
        }
    }

    // Delete room
    static void deleteRoom(Connection conn, int roomNumber) throws SQLException {
        String sql = "DELETE FROM rooms WHERE room_number = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, roomNumber);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Room deleted.");
            } else {
                System.out.println("Room not found.");
            }
        }
    }
}