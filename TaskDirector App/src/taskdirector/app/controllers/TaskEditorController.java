package taskdirector.app.controllers;

import java.util.UUID;
import taskdirector.events.listeners.ICreateTaskEventListener;
import taskdirector.events.listeners.ITaskDetailsRequestedListener;
import taskdirector.services.interfaces.ITaskService;
import taskdirector.services.viewmodels.NewTaskViewModel;
import taskdirector.services.viewmodels.TaskDetailsViewModel;
import taskdirector.ui.forms.MainForm;

/**
 * Controller that manages the retrieval and editing of tasks
 * @author KallDrexx
 */
public class TaskEditorController 
    implements IController, ICreateTaskEventListener, ITaskDetailsRequestedListener {

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
        mainForm.addTaskDetailsRequestedListener(this);
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

    @Override
    public TaskDetailsViewModel getTaskDetails(UUID taskId) {
        return taskService.getTaskDetails(taskId);
    }
    
}
