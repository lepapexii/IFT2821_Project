import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Events {

    public AlterDB db;
    public GUI gui;
    public QueryBuilder qb;


    Events() throws IOException, SQLException {

        AlterDB db = new AlterDB();
        this.db = db;

        GUI gui = new GUI();
        this.gui = gui;
        //gui.start();

        addListen();


    }

    public ArrayList<String> extractRes(String query, String col) throws SQLException {

        //STORE le resultat de la recherche
       ResultSet ok = db.getStat().executeQuery(query);
       ArrayList<String> temp = new ArrayList<>();
       String fullRow = new String();
       ArrayList<String> temp2 = new ArrayList<>();
        while(ok.next()){
            int length =  ok.getMetaData().getColumnCount();
            temp2.clear();
           //System.out.println( ok.getString(2));

           for (int i = 1; i <= length; i++) {


               temp2.add(ok.getString(i) + " ");


           }

            fullRow = temp2.toString();
            System.out.println(fullRow);
            temp.add(fullRow);




       }



       return temp;

    }

    public void ListOnMainFrame(String query, String col) throws SQLException {
       gui.tata.setText("");
       ArrayList<String> res = extractRes(query, col);
        for (String str:res) {

            gui.tata.append(col.toString() + "  " + str + "\n");
            System.out.println(str);
        }

    }

    public void ListOnTopFrame(String query, String col) throws SQLException {

        ArrayList<String> res = extractRes(query, col);
        String[] temp = new String[res.size()];
        for (String str:res) {


            int ind =   res.indexOf(str);

            temp[ind] = str;

            Box1.addItem(str);
            //JMenuItem item = new JMenuItem(str);


            //JMenuItem item2 = new JMenuItem(""+ind);
           // m2t.add(item2);
            //System.out.println(str);
        }
    }

    public void statement(String temp, String col, String location){
        try {


                if(location.startsWith("Main")){
                    ListOnMainFrame(temp, col);
                }
                if(location.startsWith("Top")){
                    ListOnTopFrame(temp, col);
                }
                else {
                    System.out.println("location introuvable on envoi au Main");
                    ListOnMainFrame(temp, col);
                }





        } catch (SQLException e1) {
            e1.printStackTrace();
        }

    }


    public  JMenu m1t;
    public  JMenu m2t;
    public JComboBox<String> Box1;
    QueryBuilder buildIt = new QueryBuilder();

    public void addListen() throws SQLException {

        //JPanel pan = this.gui.pan;
        JButton b1 =  this.gui.bb1;
        JButton b2 =  this.gui.bb2;
        JButton b3 =  this.gui.bb3;
        JMenuBar jm = this.gui.jm1;
        JComboBox boxer = this.gui.boxer;
        JTextField jtf1 = this.gui.tf1;
        JMenu m1 = new JMenu("Liste Nom");
        JMenu m2 = new JMenu("Liste ID");
        Box1 = boxer;
        m1t  = m1;
        m2t = m2;
        jm.add(m1);
        jm.add(m2);

        gui.framez.getContentPane().add(BorderLayout.NORTH, jm);

        ActionListener al1 = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               String txt1 = jtf1.getText();
               if(txt1.length() <= 3){
                  String stat =  buildIt.BenevoleInSecteur(txt1);
                   statement(stat,"","Main");
                   System.out.println(stat);
                   System.out.println("ICIC  " +  gui.tata.getText().split("]")[0]);
                   ArrayList<String> Curr =  getMenuData("Main");
                   try {
                       statement(buildIt.FormTeam(Curr), "", "Top");
                   } catch (IOException e1) {
                       e1.printStackTrace();
                   } catch (SQLException e1) {
                       e1.printStackTrace();
                   }


               }


            }
        };
        jtf1.addActionListener(al1);



        MouseAdapter ml1 = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                statement("SELECT * FROM SECTEUR", "","Main");
            }
        };
        //b1.addMouseListener(ml1);

        MouseAdapter ml2 = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                statement("SELECT * FROM SECTEUR", "","Main");
            }
        };
        b2.addMouseListener(ml2);

        MouseAdapter ml3 = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String q = buildIt.join("CLIENT","SECTEUR");
                statement(q,"","Top");
            }
        };
        b2.addMouseListener(ml3);


        MouseAdapter ml4 = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                statement("SELECT * FROM CLIENT","","Top");

            }
        };
        b1.addMouseListener(ml4);



    }

    public ArrayList<String> getMenuData(String menu){
        String[] txtTab = gui.tata.getText().split("]|\\[");
        ArrayList<String> txtParsed = new ArrayList<>();
        for (String str: txtTab) {
           // String ok = str.substring(1, str.length()).trim();
            txtParsed.add(str);

        }
        if(menu.equals("Main")){
            for (String str2: txtParsed) {
                if(str2.contains("68")){
                    System.out.println( "IL A " + str2.substring(1).trim());
                }
            }
        }
        return txtParsed;

    }





}
