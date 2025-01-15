import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mysql.cj.xdevapi.Statement;
import com.user.dao.UserDAO;
import com.user.model.User;

class UserDAOTest {

	UserDAO userDAO = new UserDAO();
	
	
	@Test
	void selectUser_testcase1()
	{
		User user = userDAO.selectUser(1);
		assertNotNull(user);
	}
	
	@Test
	void selectAllUsers_testcase2()
	{
		List<User> userList=userDAO.selectAllUsers();
		assertTrue(userList.size()>0);
	}
	
	@Test
	void deleteUser_testcase3()
	{
		Boolean status=userDAO.deleteUser(1);
		assertTrue(status);
	}
	

    @Test
    void testInsertUser() {
        User user = new User(1, "John Doe", "john@example.com", "password123", "USER", 1234567890, "2023-01-01");
        boolean result = userDAO.insertUser(user);
        assertTrue(result);
    }

    @Test
    void testGetUserById() {
        User user = new User(1, "Jane Doe", "jane@example.com", "password123", "ADMIN", 1234567890, "2023-01-01");
        userDAO.insertUser(user);
        User retrievedUser = userDAO.getUserById(1);
        assertNotNull(retrievedUser);
        assertEquals("Jane Doe", retrievedUser.getName());
    }

    @Test
    void testUpdateUser() {
        User user = new User(1, "Jane Doe", "jane@example.com", "password123", "ADMIN", 1234567890, "2023-01-01");
        userDAO.insertUser(user);
        user.setName("Jane Smith");
        boolean result = userDAO.updateUser(user);
        assertTrue(result);
        User updatedUser = userDAO.getUserById(1);
        assertEquals("Jane Smith", updatedUser.getName());
    }
}
