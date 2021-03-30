/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author jhonatan
 */
public class Mnp {

    public void InsertO(String ativo, int quantidade, float preco, String data, float taxa, String op, String tipo){
        
        Connection con = ConnectionBD.GetConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("insert into Investimento.Operacao values(?,?,?,?,?,?,?)");
            stmt.setString(1, ativo);
            stmt.setInt(2, quantidade);
            stmt.setFloat(3, preco);
            stmt.setString(4, data);
            stmt.setFloat(5, taxa);
            stmt.setString(6, op);
            stmt.setString(7, tipo);
            stmt.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally{
            ConnectionBD.closeConnection(con, stmt);
        }
    }
    
    public void InsertM(String ativo, int quantidade, float preco, float taxa, boolean validar){
        
        Connection con = ConnectionBD.GetConnection();
        PreparedStatement stmt = null;
        
        if(validar == true){
            try {
                stmt = con.prepareStatement("update Investimento.Momento set quantidade = quantidade + ? where ativo = ?");
                stmt.setInt(1, quantidade);
                stmt.setString(2, ativo);
                stmt.executeUpdate();
                
                stmt = con.prepareStatement("update Investimento.Momento set total = total + ? where ativo = ?");
                stmt.setFloat(1, (quantidade*preco)+taxa);
                stmt.setString(2, ativo);
                stmt.executeUpdate();
                
                stmt = con.prepareStatement("update Investimento.Momento set preco_medio = total/quantidade");
                stmt.executeUpdate();
                

            } catch (SQLException ex) {
                Logger.getLogger(Mnp.class.getName()).log(Level.SEVERE, null, ex);
            } finally{
            ConnectionBD.closeConnection(con, stmt);
            }
        }
        else if(validar == false){
            try {
                stmt = con.prepareStatement("insert into Investimento.Momento values(?,?,?,?)");
                stmt.setString(1, ativo);
                stmt.setFloat(2, quantidade);
                stmt.setFloat(3, preco);
                stmt.setFloat(4, (quantidade*preco)+taxa);
                stmt.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(Mnp.class.getName()).log(Level.SEVERE, null, ex);
            } finally{
            ConnectionBD.closeConnection(con, stmt);
            }
        }
    }
    
    public void InserV(String ativo, int quantidade, float preco, float precom, String data, String tipo, boolean validar){
        Connection con = ConnectionBD.GetConnection();
        PreparedStatement stmt = null;
        float resultado = (quantidade*preco)-(quantidade*precom);
        
        try {          
            if(validar == true){
                stmt = con.prepareStatement("update Investimento.Momento set quantidade = quantidade - ? where ativo = ?");
                stmt.setInt(1, quantidade);
                stmt.setString(2, ativo);
                stmt.executeUpdate();
                
                stmt = con.prepareStatement("update Investimento.Momento set total = quantidade*preco_medio where ativo = ?");
                stmt.setString(1, ativo);
                stmt.executeUpdate();
                
                stmt = con.prepareStatement("UPDATE Investimento.Momento set preco_medio = total/quantidade WHERE ativo = ?");
                stmt.setString(1, ativo);
                stmt.executeUpdate();
            }
            else if(validar == false){
                stmt = con.prepareStatement("DELETE FROM Investimento.Momento WHERE ativo = ?");
                stmt.setString(1, ativo);
                stmt.executeUpdate();
            }

            stmt = con.prepareStatement("insert into Investimento.Venda values(?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, ativo);
            stmt.setInt(2, quantidade);
            stmt.setFloat(3, preco);
            stmt.setFloat(4, precom);
            stmt.setString(5, data);
            stmt.setFloat(6, resultado);
            stmt.setString(7, tipo);
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro aqui: " + ex);
        } finally{
            ConnectionBD.closeConnection(con, stmt);
        }
    }
    
    public List<Momento> BuscarM(){
        Connection con = ConnectionBD.GetConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Momento> list = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM Investimento.Momento");
            rs = stmt.executeQuery();
            while(rs.next()){
                Momento op = new Momento();
                op.setAtivo(rs.getString("ativo"));
                op.setQuantidade(Integer.valueOf(rs.getString("quantidade")));
                op.setPreco_medio(Float.valueOf(rs.getString("preco_medio")));
                list.add(op);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Mnp.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionBD.closeConnection(con, stmt, rs);
        }

        return list;
    }
    
    public List<Operacao> BuscarO(){
        Connection con = ConnectionBD.GetConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Operacao> list = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM Investimento.Momento");
            rs = stmt.executeQuery();
            while(rs.next()){
                Operacao op = new Operacao();
                op.setAtivo(rs.getString("ativo"));
                op.setData(rs.getString("data"));
                op.setOp(rs.getString("op"));
                op.setPreco(Float.valueOf(rs.getString("preco")));
                op.setQuantidade(Integer.valueOf(rs.getString("quantidade")));
                op.setTaxa(Float.valueOf(rs.getString("taxa")));
                op.setTipo(rs.getString("tipo"));
                list.add(op);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Mnp.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionBD.closeConnection(con, stmt, rs);
        }

        return list;
    }
    
    public List<Venda> BuscarV(){
        List<Venda> lista = new ArrayList<>();
        Connection con = ConnectionBD.GetConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement("Select * from Investimento.Venda");
            rs = stmt.executeQuery();
            while(rs.next()){
                Venda op = new Venda();
                op.setAtivo(rs.getString("ativo"));
                op.setQuantidade(Integer.valueOf(rs.getString("quantidade")));
                op.setPreco(Float.valueOf(rs.getString("preco")));
                op.setPrecom(Float.valueOf(rs.getString("precom")));
                op.setData(rs.getString("data"));
                op.setResultado(Float.valueOf(rs.getString("resultado")));
                op.setTipo(rs.getString("tipo"));
                lista.add(op);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao ler " + ex);
        } finally{
            ConnectionBD.closeConnection(con, stmt, rs);
        }
        
        return lista;
    }

    public List<Venda> BuscarVData(String inicio, String fim){
        List<Venda> lista = new ArrayList<>();
        Connection con = ConnectionBD.GetConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            if(inicio == "" && fim == ""){
                stmt = con.prepareStatement("Select * from Investimento.Venda");
                rs = stmt.executeQuery();
            }
            else{
                stmt = con.prepareStatement("Select * from Investimento.Venda where data > ? and data < ?");
                stmt.setString(1, inicio);
                stmt.setString(2, fim);
                rs = stmt.executeQuery();
            }
            
            
            while(rs.next()){
                Venda op = new Venda();
                op.setAtivo(rs.getString("ativo"));
                op.setQuantidade(Integer.valueOf(rs.getString("quantidade")));
                op.setPreco(Float.valueOf(rs.getString("preco")));
                op.setData(rs.getString("data"));
                op.setPrecom(rs.getFloat("precom"));
                op.setResultado(Float.valueOf(rs.getString("resultado")));
                op.setTipo(rs.getString("tipo"));
                
                
                
                lista.add(op);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao ler " + ex);
        } finally{
            ConnectionBD.closeConnection(con, stmt, rs);
        }
        
        return lista;
    }

    public OperacaoM BuscarOM(String inicio,String fim){
        OperacaoM obj_operacaom = new OperacaoM();
        Connection con = ConnectionBD.GetConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        ResultSet rs3 = null;


        try {
        
            stmt = con.prepareStatement("select quantidade, preco, taxa from Investimento.Operacao where data between ? and ? and op = ?");
            stmt.setString(1, inicio);
            stmt.setString(2, fim);
            stmt.setString(3, "C");
            rs = stmt.executeQuery();
            
            stmt = con.prepareStatement("select quantidade, preco, resultado, tipo from investimento.venda");
            rs2 = stmt.executeQuery();
            
            while(rs.next()){
                obj_operacaom.setCompra((rs.getFloat("quantidade")*rs.getFloat("preco")+rs.getFloat("taxa")+obj_operacaom.getCompra()));
            }
            while(rs2.next()){
                obj_operacaom.setCompra((rs.getFloat("quantidade")*rs.getFloat("preco")+rs.getFloat("taxa")+obj_operacaom.getCompra()));
            }
                
        } catch (SQLException ex) {
            Logger.getLogger(Mnp.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            ConnectionBD.closeConnection(con, stmt, rs);
            ConnectionBD.closeConnectionRS(rs2);
            ConnectionBD.closeConnectionRS(rs3);
            
        }


        return obj_operacaom;
    }

}


