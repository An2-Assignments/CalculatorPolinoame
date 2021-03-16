package JUnitTesting;

import DataModels.Polinom;
import UserInterface.ModelPolinom;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JUnitPolInteg
{
    @ParameterizedTest
    @MethodSource("provideInput")
    void testareIntegrare(String s1, String s2, String rezultatDorit)
    {
        ModelPolinom model = new ModelPolinom();

        Polinom pol1 = model.extragereMonoame(s1);
        Polinom pol2 = model.extragereMonoame(s2);

        Polinom rezultat = pol1.integrarePolinom();
        String rezultatString = model.convertirePolinom(rezultat);

        assertEquals(rezultatString, rezultatDorit);
    }

    private static List<Arguments> provideInput()
    {
        List<Arguments> argumentList = new ArrayList<>();
        argumentList.add(Arguments.of("5 +x^3 +4 * x^15 -x", "x^17 -5 * x^3 -20 +5 * x",
                "0.25 * x^16 +0.25 * x^4 -0.5 * x^2 +5 * x "));
        argumentList.add(Arguments.of("5 +10 * x -x^2", "6 * x +17 * x^2", "-0.3333333333333333 * x^3 +5.0 * x^2 +5 * x "));
        argumentList.add(Arguments.of("4 +x", "x^2 +7 -x", "0.5 * x^2 +4 * x "));
        //Intentionat gresit:
        //argumentList.add(Arguments.of("5 +10 * x -x^2", "6 * x +17 * x^2", "16 * x^2 -17 * ax + 5 "));

        return argumentList;
    }
}

