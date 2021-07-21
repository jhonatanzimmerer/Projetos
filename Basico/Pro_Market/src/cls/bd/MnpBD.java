package cls.bd;

import cls.obj.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MnpBD {

    public List<Product> loadProd(){

        List<Product> list = new ArrayList<>();
        Connection con = ConnectionBD.GetConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try{
            stmt = con.prepareStatement("SELECT * FROM market.produto");
            rs = stmt.executeQuery();

            while(rs.next()){
                Product product = new Product();
                product.setName(rs.getString("Produto"));
                product.setAmount(rs.getInt("Quantidade"));
                product.setValue(rs.getDouble("Valor"));
                list.add(product);
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

}
