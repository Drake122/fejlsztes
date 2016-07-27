/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hu.kovacs.model;


import java.util.Set;


/**
 *
 * @author kovacs.sandor
 */
public class RoleDTO {
    
     private Integer idrole;
   
    private String nameRole;
    
    private Set<Integer> userCollection;

    public Integer getIdrole() {
        return idrole;
    }

    public void setIdrole(Integer idrole) {
        this.idrole = idrole;
    }

    public String getNameRole() {
        return nameRole;
    }

    public void setNameRole(String nameRole) {
        this.nameRole = nameRole;
    }

    public Set<Integer> getUserCollection() {
        return userCollection;
    }

    public void setUserCollection(Set<Integer> userCollection) {
        this.userCollection = userCollection;
    }
    
    
    
    
}
