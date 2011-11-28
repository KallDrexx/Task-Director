package taskdirector.app.controllers;

import java.util.UUID;
import taskdirector.events.listeners.ICreateTaskEventListener;
import taskdirector.events.listeners.ITaskDetailsRequestedListener;
import taskdirector.events.listeners.ITaskDetailsSaveListener;
import taskdirector.services.interfaces.ITaskService;
import taskdirector.services.viewmodels.NewTaskViewModel;
import taskdirector.services.viewmodels.TaskDetailsViewModel;
import taskdirector.ui.forms.MainForm;

/**
 * Controller that manages the retrieval and editing of tasks
 * @author KallDrexx
 */
public class TaskEditorController 
    implements IController, 
               ICreateTaskEventListener, 
               ITaskDetailsRequestedListener,
               ITaskDetailsSaveListener
{

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
        mainForm.addTaskDetailsSaveListener(this);
        mainForm.setVisible(true);
    }

    @Override
    public void handleCreateTaskEvent(String taskName) {
        NewTaskViewModel newTask = new NewTaskViewModel();
        newTask.setName(taskName);
        UUID newTaskId = taskService.CreateTask(newTask);
        
        // Update the main form's task listing
        mainForm.updateTasks(taskService.getAllTasks());
        
        // Show the details for the new task
        TaskDetailsViewModel taskDetails = taskService.getTaskDetails(newTaskId);
        mainForm.addTaskTab(taskDetails);
    }

    @Override
    public TaskDetailsViewModel getTaskDetails(UUID taskId) {
        return taskService.getTaskDetails(taskId);
    }

    @Override
    public void SaveTaskDetails(TaskDetailsViewModel task) {
        taskService.UpdateTaskDetails(task);
        mainForm.updateTasks(taskService.getAllTasks());
    }
    
}
