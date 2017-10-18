package Main.Modele;

import Main.Modele.Composantes.Aile;
import Main.Modele.Composantes.Avion;
import Main.Modele.Composantes.Moteur;
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
        capacite=10;
        entrepot=new Entrepot(intervalProd,capacite);
        entrepot.ajouterTypeProduction(Avion.class.toString(),1);
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
    @Test
    public void getStatutInventaire_Plein() throws Exception {
        entrepot.ajouterInventaire(Avion.class.toString(),capacite);
        assertEquals(100,entrepot.getStatutInventaire());
    }
    @Test
    public void getStatutInventaire_UnTiers() throws Exception {
        entrepot.ajouterInventaire(Avion.class.toString(),capacite/3+1);
        assertTrue(entrepot.getStatutInventaire()>=33 && entrepot.getStatutInventaire()<66);
    }
    @Test
    public void getStatutInventaire_DeuxTiers() throws Exception {
        entrepot.ajouterInventaire(Avion.class.toString(),capacite*2/3+1);
        assertTrue(entrepot.getStatutInventaire()>=66);
    }
    @Test
    public void getStatutInventaire_Vide() throws Exception {

        assertEquals(0,entrepot.getStatutInventaire());
    }
    @Test
    public void getStatutInventaire_MoinsQueUnTiers() throws Exception {
        //capacite doit etre >3
        entrepot.ajouterInventaire(Avion.class.toString(),capacite/3);
        assertTrue(entrepot.getStatutInventaire()<33&&entrepot.getStatutInventaire()>0);
    }
}