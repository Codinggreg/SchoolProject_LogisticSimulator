package Main.Modele;

import Main.Modele.Composantes.Avion;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

public class EntrepotIntegrationTest {
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
    public void PeutProduire_UnAvion_Vrai() throws Exception {
        entrepot.ajouterInventaire(Avion.class.toString(),1);
        assertThat(entrepot.extraireSortie(),instanceOf(Avion.class));
    }
    @Test
    public void PeutProduire_AucunAvion_Faux() throws Exception {
        assertNull(entrepot.extraireSortie());
    }
}