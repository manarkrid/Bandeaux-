package bandeau;

import java.util.List;
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

class ScenarioElement {
    Effect effect;
    int repeats;

    ScenarioElement(Effect e, int r) {
        effect = e;
        repeats = r;
    }
} 

public class Scenario {

    private final List<ScenarioElement> myElements = new LinkedList<>();
    private final ReentrantLock scenarioLock = new ReentrantLock(); // V3 : verrou sur le scénario

    /**
     * Ajouter un effet au scénario (V3 : verrouillage pendant ajout)
     */
    public void addEffect(Effect e, int repeats) {
        scenarioLock.lock();
        try {
            // temporisation pour tester le verrou
            Thread.sleep(100);
            myElements.add(new ScenarioElement(e, repeats));
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            scenarioLock.unlock();
        }
    }

    /**
     * Jouer le scénario sur un bandeau (V2 : un bandeau = un scénario à la fois)
     */
    public void playOn(Bandeau b) {
        synchronized (b) { // verrou sur le bandeau
            scenarioLock.lock(); // verrou sur le scénario pendant le jeu
            try {
                for (ScenarioElement element : myElements) {
                    for (int i = 0; i < element.repeats; i++) {
                        element.effect.playOn(b);
                    }
                }
            } finally {
                scenarioLock.unlock();
            }
        }
    }

    /**
     * Démarrer le scénario sur un bandeau dans un thread
     */
    public void startOn(Bandeau b) {
        new Thread(() -> {
            playOn(b);
            System.out.println("Scénario terminé sur le bandeau : " + b);
        }).start();
    }
}
