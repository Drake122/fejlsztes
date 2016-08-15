/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hu.kovacs.model.services.user;

import hu.kovacs.model.Role;
import hu.kovacs.model.Task;
import hu.kovacs.model.TaskDTO;
import hu.kovacs.model.User;
import hu.kovacs.model.controller.TaskJpaController;
import hu.kovacs.model.controller.UserJpaController;
import hu.kovacs.model.controller.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author kovacs.sandor
 */
public class UserService implements UserServiceImpl{
    
     EntityManagerFactory emf = Persistence.createEntityManagerFactory("todoPU");
        UserJpaController newUsercontr = new UserJpaController(emf);
       TaskJpaController newTaskJpaControll = new TaskJpaController(emf);

    @Override
    public void saveNewUser(User user) {
        newUsercontr.create(user);
        
    }

    @Override
    public User findByUsername(String username) {
         try {
             return newUsercontr.findByUsername(username);
         } catch (Exception ex) {
             Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
             return null;
         }        
    }

    @Override
    public void editUser(int id,  User user) {
        newUsercontr.findUser(id);
         try {
             newUsercontr.edit(user);
         } catch (Exception ex) {
             Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
         }
        
    }

    @Override
    public void userRoleSet(User user, Role role) {
         try {
             newUsercontr.edit(user);
         } catch (Exception ex) {
             Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
    public Collection<User> findAllUser(){
       // ArrayList<User>allUser = new ArrayList(); 
        
        return(newUsercontr.findUserEntities(true,10,1));
       
    }

    @Override
    public void setEmailOfUser(User user, String newEmail) {
         try {
             user.setEmail(newEmail);
         } catch (Exception ex) {
             Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    @Override
    public void setPasswordOfUser(User user, String newPassword) {
        user.setPassword(newPassword);
         try {
             newUsercontr.edit(user);
         } catch (Exception ex) {
             Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    @Override
    public User findById(int idUser) {
       return newUsercontr.findUser(idUser);
    }   
    
}
