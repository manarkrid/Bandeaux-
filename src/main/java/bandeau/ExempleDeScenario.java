package bandeau;

public class ExempleDeScenario {

    public static void main(String[] args) {

        Scenario s1 = new Scenario();
        s1.addEffect(new Blink("Je clignote 10x", 100), 10);
        s1.addEffect(new Zoom("Je zoome", 50), 1);

        Scenario s2 = new Scenario();
        s2.addEffect(new FontEnumerator(10), 1);
        s2.addEffect(new Rainbow("Comme c'est joli !", 30), 1);

        Bandeau b1 = new Bandeau();
        Bandeau b2 = new Bandeau();
        Bandeau b3 = new Bandeau();

        System.out.println("CTRL+C pour terminer le programme");

        // Lancer les scénarios sur les bandeaux avec startOn (threads internes)
        s1.startOn(b1); // b1 exécute s1
        s2.startOn(b1); // sera bloqué jusqu'à ce que s1 termine (V2)
        s1.startOn(b2); // tourne en parallèle sur b2
        s2.startOn(b3); // tourne en parallèle sur b3

        System.out.println("Les bandeaux sont en cours d'affichage...");
        // main continue pendant que les bandeaux tournent
    }
}
