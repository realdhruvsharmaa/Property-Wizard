package com.user.dao;
import java.io.ObjectInputFilter.Status;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.user.model.Property;
import com.user.model.User;

public class PropertyDAO {
	
	private String jdbcURL = "jbbc:mysql://localhost:30006/Wizard";
	private String jdbcUserName = "root";
	private String jdbcPassword = "root";
	
	public PropertyDAO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcURL, jdbcUserName, jdbcPassword);
    }

  
    public boolean insertProperty(Property property) {
        String sql = "INSERT INTO properties (propertyID, managerID, address, description, rent, status, dateListed, dateUpdated) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, property.getPropertyID());
            stmt.setInt(2, property.getManager().getUserID());
            stmt.setString(3, property.getAddress());
            stmt.setString(4, property.getDescription());
            stmt.setDouble(5, property.getRent());
            stmt.setString(6, property.getStatus().name()); 
            stmt.setTimestamp(7, Timestamp.valueOf(property.getDateListed()));
            stmt.setTimestamp(8, Timestamp.valueOf(property.getDateUpdated()));
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    
    public Property getPropertyById(int propertyID) {
        String sql = "SELECT * FROM properties WHERE propertyID = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, propertyID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Property(
                        rs.getInt("propertyID"),
                        getUserById(rs.getInt("managerID")), 
                        rs.getString("address"),
                        rs.getString("description"),
                        rs.getDouble("rent"),
                        Status.valueOf(rs.getString("status")), 
                        rs.getTimestamp("dateListed").toLocalDateTime(),
                        rs.getTimestamp("dateUpdated").toLocalDateTime()
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    
    public boolean updateProperty(Property property) {
        String sql = "UPDATE properties SET managerID = ?, address = ?, description = ?, rent = ?, status = ?, dateListed = ?, dateUpdated = ? WHERE propertyID = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, property.getManager().getUserID());
            stmt.setString(2, property.getAddress());
            stmt.setString(3, property.getDescription());
            stmt.setDouble(4, property.getRent());
            stmt.setString(5, property.getStatus().name());
            stmt.setTimestamp(6, Timestamp.valueOf(property.getDateListed()));
            stmt.setTimestamp(7, Timestamp.valueOf(property.getDateUpdated()));
            stmt.setInt(8, property.getPropertyID());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    
    public boolean deleteProperty(int propertyID) {
        String sql = "DELETE FROM properties WHERE propertyID = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, propertyID);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    
    public List<Property> getAllProperties() {
        List<Property> properties = new ArrayList<>();
        String sql = "SELECT * FROM properties";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                properties.add(new Property(
                    rs.getInt("propertyID"),
                    getUserById(rs.getInt("managerID")), 
                    rs.getString("address"),
                    rs.getString("description"),
                    rs.getDouble("rent"),
                    Status.valueOf(rs.getString("status")),
                    rs.getTimestamp("dateListed").toLocalDateTime(),
                    rs.getTimestamp("dateUpdated").toLocalDateTime()
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return properties;
    }

    
    private User getUserById(int userID) {
        UserDAO userDAO = new UserDAO();
        return userDAO.getUserById(userID);
    }
	
	

}
