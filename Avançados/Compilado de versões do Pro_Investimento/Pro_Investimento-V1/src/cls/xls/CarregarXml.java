package cls.xls;

import cls.obj.Operacao;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CarregarXml {

    List<Operacao> list = new ArrayList<>();

    //Carregar todos os dados do xls
    public List<Operacao> load(String path){

        Workbook wk;

        try{
            wk = Workbook.getWorkbook(new File(path.trim()));
            Sheet st = wk.getSheet(0);

            int linhas = st.getRows();
            for(int i=1;i<linhas;i++){

                Cell cat = st.getCell(0,i);
                Cell cqt = st.getCell(1,i);
                Cell cpc = st.getCell(2,i);
                Cell cdt = st.getCell(3,i);
                Cell ctx = st.getCell(4,i);
                Cell cop = st.getCell(5,i);
                Cell ccl = st.getCell(6,i);

                Operacao op = new Operacao();

                op.setAtivo(String.valueOf(cat.getContents()));
                op.setQuantidade(Integer.valueOf(cqt.getContents()));
                op.setPreco(Float.valueOf(cpc.getContents().replace(",", ".")));
                op.setData(ConvertDate(String.valueOf(cdt.getContents())));
                op.setTaxa(Float.valueOf(ctx.getContents().replace(",", ".")));
                op.setOperacao(String.valueOf(cop.getContents()));
                op.setClasse(String.valueOf(ccl.getContents()));

                list.add(op);
            }
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null,"Erro ao carregar os dados " + ex);
        }

        return list;
    }

    public String ConvertDate(String data){
        String [] novo = data.split("/");
        data = novo[2] + "-" + novo[1] + "-" + novo[0];
        return data;
    }

}
