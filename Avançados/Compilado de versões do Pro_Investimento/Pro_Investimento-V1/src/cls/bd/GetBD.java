package cls.bd;

import cls.obj.*;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GetBD {
    // Retorna o ativo que é igual al que foi informado na String ativo
    public Momento loadAtivoMomento(String coluna, String ativo){
        Momento momento =  new Momento();
        Connection con = ConnectionBD.GetConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT * FROM invest.Momento WHERE "+ coluna +"= ?");
            stmt.setString(1, ativo);
            rs = stmt.executeQuery();

            while(rs.next()){
                momento.setAtivo(rs.getString("Ativo"));
                momento.setQuantidade(rs.getInt("Quantidade"));
                momento.setPrecoM(rs.getDouble("Preco_M"));
                momento.setTotal(rs.getDouble("Total"));
                momento.setCalsse(rs.getString("Classe"));
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally{
            ConnectionBD.closeConnection(con, stmt);
        }
        return momento;
    }
    // Retorna todos os dados do Mês informado
    public Double loadMonthPerformance(String startDate, String endDate, String clas){
        Connection con = ConnectionBD.GetConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Double performance = 0.0;
        try {
            stmt = con.prepareStatement("SELECT * FROM invest.Venda WHERE Data between ? and ? and Classe = ?");
            stmt.setString(1,startDate);
            stmt.setString(2,endDate);
            stmt.setString(3,clas);
            rs = stmt.executeQuery();

            while(rs.next()){
                performance = performance + (rs.getDouble("Preco_V")-rs.getDouble("Preco_M"));
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally{
            ConnectionBD.closeConnection(con, stmt);
        }
        return performance;
    }
    // Retorna os dados do fluxo
    public List<Fluxo> loadFluxo(){
        Connection con = ConnectionBD.GetConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Fluxo> fluxoList = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM invest.fluxo");
            rs = stmt.executeQuery();
            while(rs.next()){
                Fluxo fluxo = new Fluxo();
                fluxo.setData(rs.getString("Date"));
                fluxo.setValor(rs.getDouble("Aporte"));
                fluxo.setValorCaixa(rs.getDouble("Caixa"));
                fluxoList.add(fluxo);
            }


        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally{
            ConnectionBD.closeConnection(con, stmt);
        }
        return fluxoList;
    }
    // Retorna somente a ultima linha do fluxo
    public Fluxo loadLastRowFluxo(){
        Connection con = ConnectionBD.GetConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Fluxo fluxo = null;

        try {
            stmt = con.prepareStatement("select * from fluxo where `Date` = (select Max(date) from fluxo)");
            rs = stmt.executeQuery();

            while(rs.next()){
                if(fluxo != null)
                    JOptionPane.showMessageDialog(null, "Contem mais de uma linha com a mesma data ou o mesmo se não contem a data");
                fluxo.setData(rs.getString("Date"));
                fluxo.setValor(rs.getDouble("Aporte"));
                fluxo.setValorCaixa(rs.getDouble("Caixa"));
            }


        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally{
            ConnectionBD.closeConnection(con, stmt);
        }
        return fluxo;
    }
    // Retorna os dados de acordo com a data indicada na tabela de Fluxo
    public Fluxo LoadFluxoForDate(String date){
        Connection con = ConnectionBD.GetConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Fluxo fluxo = null;

        try {
            stmt = con.prepareStatement("select * from fluxo where `Date` = ?");
            stmt.setString(1, date);
            rs = stmt.executeQuery();

            while(rs.next()){
                if(fluxo != null)
                    JOptionPane.showMessageDialog(null, "Contem mais de uma linha com a mesma data ou o mesmo se não contem a data");
                fluxo.setData(rs.getString("Date"));
                fluxo.setValor(rs.getDouble("Aporte"));
                fluxo.setValorCaixa(rs.getDouble("Caixa"));
            }


        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally{
            ConnectionBD.closeConnection(con, stmt);
        }
        return fluxo;
    }
    //
    public List<OperacaoPerformance> loadOperacao(){
        List<OperacaoPerformance> list = new ArrayList<>();
        Connection con = ConnectionBD.GetConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT Date, Operacao, Quantidade, Preco, Taxa FROM invest.operacao_lista");
            rs = stmt.executeQuery();

            while(rs.next()){
                OperacaoPerformance operacaoPerformance = new OperacaoPerformance();
                operacaoPerformance.setDate(rs.getString("Date"));
                if(rs.getString("Operacao") == "C")
                    operacaoPerformance.setValue(rs.getDouble("Quantidade")*(rs.getDouble("Preco")+rs.getDouble("Taxa")));

                operacaoPerformance.setValue(
                  rs.getDouble("Operacao")
                );
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally{
            ConnectionBD.closeConnection(con, stmt);
        }

        return list;
    }
}
