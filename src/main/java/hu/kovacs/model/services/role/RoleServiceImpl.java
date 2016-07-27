/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hu.kovacs.model.services.role;

import hu.kovacs.model.Role;
import java.util.Collection;

/**
 *
 * @author kovacs.sandor
 */
public interface RoleServiceImpl {
    
    void saveNewRole(Role role);
    
    void roleUpdate(Role role);
    
    Collection<Role> allRole();
    
    
}
