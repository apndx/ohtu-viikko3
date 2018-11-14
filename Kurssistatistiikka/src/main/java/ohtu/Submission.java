package ohtu;

import java.util.Arrays;

public class Submission {

    private int week;
    private int hours;
    private String course;
    private int[] exercises;

    public void setWeek(int week) {
        this.week = week;
    }

    public int getWeek() {
        return week;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int[] getExercises() {
        return exercises;
    }

    public void setExercises(int[] exercises) {
        this.exercises = exercises;
    }

    //@Override
    //public String toString() {
        //return ""+week;
//        return this.getCourse() + ", viikko " + this.getWeek() + " tehtyjä tehtäviä yhteensä "
//                + this.exercises.length + ", aikaa kului " + this.getHours() + " tuntia. Tehdyt tehtävät: " + Arrays.toString(this.exercises);
    
   // }

}
