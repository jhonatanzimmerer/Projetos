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
            JOptionPane.showMessageDialog(null,"Produto não encontrado","Campo invalido",JOptionPane.ERROR_MESSAGE);
            return null;
        }
        else{
            return product;
        }

    }

    //Realiza uma venda
    public void executeSell(List<Product> productList, double total, double discount, String type, String pay){
        try{
            System.out.println("aqui1");
            new SetBD().sellOrder(total-discount, discount, type, pay);
            System.out.println("aqui2");
            for(Product prod:productList) {
                if (pay == "V")
                    executeSellCash(prod);
                else if (pay == "F")
                    executeSellCredit();
            }
        }catch (Exception ex){

        }

    }

    //Pagamento a vista
    public void executeSellCash(Product prod){
        try{
            new SetBD().sellOrderCash(prod, new GetBD().loadMaxCod());
        }catch (Exception ex){

        }

    }
    //Pagamento no fiado
    public void executeSellCredit(){

    }

}
