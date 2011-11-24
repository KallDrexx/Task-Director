package taskdirector.app.controllers;

import taskdirector.services.interfaces.IUserService;
import taskdirector.ui.forms.LoginForm;
import java.util.UUID;

/**
 * Controller to handle the login process
 * @author KallDrexx
 */
public class LoginController implements IController {
    
    protected UUID sessionId;
    protected IUserService userService;
    
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
        LoginForm form = new LoginForm();
        form.setVisible(true);
        
        return;
    }
}
