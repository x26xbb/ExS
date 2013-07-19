package View.Util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Kevin Villalobos A.
 */
public class Util {

    //Validación de números
    public static boolean checkIsNumber(String toCheck) {
        try {
            Integer.parseInt(toCheck);
            return true;
        } catch (NumberFormatException numForEx) {
            //No era un numero
        }
        return false;
    }
    //Validación de mail regex

    public static boolean checkMail(String email) {
        initPattern();
        matcher = pattern.matcher(email);
        return matcher.matches();

    }

    private static void initPattern() {
        if (pattern == null) {
            pattern = Pattern.compile(EMAIL_PATTERN);
        }
    }
    private static Pattern pattern;
    private static Matcher matcher;
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    //
}
