package taskdirector.app;

import taskdirector.app.controllers.IController;
import taskdirector.app.controllers.LoginController;
import taskdirector.services.interfaces.ITaskService;
import taskdirector.services.interfaces.IUserService;
import taskdirector.services.mocks.MockTaskService;
import taskdirector.services.mocks.MockUserService;

/**
 * Task Director application starting point
 * @author KallDrexx
 */
public class TaskDirectorApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        IUserService userService = new MockUserService();
        ITaskService taskService = new MockTaskService();
        
        IController controller = new LoginController(userService, taskService);
        controller.execute();
    }
}
