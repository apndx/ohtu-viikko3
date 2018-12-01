package laskin;

import java.util.HashMap;
import java.util.Map;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Tapahtumankuuntelija implements EventHandler {

    private Button undo;
    private Sovelluslogiikka sovellus;
    private Map<Button, Komento> komennot;
    private Komento edellinen = null;

    public Tapahtumankuuntelija(TextField tuloskentta, TextField syotekentta, Button plus, Button miinus, Button nollaa, Button undo) {
        this.undo = undo;
        this.sovellus = new Sovelluslogiikka();
        this.komennot = new HashMap<>();
        komennot.put(plus, new Summa(tuloskentta, syotekentta, nollaa, undo, sovellus));
        komennot.put(miinus, new Erotus(tuloskentta, syotekentta, nollaa, undo, sovellus));
        komennot.put(nollaa, new Nollaa(tuloskentta, syotekentta, nollaa, undo, sovellus));

    }

    @Override
    public void handle(Event event) {

        if (event.getTarget() != undo) {
            Komento komento = komennot.get((Button) event.getTarget());

            int arvo = 0;

            try {
                arvo = Integer.parseInt(komento.getSyotekentta().getText());
            } catch (Exception e) {
            }

            komento.suorita(arvo);
            edellinen = komento;
        } else {
            edellinen.peru();
            edellinen = null;
        }
    }
}
