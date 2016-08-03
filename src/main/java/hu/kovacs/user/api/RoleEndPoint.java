/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hu.kovacs.user.api;

import hu.kovacs.model.Role;
import hu.kovacs.model.RoleDTO;
import hu.kovacs.model.RoleDTOConverter;
import hu.kovacs.model.User;
import hu.kovacs.model.UserDTO;
import hu.kovacs.model.UserDTOConverter;
import hu.kovacs.model.services.role.RoleService;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author kovacs.sandor
 */
@RestController
@CrossOrigin(origins = "http://localhost:63342")
@RequestMapping(value = "role")
public class RoleEndPoint {
    
    RoleService roleService = new RoleService();
    
    @CrossOrigin(origins = "http://localhost:63342")
    @RequestMapping(value = "/allRole", method = RequestMethod.GET)
    public Collection<RoleDTO> getAllRole() {
         Collection<RoleDTO> tempRole = new ArrayList<>();
         for(Role role:  roleService.allRole()) {
             tempRole.add(RoleDTOConverter.convertEntityFromRole(role));        
    }     
        return tempRole;
    }
    
}
