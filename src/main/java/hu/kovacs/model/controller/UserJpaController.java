/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hu.kovacs.model.controller;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import hu.kovacs.model.Task;
import java.util.ArrayList;
import java.util.Collection;
import hu.kovacs.model.Role;
import hu.kovacs.model.User;
import hu.kovacs.model.controller.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author kovacs.sandor
 */
public class UserJpaController implements Serializable {

    public UserJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(User user) {
        if (user.getTaskCollection() == null) {
            user.setTaskCollection(new ArrayList<Task>());
        }
        if (user.getRoleCollection() == null) {
            user.setRoleCollection(new ArrayList<Role>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Task> attachedTaskCollection = new ArrayList<Task>();
            for (Task taskCollectionTaskToAttach : user.getTaskCollection()) {
                taskCollectionTaskToAttach = em.getReference(taskCollectionTaskToAttach.getClass(), taskCollectionTaskToAttach.getIdtask());
                attachedTaskCollection.add(taskCollectionTaskToAttach);
            }
            user.setTaskCollection(attachedTaskCollection);
            Collection<Role> attachedRoleCollection = new ArrayList<Role>();
            for (Role roleCollectionRoleToAttach : user.getRoleCollection()) {
                roleCollectionRoleToAttach = em.getReference(roleCollectionRoleToAttach.getClass(), roleCollectionRoleToAttach.getIdrole());
                attachedRoleCollection.add(roleCollectionRoleToAttach);
            }
            user.setRoleCollection(attachedRoleCollection);
            em.persist(user);
            for (Task taskCollectionTask : user.getTaskCollection()) {
                taskCollectionTask.getUserCollection().add(user);
                taskCollectionTask = em.merge(taskCollectionTask);
            }
            for (Role roleCollectionRole : user.getRoleCollection()) {
                roleCollectionRole.getUserCollection().add(user);
                roleCollectionRole = em.merge(roleCollectionRole);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(User user) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            User persistentUser = em.find(User.class, user.getIduser());
            Collection<Task> taskCollectionOld = persistentUser.getTaskCollection();
            Collection<Task> taskCollectionNew = user.getTaskCollection();
            Collection<Role> roleCollectionOld = persistentUser.getRoleCollection();
            Collection<Role> roleCollectionNew = user.getRoleCollection();
            Collection<Task> attachedTaskCollectionNew = new ArrayList<Task>();
            for (Task taskCollectionNewTaskToAttach : taskCollectionNew) {
                taskCollectionNewTaskToAttach = em.getReference(taskCollectionNewTaskToAttach.getClass(), taskCollectionNewTaskToAttach.getIdtask());
                attachedTaskCollectionNew.add(taskCollectionNewTaskToAttach);
            }
            taskCollectionNew = attachedTaskCollectionNew;
            user.setTaskCollection(taskCollectionNew);
            Collection<Role> attachedRoleCollectionNew = new ArrayList<Role>();
            for (Role roleCollectionNewRoleToAttach : roleCollectionNew) {
                roleCollectionNewRoleToAttach = em.getReference(roleCollectionNewRoleToAttach.getClass(), roleCollectionNewRoleToAttach.getIdrole());
                attachedRoleCollectionNew.add(roleCollectionNewRoleToAttach);
            }
            roleCollectionNew = attachedRoleCollectionNew;
            user.setRoleCollection(roleCollectionNew);
            user = em.merge(user);
            for (Task taskCollectionOldTask : taskCollectionOld) {
                if (!taskCollectionNew.contains(taskCollectionOldTask)) {
                    taskCollectionOldTask.getUserCollection().remove(user);
                    taskCollectionOldTask = em.merge(taskCollectionOldTask);
                }
            }
            for (Task taskCollectionNewTask : taskCollectionNew) {
                if (!taskCollectionOld.contains(taskCollectionNewTask)) {
                    taskCollectionNewTask.getUserCollection().add(user);
                    taskCollectionNewTask = em.merge(taskCollectionNewTask);
                }
            }
            for (Role roleCollectionOldRole : roleCollectionOld) {
                if (!roleCollectionNew.contains(roleCollectionOldRole)) {
                    roleCollectionOldRole.getUserCollection().remove(user);
                    roleCollectionOldRole = em.merge(roleCollectionOldRole);
                }
            }
            for (Role roleCollectionNewRole : roleCollectionNew) {
                if (!roleCollectionOld.contains(roleCollectionNewRole)) {
                    roleCollectionNewRole.getUserCollection().add(user);
                    roleCollectionNewRole = em.merge(roleCollectionNewRole);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = user.getIduser();
                if (findUser(id) == null) {
                    throw new NonexistentEntityException("The user with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            User user;
            try {
                user = em.getReference(User.class, id);
                user.getIduser();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The user with id " + id + " no longer exists.", enfe);
            }
            Collection<Task> taskCollection = user.getTaskCollection();
            for (Task taskCollectionTask : taskCollection) {
                taskCollectionTask.getUserCollection().remove(user);
                taskCollectionTask = em.merge(taskCollectionTask);
            }
            Collection<Role> roleCollection = user.getRoleCollection();
            for (Role roleCollectionRole : roleCollection) {
                roleCollectionRole.getUserCollection().remove(user);
                roleCollectionRole = em.merge(roleCollectionRole);
            }
            em.remove(user);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<User> findUserEntities() {
        return findUserEntities(true, -1, -1);
    }

    public List<User> findUserEntities(int maxResults, int firstResult) {
        return findUserEntities(false, maxResults, firstResult);
    }

    public List<User> findUserEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(User.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public User findUser(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(User.class, id);
        } finally {
            em.close();
        }
    }
    
     public User findByUsername(String name) throws NonexistentEntityException{
         EntityManager em = getEntityManager();
        
         try {             
             Query query = em.createNamedQuery("User.findByName");
             query.setParameter("name",name);             
             
             return (User)query.getResultList().get(0);
          } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The user with name " + name + " no longer exists.", enfe);
            }finally {
            if (em != null) {
                em.close();
            }        
     }
     }
     
      public Collection<User> findAllUser() throws NonexistentEntityException{
         EntityManager em = getEntityManager();
        
         try {             
             Query query = em.createNamedQuery("User.findAll");           
             return query.getResultList();
          } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The users no longer exists.", enfe);
            }finally {
            if (em != null) {
                em.close();
            }        
     }
     }
     
     
    public int getUserCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<User> rt = cq.from(User.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
