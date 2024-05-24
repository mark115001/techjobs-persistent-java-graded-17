package org.launchcode.techjobs.persistent.models;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Entity
public class Employer extends AbstractEntity {

    @NotBlank(message = "Location field required")
    @Size(min = 3, max = 256, message = "Location must be 3 to 255 characters")
     private String location;

    @NotBlank(message = "Name field required")
    @Size(min = 3, max = 256, message = "Name must be 3 to 255 characters")
    private String name;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Employer{" +
                "location='" + location + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public Employer() {}   // Constructor for Hibernate

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        if (!super.equals(o)) return false;
//        Employer employer = (Employer) o;
//        return Objects.equals(getLocation(), employer.getLocation()) && Objects.equals(getName(), employer.getName());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(super.hashCode(), getLocation(), getName());
//    }
}
