package hu.kovacs.model;

import hu.kovacs.model.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2016-07-27T10:40:50")
@StaticMetamodel(Task.class)
public class Task_ { 

    public static volatile SingularAttribute<Task, Date> finishTime;
    public static volatile SingularAttribute<Task, Integer> idtask;
    public static volatile SingularAttribute<Task, Integer> responsible;
    public static volatile CollectionAttribute<Task, User> userCollection;
    public static volatile SingularAttribute<Task, String> description;
    public static volatile SingularAttribute<Task, Date> startTime;
    public static volatile SingularAttribute<Task, String> label;
    public static volatile SingularAttribute<Task, Integer> priority;
    public static volatile SingularAttribute<Task, Integer> status;

}