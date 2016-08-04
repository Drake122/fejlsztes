/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.kovacs.user.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import hu.kovacs.model.Role;
import hu.kovacs.model.Task;
import hu.kovacs.model.TaskDTO;
import hu.kovacs.model.TaskDTOConverter;
import hu.kovacs.model.User;
import hu.kovacs.model.UserDTO;
import hu.kovacs.model.UserDTOConverter;
import hu.kovacs.model.services.task.TaskService;
import hu.kovacs.model.services.user.UserService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.swing.text.html.HTMLDocument;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:63342")
@RequestMapping(value = "user")
public class UserEndpoint {

    UserService userservice = new UserService();
     TaskService taskService = new TaskService();
   // private int roleid;

     @CrossOrigin(origins = "http://localhost:63342")
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String sayHello() {
        return "Hello world!";
    }

    @CrossOrigin(origins = "http://localhost:63342")
    @RequestMapping(value = "/findUserById/{id}", method = RequestMethod.GET)
    public UserDTO findUserById(@PathVariable(value = "id") int idUser) {       
        return UserDTOConverter.convertFromEntity(userservice.findById(idUser));       
    }
   

    @CrossOrigin(origins = "http://localhost:63342")
    @RequestMapping(value = "/newUser", method = RequestMethod.POST)
    public String idNewUser(@RequestParam(value = "name") String name, @RequestParam(value = "password") String password
            , @RequestParam(value = "email") String email, @RequestParam(value = "enabled") Boolean enabled, @RequestParam(value = "idRole") int idRole) {
        User testUser1 = new User(name, password, email, enabled, idRole);//TODO -idrole
        userservice.saveNewUser(testUser1);

        return "ok! " + testUser1.getIduser();
    }
    
    @CrossOrigin(origins = "http://localhost:63342")
    @RequestMapping(value = "/newUserManyRole", method = RequestMethod.PUT)
    public String NewUserManyRole(@RequestParam(value = "name") String name, @RequestParam(value = "password") String password, @RequestParam(value = "email") String email, @RequestParam(value = "enabled") Boolean enabled, @RequestParam(value = "roles") Collection<Role> roles) {
        User testUser1 = new User(name, password, email, enabled, roles);//TODO -idrole
        userservice.saveNewUser(testUser1);

        return "ok! " + testUser1.getIduser();
    }

    @CrossOrigin(origins = "http://localhost:63342")
    @RequestMapping(value = "/login", method = RequestMethod.POST)//body-ban kell küldeni
    public String loggedUser(@RequestParam(value = "name") String name, @RequestParam(value = "password") String password) {
        User loginUser = userservice.findByUsername(name);
        if (loginUser.getPassword().equals(password)) {
            return "Logged " + name + "!";
        } else {
            return "Hibás felhasználónév vagy jelszó!";
        }
    }

    @CrossOrigin(origins = "http://localhost:63342")
    @RequestMapping(value = "/updatUser", method = RequestMethod.PUT)//!
    public String updatUser(@RequestParam(value = "user") User user) {

        userservice.editUser(user.getIduser(), user);
        return "User updated!";
    }

    @CrossOrigin(origins = "http://localhost:63342")
    @RequestMapping(value = "/allUser", method = RequestMethod.GET)
    public Collection<UserDTO> getAllUser() {
         Collection<UserDTO> tempUser = new ArrayList<>();
         for(User user:  userservice.findAllUser()) {
             tempUser.add(UserDTOConverter.convertFromEntity(user));        
    }     
        return tempUser;
    }

    @CrossOrigin(origins = "http://localhost:63342")
     @RequestMapping(value = "/allUserByTaskId/{id}", method = RequestMethod.GET)
    public Collection<UserDTO> getAllUserByTaskId(@PathVariable(value = "id") int idTask) {        
      Collection<UserDTO> tempUser = new ArrayList<>();
      
      Collection<Integer> userIds= new HashSet<>();
      userIds=TaskDTOConverter.entityConvertFromTask(taskService.findTaskById(idTask)).getUserCollection();
      for(User user: taskService.findTaskById(idTask).getUserCollection()){
         
          tempUser.add( UserDTOConverter.convertFromEntity(user));
      }
        return tempUser;
   
    }
    
    
}
