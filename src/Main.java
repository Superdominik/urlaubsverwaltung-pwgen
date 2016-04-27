import org.springframework.security.crypto.password.StandardPasswordEncoder;

import java.io.Console;
import java.io.IOException;
import java.util.Arrays;

public class Main {

    public static void main (String args[]) throws IOException {

        Console c = System.console();
        if (c == null) {
            System.err.println("Fehler: Die Anwendung wurde nicht von der Konsole ausgeführt!");
            System.exit(1);
        }

        boolean noMatch;
        do {
            char [] newPassword1 = c.readPassword("Geben Sie das Passwort ein: ");
            char [] newPassword2 = c.readPassword("Geben Sie das Passwort erneut ein: ");
            noMatch = ! Arrays.equals(newPassword1, newPassword2);
            if (noMatch) {
                c.format("Keine Übereinstimmung der Passwörter. Haben Sie sich vertippt? Erneute Eingabe:%n");
            } else {
                String hash = toUVHash(newPassword1);
                c.format("Der zu diesem Passwort gehörige, mit der Datenbank der Urlaubsverwaltung konforme Hash, lautet:%n" + hash);
            }
            Arrays.fill(newPassword1, ' ');
            Arrays.fill(newPassword2, ' ');
        } while (noMatch);
    }

    private static String toUVHash(char[] password) {

        CharSequence cs_password = java.nio.CharBuffer.wrap(password);

        return new StandardPasswordEncoder().encode(cs_password);
    }
}