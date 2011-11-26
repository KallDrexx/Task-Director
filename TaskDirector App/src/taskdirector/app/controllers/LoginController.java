package taskdirector.app.controllers;

import taskdirector.services.interfaces.IUserService;
import taskdirector.ui.forms.LoginForm;
import taskdirector.events.listeners.ILoginAttemptEventListener;
import java.util.UUID;
import taskdirector.services.interfaces.ITaskService;

/**
 * Controller to handle the login process
 * @author KallDrexx
 */
public class LoginController implements IController, ILoginAttemptEventListener {
    
    protected IUserService userService;
    protected ITaskService taskService;
    protected LoginForm loginForm;
    
    public LoginController(IUserService userService, ITaskService taskService)
    {
        this.userService = userService;
        this.taskService = taskService;
    }
    
    /**
     * Executes the login process
     * @return 
     */
    @Override
    public void execute()
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
        
        // Activate the task editor controller
        taskService.setCurrentSessionId(sessionId);
        TaskEditorController controller = new TaskEditorController(taskService);
        controller.execute();
    }
}
