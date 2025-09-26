package bandeau;

import java.util.List;
import java.util.LinkedList;

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

    /**
     * Ajouter un effet au scénario
     */
    public void addEffect(Effect e, int repeats) {
        synchronized (myElements) {
            myElements.add(new ScenarioElement(e, repeats));
        }
    }

    /**
     * Jouer le scénario sur un bandeau
     */
    public void playOn(Bandeau b) {
        synchronized (b) {
            for (ScenarioElement element : myElements) {
                for (int repeats = 0; repeats < element.repeats; repeats++) {
                    element.effect.playOn(b);
                }
            }
        }
    }

    /**
     * Lancer plusieurs bandeaux en même temps 
     */
    public void playOnAll(Bandeau... bandeaux) {
        for (Bandeau b : bandeaux) {
            new Thread(() -> playOn(b)).start();
        }
    }
}
