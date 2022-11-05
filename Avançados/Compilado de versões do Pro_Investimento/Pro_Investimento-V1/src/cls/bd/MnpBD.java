package cls.bd;

import cls.obj.Fluxo;
import cls.obj.Momento;
import cls.obj.Operacao;
import cls.obj.Venda;

import javax.swing.*;
import java.util.Calendar;
import java.util.Date;

public class MnpBD {

    //Iniciar o um seter na tabela operacao_lista
    public void insertOperationMnp(Operacao op) {
        try {
            SetBD setBD = new SetBD();
            GetBD getBD = new GetBD();
            Momento momentoLoad = getBD.loadAtivoMomento("ativo", op.getAtivo());

            //Identifica se é uma compra ou venda e se tem o ativo no momento
            if (momentoLoad.getAtivo() == null) {
                Momento momento = new Momento();
                momento.setTotal(op.getPreco() * op.getQuantidade());
                momento.setQuantidade(op.getQuantidade());
                momento.setAtivo(op.getAtivo());
                momento.setPrecoM(momento.getTotal() / momento.getQuantidade());
                momento.setCalsse(op.getClasse());
                new SetBD().insertMomento(momento);
                ManipulationFluxo(op);
            } else if (op.getOperacao().equals("C")) {
                momentoLoad.setQuantidade(momentoLoad.getQuantidade() + op.getQuantidade());
                momentoLoad.setTotal(momentoLoad.getTotal() + (op.getQuantidade() * op.getPreco()));
                momentoLoad.setPrecoM(momentoLoad.getTotal() / momentoLoad.getQuantidade());
                new UpdateBD().updateMomento(momentoLoad);
                ManipulationFluxo(op);
            } else if (op.getOperacao().equals("V")) {
                Venda venda = new Venda();
                venda.setPrecoM(momentoLoad.getPrecoM());
                if (momentoLoad.getQuantidade() == op.getQuantidade()) {
                    new RemoveBD().removeMomento(op.getAtivo());

                    ManipulationFluxo(op);

                    venda.setData(op.getData());
                    venda.setAtivo(op.getAtivo());
                    venda.setQuantidade(op.getQuantidade());
                    venda.setPrecoV(op.getPreco());
                    venda.setClasse(op.getClasse());
                    venda.setTaxa(op.getTaxa());
                    setBD.insertVenda(venda);
                } else if (momentoLoad.getQuantidade() > op.getQuantidade()) {
                    momentoLoad.setQuantidade(momentoLoad.getQuantidade() - op.getQuantidade());
                    momentoLoad.setTotal(momentoLoad.getTotal() - (momentoLoad.getPrecoM() * op.getQuantidade()));
                    momentoLoad.setPrecoM(momentoLoad.getTotal() / momentoLoad.getQuantidade());
                    new UpdateBD().updateMomento(momentoLoad);

                    ManipulationFluxo(op);

                    venda.setData(op.getData());
                    venda.setAtivo(op.getAtivo());
                    venda.setQuantidade(op.getQuantidade());
                    venda.setPrecoV(op.getPreco());
                    venda.setClasse(op.getClasse());
                    venda.setTaxa(op.getTaxa());
                    setBD.insertVenda(venda);
                } else if (momentoLoad.getQuantidade() < op.getQuantidade()) {
                    JOptionPane.showMessageDialog(null, "Quantidade insuficiente para ser vendida");
                } else
                    JOptionPane.showMessageDialog(null, "Erro ao tentar inserir dados de quantidade");


            }
            setBD.insertOperation(op);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar inserir dados.\nTipo do Erro: " + ex);
        }
    }
    //Manipulation fluxo
    public void ManipulationFluxo(Operacao op) {
        SetBD setBD = new SetBD();
        Fluxo fluxo = new GetBD().loadLastRowFluxo();
        Fluxo fluxo2 = new Fluxo();
        double custoOp = (op.getQuantidade() * op.getPreco()) + op.getTaxa();
        if(fluxo == null){
            fluxo2.setValorCaixa(0.00);
            fluxo2.setData(String.valueOf(op.getData()));     // String.valueOf(Calendar.getInstance())
            fluxo2.setValor(custoOp);
        }else{
            if (fluxo.getValorCaixa() < custoOp) {
                fluxo2.setData(String.valueOf(op.getData()));        // Calendar.getInstance())
                fluxo2.setValor(fluxo.getValorCaixa() - custoOp);
                fluxo2.setValorCaixa(0.0);
                setBD.insertFluxo(fluxo2);
            } else {
                fluxo2.setData(String.valueOf(op.getData()));     //Calendar.getInstance())
                fluxo2.setValor(0.0);
                fluxo2.setValorCaixa(fluxo.getValorCaixa()-custoOp);
                setBD.insertFluxo(fluxo2);
            }
        }

    }
    //Carrega o desempenho do mês indicado
    public double loadMonthPerformance(Integer month, String year, String clas){
        month++;
        String startDate;
        if(month>=10)
            startDate = year+"-"+month+"-"+ "01";
        else
            startDate = year+"-"+"0"+month+"-"+ "01";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(startDate.replaceAll("-","/")));
        String endDate = calendar.get(Calendar.YEAR)+"-"
                        +(Integer.valueOf(calendar.get(Calendar.MONTH))+1)+"-"
                        +calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        return new GetBD().loadMonthPerformance(startDate, endDate, clas);
    }
    //Condição para identificar se é um aporte
    /*public void validateFluxo(String date, Double valorOperacao){ //ValorOperação tem que ser inserido com o custo sendo que negativo estou comprando e positvo estou vendendo
        Fluxo fluxoDate = new GetBD().LoadFluxoForDate(date);
        Fluxo fluxoLast = new GetBD().loadLastRowFluxo();

        if(fluxoLast == null)
            new SetBD().insertFluxo(fluxoDate);
        else{
            if(fluxoDate == null){
                fluxoDate = new Fluxo();
                fluxoDate.setData(date);
                if(valorOperacao<0) {
                    fluxoDate.setCaixa(0.0);
                    fluxoDate.setAporte(fluxoLast.getAporte()+(fluxoDate.getCaixa()*(-1)));
                }
                else{

                }
            }


        }


        if(fluxoDate == null)
            new SetBD().insertFluxo(); //Tem que colocar os valores que vão ser inseridos
        else
            new UpdateBD().updateFluxo(fluxoDate);
    }*/



}
