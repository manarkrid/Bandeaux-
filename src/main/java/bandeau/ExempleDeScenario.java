package bandeau;

public class ExempleDeScenario {

    public static void main(String[] args) {
        String message = (args.length > 0) ? args[0] : "Démonstration du bandeau";

        Scenario s = new Scenario();
        s.addEffect(new RandomEffect(message, 700), 1);
        s.addEffect(new TeleType("Je m'affiche caractère par caractère", 100), 1);
        s.addEffect(new Blink("Je clignote 10x", 100), 10);
        s.addEffect(new Zoom("Je zoome", 50), 1);
        s.addEffect(new FontEnumerator(10), 1);
        s.addEffect(new Rainbow("Comme c'est joli !", 30), 1);
        s.addEffect(new Rotate("2 tours à droite", 180, 4000, true), 2);

        Bandeau b1 = new Bandeau();
        Bandeau b2 = new Bandeau();
        Bandeau b3 = new Bandeau();

        System.out.println("CTRL+C pour terminer le programme");

        // Les threads sont démarrés 
        s.playOnAll(b1, b2, b3);
    }
}
