package UserInterface;

import DataModels.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ModelPolinom {

    //Fara fielduri, nimic in constructori, doar pentru apeluri de metode:
    public ModelPolinom()
    {
    }

    //Convertirea polinomului din lista de monoame in string, pentru afisarea in gui:
    public String convertirePolinom(Polinom p)
    {
        //Sortez inainte de afisare:
        p.sortarePolinoame();

        //Multe conditii pentru afisare ce doresc, in functie de structura matchului:
        //de exemplu: x, coef * x ^ grad, coef, x^grad, etc...
        //De asemenea am conditii speciale pentru numere reale sau intregi:
        String s = "";
        for(Monom m: p.getMonoame()) {
            if(m.getCoeficientReal() == -50000) {
                if(m.getCoeficient() == 0 && m.getGrad() == 0) {
                    continue;
                }

                if(m.getCoeficient() == 0) {
                    continue;
                }

                if(m.getCoeficient() > 0) {
                    if(s.equals("") == false) {
                        s = s + "+";
                    }
                }

                if(m.getGrad() == 0) {
                    if(m.getCoeficient() == 1  || m.getCoeficient() == -1) {
                        if(m.getCoeficient() == 1) {
                            s = s + "1 ";
                        }
                        else {
                            s = s + "-1 ";
                        }
                    }
                    else {
                        s = s + m.getCoeficient() + " ";
                    }
                    continue;
                }

                if(m.getGrad() == 1) {
                    if(m.getCoeficient() == 1 || m.getCoeficient() == -1) {
                        s = s + "x ";
                    }
                    else {
                        s = s + m.getCoeficient() + " * x ";
                    }
                    continue;
                }

                if(m.getCoeficient() == 1) {
                    s = s + "x^" + m.getGrad() + " ";
                }
                else {
                    s = s + m.getCoeficient() + " * x^" + m.getGrad() + " ";
                }
            }

            else
            {
                if(m.getCoeficientReal() == 0 && m.getGrad() == 0) {
                    continue;
                }
                if(m.getCoeficientReal() > 0) {
                    if(s.equals("") == false) {
                        s = s + "+";
                    }
                }
                if(m.getGrad() == 0) {
                    if(m.getCoeficientReal() == 1  || m.getCoeficient() == -1) {
                        s = s + " ";
                    }
                    else {
                        s = s + m.getCoeficientReal() + " ";
                    }
                    continue;
                }

                if(m.getGrad() == 1) {
                    if(m.getCoeficientReal() == 1 || m.getCoeficientReal() == -1) {
                        s = s + "x ";
                    }
                    else {
                        s = s + m.getCoeficientReal() + " * x ";
                    }
                    continue;
                }

                if(m.getCoeficientReal() == 1) {
                    s = s + "x^" + m.getGrad() + " ";
                }
                else {
                    s = s + m.getCoeficientReal() + " * x^" + m.getGrad() + " ";
                }
            }
        }
        return s;
    }

    //Pentru extragerea monoamelor din stringurile date in interfata, folosing pattern matching:
    public Polinom extragereMonoame(String s1)
    {
        //Patternurile pe care le caut, primul pentru erori, urmatoarele 3 in whileuri diferite, gasesc matchrui pe rand
        //si astfel am grupuri de matchuri diferite pe care le tratez pe rand; (cu multe conditii)
        //Sunt aceleasti tipuri ca si cum ziceam mai sus sau in documentatie, x, x^grad, etc...
        String eroare = "(([+-]?)(\\d{0,}))?( \\* )?x?(\\^(\\d{0,}))?";
        String test1 = "(([+-]?)(\\d{0,}))?( \\* )?x\\^(\\d{0,})";
        String test2 = "(([+-]?)(\\d{0,}))?( \\* )?x( [+-]|$)";
        String test3 = "(([+-] ?)|^)([+-])?(\\d*)( [+-]|$)";

        Pattern testEroare = Pattern.compile(eroare);
        Pattern p1 = Pattern.compile(test1);
        Pattern p2 = Pattern.compile(test2);
        Pattern p3 = Pattern.compile(test3);

        //Gasesc un matcher:
        Matcher testEroareFinal = testEroare.matcher(s1);
        Matcher m11 = p1.matcher(s1);
        Matcher m12 = p2.matcher(s1);
        Matcher m13 = p3.matcher(s1);

        Polinom pol1 = new Polinom();

        int contor1 = 0;
        while(testEroareFinal.find()) {
            contor1++;
        }
        contor1 = (contor1+1)/2;

        int contor2 = 0;
        //In aceste 3 while gasesc diferite grupe, pe care le adaug la polinomul rezultat pe rand;
        //Nu voi explica conditiile, dar au fost facute astfel incat sa fie cat mai bun rezultatul;
        while(m11.find()) {
            contor2++;

            String semn;
            String coef;
            String grad;

            semn = m11.group(2);
            coef = m11.group(3);
            grad = m11.group(5);

            if(coef.equals("") == true) {
                if(semn.equals("-") == true) {
                    pol1.addMonom(new Monom(Integer.parseInt(grad), -1));
                }
                else {
                    pol1.addMonom(new Monom(Integer.parseInt(grad), 1));
                }
                continue;
            }

            if(semn.equals("-") == true) {
                pol1.addMonom(new Monom(Integer.parseInt(grad), -Integer.parseInt(coef)));
            }
            else {
                pol1.addMonom(new Monom(Integer.parseInt(grad), Integer.parseInt(coef)));
            }
        }

        while(m12.find())
        {
            contor2++;

            String semn;
            String coef;

            semn = m12.group(2);
            coef = m12.group(3);

            if(semn.equals("-") == true)
            {
                if(coef.equals("") == true) {
                    pol1.addMonom(new Monom(1, -1));
                }
                else {
                    pol1.addMonom(new Monom(1, -Integer.parseInt(coef)));
                }
            }
            else
            {
                if(coef.equals("") == true) {
                    pol1.addMonom(new Monom(1, 1));
                }
                else {
                    pol1.addMonom(new Monom(1, Integer.parseInt(coef)));
                }
            }
        }

        while(m13.find())
        {
            contor2++;

            String semn;
            String coef;

            semn = m13.group(1);
            coef = m13.group(4);

            if(semn.equals("-") == true) {
                pol1.addMonom(new Monom(0, -Integer.parseInt(coef)));
            }
            else {
                pol1.addMonom(new Monom(0, Integer.parseInt(coef)));
            }
        }

        if(contor1 != contor2) {
            Polinom pol2 = new Polinom();
            pol2.addMonom(new Monom(0, 0));
            return pol2;
        }
        else {
            return pol1;
        }
    }
}