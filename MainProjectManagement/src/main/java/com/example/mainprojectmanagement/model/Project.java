package com.example.mainprojectmanagement.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="PROJECT")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer PID;
    @Column(name="PNAME")
    private String PNAME;
    @Column(name="MANAGER")
    private String MANAGER;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "PROJECT_USERS", joinColumns = { @JoinColumn(name = "PID") }, inverseJoinColumns = { @JoinColumn(name = "UID") })
    private Set<User> users = new HashSet<>(0);

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "PROJECT_TASK", joinColumns = { @JoinColumn(name = "PID") }, inverseJoinColumns = { @JoinColumn(name = "TID") })
    private Set<Task> task;

    public Project() {
        super();
    }

    public Project(Integer PID, String PNAME, String MANAGER) {
        this.PID = PID;
        this.PNAME = PNAME;
        this.MANAGER = MANAGER;
    }

    public Integer getPID() {
        return PID;
    }

    public void setPID(Integer PID) {
        this.PID = PID;
    }

    public String getPNAME() {
        return PNAME;
    }

    public void setPNAME(String PNAME) {
        this.PNAME = PNAME;
    }

    public String getMANAGER() {
        return MANAGER;
    }

    public void setMANAGER(String MANAGER) {
        this.MANAGER = MANAGER;
    }
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "PROJECT_USERS", joinColumns = { @JoinColumn(name = "PID") }, inverseJoinColumns = { @JoinColumn(name = "UID") })
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
    public boolean hasUsers(User user) {
        for (User projectUser: getUsers()) {
            if (projectUser.getUID() == user.getUID()) {
                return true;
            }
        }
        return false;
    }

    public Set<Task> getTask() {
        return task;
    }

    public void setTask(Set<Task> task) {
        this.task = task;
    }

    public boolean hasTask(Task task) {
        for (Task projectTask: getTask()) {
            if (projectTask.getTID() == task.getTID()) {
                return true;
            }
        }
        return false;
    }
}
