
package taskdirector.services.mocks;

import java.util.List;
import java.util.UUID;
import org.junit.Test;
import static org.junit.Assert.*;
import taskdirector.services.viewmodels.NewTaskViewModel;
import taskdirector.services.viewmodels.TaskDetailsViewModel;
import taskdirector.services.viewmodels.TaskSummaryViewModel;

/**
 *
 * @author KallDrexx
 */
public class MockTaskServiceTest {
    
    @Test
    public void Can_Create_And_Get_Task_By_Id() {
        // Setup
        String taskName = "Test";
        NewTaskViewModel newTask = new NewTaskViewModel();
        newTask.setName(taskName);
        
        MockTaskService instance = new MockTaskService();
        
        // Act
        UUID newId = instance.CreateTask(newTask);
        TaskDetailsViewModel task = instance.getTaskDetails(newId);
        
        // Verify
        assertNotNull("Returned task was null", task);
        assertEquals("Task's name was incorrect", taskName, task.getName());
    }

    @Test
    public void Can_Get_Summary_List_Of_Tasks()
    {
        // Setup
        MockTaskService instance = new MockTaskService();
        
        NewTaskViewModel newTask1 = new NewTaskViewModel();
        newTask1.setName("task1");
        
        NewTaskViewModel newTask2 = new NewTaskViewModel();
        newTask2.setName("task2");
        
        NewTaskViewModel newTask3 = new NewTaskViewModel();
        newTask3.setName("task3");
        
        UUID task1Id = instance.CreateTask(newTask1);
        UUID task2Id = instance.CreateTask(newTask2);
        UUID task3Id = instance.CreateTask(newTask3);
        
        TaskDetailsViewModel taskdt1 = instance.getTaskDetails(task1Id);
        taskdt1.setPercentCompleted(0.5);
        instance.UpdateTaskDetails(taskdt1);
        
        TaskDetailsViewModel taskdt2 = instance.getTaskDetails(task2Id);
        taskdt2.setPercentCompleted(1);
        instance.UpdateTaskDetails(taskdt2);
        
        // Act
        List<TaskSummaryViewModel> taskList = instance.getAllTasks();
        
        // Verify
        assertNotNull("Task list was null", taskList);
        assertEquals("Task list had an incorrect number of elements", 3, taskList.size());
        assertEquals("First task's ID was incorrect", task1Id, taskList.get(0).getId());
        assertEquals("First task's name was incorrect", "task1", taskList.get(0).getName());
        assertEquals("First task's completion value was incorrect", false, taskList.get(0).getIsCompleted());
        assertEquals("Second task's ID was incorrect", task2Id, taskList.get(1).getId());
        assertEquals("Second task's name was incorrect", "task2", taskList.get(1).getName());
        assertEquals("Second task's completion value was incorrect", true, taskList.get(1).getIsCompleted());
        assertEquals("Third task's ID was incorrect", task3Id, taskList.get(2).getId());
        assertEquals("Third task's name was incorrect", "task3", taskList.get(2).getName());
        assertEquals("Third task's completion value was incorrect", false, taskList.get(2).getIsCompleted());
    }
}
