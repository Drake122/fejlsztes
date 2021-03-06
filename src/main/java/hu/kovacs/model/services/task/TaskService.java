/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hu.kovacs.model.services.task;

import hu.kovacs.model.Task;
import hu.kovacs.model.TaskDTO;
import hu.kovacs.model.TaskDTOConverter;
import hu.kovacs.model.User;
import hu.kovacs.model.UserDTO;
import hu.kovacs.model.UserDTOConverter;
import hu.kovacs.model.controller.TaskJpaController;
import hu.kovacs.model.controller.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author kovacs.sandor
 */
public class TaskService implements TaskServiceImpl{

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("todoPU");
    TaskJpaController newTaskJpaControll = new TaskJpaController(emf);

    
    @Override
    public void saveNewTask(Task task) {
        newTaskJpaControll.create(task);
    }

    @Override
    public void editTask(Task task) {
        try {
            newTaskJpaControll.edit(task);
        } catch (Exception ex) {
            Logger.getLogger(TaskService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  /*  @Override
    public void setUsers(Set<String> userNames) {
        TaskDTOConverter newTaskDTO = new TaskDTOConverter();
       
      
    }*/

    @Override
    public void editTask(int idTask, Task task) {
        try {
            newTaskJpaControll.editTaskById(idTask, task);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(TaskService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Set<UserDTO> getUsersByTaskId(int taskId) {
        Set<UserDTO> tempUsers=new HashSet<UserDTO>();
        Task tempTask= newTaskJpaControll.findTask(taskId);
        for(User tempUser:tempTask.getUserCollection()){
            tempUsers.add( UserDTOConverter.convertFromEntity(tempUser));
        }
      
       return tempUsers; 
    }

    @Override
    public Task findTaskById(int id) {
         TaskDTOConverter newTaskDTO = new TaskDTOConverter();
        
        return newTaskJpaControll.findTask(id);
    }

 /*   @Override
    public Task findTaskByLabel(String label) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
    
    public List<TaskDTO> allTask(){
       List<TaskDTO> tempAllTask = new ArrayList<>();
        for(Task task : newTaskJpaControll.findTaskEntities()){
           tempAllTask.add(TaskDTOConverter.entityConvertFromTask(task));
        }
        return tempAllTask;
    }
    
    
}
