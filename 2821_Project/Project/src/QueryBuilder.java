import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.function.*;

public class QueryBuilder {
    AlterDB adb = new AlterDB();
    QueryBuilder() throws IOException {
    }




    public String join(String tab1, String tab2) {
        String id1 = tab1 + "_ID";
        String id2 = tab2 + "_ID";
        String id1Full = tab1 + "." + id1;
        String id2Full = tab2 + "." + id2;

        String joinQuery = "SELECT " + tab1 + ".NOM, " + tab2 + ".NOM " + " FROM " + tab1 + "," + tab2 + " WHERE " + id1Full + "=" + id2Full;
        System.out.println(joinQuery);


        return joinQuery;
    }

    public String BenSearch;
    public String BenevoleInSecteur(String prefixPostal) {
     String getBen =  "select * FROM BENEVOLE where BENEVOLE_SECTEUR_ID like (select SECTEUR_ID from SECTEUR where PREFIX_POSTAL like '"+ prefixPostal.toUpperCase() +"')";
        BenSearch = getBen;

        return getBen;

    }



    public String FormTeam( ArrayList<String> tempoA ) throws IOException, SQLException {

        String rec1[] = BenSearch.split("]");

        ArrayList<String> temp2 =  tempoA;

        System.out.println( "Il a MENU " +temp2.get(1));
        String statVoiture = "SELECT * FROM BAV WHERE BSV_BENEVOLE_ID = '68'";

        System.out.println( "Il a MENU " +temp2.get(1));

        for (String str: temp2
             ) {
            String[] separatedStr = str.split(",");

        }

        return temp2.get(1);
    }




}
