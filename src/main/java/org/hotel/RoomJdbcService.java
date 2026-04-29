package org.hotel;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomJdbcService {

    public void insertRoom(Room room) {
        String sql = "INSERT INTO bs_rooms (room_number, type, rate, available, capacity) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, room.getRoomNumber());
            statement.setString(2, room.getType());
            statement.setBigDecimal(3, room.getRate());
            statement.setBoolean(4, room.isAvailable());
            statement.setInt(5, room.getCapacity());

            statement.executeUpdate();
            System.out.println("Room inserted successfully: " + room.getRoomNumber());

        } catch (SQLException e) {
            System.out.println("Error inserting room: " + e.getMessage());
        }
    }

    public Room getRoomByNumber(String roomNumber) {
        String sql = "SELECT * FROM bs_rooms WHERE room_number = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, roomNumber);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToRoom(resultSet);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error fetching room: " + e.getMessage());
        }

        return null;
    }

    public List<Room> getAllRooms() {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT * FROM bs_rooms";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                rooms.add(mapResultSetToRoom(resultSet));
            }

        } catch (SQLException e) {
            System.out.println("Error fetching rooms: " + e.getMessage());
        }

        return rooms;
    }

    public void updateRoom(Room room) {
        String sql = "UPDATE bs_rooms SET type = ?, rate = ?, available = ?, capacity = ? WHERE room_number = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, room.getType());
            statement.setBigDecimal(2, room.getRate());
            statement.setBoolean(3, room.isAvailable());
            statement.setInt(4, room.getCapacity());
            statement.setString(5, room.getRoomNumber());

            int rowsUpdated = statement.executeUpdate();
            System.out.println("Rows updated: " + rowsUpdated);

        } catch (SQLException e) {
            System.out.println("Error updating room: " + e.getMessage());
        }
    }

    public void deleteRoomByNumber(String roomNumber) {
        String sql = "DELETE FROM bs_rooms WHERE room_number = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, roomNumber);

            int rowsDeleted = statement.executeUpdate();
            System.out.println("Rows deleted: " + rowsDeleted);

        } catch (SQLException e) {
            System.out.println("Error deleting room: " + e.getMessage());
        }
    }

    private Room mapResultSetToRoom(ResultSet resultSet) throws SQLException {
        String roomNumber = resultSet.getString("room_number");
        String type = resultSet.getString("type");
        BigDecimal rate = resultSet.getBigDecimal("rate");
        boolean available = resultSet.getBoolean("available");
        int capacity = resultSet.getInt("capacity");

        Room room = new Room(roomNumber, type, rate, capacity);
        room.setAvailable(available);

        return room;
    }
}