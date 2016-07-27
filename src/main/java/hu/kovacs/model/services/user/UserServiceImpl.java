/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hu.kovacs.model.services.user;

import hu.kovacs.model.Role;
import hu.kovacs.model.User;

/**
 *
 * @author kovacs.sandor
 */
public interface UserServiceImpl {
     void saveNewUser(User user);

    User findByUsername(String username);
    
    User findById(int idUser);
    
    void editUser(int id, User user);
    
    void userRoleSet(User user, Role role);
    
    void setEmailOfUser(User user, String newEmail);
    
    void setPasswordOfUser(User user, String newPassword);
    
    
}
