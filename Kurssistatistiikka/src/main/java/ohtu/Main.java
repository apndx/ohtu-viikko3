package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import org.apache.http.client.fluent.Request;
import java.util.Arrays;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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

        String ohtuInfoURL = "https://studies.cs.helsinki.fi/courses/ohtu2018/stats";
        String railInfoURL = "https://studies.cs.helsinki.fi/courses/rails2018/stats";

        String statsResponseOhtu = Request.Get(ohtuInfoURL).execute().returnContent().asString();
        String statsResponseRails = Request.Get(railInfoURL).execute().returnContent().asString();

        JsonParser parser = new JsonParser();
        JsonObject parsittuDataOhtu = parser.parse(statsResponseOhtu).getAsJsonObject();
        JsonObject parsittuDataRails = parser.parse(statsResponseRails).getAsJsonObject();

        int ohtuAllSubs = 0;
        int ohtuAllHours = 0;
        int ohtuAllExer = 0;

        int railAllSubs = 0;
        int railAllHours = 0;
        int railAllExer = 0;

        for (String key : parsittuDataOhtu.keySet()) {
            ohtuAllSubs = ohtuAllSubs + parsittuDataOhtu.get(key).getAsJsonObject().get("students").getAsInt();
            ohtuAllHours = ohtuAllSubs + parsittuDataOhtu.get(key).getAsJsonObject().get("hour_total").getAsInt();
            ohtuAllExer = ohtuAllSubs + parsittuDataOhtu.get(key).getAsJsonObject().get("exercise_total").getAsInt();
        }

        for (String key : parsittuDataRails.keySet()) {
            railAllSubs = railAllSubs + parsittuDataRails.get(key).getAsJsonObject().get("students").getAsInt();
            railAllHours = railAllSubs + parsittuDataRails.get(key).getAsJsonObject().get("hour_total").getAsInt();
            railAllExer = railAllSubs + parsittuDataRails.get(key).getAsJsonObject().get("exercise_total").getAsInt();
        }

        //System.out.println(parsittuDataOhtu.get("1").getAsJsonObject().get("students").getAsInt());
        //System.out.println(parsittuDataOhtu.keySet());
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

                if (course.getName().equals("ohtu2018")) {
                    course.setAllSubs(ohtuAllSubs);
                    course.setAllHours(ohtuAllHours);
                    course.setAllExer(ohtuAllExer);
                }

                if (course.getName().equals("rails2018")) {
                    course.setAllSubs(railAllSubs);
                    course.setAllHours(railAllHours);
                    course.setAllExer(railAllExer);
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
            System.out.println("kurssilla yhteensä " + key.getAllSubs() + " palautusta");
            System.out.println("palautettuja tehtäviä " + key.getAllExer() + " kpl");
            System.out.println("aikaa käytetty yhteensä " + key.getAllHours() + " tuntia");
        }
    }
}
