package taskdirector.services.interfaces;

import java.util.UUID;

/**
 * Contains methods for interacting with user data
 * @author KallDrexx
 */
public interface IUserService {
    /**
     * Creates a new user in the system
     * @param username Name of the user to create the account for
     * @param password Password for the user
     * @return Returns The session ID for the user's current session
     */
    UUID CreateUser(String username, String password);
    
    /**
     * Logs the userin using the specified username and password
     * @param username
     * @param password
     * @return The session ID for operations
     */
    UUID Login(String username, String password);
    
    /**
     * Logs the user out of the system
     * @param sessionId SessionID for the session to log out of
     */
    void LogOut(UUID sessionId);
    
    /**
     * Changes the user's credentials
     * @param username
     * @param currentPassword
     * @param newPassword 
     */
    void ChangePassword(String username, String currentPassword, String newPassword);
}
