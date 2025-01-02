package com.user.dao;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.user.model.RentalApplication;
import com.user.model.RentalApplication.ApplicationStatus;
import com.user.model.User;
import com.user.model.Property;

public class RentalApplicationDAO {
	
	private String jdbcURL = "jbbc:mysql://localhost:30006/Wizard";
	private String jdbcUserName = "root";
	private String jdbcPassword = "root";
	
	 public RentalApplicationDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	private Connection getConnection() throws SQLException {
	        return DriverManager.getConnection(jdbcURL, jdbcUserName, jdbcPassword);
	    }

	    
	    public boolean insertRentalApplication(RentalApplication application) {
	        String sql = "INSERT INTO rental_applications (applicationID, tenantID, propertyID, applicationDate, status) VALUES (?, ?, ?, ?, ?)";
	        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setInt(1, application.getApplicationID());
	            stmt.setInt(2, application.getTenant().getUserID());
	            stmt.setInt(3, application.getProperty().getPropertyID());
	            stmt.setTimestamp(4, Timestamp.valueOf(application.getApplicationDate()));
	            stmt.setString(5, application.getStatus().name());
	            return stmt.executeUpdate() > 0;
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return false;
	    }

	    
	    public RentalApplication getRentalApplicationById(int applicationID) {
	        String sql = "SELECT * FROM rental_applications WHERE applicationID = ?";
	        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setInt(1, applicationID);
	            try (ResultSet rs = stmt.executeQuery()) {
	                if (rs.next()) {
	                    return new RentalApplication(
	                        rs.getInt("applicationID"),
	                        getUserById(rs.getInt("tenantID")),
	                        getPropertyById(rs.getInt("propertyID")), 
	                        rs.getTimestamp("applicationDate").toLocalDateTime(),
	                        ApplicationStatus.valueOf(rs.getString("status"))
	                    );
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }

	   
	    public boolean updateRentalApplication(RentalApplication application) {
	        String sql = "UPDATE rental_applications SET tenantID = ?, propertyID = ?, applicationDate = ?, status = ? WHERE applicationID = ?";
	        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setInt(1, application.getTenant().getUserID());
	            stmt.setInt(2, application.getProperty().getPropertyID());
	            stmt.setTimestamp(3, Timestamp.valueOf(application.getApplicationDate()));
	            stmt.setString(4, application.getStatus().name());
	            stmt.setInt(5, application.getApplicationID());
	            return stmt.executeUpdate() > 0;
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return false;
	    }

	    
	    public boolean deleteRentalApplication(int applicationID) {
	        String sql = "DELETE FROM rental_applications WHERE applicationID = ?";
	        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setInt(1, applicationID);
	            return stmt.executeUpdate() > 0;
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return false;
	    }

	   
	    public List<RentalApplication> getAllRentalApplications() {
	        List<RentalApplication> applications = new ArrayList<>();
	        String sql = "SELECT * FROM rental_applications";
	        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
	            while (rs.next()) {
	                applications.add(new RentalApplication(
	                    rs.getInt("applicationID"),
	                    getUserById(rs.getInt("tenantID")),
	                    getPropertyById(rs.getInt("propertyID")), 
	                    rs.getTimestamp("applicationDate").toLocalDateTime(),
	                    ApplicationStatus.valueOf(rs.getString("status"))
	                ));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return applications;
	    }

	    
	    private User getUserById(int userID) {
	        
	        UserDAO userDAO = new UserDAO();
	        return userDAO.getUserById(userID);
	    }

	    
	    private Property getPropertyById(int propertyID) {
	       
	        PropertyDAO propertyDAO = new PropertyDAO();
	        return propertyDAO.getPropertyById(propertyID);

	    }
}
