package DataModels;

public class Monom implements Comparable<Monom>
{
    //Fieldurile, coeficient sau coeficient real:
    private int grad;
    private int coeficient;
    private double coeficientReal;

    public Monom(int grad, int coeficient)
    {
        this.grad = grad;
        this.coeficientReal = -50000; //initializez cu atat pentru a stii ca nu il folosesc daca nu folosesc constructorul de jos
        this.coeficient = coeficient;
    }

    public Monom(int grad, double coeficientReal)
    {
        this.grad = grad;
        this.coeficient = -10000; //la fel ca mai sus logica;
        this.coeficientReal = coeficientReal;
    }

    public Monom addMonoame(Monom m1, Monom m2)
    {
        Monom m3 = new Monom(0, 0);
        if(m1.grad == m2.grad) { //daca sunt egale gradele pot face adunarea
            m3.coeficient = m1.coeficient + m2.coeficient;
            m3.grad = m1.grad;
        }
        return m3;
    }

    public Monom subbMonom(Monom m1, Monom m2)
    {
        Monom m3 = new Monom(0, 0);
        if(m1.grad == m2.grad) { //la fel ca la adunare
            m3.coeficient = m1.coeficient - m2.coeficient;
            m3.grad = m1.grad;
        }
        return m3;
    }

    public Monom inmultireMonom(Monom m1, Monom m2)
    {
        Monom m3 = new Monom(-5, -5); //initializat cu atat pentru caz de erori
        m3.coeficient = m1.coeficient * m2.coeficient; //fara conditii aici
        m3.grad = m1.grad + m2.grad;
        return m3;
    }

    public Monom impartireMonom(Monom m1, Monom m2)
    {
        if(m1.getGrad() >= m2.getGrad()) //conditie
        {
            //Lucrez pe real:
            Monom m3 = new Monom(m1.getGrad() - m2.getGrad(), m1.coeficient / m2.getCoeficient());
            return m3;
        }
        else
        {
            Monom m3 = new Monom(-5, -5); //pentru erori
            return m3;
        }
    }

    public Monom derivareMonom()
    {
        //Luat pe cazuri:
        if(grad == 0) {
            Monom m4 = new Monom(0, 0);
            return m4;
        }
        if(grad == 1) {
            Monom m4 = new Monom(0, coeficient);
            return m4;
        }

        Monom m3 = new Monom(grad-1, coeficient * grad);
        return m3;
    }

    public Monom integrareMonom()
    {
        //Luat pe cazuri:
        if(grad == 0) {
            Monom m4 = new Monom(1, coeficient);
            return m4;
        }
        double aux = (double)coeficient / (grad+1);
        Monom m3 = new Monom(grad+1, aux);
        return m3;
    }

    public int compareTo(Monom m)
    {
        //Pentru sortare in functie de grad:
        if(this.grad >  m.getGrad()) {
            return -1;
        }
        else if(this.grad ==  m.getGrad()) {
            return 0;
        }
        else {
            return +1;
        }
    }

    //Setteri si getteri, nu ii folosesc pe toti, dar ii am aici (pentru ca fieldurile sunt private):
    public void setGrad(int grad)
    {
        this.grad = grad;
    }

    public void setCoeficient(int coeficient)
    {
        this.coeficient = coeficient;
    }

    public void setCoeficientReal(double coeficientReal)
    {
        this.coeficientReal = coeficientReal;
    }

    public int getGrad()
    {
        return grad;
    }

    public int getCoeficient()
    {
        return coeficient;
    }

    public double getCoeficientReal()
    {
        return coeficientReal;
    }
}