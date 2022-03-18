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
    public void executeSell(List<Product> productList, double total, double discount, String type, String pay, String payType, String adress, String ref, String payTotal, String name, String date){
        try{
            new SetBD().sellOrder(total-discount, discount, type, pay, payType, adress, ref,payTotal,convetDate(date));
            for(Product prod:productList) {
                if (pay == "V")
                    executeSellCash(prod);
                else if (pay == "C")
                    executeSellCredit(prod,name);
            }
            JOptionPane.showMessageDialog(null,"Venda Realizada!");
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
    public void executeSellCredit(Product prod, String name){
        try{
            new SetBD().sellOrderCredit(prod, new GetBD().loadMaxCod(), name);
        }catch (Exception ex){

        }
    }

    //
    public String convetDate(String date){
        String[] dates = date.split("/");
        date = dates[2]+"-"+dates[1]+"-"+dates[0];
        return date;
    }

}
