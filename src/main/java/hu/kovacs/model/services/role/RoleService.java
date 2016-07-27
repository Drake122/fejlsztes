/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hu.kovacs.model.services.role;

import hu.kovacs.model.Role;
import hu.kovacs.model.controller.RoleJpaController;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author kovacs.sandor
 */
public class RoleService implements RoleServiceImpl{

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("todoPU");
       RoleJpaController newRoleContr = new RoleJpaController(emf);
       
        
    @Override
    public void saveNewRole(Role role) {
        newRoleContr.create(role);
    }

    @Override
    public void roleUpdate(Role role) {
        try {
            newRoleContr.edit(role);
        } catch (Exception ex) {
            Logger.getLogger(RoleService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Collection<Role> allRole() {
      return  newRoleContr.findRoleEntities();
    }
    
}
