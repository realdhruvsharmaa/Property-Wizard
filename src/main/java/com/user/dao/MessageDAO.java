package com.user.dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.user.model.Message;
import com.user.model.User;
import com.user.model.Message.MessageStatus;

public class MessageDAO {
	
	private String jdbcURL = "jbbc:mysql://localhost:30006/Wizard";
	private String jdbcUserName = "root";
	private String jdbcPassword = "root";
	
	public MessageDAO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcURL, jdbcUserName, jdbcPassword);
    }

   
    public boolean insertMessage(Message message) {
        String sql = "INSERT INTO messages (messageID, senderID, receiverID, content, dateSent, status) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, message.getMessageID());
            stmt.setInt(2, message.getSender().getUserID());
            stmt.setInt(3, message.getReceiver().getUserID());
            stmt.setString(4, message.getContent());
            stmt.setTimestamp(5, Timestamp.valueOf(message.getDateSent()));
            stmt.setString(6, message.getStatus().name());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    
    public Message getMessageById(int messageID) {
        String sql = "SELECT * FROM messages WHERE messageID = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, messageID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Message(
                        rs.getInt("messageID"),
                        getUserById(rs.getInt("senderID")),  
                        getUserById(rs.getInt("receiverID")), 
                        rs.getString("content"),
                        rs.getTimestamp("dateSent").toLocalDateTime(),
                        MessageStatus.valueOf(rs.getString("status"))
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

  
    public boolean updateMessage(Message message) {
        String sql = "UPDATE messages SET senderID = ?, receiverID = ?, content = ?, dateSent = ?, status = ? WHERE messageID = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, message.getSender().getUserID());
            stmt.setInt(2, message.getReceiver().getUserID());
            stmt.setString(3, message.getContent());
            stmt.setTimestamp(4, Timestamp.valueOf(message.getDateSent()));
            stmt.setString(5, message.getStatus().name());
            stmt.setInt(6, message.getMessageID());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    
    public boolean deleteMessage(int messageID) {
        String sql = "DELETE FROM messages WHERE messageID = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, messageID);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

   
    public List<Message> getAllMessages() {
        List<Message> messages = new ArrayList<>();
        String sql = "SELECT * FROM messages";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                messages.add(new Message(
                    rs.getInt("messageID"),
                    getUserById(rs.getInt("senderID")),  
                    getUserById(rs.getInt("receiverID")), 
                    rs.getString("content"),
                    rs.getTimestamp("dateSent").toLocalDateTime(),
                    MessageStatus.valueOf(rs.getString("status"))
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messages;
    }

  
    private User getUserById(int userID) {
        UserDAO userDAO = new UserDAO();
        return userDAO.getUserById(userID);
    }

}
