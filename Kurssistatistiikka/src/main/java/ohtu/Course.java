/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu;

import java.util.Objects;

/**
 *
 * @author apndx
 */
public class Course {

    private String name;
    private String fullName;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    private int[] exercises;

    public String getName() {
        return name;
    }

    public int getMaxExercises() {
        int maxTehtavatCourseAll = 0;
        for (int i = 0; i < this.getExercises().length; i++) {

            maxTehtavatCourseAll = maxTehtavatCourseAll + this.getExercises()[i];
        }
        return maxTehtavatCourseAll;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[] getExercises() {
        return exercises;
    }

    public void setExercises(int[] exercises) {
        this.exercises = exercises;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Course other = (Course) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    
    
}
