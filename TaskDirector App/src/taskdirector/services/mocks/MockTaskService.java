package taskdirector.services.mocks;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
/*import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.CustomConverter;
import org.dozer.MappingException;
import org.dozer.loader.api.FieldsMappingOptions.*;
import org.dozer.loader.api.TypeMappingOptions.*;
import org.dozer.DozerBeanMapper;
import org.dozer.loader.api.FieldsMappingOptions;*/
import taskdirector.services.interfaces.ITaskService;
import taskdirector.services.viewmodels.NewTaskViewModel;
import taskdirector.services.viewmodels.TaskDetailsViewModel;
import taskdirector.services.viewmodels.TaskSummaryViewModel;

/**
 * Provides an in memory implementation of the MockTaskService
 * @author KallDrexx
 */
public class MockTaskService implements ITaskService {

    protected List<TaskDetailsViewModel> taskList;
    //protected DozerBeanMapper mapper;
    
    public MockTaskService() {
        initEntityMappings();
        taskList = new ArrayList<TaskDetailsViewModel>();
    }
    
    @Override
    public void setCurrentSessionId(UUID sessionId) {
        // no-op
    }

    @Override
    public List<TaskSummaryViewModel> getAllTasks() {
        // Returns all tasks in the list
        List<TaskSummaryViewModel> resultList = new ArrayList<TaskSummaryViewModel>();
        for (TaskDetailsViewModel task : taskList)
        {
            TaskSummaryViewModel result = new TaskSummaryViewModel();
            result.setId(task.getId());
            result.setName(task.getName());
            result.setIsCompleted(task.getPercentCompleted() == 1);
            resultList.add(result);
        }
        
        return resultList;
    }

    @Override
    public TaskDetailsViewModel getTaskDetails(UUID taskId) {
        // Find the task with the matching ID value
        for (TaskDetailsViewModel task : taskList)
            if (task.getId() == taskId)
                return task;
        
        return null;
    }

    @Override
    public void UpdateTaskDetails(TaskDetailsViewModel task) {      
        // If the task is in the task list already, update it
        int foundIndex = -1;
        for (int x = 0; x < taskList.size(); x++)
        {
            if (taskList.get(x).getId() == task.getId())
            {
                foundIndex = x;
                break;
            }
        }
        
        if (foundIndex >= 0)
            taskList.set(foundIndex, task);
    }

    @Override
    public UUID CreateTask(NewTaskViewModel newTask) {
        // Create a new task details view model for the list and add it to the task list
        TaskDetailsViewModel task = new TaskDetailsViewModel();
        task.setId(UUID.randomUUID());
        task.setName(newTask.getName());
        taskList.add(task);
        
        return task.getId();
    }
    
    private void initEntityMappings()
    {
        /* Commented out until I can get dozer not to give me a NoClassDefFound error
        BeanMappingBuilder builder = new BeanMappingBuilder() {
            @Override
            protected void configure() {
                class isCompletedConverter implements CustomConverter {
                    @Override
                    public Object convert(Object destination, Object source, Class destClass, Class sourceClass) {
                        if (source == null) { return null; }

                        // Make sure the source is a double and the destination is a boolean
                        if (!(source instanceof Double) || !(destination instanceof Boolean))
                            throw new MappingException("Source was not a double or destination was not a boolean");

                        Boolean castedDest = (Boolean)destination;
                        Double castedSrc = (Double)source;
                        castedDest = castedSrc == 1;

                        return castedDest;
                    }
                };

                mapping(TaskDetailsViewModel.class, TaskSummaryViewModel.class)
                    .fields("percentCompleted", "isCompleted",
                        FieldsMappingOptions.customConverter(isCompletedConverter.class));
            }
        };  

        mapper = new DozerBeanMapper();
        mapper.addMapping(builder); 
         */
    }
}
