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
public class RoleDTOConverter {
    
    public static RoleDTO convertEntityFromRole(Role role){
    RoleDTO roleDTO = new RoleDTO();
    Set<Integer> userIds = new HashSet<>();
    
    for(User user: role.getUserCollection()){
        userIds.add(user.getIduser());        
    }
        roleDTO.setUserCollection(userIds);
        roleDTO.setIdrole(role.getIdrole());
        roleDTO.setNameRole(role.getNameRole());
                
        return roleDTO;
    }
       
    
}
