import javax.swing.*;
import javax.xml.transform.Result;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.lang.System.out;

public class GUI{

    GUI(){
        start();
        evt();
        try {
            AlterDB ok = new AlterDB();
            ok.getConn();
            ok.createTables(ok.importThis.splitFileLMDByCommand(";"));
            /*for (int i = 0; i < ; i++) { }*/
            ok.insertInto(ok.importThis.splitFileLMDByCommand(";"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static JTextArea tata = new JTextArea();
    public static JFrame framez = new JFrame();
    public static JButton bb1 = new JButton();
    public static JButton bb2 = new JButton();
    public static JButton bb3 = new JButton();
    public static JMenuBar jm1 = new JMenuBar();
    public static JPanel pan=new JPanel();
    public static JTextField tf1 = new JTextField();

    public JComboBox<String> boxer = new JComboBox<> ();
    public void start()
    {    //CREATING	THE	frame
        JFrame frame = new JFrame("Bienvenue");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,400);
        //Creating the menubar and adding components
        JMenuBar MB = new JMenuBar();
        JComboBox<String> CB=new JComboBox<String> ();
        //Creating the panel at the bottom and adding components
        JPanel panel = new JPanel();
        //The panel is not visible in output
        JButton B1=new JButton("Lister Usagers");
        //JTEXTFIELD
        JTextField TF =new JTextField(10);
        //Accepts up to 10 characters
        JButton	B2 = new JButton("Lister Secteurs");
        JButton B3 = new JButton("Lister ?");
        panel.add(B1);
        //Components added using flow layout
        panel.add(B1);
        //Components added using flow layout
        panel.add(TF);
        panel.add(B2);
        panel.add(B3);
        //	Text area at the center
        JTextArea TA = new JTextArea();
        bb1=B1;
        bb2=B2;
        bb3=B3;
        tata=TA;
        pan=panel;
        boxer=CB;
        tf1 = TF;
        //Adding components to the frame
        frame.getContentPane().add(BorderLayout.SOUTH,panel);
        frame.getContentPane().add(BorderLayout.NORTH, MB);
        frame.getContentPane().add(BorderLayout.CENTER,TA);
        frame.getContentPane().add(BorderLayout.NORTH,CB);
        MB.setVisible(true);
        frame.setVisible(true);
        this.framez = frame;
    }

    public void evt(){
        if(!tata.getMouseListeners().equals(false)){
            out.println(tata.getMouseListeners());
        }
    }
}