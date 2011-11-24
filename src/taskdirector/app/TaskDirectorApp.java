package taskdirector.app;

import taskdirector.app.controllers.IController;
import taskdirector.app.controllers.LoginController;
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
        IController controller = new LoginController(new MockUserService());
        controller.Execute();
    }
}
