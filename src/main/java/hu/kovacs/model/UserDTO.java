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
public class UserDTO {

    private Integer iduser;

    private String name;

    private String password;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation

    private String email;

    private boolean enabled;

    private Set<Integer> taskCollection;

    private Set<Integer> roleCollection;

    public Integer getIduser() {
        return iduser;
    }

    public void setIduser(Integer iduser) {
        this.iduser = iduser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Integer> getTaskCollection() {
        return taskCollection;
    }

    public void setTaskCollection(Set<Integer> taskCollection) {
        this.taskCollection = taskCollection;
    }

    public Set<Integer> getRoleCollection() {
        return roleCollection;
    }

    public void setRoleCollection(Set<Integer> roleCollection) {
        this.roleCollection = roleCollection;
    }

   

}
