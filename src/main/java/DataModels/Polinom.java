package DataModels;
import java.util.ArrayList;
import java.util.Collections;

public class Polinom
{
    //Lista de monoame, un array list
    private ArrayList<Monom> monoame = new ArrayList<Monom>();

    //Constructorul gol
    public Polinom() {
    }

    //Adaug monoame:
    public void addMonom(Monom m1)
    {
        monoame.add(m1);
    }

    //Sortez in functie de grad:
    public void sortarePolinoame()
    {
        Collections.sort(monoame);
    }

    //Pentru ca in polinom sa avem doar un monom de un grad anume, nu mai multe
    public Polinom gasireGradeMultiple()
    {
        Polinom pol = new Polinom();
        int[] vectorFrecventa = new int[100000];

        for(Monom m: monoame) {
            vectorFrecventa[m.getGrad()]++;
        }

        for(int i=0; i<100000; i++) {
            if(vectorFrecventa[i]!=0) {
                Monom aux = new Monom(i, 0);

                for(Monom m: monoame) {
                    if(m.getGrad() == i) {
                        aux = m.addMonoame(m, aux);
                    }
                }
                pol.addMonom(aux);
            }
        }
        return pol;
    }

    //Operatia de inmultire:
    public Polinom inmultirePolinoame(Polinom p1, Polinom p2) {
        Polinom p3 = new Polinom();

        for(Monom mon1: p1.getMonoame()) {
            for (Monom mon2 : p2.getMonoame()) {
                if(mon1.getGrad() == mon2.getGrad() && mon1.getGrad() != 0)
                {
                    p3.addMonom(mon1.inmultireMonom(mon1, mon2));
                    continue;
                }
                if (mon1.inmultireMonom(mon1, mon2).getGrad() != -5 && //caz special
                        (mon1.inmultireMonom(mon1, mon2).getCoeficient() != -5)) {
                    p3.addMonom(mon1.inmultireMonom(mon1, mon2));
                }
            }
        }

        //Sortez sa nu se repete gradele intre monoame
        Polinom p4;
        p4 = p3.gasireGradeMultiple();
        return p4;
    }

    public Polinom derivarePolinom()
    {
        Polinom p3 = new Polinom();
        Polinom p4 = new Polinom();
        p4 = gasireGradeMultiple();

        //Doar un for este destul:
        for(Monom mon1: p4.getMonoame()) {
            p3.addMonom(mon1.derivareMonom());
        }
        return p3;
    }

    public Polinom integrarePolinom()
    {
        //Analog cu derivarea:
        Polinom p3 = new Polinom();
        Polinom p4 = gasireGradeMultiple();

        for(Monom mon1: p4.getMonoame()) {
            p3.addMonom(mon1.integrareMonom());
        }
        return p3;
    }

    public Polinom addPolinoame(Polinom p1, Polinom p2)
    {
        Polinom p3 = new Polinom();

        //La inceput fac monoamele unice, la fiecare operatie
        Polinom pe1 = p1.gasireGradeMultiple();
        Polinom pe2 = p2.gasireGradeMultiple();

        //Primele adaugari: (la gradele identice intre polinoame si la gradele din polinom 1 unice)
        for(Monom mon1: pe1.getMonoame()) {
            int TestIntrare = 0;
            for(Monom mon2: pe2.getMonoame()) {
                if(mon1.getGrad() == mon2.getGrad())
                {
                    TestIntrare = 1;
                    Monom mon3 = mon2.addMonoame(mon1, mon2);
                    p3.addMonom(mon3);
                }
            }
            if(TestIntrare == 0) {
                p3.addMonom(mon1);
            }
        }

        //Adaugarile din polinomul 2: (gradele unice)
        for(Monom monom2: pe2.getMonoame())
        {
            int DiferitDeToate = 0;
            for(Monom monom3: p3.getMonoame()) {
                if(monom2.getGrad() == monom3.getGrad()) {
                    DiferitDeToate = 1;
                    break;
                }
            }

            if(DiferitDeToate == 0) {
                p3.addMonom(monom2);
            }
        }
        return p3;
    }

    public Polinom subbPolinoame(Polinom p1, Polinom p2)
    {
        //Analog ca la adunare:
        Polinom p3 = new Polinom();

        Polinom pe1 = p1.gasireGradeMultiple();
        Polinom pe2 = p2.gasireGradeMultiple();

        for (Monom mon1 : pe1.getMonoame()) {
            int TestIntrare = 0;
            for (Monom mon2 : pe2.getMonoame()) {
                if (mon1.getGrad() == mon2.getGrad()) {
                    TestIntrare = 1;
                    Monom mon3 = mon2.subbMonom(mon1, mon2);
                    p3.addMonom(mon3);
                }
            }
            if (TestIntrare == 0) {
                p3.addMonom(mon1);
            }
        }
        for (Monom monom2 : pe2.getMonoame()) {
            int DiferitDeToate = 0;
            for (Monom monom3 : p3.getMonoame()) {
                if (monom2.getGrad() == monom3.getGrad()) {
                    DiferitDeToate = 1;
                    break;
                }
            }
            if (DiferitDeToate == 0) {
                //Conditia in plus fata de adunare, -1:
                Monom aux = new Monom(monom2.getGrad(), (-1) * monom2.getCoeficient());
                p3.addMonom(aux);
            }
        }
        return p3;
    }

    //As fi putut implementa diferit, dar am incercat doar sa mearga:
    public Polinom impartirePolinoame(Polinom p1, Polinom p2) {
        Polinom pe1 = p1.gasireGradeMultiple();
        Polinom pe2 = p2.gasireGradeMultiple();
        Polinom p3 = new Polinom();
        pe1.sortarePolinoame();
        pe2.sortarePolinoame();
        Polinom auxPol1 = new Polinom();
        Polinom auxPol2 = new Polinom();

        //Copiez continut polinoame
        for(Monom m : pe1.monoame) {
            auxPol1.addMonom(m);
        }
        for(Monom m : pe2.monoame) {
            auxPol2.addMonom(m);
        }

        int counter = 0;
        while(true) {
            //Conditie de iesire: daca nu gaseste rezultat
            if(counter == 50) {
                p3.getMonoame().removeAll(p3.getMonoame());
                p3.addMonom(new Monom(0, 0));
                break;
            }

            //Initializate de fiecare data in for:
            Monom auxMon1 = new Monom(0, 0);
            Monom auxMon2 = new Monom(0, 0);
            Monom auxMon3 = new Monom(0, 0);
            auxPol1.sortarePolinoame();
            auxMon1 = auxPol1.getMonoame().get(0); //gradele maxime, dupa ce le sortez;
            auxMon2 = auxPol2.getMonoame().get(0); //auxPol2 nu se schimba deci nu trebuie sortat

            Polinom auxPol5 = new Polinom();
            Polinom auxPol3 = new Polinom();
            Polinom auxPol4;

            //Impartire grade maxime:
            auxMon3 = auxMon1.impartireMonom(auxMon1, auxMon2);
            auxPol3.addMonom(auxMon3);
            //Inmultire rezultat de mai sus cu polinomul la care impartim, p2;
            auxPol4 = auxPol3.inmultirePolinoame(p2, auxPol3);
            //Scadem pentru a obtine noul polinom dupa care ne uitam
            auxPol3 = auxPol1.subbPolinoame(auxPol1, auxPol4);

            for (Monom em : auxPol3.getMonoame()) {
                //Extragem fortat monoamele cu coeficient 0 afara, sa nu incurce la calcule
                if (em.getCoeficient() != 0 || em.getCoeficient() == -50000) {
                    auxPol5.addMonom(em);
                }
            }

            //Rescriem in auxPol1, deci il golim prima data:
            auxPol1.getMonoame().removeAll(auxPol1.getMonoame());
            for(Monom em : auxPol5.monoame) {
                auxPol1.addMonom(em);
            }
            //Adaugam monoamele pe rand in rezultatul final, cele gasite:
            p3.addMonom(auxMon3);
            //Conditia de break daca gaseste ce trebuie
            if(auxPol1.getMonoame().size() == 0) {
                break;
            }
            counter++;
        }
        return p3;
    }

    //Getter;
    public ArrayList<Monom> getMonoame()
    {
        return monoame;
    }

}

//Folosit pentru testare:
/*public void afisarePolinom()
    {
        String PolinomNou;
        sortarePolinoame();

        int IntrareOData = 0;
        if(monoame.get(0).getCoeficient() != -10000)
        {
            for(Monom m: monoame) {
                if(IntrareOData == 0) {
                    if(m.getCoeficient() > 0) {
                        System.out.printf(m.getCoeficient() + " * " + "x^" + m.getGrad());
                    }
                    else {
                        System.out.printf(" " + m.getCoeficient() + " * " + "x^" + m.getGrad());
                    }
                    IntrareOData = 1;
                    continue;
                }
                if(m.getCoeficient() > 0) {
                    System.out.printf(" +" + m.getCoeficient() + " * " + "x^" + m.getGrad());
                }
                else {
                    System.out.printf(" " + m.getCoeficient() + " * " + "x^" + m.getGrad());
                }
            }
            System.out.println(" = ?");
        }
        else
        {
            for(Monom m: monoame) {
                if(IntrareOData == 0) {
                    if(m.getCoeficientReal() > 0) {
                        System.out.printf(m.getCoeficientReal() + " * " + "x^" + m.getGrad());
                    }
                    else {
                        System.out.printf(" " + m.getCoeficientReal() + " * " + "x^" + m.getGrad());
                    }
                    IntrareOData = 1;
                    continue;
                }
                if(m.getCoeficientReal() > 0) {
                    System.out.printf(" +" + m.getCoeficientReal() + " * " + "x^" + m.getGrad());
                }
                else {
                    System.out.printf(" " + m.getCoeficientReal() + " * " + "x^" + m.getGrad());
                }
            }
            System.out.println(" = ?");
        }
    }
    */