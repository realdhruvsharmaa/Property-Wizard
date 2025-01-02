package com.user.dao;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.user.model.RentalAgreement;
import com.user.model.RentalAgreement.AgreementStatus;
import com.user.model.Property;
import com.user.model.User;

public class RentalAgreementDAO {

	private String jdbcURL = "jbbc:mysql://localhost:30006/Wizard";
	private String jdbcUserName = "root";
	private String jdbcPassword = "root";
	
	public RentalAgreementDAO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcURL, jdbcUserName, jdbcPassword);
    }


    public boolean insertRentalAgreement(RentalAgreement agreement) {
        String sql = "INSERT INTO rental_agreements (agreementID, propertyID, tenantID, startDate, endDate, monthlyRent, agreementStatus, dateCreated) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, agreement.getAgreementID());
            stmt.setInt(2, agreement.getProperty().getPropertyID());
            stmt.setInt(3, agreement.getTenant().getUserID());
            stmt.setDate(4, Date.valueOf(agreement.getStartDate()));
            stmt.setDate(5, Date.valueOf(agreement.getEndDate()));
            stmt.setDouble(6, agreement.getMonthlyRent());
            stmt.setString(7, agreement.getAgreementStatus().name());
            stmt.setDate(8, Date.valueOf(agreement.getDateCreated()));
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    
    public RentalAgreement getRentalAgreementById(int agreementID) {
        String sql = "SELECT * FROM rental_agreements WHERE agreementID = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, agreementID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new RentalAgreement(
                        rs.getInt("agreementID"),
                        getPropertyById(rs.getInt("propertyID")), 
                        getUserById(rs.getInt("tenantID")), 
                        rs.getDate("startDate").toLocalDate(),
                        rs.getDate("endDate").toLocalDate(),
                        rs.getDouble("monthlyRent"),
                        AgreementStatus.valueOf(rs.getString("agreementStatus")),
                        rs.getDate("dateCreated").toLocalDate()
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

  
    public boolean updateRentalAgreement(RentalAgreement agreement) {
        String sql = "UPDATE rental_agreements SET propertyID = ?, tenantID = ?, startDate = ?, endDate = ?, monthlyRent = ?, agreementStatus = ?, dateCreated = ? WHERE agreementID = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, agreement.getProperty().getPropertyID());
            stmt.setInt(2, agreement.getTenant().getUserID());
            stmt.setDate(3, Date.valueOf(agreement.getStartDate()));
            stmt.setDate(4, Date.valueOf(agreement.getEndDate()));
            stmt.setDouble(5, agreement.getMonthlyRent());
            stmt.setString(6, agreement.getAgreementStatus().name());
            stmt.setDate(7, Date.valueOf(agreement.getDateCreated()));
            stmt.setInt(8, agreement.getAgreementID());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

   
    public boolean deleteRentalAgreement(int agreementID) {
        String sql = "DELETE FROM rental_agreements WHERE agreementID = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, agreementID);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

  
    public List<RentalAgreement> getAllRentalAgreements() {
        List<RentalAgreement> agreements = new ArrayList<>();
        String sql = "SELECT * FROM rental_agreements";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                agreements.add(new RentalAgreement(
                    rs.getInt("agreementID"),
                    getPropertyById(rs.getInt("propertyID")), 
                    getUserById(rs.getInt("tenantID")), 
                    rs.getDate("startDate").toLocalDate(),
                    rs.getDate("endDate").toLocalDate(),
                    rs.getDouble("monthlyRent"),
                    AgreementStatus.valueOf(rs.getString("agreementStatus")),
                    rs.getDate("dateCreated").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return agreements;
    }

  
    private Property getPropertyById(int propertyID) {
        PropertyDAO propertyDAO = new PropertyDAO();
        return propertyDAO.getPropertyById(propertyID);
    }

   
    private User getUserById(int userID) {
        UserDAO userDAO = new UserDAO();
        return userDAO.getUserById(userID);
    }
	
	
}
