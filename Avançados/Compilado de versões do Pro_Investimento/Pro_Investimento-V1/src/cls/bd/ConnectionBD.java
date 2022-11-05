package cls.bd;

import javax.swing.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionBD {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/invest";
    private static final String USER = "root";
    private static final String PASS = "";

    public static Connection GetConnection(){
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);
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
}
