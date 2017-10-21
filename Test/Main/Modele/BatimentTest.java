package Main.Modele;

import Main.Modele.Usines.UsineAvion;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BatimentTest {
    public static final String COMPOSANTE_UN = "moteur";
    public static final String COMPOSANTE_DEUX = "aile";
    Batiment usine;
    int intervalProd;
    int qtyAile,qtyMoteur;
    @Before
    public void setUp() throws Exception {
        intervalProd=10;
        qtyAile=3;
        qtyMoteur=5;
        usine=new UsineAvion(intervalProd,0,null);
        usine.ajouterTypeProduction(COMPOSANTE_UN,qtyMoteur);
        usine.ajouterTypeProduction(COMPOSANTE_DEUX,qtyAile);
    }
    @Test
    public void extraireSortie_PeutPasProduire_Faux(){
        usine.avancerTour(intervalProd);
        assertNull(usine.extraireSortie());
    }

    //region isInventaireSuffisant
    @Test
    public void isInventaireSuffisant_InventaireSuffisant_True() throws Exception {
        usine.gererAjout(COMPOSANTE_UN,qtyMoteur);
        usine.gererAjout(COMPOSANTE_DEUX,qtyAile);
        assertTrue(usine.isInventaireSuffisant());
    }
    @Test
    public void isInventaireSuffisant_InventaireInsuffisant_False() throws Exception {
        usine.gererAjout(COMPOSANTE_UN,qtyMoteur-2);
        usine.gererAjout(COMPOSANTE_DEUX,qtyAile-2);
        assertFalse(usine.isInventaireSuffisant());
    }
    @Test
    public void isInventaireSuffisant_UneEntreeSuffisanteAutreInsuffisante_False() throws Exception {
        usine.gererAjout(COMPOSANTE_UN,qtyMoteur-2);
        usine.gererAjout(COMPOSANTE_DEUX,qtyAile);
        assertFalse(usine.isInventaireSuffisant());
    }
    //endregion\

    //region ajouterInventaire
    @Test
    public void ajouterInventaire_AjouterUneComposante_Unecomposante(){
        usine.gererAjout(COMPOSANTE_UN,qtyMoteur);
        assertEquals(qtyMoteur,usine.getQuantiteInventaire(COMPOSANTE_UN));
    }
    @Test
    public void AjouterInventaire_ProductionNonDefinie_RetourneMoinsUn(){
        int ajoutInventaire=4;
        String classeNonSupportee="metal";
        usine.gererAjout(classeNonSupportee,ajoutInventaire);

        assertEquals(-1,usine.getQuantiteInventaire(classeNonSupportee));
    }
    //endregion


}