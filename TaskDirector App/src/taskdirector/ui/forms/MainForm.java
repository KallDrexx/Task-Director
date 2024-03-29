/*
 * MainForm.java
 *
 * Created on Nov 24, 2011, 3:54:43 PM
 */
package taskdirector.ui.forms;

import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory.Default;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import javax.swing.JPanel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.ExpandVetoException;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import taskdirector.events.listeners.ICreateTaskEventListener;
import taskdirector.events.listeners.ITaskDetailsPanelClosePressedListener;
import taskdirector.events.listeners.ITaskDetailsRequestedListener;
import taskdirector.events.listeners.ITaskDetailsSaveListener;
import taskdirector.services.viewmodels.TaskDetailsViewModel;
import taskdirector.services.viewmodels.TaskSummaryViewModel;
import taskdirector.ui.dialogs.CreateTaskDialog;
import taskdirector.ui.panels.TaskSummaryPanel;
import taskdirector.ui.panels.TaskDetailsPanel;

/**
 *
 * @author KallDrexx
 */
public class MainForm extends javax.swing.JFrame 
    implements ITaskDetailsPanelClosePressedListener {

    /** Creates new form MainForm */
    public MainForm() {
        initComponents();
        
        internalTaskList = new ArrayList<TaskSummaryViewModel>();
        tabbedTaskList = new ArrayList<TaskDetailsPanel>();
        
        // Add the summary tab
        summaryPanel = new TaskSummaryPanel();
        taskTabs.add("Summary", summaryPanel);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        taskTabs = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        taskTree = new javax.swing.JTree();
        jMenuBar1 = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        newTaskMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Task Director - Dashboard");

        jSplitPane1.setDividerLocation(125);
        jSplitPane1.setDividerSize(10);
        jSplitPane1.setRightComponent(taskTabs);

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("root");
        taskTree.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        taskTree.setRootVisible(false);
        taskTree.addTreeWillExpandListener(new javax.swing.event.TreeWillExpandListener() {
            public void treeWillCollapse(javax.swing.event.TreeExpansionEvent evt)throws javax.swing.tree.ExpandVetoException {
                taskTreeWillCollapseHandler(evt);
            }
            public void treeWillExpand(javax.swing.event.TreeExpansionEvent evt)throws javax.swing.tree.ExpandVetoException {
            }
        });
        taskTree.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                treeMousePressedHandler(evt);
            }
        });
        jScrollPane1.setViewportView(taskTree);

        jSplitPane1.setLeftComponent(jScrollPane1);

        fileMenu.setText("File");

        newTaskMenuItem.setText("New Task");
        newTaskMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newTaskMenuItemClicked(evt);
            }
        });
        fileMenu.add(newTaskMenuItem);

        exitMenuItem.setText("Exit");
        fileMenu.add(exitMenuItem);

        jMenuBar1.add(fileMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 755, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void newTaskMenuItemClicked(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newTaskMenuItemClicked
        // Show the create task dialog
        CreateTaskDialog dlg = new CreateTaskDialog(this, true);
        dlg.setVisible(true);
        
        // Get the desired task name and create the task
        String taskName = dlg.getTaskName();
        if (createTaskListeners != null)
            for (ICreateTaskEventListener listener : createTaskListeners)
                listener.handleCreateTaskEvent(taskName);
        
        dlg.dispose();
    }//GEN-LAST:event_newTaskMenuItemClicked

    private void treeMousePressedHandler(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_treeMousePressedHandler
        // Check if this was a double click on a node
        int selRow = taskTree.getRowForLocation(evt.getX(), evt.getY());
        TreePath selPath = taskTree.getPathForLocation(evt.getX(), evt.getY());
        
        if (selRow != -1)
        {
            if (evt.getClickCount() == 2)
            {
                Object node = selPath.getLastPathComponent();
                if (node instanceof DefaultMutableTreeNode)
                {
                    DefaultMutableTreeNode tNode = (DefaultMutableTreeNode)node;
                    if (tNode.getUserObject() instanceof TaskSummaryViewModel)
                    {
                        // Task was double clicked.  Get the details of the task and create a tab
                        if (taskDetailsReqListeners == null)
                            return;
                        
                        TaskSummaryViewModel taskObj = (TaskSummaryViewModel)tNode.getUserObject();
                        TaskDetailsViewModel task = taskDetailsReqListeners.getTaskDetails(taskObj.getId());
                        addTaskTab(task);
                    }
                }
            }
        }
    }//GEN-LAST:event_treeMousePressedHandler

    private void taskTreeWillCollapseHandler(javax.swing.event.TreeExpansionEvent evt) throws javax.swing.tree.ExpandVetoException {//GEN-FIRST:event_taskTreeWillCollapseHandler
        // Prevent nodes from collapsing
        throw new ExpandVetoException(evt);
    }//GEN-LAST:event_taskTreeWillCollapseHandler

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new MainForm().setVisible(true);
            }
        });
    }
            
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JMenuItem newTaskMenuItem;
    private javax.swing.JTabbedPane taskTabs;
    private javax.swing.JTree taskTree;
    // End of variables declaration//GEN-END:variables

    /**
     * Custom variable definitions
     */
    protected List<TaskSummaryViewModel> internalTaskList;
    protected List<ICreateTaskEventListener> createTaskListeners;
    protected ITaskDetailsRequestedListener taskDetailsReqListeners;
    protected TaskSummaryPanel summaryPanel;
    protected List<TaskDetailsPanel> tabbedTaskList;
    protected ITaskDetailsSaveListener saveTaskDetailsListener;
    
    /**
     * Updates the list of tasks for the form
     * @param taskList List of tasks the form should keep track of
     */
    public void updateTasks(List<TaskSummaryViewModel> taskList)
    {
        // Update the summary tab
        summaryPanel.UpdateSummary(taskList);
        
        // Update the task tree
        // Copy the tasks to a new list and sort it
        List<TaskSummaryViewModel> sortedList = new ArrayList<TaskSummaryViewModel>(taskList);
        Collections.sort(sortedList);
        
        // Setup the core tree nodes
        DefaultMutableTreeNode root = new DefaultMutableTreeNode();
        DefaultMutableTreeNode openTasks = new DefaultMutableTreeNode("Open Tasks", true);
        DefaultMutableTreeNode completedTasks = new DefaultMutableTreeNode("Completed Tasks", true);
        
        // Go through the passed in task list and create the tree nodes for it
        for (TaskSummaryViewModel task : sortedList)
        {
            DefaultMutableTreeNode node = new DefaultMutableTreeNode(task, false);
            if (task.getIsCompleted())
                completedTasks.add(node);
            else
                openTasks.add(node);
        }
        
        // Only add the core nodes if they have at least one child
        if (openTasks.children().hasMoreElements())
            root.add(openTasks);
        if (completedTasks.children().hasMoreElements())
            root.add(completedTasks);
        
        if (!root.children().hasMoreElements())
            root.add(new DefaultMutableTreeNode("No Tasks Exist"));
        
        TreeModel model = new DefaultTreeModel(root);
        taskTree.setModel(model);
        
        // expand all rows
        for (int x = 0; x < taskTree.getRowCount(); x++)
            taskTree.expandRow(x);
    }
    
    /**
     * Creates a details tab for the specified task
     * @param task 
     */
    public void addTaskTab(TaskDetailsViewModel task)
    {
        if (task == null)
            return;
        
        // Make sure this task does not already have a tab opened for it
        for (TaskDetailsPanel tabbedTaskPanel : tabbedTaskList)
        {
            if (tabbedTaskPanel.getTaskId() == task.getId())
            {
                taskTabs.setSelectedComponent(tabbedTaskPanel);
                return;
            }
        }
        
        TaskDetailsPanel tab = new TaskDetailsPanel(task);
        tab.addTaskDetailsPanelClosePressedListener(this);
        tab.addTaskDetailsSaveListener(saveTaskDetailsListener);
        taskTabs.add(task.getName(), tab);
        taskTabs.setSelectedComponent(tab);
        tabbedTaskList.add(tab);
    }
    
    // Listeners
    
    /**
     * Adds the specified object as a create task event listener
     * @param listener 
     */
    public void addCreateTaskEventListener(ICreateTaskEventListener listener)
    {
        if (createTaskListeners == null)
            createTaskListeners = new ArrayList<ICreateTaskEventListener>();
        
        if (!createTaskListeners.contains(listener))
            createTaskListeners.add(listener);
    }
    
    /**
     * Adds the specified object as a task details requested listener
     * @param listener 
     */
    public void addTaskDetailsRequestedListener(ITaskDetailsRequestedListener listener)
    {
        // Only one listener is allowed
        taskDetailsReqListeners = listener;
    }
    
    public void addTaskDetailsSaveListener(ITaskDetailsSaveListener listener)
    {
        saveTaskDetailsListener = listener;
    }

    @Override
    public void CloseTaskDetailsPane(UUID taskId) {
        // Remove the task details tab for the requested task id
        TaskDetailsPanel foundPanel = null;
        for (TaskDetailsPanel panel : tabbedTaskList)
        {
            if (panel.getTaskId() == taskId)
            {
                foundPanel = panel;
                break;
            }
        }
        
        // Remove the selected tab
        if (foundPanel != null)
        {
            tabbedTaskList.remove(foundPanel);
            taskTabs.remove(foundPanel);
        }
    }
}
