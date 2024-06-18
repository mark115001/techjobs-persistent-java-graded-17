package org.launchcode.techjobs.persistent.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Employer extends AbstractEntity {

    @NotBlank(message = "Required field")
    @Size(min = 3, max = 256, message = "Location must be 3 to 255 characters")
     private String location;

    @OneToMany
    @JoinColumn(name ="employer_id")
    private List<Job> jobs = new ArrayList<>();

    public Employer() {}   // Constructor for Hibernate

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    //    @Override
//    public String toString() {
//        return "Employer{" +
//                "location='" + location + '\'' +
//                ", name='" + name + '\'' +
//                '}';
//    }
}
