
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

import oracle.jdbc.*;

public class DBConnect {

    private String currentC;

    private Statement stat;
    private Connection conn1;

    public DBConnect() {

    }

    public DBConnect(String connection) {
        this.currentC = connection;
    }

    public void createConn() {

        try {
            String url2 = "jdbc:oracle:thin:@pythia.iro.umontreal.ca:1521:orcl";
            String usr = "gauthphi";
            String pwd = "phip110G";
            Class.forName("oracle.jdbc.driver.OracleDriver");

            System.out.println("Connecting Connecting to Database Database URL = " + url2);
            System.out.println("Connected Connected...Now creating creating a statement statement");
            Connection conn = DriverManager.getConnection(url2, usr, pwd);
            setConn1(conn);
            Statement stat = conn.createStatement();
            setStat(stat);


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();

        }
    }


    public void closeConn() {
        try {
            System.out.println("Closing connection" + conn1.getClientInfo());
            conn1.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    public Statement getStat() {
        return stat;
    }

    public void setStat(Statement stat) {
        this.stat = stat;
    }

    public Connection getConn1() {
        return conn1;
    }

    public void setConn1(Connection conn1) {
        this.conn1 = conn1;
    }
}
