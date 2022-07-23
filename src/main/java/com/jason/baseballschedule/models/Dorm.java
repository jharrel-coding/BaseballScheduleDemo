package com.jason.baseballschedule.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="dorms")
public class Dorm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Size(min=3, message="Name must be at least 3 characters long")
    private String name;

    @Column(updatable=false)
    @OneToMany(mappedBy="dorm", fetch=FetchType.LAZY)
    private List<Student> dorms;

    public Dorm() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getDorms() {
        return dorms;
    }

    public void setDorms(List<Student> dorms) {
        this.dorms = dorms;
    }

}