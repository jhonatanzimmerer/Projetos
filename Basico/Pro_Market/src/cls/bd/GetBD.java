package cls.bd;

import cls.obj.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetBD {

    //Busca todos os dados dos produtos
    public List<Product> loadProd(){

        List<Product> list = new ArrayList<>();
        Connection con = ConnectionBD.GetConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try{
            stmt = con.prepareStatement("SELECT Cod, Produto, Quantidade, Valor FROM market.produto");
            rs = stmt.executeQuery();

            while(rs.next()){
                Product product = new Product();
                product.setCod(rs.getInt("Cod"));
                product.setName(rs.getString("Produto"));
                product.setAmount(rs.getInt("Quantidade"));
                product.setValue(rs.getDouble("Valor"));
                list.add(product);
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            ConnectionBD.closeConnection(con,stmt,rs);
        }
        return list;
    }

    //Busca o produto de acordo com  o codigo e retorna somente o nome
    public String loadProdName(int cod){
        Connection con = ConnectionBD.GetConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try{
            stmt = con.prepareStatement("SELECT Produto FROM market.produto where Cod = ?");
            stmt.setInt(1,cod);
            rs = stmt.executeQuery();

            //Evita estourarum catch por não encontrar um produto
            while(rs.next())
                return rs.getString("Produto");
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            ConnectionBD.closeConnection(con,stmt,rs);
        }
        return null;
    }
    //Busca o prorudto de acordo com o nome e o codigo
    public Product loadProd(int cod,String prod){
        Product product = new Product();
        Connection con = ConnectionBD.GetConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try{
            stmt = con.prepareStatement("SELECT cod, produto, quantidade, valor FROM market.produto where Cod = ? and produto = ?");
            stmt.setInt(1,cod);
            stmt.setString(2,prod);
            rs = stmt.executeQuery();

            //Evita estourarum catch por não encontrar um produto
            while(rs.next()) {
                product.setCod(rs.getInt("Cod"));
                product.setAmount(rs.getInt("Quantidade"));
                product.setName(rs.getString("Produto"));
                product.setValue(rs.getDouble("Valor"));
                return product;
            }

        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            ConnectionBD.closeConnection(con,stmt,rs);
        }
        return null;
    }

    //Busca o maior cod da tabela de venda
    public int loadMaxCod(){
        Connection con = ConnectionBD.GetConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try{
            stmt = con.prepareStatement("SELECT MAX(Cod) FROM market.venda");
            rs = stmt.executeQuery();

            //Evita estourarum catch por não encontrar um produto
            while(rs.next()) {
                int cod = rs.getInt("MAX(Cod)");
                return cod;
            }

        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            ConnectionBD.closeConnection(con,stmt,rs);
        }
        return 0;
    }
}
