package cls.bd;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RemoveBD {
    public void removeMomento(String ativo){
        Connection con = ConnectionBD.GetConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM invest.momento WHERE Ativo = ? LIMIT 1");
            stmt.setString(1, ativo);
            stmt.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally{
            ConnectionBD.closeConnection(con, stmt);
        }
    }
}
