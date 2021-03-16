package JUnitTesting;

import DataModels.Polinom;
import UserInterface.ModelPolinom;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JUnitPolMul
{
    @ParameterizedTest
    @MethodSource("provideInput")
    void testareInmultire(String s1, String s2, String rezultatDorit)
    {
        ModelPolinom model = new ModelPolinom();

        Polinom pol1 = model.extragereMonoame(s1);
        Polinom pol2 = model.extragereMonoame(s2);

        Polinom rezultat = pol1.inmultirePolinoame(pol1, pol2);
        String rezultatString = model.convertirePolinom(rezultat);

        assertEquals(rezultatString, rezultatDorit);
    }

    private static List<Arguments> provideInput()
    {
        List<Arguments> argumentList = new ArrayList<>();
        argumentList.add(Arguments.of("5 +x^3 +4 * x^15 -x", "x^17 -5 * x^3 -20 +5 * x",
                "4 * x^32 +x^20 -21 * x^18 +5 * x^17 +20 * x^16 -80 * x^15 -5 * x^6 +10 * x^4 -45 * x^3 -5 * x^2 +45 * x -100 "));
        argumentList.add(Arguments.of("5 +10 * x -x^2", "6 * x +17 * x^2", "-17 * x^4 +164 * x^3 +145 * x^2 +30 * x "));
        argumentList.add(Arguments.of("4 +x", "x^2 +7 -x", "x^3 +3 * x^2 +3 * x +28 "));
        //Intentionat gresit:
        //argumentList.add(Arguments.of("5 +10 * x -x^2", "6 * x +17 * x^2", "16 * x^2 +20 * x + 5 "));

        return argumentList;
    }
}
