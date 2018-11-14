/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author apndx
 */
public class StudentRecord {

    private String studentNro;
    private HashMap<Course, ArrayList<Submission>> suoritukset; //kurssin nimi avain

    public StudentRecord(String studentNro) {
        this.studentNro = studentNro;
        this.suoritukset = new HashMap<>();
    }

    public String getStudentNro() {
        return studentNro;
    }

    public void setStudentNro(String studentNro) {
        this.studentNro = studentNro;
    }

    public HashMap<Course, ArrayList<Submission>> getSuoritukset() {
        return suoritukset;
    }

    public void addSuoritus(Course course, Submission submission) {

        if (suoritukset.containsKey(course)) {
            ArrayList<Submission> kurssisuoritukset = suoritukset.get(course);
            kurssisuoritukset.add(submission);
            suoritukset.put(course, kurssisuoritukset);
        } else {
            ArrayList<Submission> kurssisuoritukset = new ArrayList<>();
            kurssisuoritukset.add(submission);
            suoritukset.put(course, kurssisuoritukset);
        }
    }

}
