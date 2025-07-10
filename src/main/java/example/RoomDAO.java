package example;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {

    public void bookRoom(int roomNumber, String customerName) throws SQLException {
        String query = "UPDATE rooms SET is_booked = true, customer_name = ? WHERE room_number = ?";
        try (PreparedStatement stmt = DBConnection.getConnection().prepareStatement(query)) {
            stmt.setString(1, customerName);
            stmt.setInt(2, roomNumber);
            stmt.executeUpdate();
        }
    }

    public List<Room> getAllRooms() throws SQLException {
        List<Room> rooms = new ArrayList<>();
        String query = "SELECT * FROM rooms";
        try (Statement stmt = DBConnection.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                rooms.add(new Room(
                    rs.getInt("room_number"),
                    rs.getBoolean("is_booked"),
                    rs.getString("customer_name")
                ));
            }
        }
        return rooms;
    }
}

