package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import org.apache.http.client.fluent.Request;

public class Main {

    public static void main(String[] args) throws IOException {

        String studentNr = "012345678";
        if (args.length > 0) {
            studentNr = args[0];
        }

        String url = "https://studies.cs.helsinki.fi/courses/students/" + studentNr + "/submissions";

        String bodyText = Request.Get(url).execute().returnContent().asString();

        System.out.println("opiskelijanumero " + studentNr);
        System.out.println(" ");
        //System.out.println(bodyText);

        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(bodyText, Submission[].class);

        int yhtTehtavat = 0;
        int yhtTunnit = 0;
        
        //System.out.println("Oliot:");
        for (Submission submission : subs) {           
            
            System.out.println(submission);
            yhtTehtavat = yhtTehtavat + submission.getExercises().length;
            yhtTunnit = yhtTunnit + submission.getHours();
            
        }
        System.out.println(" ");
        System.out.println("yhteensä: "+ yhtTehtavat + " tehtävää, " + yhtTunnit + " tuntia");
    }

}
