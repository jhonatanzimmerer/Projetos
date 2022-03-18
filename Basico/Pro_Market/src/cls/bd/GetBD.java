package cls.bd;

import cls.obj.Customer;
import cls.obj.Product;
import cls.obj.Sell;
import cls.obj.SellCash;

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

    public List<Customer> locaCustomer(){
        List<Customer> list = new ArrayList<>();
        Connection con = ConnectionBD.GetConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try{
            stmt = con.prepareStatement("SELECT * FROM market.cliente");
            rs = stmt.executeQuery();

            while(rs.next()){
                Customer customer = new Customer();
                customer.setCod(rs.getInt("Cod"));
                customer.setName(rs.getString("Nome"));
                customer.setCompany(rs.getString("Empresa"));
                customer.setAdress(rs.getString("Endereco"));
                customer.setReference(rs.getString("Referencia"));
                customer.setPhone(rs.getString("Telefone"));
                list.add(customer);
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            ConnectionBD.closeConnection(con,stmt,rs);
        }
        return list;
    }

    public List<Sell> loadSell(){
        List<Sell> list = new ArrayList<>();
        Connection con = ConnectionBD.GetConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try{
            stmt = con.prepareStatement("SELECT * FROM market.venda");
            rs = stmt.executeQuery();

            while(rs.next()){
                Sell sell = new Sell();

                sell.setCod(rs.getInt("Cod"));
                sell.setTotal(rs.getDouble("Total"));
                sell.setType(rs.getString("Tipo"));
                sell.setPayment(rs.getString("Pagamento"));
                sell.setAdress(rs.getString("Endereco"));
                sell.setReference(rs.getString("Referencia"));
                sell.setDiscount(rs.getDouble("Desconto"));
                sell.setPayTotal(rs.getString("Pg_Total"));
                sell.setDate(rs.getString("date"));

                list.add(sell);
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            ConnectionBD.closeConnection(con,stmt,rs);
        }
        return list;
    }

    public List<SellCash> loadSellCash(){
        List<SellCash> list = new ArrayList<>();
        Connection con = ConnectionBD.GetConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try{
            stmt = con.prepareStatement("SELECT * FROM market.venda_vista");
            rs = stmt.executeQuery();

            while(rs.next()){
                SellCash sellCash = new SellCash();

                sellCash.setCod(rs.getInt("Cod"));
                sellCash.setProduct(rs.getString(""));

                list.add(sell);
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            ConnectionBD.closeConnection(con,stmt,rs);
        }
        return list;
    }

    public List<SellCredit> loadSellCredit(){
        List<Sell> list = new ArrayList<>();
        Connection con = ConnectionBD.GetConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try{
            stmt = con.prepareStatement("SELECT * FROM market.venda");
            rs = stmt.executeQuery();

            while(rs.next()){
                Sell sell = new Sell();

                sell.setCod(rs.getInt("Cod"));
                sell.setTotal(rs.getDouble("Total"));
                sell.setType(rs.getString("Tipo"));
                sell.setPayment(rs.getString("Pagamento"));
                sell.setAdress(rs.getString("Endereco"));
                sell.setReference(rs.getString("Referencia"));
                sell.setDiscount(rs.getDouble("Desconto"));
                sell.setPayTotal(rs.getString("Pg_Total"));
                sell.setDate(rs.getString("date"));

                list.add(sell);
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            ConnectionBD.closeConnection(con,stmt,rs);
        }
        return list;
    }

}
