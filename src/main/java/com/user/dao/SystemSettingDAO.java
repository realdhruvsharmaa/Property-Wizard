package com.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;
import com.user.model.SystemSetting;

public class SystemSettingDAO {

	private String jdbcURL = "jbbc:mysql://localhost:30006/Wizard";
	private String jdbcUserName = "root";
	private String jdbcPassword = "root";
	
	
	
	public SystemSettingDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcURL, jdbcUserName, jdbcPassword);
    }

    
    public boolean insertSystemSetting(SystemSetting setting) {
        String sql = "INSERT INTO system_settings (settingID, key, value, dateUpdated) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, setting.getSettingID());
            stmt.setString(2, setting.getKey());
            stmt.setString(3, setting.getValue());
            stmt.setTimestamp(4, Timestamp.valueOf(setting.getDateUpdated()));
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

   
    public SystemSetting getSystemSettingById(int settingID) {
        String sql = "SELECT * FROM system_settings WHERE settingID = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, settingID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new SystemSetting(
                        rs.getInt("settingID"),
                        rs.getString("key"),
                        rs.getString("value"),
                        rs.getTimestamp("dateUpdated").toLocalDateTime()
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    
    public boolean updateSystemSetting(SystemSetting setting) {
        String sql = "UPDATE system_settings SET key = ?, value = ?, dateUpdated = ? WHERE settingID = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, setting.getKey());
            stmt.setString(2, setting.getValue());
            stmt.setTimestamp(3, Timestamp.valueOf(setting.getDateUpdated()));
            stmt.setInt(4, setting.getSettingID());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

  
    public boolean deleteSystemSetting(int settingID) {
        String sql = "DELETE FROM system_settings WHERE settingID = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, settingID);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

   
    public List<SystemSetting> getAllSystemSettings() {
        List<SystemSetting> settingsList = new ArrayList<>();
        String sql = "SELECT * FROM system_settings";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                settingsList.add(new SystemSetting(
                    rs.getInt("settingID"),
                    rs.getString("key"),
                    rs.getString("value"),
                    rs.getTimestamp("dateUpdated").toLocalDateTime()
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return settingsList;
    }
}
