package Main.Modele;

import Main.Modele.Composantes.Aile;
import Main.Modele.Composantes.Avion;
import Main.Modele.Composantes.Metal;
import Main.Modele.Composantes.Moteur;
import Main.Modele.Usines.UsineAile;
import Main.Modele.Usines.UsineAvion;
import Main.Modele.Usines.UsineMatiere;
import Main.Modele.Usines.UsineMoteur;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class UsineTest {
    private Usine usAile;
    private final int QTY_MIN=4;
    int int_prod;
    String composanteEntree;

    @Before
    public void Setup()
    {
        int_prod=10;
        composanteEntree = Metal.class.toString();

        usAile=new UsineAile(int_prod);
        usAile.AjouterTypeProduction(composanteEntree,QTY_MIN);

    }
    @Test
    public void AjoutInventaire_UsineInventaireVide_UsineRetourneQuantiteAjouteeContenueEnInventaire(){
        int quantiteAjoutee=1;

        assertEquals(0,usAile.getQuantiteInventaire(composanteEntree));

        usAile.ajouterInventaire(composanteEntree,quantiteAjoutee);

        assertEquals(quantiteAjoutee,usAile.getQuantiteInventaire(composanteEntree));
    }
    @Test
    public void UsinesProduisentBonnesComposantes_CreerChaqueTypeUsine_ChaqueTypeUsineRetourneTypeDeProductionEnSortie()
    {

        Usine usAvion=new UsineAvion(int_prod);
        usAvion.AjouterTypeProduction(Moteur.class.toString(),QTY_MIN);
        usAvion.AjouterTypeProduction(Aile.class.toString(),QTY_MIN);
        Usine usMoteur=new UsineMoteur(int_prod);
        usMoteur.AjouterTypeProduction(Metal.class.toString(),QTY_MIN);
        Usine usMetal=new UsineMatiere(int_prod);

        usAvion.ajouterInventaire(Moteur.class.toString(),QTY_MIN);
        usAvion.ajouterInventaire(Aile.class.toString(),QTY_MIN);
        usMoteur.ajouterInventaire(Metal.class.toString(),QTY_MIN);
        usAile.ajouterInventaire(Metal.class.toString(),QTY_MIN);

        usAvion.avancerTour(int_prod);
        usAile.avancerTour(int_prod);
        usMoteur.avancerTour(int_prod);
        usMetal.avancerTour(int_prod);

        assertThat(usAile.extraireSortie(),instanceOf(Aile.class));
        assertThat(usAvion.extraireSortie(),instanceOf(Avion.class));
        assertThat(usMoteur.extraireSortie(),instanceOf(Moteur.class));
        assertThat(usMetal.extraireSortie(),instanceOf(Metal.class));
    }
    @Test
    public void AjouterInventaire_ProductionNonDefinie_RetourneMoinsUn(){
        int ajoutInventaire=4;

        usAile.ajouterInventaire(Aile.class.toString(),ajoutInventaire);

        assertEquals(-1,usAile.getQuantiteInventaire(Aile.class.toString()));
    }
    @Test
    public void EnleverDeInventaire_UsineAInventaireRetraitPlusGrandInventaire_InventaireEstZero(){
        int ajoutInventaire=4;
        int retraitInventaire=-5;
        usAile.ajouterInventaire(composanteEntree,ajoutInventaire);
        usAile.ajouterInventaire(composanteEntree,retraitInventaire);

        assertEquals(0,usAile.getQuantiteInventaire(composanteEntree));
    }
    @Test
    public void ProduireComposante_UsineAInventaireSuffisantPourProduire_InventaireDuneComposanteADiminueDeLaQuantiteRequise(){
        int ajoutInventaire=5;

        usAile.ajouterInventaire(composanteEntree,ajoutInventaire);
        usAile.avancerTour(int_prod);

        usAile.extraireSortie();

        assertEquals(ajoutInventaire-QTY_MIN,usAile.getQuantiteInventaire(composanteEntree));
    }
    @Test
    public void PasDeProductionSiInventaireInsuffisant_UsineContientpeuInventaire_UsineNeProduitPas(){
        int ajoutInventaire=3;

        usAile.ajouterInventaire(composanteEntree,ajoutInventaire);
        usAile.avancerTour(int_prod);

        assertNull(usAile.extraireSortie());
    }
    @Test
    public void usineNeProduitPasSiPasAssezDeTemps_UsineAInventaireSuffisantAuPremierTour_UsineNeProduitPas(){
        int avancementTour=1;

        usAile.ajouterInventaire(composanteEntree,QTY_MIN);
        usAile.avancerTour(avancementTour);

        assertNull(usAile.extraireSortie());
    }

    @Test
    public void usineProduitSiInventaireSuffisant_UsineQuantiteSuffisanteBonneComposanteBonTour_UneComposante()
    {
        int ajoutInventaire=4;

        usAile.ajouterInventaire(composanteEntree,QTY_MIN);
        usAile.avancerTour(int_prod);

        assertNotNull(usAile.extraireSortie());

    }
}
