package taskdirector.app.controllers;

import taskdirector.services.interfaces.IUserService;
import taskdirector.ui.forms.LoginForm;
import taskdirector.events.listeners.ILoginAttemptEventListener;
import java.util.UUID;

/**
 * Controller to handle the login process
 * @author KallDrexx
 */
public class LoginController implements IController, ILoginAttemptEventListener {
    
    protected UUID sessionId;
    protected IUserService userService;
    protected LoginForm loginForm;
    
    public LoginController(IUserService userService)
    {
        this.userService = userService;
    }
    
    /**
     * Executes the login process
     * @return 
     */
    @Override
    public void Execute()
    {
        // Display the login form
        loginForm = new LoginForm();
        loginForm.addLoginAttemptEventListener(this);
        loginForm.setVisible(true);
        
        return;
    }

    @Override
    public void LoginAttempted(String username, String password) {
        UUID sessionId = userService.Login(username, password);
        loginForm.dispose();
    }
}
