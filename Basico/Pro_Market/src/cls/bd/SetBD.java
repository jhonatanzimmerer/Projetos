package cls.bd;

import cls.obj.Customer;
import cls.obj.Product;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SetBD {

    public void sellOrder(double total, double discount, String type, String pay, String payType, String adress, String ref,String payTotal){
        Connection con = ConnectionBD.GetConnection();
        PreparedStatement stmt = null;

        try{
            stmt = con.prepareStatement("INSERT INTO market.venda (Total,Tipo,Pagamento,Endereco,Referencia,Desconto,Pg_Total) VALUES(?,?,?,?,?,?,?)");
            stmt.setDouble(1,total);
            stmt.setString(2,type);
            stmt.setString(3,payType);
            stmt.setString(4,adress);
            stmt.setString(5,ref);
            stmt.setDouble(6,discount);
            stmt.setString(7,payTotal);
            stmt.executeUpdate();
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            ConnectionBD.closeConnection(con,stmt);
        }
    }

    public void sellOrderCash(Product prod, int cod){
        Connection con = ConnectionBD.GetConnection();
        PreparedStatement stmt = null;

        try{
            stmt = con.prepareStatement("INSERT INTO market.venda_vista VALUES(?,?,?,?)");
            stmt.setInt(1,cod);
            stmt.setInt(2,prod.getCod());
            stmt.setInt(3,prod.getAmount());
            stmt.setDouble(4,prod.getValue());
            stmt.executeUpdate();
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            ConnectionBD.closeConnection(con,stmt);
        }
    }

    public void sellOrderCredit(Product prod, int cod, String name){
        Connection con = ConnectionBD.GetConnection();
        PreparedStatement stmt = null;

        try{
            stmt = con.prepareStatement("INSERT INTO market.venda_fiado VALUES(?,?,?,?,?)");
            stmt.setInt(1,cod);
            stmt.setInt(2,prod.getCod());
            stmt.setString(3,name);
            stmt.setInt(4,prod.getAmount());
            stmt.setDouble(5,prod.getValue());
            stmt.executeUpdate();
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            ConnectionBD.closeConnection(con,stmt);
        }
    }

    public void registerCustomer(Customer customer){
        Connection con = ConnectionBD.GetConnection();
        PreparedStatement stmt = null;

        try{
            stmt = con.prepareStatement("INSERT INTO market.cliente (Nome,Empresa,Endereco,Referencia,Telefone) VALUES(?,?,?,?,?)");
            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getCompany());
            stmt.setString(3, customer.getAdress());
            stmt.setString(4, customer.getReference());
            stmt.setString(5, customer.getPhone());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null,"Cliente cadastrado com sucesso!");
        }catch (SQLException throwables) {
            throwables.printStackTrace();
            JOptionPane.showMessageDialog(null,"Erro ao cadastrar o cliente!");
        }finally {
            ConnectionBD.closeConnection(con,stmt);
        }
    }

}
