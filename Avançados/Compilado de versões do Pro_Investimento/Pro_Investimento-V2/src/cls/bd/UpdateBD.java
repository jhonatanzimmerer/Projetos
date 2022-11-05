package cls.bd;

import cls.obj.Fluxo;
import cls.obj.Momento;
import cls.obj.Operacao;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateBD {
    //Atualiza um aivo da tabela momento
    public void updateMomento(Momento momento){

        Connection con = ConnectionBD.GetConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE invest.momento set Quantidade = ?, Preco_M = ?, Total = ? WHERE ativo = ? LIMIT 1");
            stmt.setInt(1,momento.getQuantidade());
            stmt.setDouble(2,momento.getPrecoM());
            stmt.setDouble(3, momento.getTotal());
            stmt.setString(4, momento.getAtivo());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally{
            ConnectionBD.closeConnection(con, stmt);
        }
    }

    //Atualiza o Aporte/Caixa da tabela momento
    public void updateFluxo(Fluxo fluxo){
        Connection con = ConnectionBD.GetConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE invest.fluxo set aporte = ? Caixa = ? WHERE `date` = ? LIMIT 1");
            stmt.setDouble(1, fluxo.getValor());
            stmt.setDouble(2, fluxo.getValorCaixa());
            stmt.setString(3, fluxo.getData());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally{
            ConnectionBD.closeConnection(con, stmt);
        }
    }
}
