package cls.bd;

import cls.obj.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SetBD {

    public void sellOrder(double total, double discount, String type, String pay){
        Connection con = ConnectionBD.GetConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try{
            stmt = con.prepareStatement("INSERT INTO market.venda (Total,Tipo_Venda,Pagamento, Desconto) VALUES(?,?,?,?)");
            stmt.setDouble(1,total);
            stmt.setString(2,type);
            stmt.setString(3,pay);
            stmt.setDouble(4,discount);
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
        ResultSet rs = null;

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

    public void sellOrderCredit(int cod, double total, String type, String pay ){
        Connection con = ConnectionBD.GetConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try{
            stmt = con.prepareStatement("INSERT INTO market.venda VALUES(?,?,?,?)");
            stmt.setInt(1,cod);
            stmt.setDouble(2,total);
            stmt.setString(3,type);
            stmt.setString(4,pay);
            stmt.executeUpdate();
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            ConnectionBD.closeConnection(con,stmt,rs);
        }
    }

}
