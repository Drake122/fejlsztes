/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.kovacs.user.api;

import hu.kovacs.model.Task;
import hu.kovacs.model.TaskDTO;
import hu.kovacs.model.TaskDTOConverter;
import hu.kovacs.model.User;
import hu.kovacs.model.services.task.TaskService;
import hu.kovacs.model.services.user.UserService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author kovacs.sandor
 */
@RestController
@RequestMapping(value = "task")
public class TaskEndPoint {

    TaskService taskService = new TaskService();

    @RequestMapping(value = "/findTaskById/{id}", method = RequestMethod.GET)
    public TaskDTO findTaskById(@PathVariable(value = "id") int idTask) {
        return TaskDTOConverter.entityConvertFromTask(taskService.findTaskById(idTask));
    }

    @RequestMapping(value = "/taskNew/{id}", method = RequestMethod.PUT)
    public String newTask(@RequestBody Task task, @PathVariable(value = "id") Integer id) {
        taskService.saveNewTask(task);
        return "Sikerült felvinni a taskot: " + task.getIdtask() + ". -val";

    }
    //  public Task(String label, String description, Integer status, Date startTime, Date finishTime, int priority, Integer responsible, Collection<User> userCollection) 

    @RequestMapping(value = "/newTask", method = RequestMethod.PUT)
    public String newTask(@RequestParam(value = "label") String label, @RequestParam(value = "description") String description, @RequestParam(value = "status") Integer status,
            @RequestParam(value = "startTime") String startTime, @RequestParam(value = "finishTime") String finishTime, @RequestParam(value = "priority") int priority,
            @RequestParam(value = "responsible") Integer responsible, @RequestParam(value = "userCollection") Collection<Integer> userCollection) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Task task = null;
        try {
            UserService userService = new UserService();
            List<User> users = new ArrayList<>();

            for (Integer userId : userCollection) {
                users.add(userService.findById(userId));
            }
            task = new Task(label, description, status, sdf.parse(startTime), sdf.parse(finishTime), priority, responsible, users);
            taskService.saveNewTask(task);
        } catch (ParseException ex) {
            Logger.getLogger(TaskEndPoint.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "Sikerült felvinni a taskot: " + (task != null ? task.getIdtask() : " sikertelen ") + ". -val";
    }

    @RequestMapping(value = "/updateTask/{id}", method = RequestMethod.PUT)
    public String updateTask(@PathVariable(value = "id") Integer id, @RequestBody() Task task) {
        taskService.editTask(id, task);
        return "Sikerült módosítani a " + task.getLabel() + " cimkéjű taszkot!";
    }

}
