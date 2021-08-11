package cls.bd;


import cls.obj.Product;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class MnpBD {

    //Retorna o produto de acordo com o codigo indicado
    public String searchProdCod(int cod){

        String prod = new GetBD().loadProdName(cod);
        if (prod == null)
            return "";
        else
            return prod;
    }

    //Valida se existe o produto
    public Product validateProd(int cod, String prod){
        Product product = new GetBD().loadProd(cod, prod);

        if(product==null){
            JOptionPane.showMessageDialog(null,"Quantidade não é numero","Campo invalido",JOptionPane.ERROR_MESSAGE);
            return null;
        }
        else{
            return product;
        }

    }

}
