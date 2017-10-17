package Main.Modele;

import Main.Modele.Composantes.Avion;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

public class EntrepotTest {
    Entrepot entrepot;
    int intervalProd;
    int capacite;

    @Before
    public void setUp() throws Exception {
        intervalProd=1;
        capacite=5;
        entrepot=new Entrepot(intervalProd,capacite);
        entrepot.AjouterTypeProduction(Avion.class.toString(),1);
    }

    @Test
    public void getTypeSortie() throws Exception {
       assertThat(entrepot.getTypeSortie(),instanceOf(Avion.class));
    }

    @Test
    public void estPlein() throws Exception {
        entrepot.ajouterInventaire(Avion.class.toString(),capacite);
        assertTrue(entrepot.estPlein());
    }

}