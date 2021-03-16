package UserInterface;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class ViewPolinom extends JFrame
{
    //Declararile obiectelor folosite pentru gui:
    private ModelPolinom model;
    private JLabel titlu = new JLabel("Polynomial calculator:");
    private JLabel pol1 = new JLabel("Introduce-ti primul polinom:");
    private JTextField polinom1 = new JTextField(35);
    private JLabel pol2 = new JLabel("Introduce-ti al doilea polinom:");
    private JTextField polinom2 = new JTextField(35);
    private JLabel pol3 = new JLabel("Polinomul ce rezulta:");
    private JTextField polinom3 = new JTextField(50);
    private JButton buton1 = new JButton("Add");
    private JButton buton2= new JButton("Substract");
    private JButton buton3 = new JButton("Multiply");
    private JButton buton4 = new JButton("Divide");
    private JButton buton5 = new JButton("Derivate");
    private JButton buton6 = new JButton("Integral");

    public ViewPolinom(ModelPolinom model)
    {
        //Modificare sizeuri: la labels, texts, butoane, etc...
        this.model = model;
        titlu.setFont(new Font("Times New Roman", Font.BOLD, 20));
        pol1.setFont(new Font("Times New Roman", Font.BOLD, 20));
        pol2.setFont(new Font("Times New Roman", Font.BOLD, 20));
        pol3.setFont(new Font("Times New Roman", Font.BOLD, 20));

        polinom1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        polinom1.setPreferredSize(new Dimension(300, 30));
        polinom2.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        polinom2.setPreferredSize(new Dimension(300, 30));
        polinom3.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        polinom3.setPreferredSize(new Dimension(300, 30));

        polinom3.setEditable(false);

        buton1.setPreferredSize(new Dimension(200, 60));
        buton2.setPreferredSize(new Dimension(200, 60));
        buton3.setPreferredSize(new Dimension(200, 60));
        buton4.setPreferredSize(new Dimension(200, 60));
        buton5.setPreferredSize(new Dimension(200, 60));
        buton6.setPreferredSize(new Dimension(200, 60));

        //Creeare de paneluri in frame, pentru a arata frumos:
        JPanel c = new JPanel();
        c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
        c.setPreferredSize(new Dimension(800, 500));
        c.setBackground(Color.yellow);

        JPanel rand1 = new JPanel();
        rand1.setLayout(new FlowLayout());
        rand1.setBackground(Color.yellow);

        JPanel rand2 = new JPanel();
        rand2.setLayout(new FlowLayout());
        rand2.setBackground(Color.yellow);

        JPanel rand3 = new JPanel();
        rand3.setLayout(new FlowLayout());
        rand3.setBackground(Color.yellow);

        JPanel rand4 = new JPanel();
        rand4.setLayout(new FlowLayout());
        rand4.setBackground(Color.yellow);

        JPanel rand5 = new JPanel();
        rand5.setLayout(new FlowLayout());
        rand5.setBackground(Color.yellow);

        JPanel rand6 = new JPanel();
        rand6.setLayout(new FlowLayout());
        rand6.setBackground(Color.yellow);

        JPanel rand7 = new JPanel();
        rand7.setLayout(new FlowLayout());
        rand7.setBackground(Color.yellow);

        rand1.add(titlu);

        rand2.add(pol1);
        rand2.add(Box.createRigidArea(new Dimension(100, 0)));
        rand2.add(polinom1);

        rand3.add(pol2);
        rand3.add(Box.createRigidArea(new Dimension(100, 0)));
        rand3.add(polinom2);

        rand4.add(pol3);
        rand4.add(Box.createRigidArea(new Dimension(20, 0)));
        rand4.add(polinom3);

        rand5.add(buton1);
        rand5.add(Box.createRigidArea(new Dimension(100, 0)));
        rand5.add(buton2);

        rand6.add(buton3);
        rand6.add(Box.createRigidArea(new Dimension(100, 0)));
        rand6.add(buton4);

        rand7.add(buton5);
        rand7.add(Box.createRigidArea(new Dimension(100, 0)));
        rand7.add(buton6);

        //Adaugare la frameul final:
        c.add(rand1);
        c.add(Box.createRigidArea(new Dimension(0, 10)));
        c.add(rand2);
        c.add(Box.createRigidArea(new Dimension(0, 10)));
        c.add(rand3);
        c.add(Box.createRigidArea(new Dimension(0, 10)));
        c.add(rand4);
        c.add(Box.createRigidArea(new Dimension(0, 10)));
        c.add(rand5);
        c.add(Box.createRigidArea(new Dimension(0, 10)));
        c.add(rand6);
        c.add(Box.createRigidArea(new Dimension(0, 10)));
        c.add(rand7);

        this.setContentPane(c);
        this.pack();
        this.setTitle("Calculator Tema 1.");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //2 getteri, 1 etter:
    public String getPol1()
    {
        return polinom1.getText();
    }

    public String getPol2()
    {
        return polinom2.getText();
    }

    public void setPol3(String sir)
    {
        polinom3.setText(sir);
    }

    //Pentru toate 6 butoanele:
    public void butonAdunare(ActionListener buton)
    {
        buton1.addActionListener(buton);
    }

    public void butonScadere(ActionListener buton)
    {
        buton2.addActionListener(buton);
    }

    public void butonInmultire(ActionListener buton)
    {
        buton3.addActionListener(buton);
    }

    public void butonImpartire(ActionListener buton)
    {
        buton4.addActionListener(buton);
    }

    public void butonDerivare(ActionListener buton)
    {
        buton5.addActionListener(buton);
    }

    public void butonIntegrare(ActionListener buton)
    {
        buton6.addActionListener(buton);
    }
}
