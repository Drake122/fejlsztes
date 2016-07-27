/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hu.kovacs.model.services.task;

import hu.kovacs.model.Task;
import hu.kovacs.model.User;
import hu.kovacs.model.UserDTO;
import java.util.Set;

/**
 *
 * @author kovacs.sandor
 */
public interface TaskServiceImpl {
    
    void saveNewTask(Task task);
    
    void editTask(Task task);
    
    void setUsers(Set<Integer>userIds);
    
    void editTask(int idTask, Task task);
    
    Set<UserDTO> getUsersByTaskId(int taskId);
    
    Task findTaskById(int id);
    
    Task findTaskByLabel(String label);
    
    
    
}
