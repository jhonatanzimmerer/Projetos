package cls;

import java.util.HashMap;
import java.util.Map;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ConnectionBD {
    private static final Map<String, String> path = new HashMap<>();

    public static Connection GetConnection(){
        try {
            Class.forName(path.get("DRIVER"));
            return DriverManager.getConnection(path.get("URL"), path.get("USER"), path.get("PASS"));
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro no getConnection " + ex);
            throw new RuntimeException("Erro na conexão",ex);

        }
    }

    public static void closeConnection(Connection con){

        try {
            if(con != null)
                con.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro no closeConnection1" + ex);
            Logger.getLogger(ConnectionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void closeConnection(Connection con, PreparedStatement stmt){
        closeConnection(con);

        try {
            if(stmt != null)
                stmt.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro no closeConnection2" + ex);
            Logger.getLogger(ConnectionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs){
        closeConnection(con, stmt);
        try {
            if(rs != null)
                rs.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro no closeConnection3" + ex);
            Logger.getLogger(ConnectionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void closeConnectionRS(ResultSet rs){
        try {
            if(rs != null)
                rs.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro no closeConnectionRS" + ex);
            Logger.getLogger(ConnectionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
