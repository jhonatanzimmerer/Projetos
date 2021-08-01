package cls.bd;


import cls.obj.Product;

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

    //
    public List<Product> validateProd(int cod, String name){
        List<Product> list = new ArrayList<>();

        return list;
    }

}
