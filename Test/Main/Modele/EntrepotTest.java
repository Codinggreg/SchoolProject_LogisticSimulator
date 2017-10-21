package Main.Modele;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EntrepotTest {
    public static final String TYPE_COMPOSANTE = "avion";
    Entrepot entrepot;
    int intervalProd;
    int capacite;

    @Before
    public void setUp() throws Exception {
        intervalProd=1;
        capacite=10;
        entrepot=new Entrepot(intervalProd,0,null,capacite);
        entrepot.ajouterTypeProduction(TYPE_COMPOSANTE,1);
    }

    @Test
    public void getTypeSortie() throws Exception {
        assertEquals(TYPE_COMPOSANTE,entrepot.getComposante().get_type());
    }

    @Test
    public void estPlein() throws Exception {
        entrepot.ajouterInventaire(TYPE_COMPOSANTE,capacite);
        assertTrue(entrepot.estPlein());
    }
    @Test
    public void getStatut_InventairePlein_Vrai() throws Exception {
        entrepot.ajouterInventaire(TYPE_COMPOSANTE,capacite);
        assertEquals(100,entrepot.getStatut());
    }
    @Test
    public void getStatut_Inventaire_UnTiers() throws Exception {
        entrepot.ajouterInventaire(TYPE_COMPOSANTE,capacite/3+1);
        assertTrue(entrepot.getStatut()>=33 && entrepot.getStatut()<66);
    }
    @Test
    public void getStatut_Inventaire_DeuxTiers() throws Exception {
        entrepot.ajouterInventaire(TYPE_COMPOSANTE,capacite*2/3+1);
        assertTrue(entrepot.getStatut()>=66);
    }
    @Test
    public void getStatut_Inventaire_Vide() throws Exception {

        assertEquals(0,entrepot.getStatut());
    }
    @Test
    public void getStatut_Inventaire_MoinsQueUnTiers() throws Exception {
        //capacite doit etre >3
        entrepot.ajouterInventaire(TYPE_COMPOSANTE,capacite/3);
        assertTrue(entrepot.getStatut()<33&&entrepot.getStatut()>0);
    }
}