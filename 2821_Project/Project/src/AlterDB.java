import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AlterDB {

    ImportSQL importThis = new ImportSQL("C:\\temp\\LDD.sql");
    ImportSQL importThis2 = new ImportSQL("C:\\temp\\projet2.txt");
    public ArrayList<String> allCommands = new ArrayList<>();
    //public ArrayList<String> allCommands2 = new ArrayList<>();
    public String importEZ;
    private DBConnect conn;
    public ResultSet result;


    AlterDB() throws IOException {

        DBConnect dbc = new DBConnect();
        this.conn = dbc;
        //allCommands =    importThis.splitFileLMDByCommand();
        //allCommands2 =    importThis2.splitFileLMDByCommand();

    }

    //return a statement
    public Statement getStat() {
        DBConnect currCon = getConn();
        currCon.createConn();
        Statement currStat = currCon.getStat();
        return currStat;
    }


    public void execSQLQuery(String query) {

        try {
            this.result = getStat().executeQuery(query);

            getStat().close();
            this.conn.closeConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void execSQLUpdate(String query) {

        try {
            getStat().executeUpdate(query);

            getStat().close();
            this.conn.closeConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void select(String query) {

        try {
            getStat().executeQuery(query);
            getStat().close();
            this.conn.closeConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void createTables(ArrayList<String> commands) {
        try {

            for (String arr : commands
            ) {
                String commandeSQL = arr;
                execSQLQuery(commandeSQL);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        this.conn.closeConn();
        //conn.closeConn();
    }

    public void insertInto(ArrayList<String> commands) {
        try {
            for (String arr : commands
            ) {
                String commandeSQL = arr;
                execSQLUpdate(commandeSQL);


            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        this.conn.closeConn();
        //conn.closeConn();
    }


    public DBConnect getConn() {
        return conn;
    }

    public void setConn(DBConnect conn) {
        this.conn = conn;
    }
}
