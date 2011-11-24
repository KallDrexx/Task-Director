package taskdirector.events.listeners;

/**
 * Listener for the login attempt events
 * @author KallDrexx
 */
public interface ILoginAttemptEventListener {
    
    /**
     * Performs a login attempt
     * @param username
     * @param password 
     */
    void LoginAttempted(String username, String password);
}
