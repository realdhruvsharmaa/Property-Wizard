package com.user.dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.user.model.ProfileModel;
import com.user.model.User;

public class ProfileModelDAO {
	
	private String jdbcURL = "jbbc:mysql://localhost:30006/Wizard";
	private String jdbcUserName = "root";
	private String jdbcPassword = "root";
	
	public ProfileModelDAO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcURL, jdbcUserName, jdbcPassword);
    }

    
    public boolean insertProfile(ProfileModel profile) {
        String sql = "INSERT INTO profiles (profileID, userID, rentalHistory, dateUpdated) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, profile.getProfileID());
            stmt.setInt(2, profile.getUser().getUserID());
            stmt.setString(3, profile.getRentalHistory());
            stmt.setTimestamp(4, Timestamp.valueOf(profile.getDateUpdated()));
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    
    public ProfileModel getProfileById(int profileID) {
        String sql = "SELECT * FROM profiles WHERE profileID = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, profileID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new ProfileModel(
                        rs.getInt("profileID"),
                        getUserById(rs.getInt("userID")),
                        rs.getString("rentalHistory"),
                        rs.getTimestamp("dateUpdated").toLocalDateTime()
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

  
    public boolean updateProfile(ProfileModel profile) {
        String sql = "UPDATE profiles SET userID = ?, rentalHistory = ?, dateUpdated = ? WHERE profileID = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, profile.getUser().getUserID());
            stmt.setString(2, profile.getRentalHistory());
            stmt.setTimestamp(3, Timestamp.valueOf(profile.getDateUpdated()));
            stmt.setInt(4, profile.getProfileID());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

   
    public boolean deleteProfile(int profileID) {
        String sql = "DELETE FROM profiles WHERE profileID = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, profileID);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    
    public List<ProfileModel> getAllProfiles() {
        List<ProfileModel> profiles = new ArrayList<>();
        String sql = "SELECT * FROM profiles";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                profiles.add(new ProfileModel(
                    rs.getInt("profileID"),
                    getUserById(rs.getInt("userID")), 
                    rs.getString("rentalHistory"),
                    rs.getTimestamp("dateUpdated").toLocalDateTime()
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return profiles;
    }

   
    private User getUserById(int userID) {
        UserDAO userDAO = new UserDAO();
        return userDAO.getUserById(userID);
    }
	

}
