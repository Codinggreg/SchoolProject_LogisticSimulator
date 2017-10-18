package Main.Controlleur.Workers;

import javax.swing.SwingWorker;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Environnement extends SwingWorker<Object, String>{
	private boolean actif = true;
	private static final int DELAI = 100;
	
	@Override
	protected Object doInBackground() throws Exception {
		while(actif) {
			Thread.sleep(DELAI);
			/**
			 * C'est ici que vous aurez � faire la gestion de la notion de tour.
			 */
			firePropertyChange("TEST", null, "Ceci est un test");
		}
		return null;
	}

}