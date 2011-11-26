package taskdirector.app.controllers;

import taskdirector.services.interfaces.ITaskService;
import taskdirector.ui.forms.MainForm;

/**
 * Controller that manages the retrieval and editing of tasks
 * @author KallDrexx
 */
public class TaskEditorController implements IController {

    protected MainForm mainForm;
    protected ITaskService taskService;
    
    public TaskEditorController(ITaskService taskService)
    {
        this.taskService = taskService;
    }
    
    @Override
    public void Execute() {
        // Show the main form
        mainForm = new MainForm();
        mainForm.setVisible(true);
    }
    
}
