package taskdirector.app.controllers;

import taskdirector.events.listeners.ICreateTaskEventListener;
import taskdirector.services.interfaces.ITaskService;
import taskdirector.services.viewmodels.NewTaskViewModel;
import taskdirector.ui.forms.MainForm;

/**
 * Controller that manages the retrieval and editing of tasks
 * @author KallDrexx
 */
public class TaskEditorController implements IController, ICreateTaskEventListener {

    protected MainForm mainForm;
    protected ITaskService taskService;
    
    public TaskEditorController(ITaskService taskService)
    {
        this.taskService = taskService;
    }
    
    @Override
    public void execute() {
        // Show the main form
        mainForm = new MainForm();
        mainForm.addCreateTaskEventListener(this);
        mainForm.updateTasks(taskService.getAllTasks());
        mainForm.setVisible(true);
    }

    @Override
    public void handleCreateTaskEvent(String taskName) {
        NewTaskViewModel newTask = new NewTaskViewModel();
        newTask.setName(taskName);
        taskService.CreateTask(newTask);
        
        // Update the main form's task listing
        mainForm.updateTasks(taskService.getAllTasks());
    }
    
}
