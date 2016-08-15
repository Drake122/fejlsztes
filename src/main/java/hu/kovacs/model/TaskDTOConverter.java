/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hu.kovacs.model;

import java.util.HashSet;
import java.util.Set;


/**
 *
 * @author kovacs.sandor
 */
public class TaskDTOConverter {

   
    
    
    public static TaskDTO entityConvertFromTask(Task task){
        TaskDTO taskDTO = new TaskDTO();
        
        taskDTO.setIdtask(task.getIdtask());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setFinishTime(task.getFinishTime());
        taskDTO.setLabel(task.getLabel());
        taskDTO.setPriority(task.getPriority());        
        taskDTO.setResponsible(task.getResponsible());
        taskDTO.setStartTime(task.getStartTime());
        taskDTO.setStatus(task.getStatus());
        
        
        
        Set<String> userNames = new HashSet<>();
         
        for(User user: task.getUserCollection()){
            userNames.add(user.getName());
            
        }
        taskDTO.setUserCollection(userNames);
        
        return taskDTO;
    }
    
}
