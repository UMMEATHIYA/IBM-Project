package com.example.mainprojectmanagement.model;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer UID;
    @Column(name="UNAME")
    private String UNAME;


    public User() {
        super();
    }

    public User(String UNAME) {
        this.UNAME = UNAME;
    }

    public Integer getUID() {
        return UID;
    }

    public void setUID(Integer UID) {
        this.UID = UID;
    }

    public String getUNAME() {
        return UNAME;
    }

    public void setUNAME(String UNAME) {
        this.UNAME = UNAME;
    }

    @ManyToMany(mappedBy = "users")
    private Set<Project> projects;

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }
}
