package cls.bd;

import cls.obj.Fluxo;
import cls.obj.Momento;
import cls.obj.Operacao;
import cls.obj.Venda;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class SetBD {
    //Insere uma ordem na tabela Operacao_List
    public void insertOperation(Operacao op){

        Connection con = ConnectionBD.GetConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("insert into invest.operacao_lista values(?,?,?,?,?,?,?)");
            stmt.setString(1, op.getData());
            stmt.setString(2, op.getAtivo());
            stmt.setString(3, op.getOperacao());
            stmt.setInt(4, op.getQuantidade());
            stmt.setDouble(5, op.getPreco());
            stmt.setString(6, op.getClasse());
            stmt.setDouble(7, op.getTaxa());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally{
            ConnectionBD.closeConnection(con, stmt);
        }
    }

    //Insere uma ordem na tabela Momento
    public void insertMomento(Momento mn){
        Connection con = ConnectionBD.GetConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("insert into invest.momento values(?,?,?,?,?)");
            stmt.setString(1, mn.getAtivo());
            stmt.setInt(2, mn.getQuantidade());
            stmt.setDouble(3, mn.getPrecoM());
            stmt.setDouble(4, mn.getTotal());
            stmt.setString(5, mn.getCalsse());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally{
            ConnectionBD.closeConnection(con, stmt);
        }
    }

    //Insere uma ordem na tabela Venda
    public void insertVenda(Venda ve){

        Connection con = ConnectionBD.GetConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("insert into invest.venda values(?,?,?,?,?,?,?)");
            stmt.setString(1, ve.getData());
            stmt.setString(2, ve.getAtivo());
            stmt.setInt(3, ve.getQuantidade());
            stmt.setDouble(4, ve.getPrecoV());
            stmt.setDouble(5, ve.getPrecoM());
            stmt.setString(6, ve.getClasse());
            stmt.setDouble(7, ve.getTaxa());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally{
            ConnectionBD.closeConnection(con, stmt);
        }

    }

    //Insere uma linha na tabela Fluxo
    public void insertFluxo(Fluxo fluxo){
        Connection con = ConnectionBD.GetConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("insert into invest.fluxo values(?, ?, ?)");
            stmt.setString(1, fluxo.getData());
            stmt.setDouble(2, fluxo.getValor());
            stmt.setDouble(3, fluxo.getValorCaixa());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally{
            ConnectionBD.closeConnection(con, stmt);
        }
    }
}
