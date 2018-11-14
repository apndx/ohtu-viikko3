package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import org.apache.http.client.fluent.Request;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {

        String studentNr = "012345678";
        if (args.length > 0) {
            studentNr = args[0];
        }

        StudentRecord studentRecord = new StudentRecord(studentNr);

        String url = "https://studies.cs.helsinki.fi/courses/students/" + studentNr + "/submissions";
        String bodyText = Request.Get(url).execute().returnContent().asString();
        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(bodyText, Submission[].class);
        //System.out.println("opiskelijanumero " + studentNr);
        //System.out.println(" ");
        //System.out.println(bodyText);

        String courseInfoUrl = "https://studies.cs.helsinki.fi/courses/courseinfo";
        String bodyTextCourse = Request.Get(courseInfoUrl).execute().returnContent().asString();
        Course[] courses = mapper.fromJson(bodyTextCourse, Course[].class);

        int yhtTehtavatStudent = 0;
        int yhtTunnitStudent = 0;

        //System.out.println("Oliot:");
        for (Submission submission : subs) {

            Course courseToCheck = null;

            for (Course course : courses) {
                if (submission.getCourse().equals(course.getName())) {
                    courseToCheck = course;
                    studentRecord.addSuoritus(courseToCheck, submission);
                }
            }
        }
        for (Course key : studentRecord.getSuoritukset().keySet()) {
            // käydään läpi kaikki avaimet!
            System.out.println(key.getFullName());
            System.out.println(" ");
            int studKurssinTehtavat = 0;
            int studKurssinTunnit = 0;

            for (Submission sub : studentRecord.getSuoritukset().get(key)) {
                System.out.println("viikko " + sub.getWeek() + ":");
                System.out.println("");
                yhtTehtavatStudent = yhtTehtavatStudent + sub.getExercises().length;
                yhtTunnitStudent = yhtTunnitStudent + sub.getHours();
                System.out.println("tehtyjä tehtäviä " + sub.getExercises().length + "/" + key.getExercises()[sub.getWeek()]);
                System.out.println("aikaa kului " + sub.getHours() + " tuntia");
                studKurssinTehtavat = studKurssinTehtavat + sub.getExercises().length;
                studKurssinTunnit = studKurssinTunnit + sub.getHours();
                System.out.println("tehdyt tehtävät " + Arrays.toString(sub.getExercises()));
                System.out.println(" ");
            }

            System.out.println("yhteensä: " + yhtTehtavatStudent + "/ " + key.getMaxExercises() + " tehtävää, " + yhtTunnitStudent + " tuntia");
            System.out.println(" ");
        }
    }
}
