package Main.Modele;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class EntrepotIntegrationTest {
    public static final String TYPE_COMPOSANTE = "avion";
    Entrepot entrepot;
    int intervalProd;
    int capacite;

    @Before
    public void setUp() throws Exception {
        intervalProd=1;
        capacite=5;
        entrepot=new Entrepot(intervalProd,0,new Point(0,0),capacite);
        entrepot.ajouterTypeProduction(TYPE_COMPOSANTE,1);
        Batiment dest=new Entrepot(intervalProd,1,new Point(0,0),2);
        entrepot.set_destination(dest);
    }

    @Test
    public void PeutProduire_UnAvion_Vrai() throws Exception {
        entrepot.ajouterInventaire(TYPE_COMPOSANTE,1);

        assertEquals(TYPE_COMPOSANTE,entrepot.extraireSortie().get_type());
    }
    @Test
    public void PeutProduire_AucunAvion_Faux() throws Exception {
        assertNull(entrepot.extraireSortie());
    }

}