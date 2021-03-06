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
public class Summa implements Komento {

    private final TextField tuloskentta;
    private final TextField syotekentta;
    private final Button nollaa;
    private final Button undo;
    private final Sovelluslogiikka sovellus;
    private int muutos;
    private int edellinenTulos;

    public Summa(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {

        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        this.nollaa = nollaa;
        this.undo = undo;
        this.sovellus = sovellus;
        this.muutos = 0;
    }

    @Override
    public int getMuutos() {
        return muutos;
    }

    public void setMuutos(int muutos) {
        this.muutos = muutos;
    }

    @Override
    public void suorita(int arvo) {
        sovellus.plus(arvo);

        int laskunTulos = sovellus.tulos();

        syotekentta.setText("");
        tuloskentta.setText("" + laskunTulos);
        this.muutos = arvo;

        if (laskunTulos == 0) {
            nollaa.disableProperty().set(true);
        } else {
            nollaa.disableProperty().set(false);
        }
        undo.disableProperty().set(false);
    }

    @Override
    public void peru(int arvo) {

        sovellus.miinus(muutos);
        int laskunTulos = sovellus.tulos();

        syotekentta.setText("");
        tuloskentta.setText("" + laskunTulos);

        if (this.muutos == 0) {
            nollaa.disableProperty().set(true);
        } else {
            nollaa.disableProperty().set(false);
        }
        undo.disableProperty().set(true);

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
    public int getEdellinenTulos() {
        return edellinenTulos;
    }

    @Override
    public void setEdellinenTulos(int edellinenTulos) {
        this.edellinenTulos = edellinenTulos;
    }

}
