/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 *
 * @author apndx
 */
public class Nollaa implements Komento {

    private final TextField tuloskentta;
    private final TextField syotekentta;
    private final Button nollaa;
    private final Button undo;
    private final Sovelluslogiikka sovellus;
    private int edellinenTulos;
    private int muutos;

    public Nollaa(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        this.nollaa = nollaa;
        this.undo = undo;
        this.sovellus = sovellus;
        this.edellinenTulos = 0;
        this.muutos = 0;

    }

    @Override
    public void suorita(int arvo) {
        sovellus.nollaa();

        int laskunTulos = sovellus.tulos();

        syotekentta.setText("");
        tuloskentta.setText("" + laskunTulos);;

        if (laskunTulos == 0) {
            nollaa.disableProperty().set(true);
        } else {
            nollaa.disableProperty().set(false);
        }
        undo.disableProperty().set(false);
    }

    @Override
    public void peru(int arvo) {

        syotekentta.setText("");
        tuloskentta.setText("" + arvo);
        sovellus.peru(arvo);
        
        if (arvo == 0) {
            nollaa.disableProperty().set(true);
        } else {
            nollaa.disableProperty().set(false);
        }
        undo.disableProperty().set(false);

    }

    @Override
    public TextField getSyotekentta() {
        return syotekentta;
    }

    @Override
    public TextField getTuloskentta() {
        return tuloskentta;
    }

    @Override
    public void setEdellinenTulos(int edellinenTulos) {
        this.edellinenTulos = edellinenTulos;
    }

    @Override
    public int getMuutos() {
        return this.muutos;
    }
    
     @Override
    public int getEdellinenTulos() {
        return edellinenTulos;
    }
}
