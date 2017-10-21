package Main.Modele;

import Main.Modele.Usines.UsineAile;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class ComposanteTest {
    Composante composante;
    UsineAile usine;
    final String TYPE_COMPOSANTE="aile";
    @Before
    public void setUp() throws Exception {
        composante=new Composante(0,new Point(32,32),TYPE_COMPOSANTE);
        usine=new UsineAile(0,1,new Point(320,32));
    }

    @Test
    public void get_type() throws Exception {
        assertEquals(TYPE_COMPOSANTE,composante.get_type());
    }

    @Test
    public void avancer() throws Exception {
        composante.setVitesse(0,1);
        composante.avancer();
        assertEquals(new Point(32,33),composante.get_position());
    }

    @Test
    public void arriveADestination() throws Exception {
        composante.set_destination(usine);
        composante.set_position(new Point(320,32));
        assertTrue(composante.arriveADestination());
    }
    @Test
    public void calculerVitesse(){
        Point vitesse=composante.calculerVitesse(usine.get_position());
        assertEquals(new Point(1,0),vitesse);
    }

}