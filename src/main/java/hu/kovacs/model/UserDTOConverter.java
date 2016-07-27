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
public class UserDTOConverter {
    
    
    public static UserDTO convertFromEntity(User user){
    
        UserDTO userDTO = new UserDTO();
        
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setIduser(user.getIduser());
        userDTO.setEnabled(user.getEnabled());
        userDTO.setPassword(user.getPassword());
        
                
        
        
        Set<Integer> roleIds = new HashSet<>();
        
        
        for(Role role : user.getRoleCollection()){
            roleIds.add(role.getIdrole());
        }
        
        userDTO.setRoleCollection(roleIds);
        
        Set<Integer> taskIds = new HashSet<>();
        
        for(Task task : user.getTaskCollection()){
            taskIds.add(task.getIdtask());
        }
        userDTO.setTaskCollection(taskIds);
        
        return userDTO;
    }
}
