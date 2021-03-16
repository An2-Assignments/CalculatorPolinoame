import UserInterface.ControllerPolinom;
import UserInterface.ModelPolinom;
import UserInterface.ViewPolinom;

public class Main
{
    public static void main(String[] args)
    {
        ModelPolinom model = new ModelPolinom();
        ViewPolinom view = new ViewPolinom(model);
        ControllerPolinom controller = new ControllerPolinom(model, view);
        view.setVisible(true);

        //System.out.println("Gata program.");
    }
}