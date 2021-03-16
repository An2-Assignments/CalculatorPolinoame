package JUnitTesting;

import DataModels.Polinom;
import UserInterface.ModelPolinom;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JUnitPolDiv
{
    @ParameterizedTest
    @MethodSource("provideInput")
    void testareImpartire(String s1, String s2, String rezultatDorit)
    {
        ModelPolinom model = new ModelPolinom();

        Polinom pol1 = model.extragereMonoame(s1);
        Polinom pol2 = model.extragereMonoame(s2);

        Polinom rezultat = pol1.impartirePolinoame(pol1, pol2);
        String rezultatString = model.convertirePolinom(rezultat);

        assertEquals(rezultatString, rezultatDorit);
    }

    private static List<Arguments> provideInput()
    {
        List<Arguments> argumentList = new ArrayList<>();
        argumentList.add(Arguments.of("x^4 -5 * x^2 +4", "x -1", "x^3 +x^2 -4 * x -4 "));
        argumentList.add(Arguments.of("15 * x^4 +30 * x^3 +3 * x^2 +9 * x +6", "3 * x +6", "5 * x^3 +x +1 "));
        argumentList.add(Arguments.of("x^2", "x", "x "));
        //Intentionat gresit:
        //argumentList.add(Arguments.of("5 +10 * x -x^2", "6 * x +17 * x^2", "16 * x^2 +17 * x + 5 "));

        return argumentList;
    }
}

