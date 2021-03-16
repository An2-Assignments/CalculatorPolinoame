package UserInterface;

import DataModels.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerPolinom
{
    //Instantele model, view
    private ModelPolinom model;
    private ViewPolinom view;

    public ControllerPolinom(ModelPolinom model, ViewPolinom view) {
        this.model = model;
        this.view = view;

        //Toate butoanele;
        view.butonAdunare(new butonAdunareController());
        view.butonScadere(new butonScadereController());
        view.butonInmultire(new butonInmultireController());
        view.butonImpartire(new butonImpartireController());
        view.butonDerivare(new butonDerivareController());
        view.butonIntegrare(new butonIntegrareController());
    }

    //Toate clasele de la butoane:
    //La toate este analog cu prima, comentez doar la prima cum functioneaza
    class butonAdunareController implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            //Extragem polinoamele din interfata:
            String s1 = view.getPol1();
            String s2 = view.getPol2();

            //s1 = "x^2 +2 +5 * x^3";
            //s2 = "5 * x -2 * x^3";

            Polinom pol1 = model.extragereMonoame(s1);
            Polinom pol2 = model.extragereMonoame(s2);

            //Verificam daca este bine sau nu ce am extras:
            if((pol1.getMonoame().get(0).getGrad() == 0 && pol1.getMonoame().get(0).getCoeficient() == 0)
                    || (pol2.getMonoame().get(0).getGrad() == 0 && pol2.getMonoame().get(0).getCoeficient() == 0)) {
                view.setPol3("Date gresite de intrare. Rescrieti datele de intrare!");
            }
            else
                //daca este bine, calculam rezultatul, specific operatiei butonului:
            { Polinom rezultat = pol1.addPolinoame(pol1, pol2);
                String rezultatString = model.convertirePolinom(rezultat);
                view.setPol3(rezultatString);
            }
        }
    }

    class butonScadereController implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String s1 = view.getPol1();
            String s2 = view.getPol2();

            //s1 = "x +5 * x^2 +5 +6 * x^4";
            //s2 = "5 * x -2 * x^3 +17 * x^16";

            Polinom pol1 = model.extragereMonoame(s1);
            Polinom pol2 = model.extragereMonoame(s2);

            if((pol1.getMonoame().get(0).getGrad() == 0 && pol1.getMonoame().get(0).getCoeficient() == 0)
                || (pol2.getMonoame().get(0).getGrad() == 0 && pol2.getMonoame().get(0).getCoeficient() == 0)) {
                view.setPol3("Date gresite de intrare. Rescrieti datele de intrare!");
            }
            else {
                Polinom rezultat = pol1.subbPolinoame(pol1, pol2);
                String rezultatString = model.convertirePolinom(rezultat);
                view.setPol3(rezultatString);
            }
        }
    }

    class butonInmultireController implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String s1 = view.getPol1();
            String s2 = view.getPol2();

            //s1 = "x^2 +4";
            //s2 = "5 * x -2 * x^3";

            Polinom pol1 = model.extragereMonoame(s1);
            Polinom pol2 = model.extragereMonoame(s2);

            if((pol1.getMonoame().get(0).getGrad() == 0 && pol1.getMonoame().get(0).getCoeficient() == 0)
                    || (pol2.getMonoame().get(0).getGrad() == 0 && pol2.getMonoame().get(0).getCoeficient() == 0)) {
                view.setPol3("Date gresite de intrare. Rescrieti datele de intrare!");
            }
            else {
                Polinom rezultat = pol1.inmultirePolinoame(pol1, pol2);
                String rezultatString = model.convertirePolinom(rezultat);
                view.setPol3(rezultatString);
            }
        }
    }

    class butonImpartireController implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String s1 = view.getPol1();
            String s2 = view.getPol2();

            //s1 = "9 * x +x^2 +20";
            //s2 = "5 +x";

            Polinom pol1 = model.extragereMonoame(s1);
            Polinom pol2 = model.extragereMonoame(s2);

            if((pol1.getMonoame().get(0).getGrad() == 0 && pol1.getMonoame().get(0).getCoeficient() == 0)
                    || (pol2.getMonoame().get(0).getGrad() == 0 && pol2.getMonoame().get(0).getCoeficient() == 0)) {
                view.setPol3("Date gresite de intrare. Rescrieti datele de intrare!");
            }
            else {
                Polinom rezultat = pol1.impartirePolinoame(pol1, pol2);
                if(rezultat.getMonoame().get(0).getGrad() == 0 && rezultat.getMonoame().get(0).getCoeficient() == 0)
                {
                    view.setPol3("Date gresite de intrare, nu se poate imparti exact.");
                    return;
                }
                if(rezultat.getMonoame().get(0).getGrad() == 3 && rezultat.getMonoame().get(0).getCoeficient() == 3)
                {
                    view.setPol3("Implementare neterminata.");
                    return;
                }
                String rezultatString = model.convertirePolinom(rezultat);
                view.setPol3(rezultatString);
            }
        }
    }

    class butonDerivareController implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String s1 = view.getPol1();
            String s2 = view.getPol2();

            //s1 = "14 * x^16 +2 * x +5 -4 * x^3";
            //s2 = "13";

            Polinom pol1 = model.extragereMonoame(s1);
            Polinom pol2 = model.extragereMonoame(s2);

            if((pol1.getMonoame().get(0).getGrad() == 0 && pol1.getMonoame().get(0).getCoeficient() == 0)
                    || (pol2.getMonoame().get(0).getGrad() == 0 && pol2.getMonoame().get(0).getCoeficient() == 0)) {
                view.setPol3("Date gresite de intrare. Rescrieti datele de intrare!");
            }
            else {
                Polinom rezultat = pol1.derivarePolinom();
                String rezultatString = model.convertirePolinom(rezultat);
                view.setPol3(rezultatString);
            }
        }
    }

    class butonIntegrareController implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String s1 = view.getPol1();
            String s2 = view.getPol2();

            //s1 = "5 +3 * x^2 -5 * x +7 * x^18";
            //s2 = "13";

            Polinom pol1 = model.extragereMonoame(s1);
            Polinom pol2 = model.extragereMonoame(s2);

            if((pol1.getMonoame().get(0).getGrad() == 0 && pol1.getMonoame().get(0).getCoeficient() == 0)
                    || (pol2.getMonoame().get(0).getGrad() == 0 && pol2.getMonoame().get(0).getCoeficient() == 0)) {
                view.setPol3("Date gresite de intrare. Rescrieti datele de intrare!");
            }
            else {
                Polinom rezultat = pol1.integrarePolinom();
                String rezultatString = model.convertirePolinom(rezultat);
                view.setPol3(rezultatString);
            }
        }
    }
}