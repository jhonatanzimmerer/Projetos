package view;

import cls.bd.MnpBD;
import cls.obj.Operacao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ImportData extends JFrame {
    private JPanel jPanelMain;
    private JTable JTableAdd;
    private JButton JButtonConfirm;
    private JButton JButtonRemove;
    private JTextField JTextFieldAtivo;
    private JTextField JTextFieldTaxa;
    private JTextField JTextFieldQuantidade;
    private JTextField JTextFieldPreco;
    private JTextField JTextFieldData;
    private JButton JButtonAdd;
    private JRadioButton JRadioButtonAcao;
    private JRadioButton JRadioButtonBuy;
    private JRadioButton JRadioButtonFII;
    private JRadioButton JRadioButtonSell;
    private JButton JButtonClear;
    private ButtonGroup groupButtonClass;
    private ButtonGroup groupButtonOperacao;
    public ImportData(){
        groupButtonClass = new ButtonGroup();
        groupButtonClass.add(JRadioButtonFII);
        groupButtonClass.add(JRadioButtonAcao);
        groupButtonOperacao = new ButtonGroup();
        groupButtonOperacao.add(JRadioButtonBuy);
        groupButtonOperacao.add(JRadioButtonSell);
        this.setContentPane(jPanelMain);
        this.setSize(900,800);
        this.setDefaultCloseOperation(ImportDataExcel.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        confTable();
        actions();

        JButtonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(statusEnableButtonAdd()){
                    Operacao operacao = new Operacao();
                    operacao.setData(dateEN(JTextFieldData.getText()));
                    operacao.setAtivo(JTextFieldAtivo.getText());
                    operacao.setPreco(Double.parseDouble(JTextFieldPreco.getText().replace(",",".")));
                    operacao.setQuantidade(Integer.valueOf(JTextFieldQuantidade.getText()));
                    operacao.setTaxa(Double.valueOf(JTextFieldTaxa.getText().replace(",",".")));
                    if(JRadioButtonAcao.isSelected())
                        operacao.setClasse("A");
                    else
                        operacao.setClasse("F");
                    if(JRadioButtonBuy.isSelected())
                        operacao.setOperacao("C");
                    else
                        operacao.setOperacao("V");
                    insertTabel(operacao);
                }
                else
                    JOptionPane.showMessageDialog(null,"Verifique se todos os campos estão preenchidos!");
            }
        });
        JButtonConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel defaultTableModel = (DefaultTableModel) JTableAdd.getModel();
                for(int x=0;x<defaultTableModel.getRowCount();x++){
                    Operacao operacao = new Operacao();
                    operacao.setAtivo((String) defaultTableModel.getValueAt(x,0));
                    operacao.setQuantidade((Integer) defaultTableModel.getValueAt(x,1));
                    operacao.setPreco((Double) defaultTableModel.getValueAt(x,2));
                    operacao.setData((String) defaultTableModel.getValueAt(x,3));
                    operacao.setClasse((String) defaultTableModel.getValueAt(x,4));
                    operacao.setOperacao((String) defaultTableModel.getValueAt(x,5));
                    operacao.setTaxa((Double) defaultTableModel.getValueAt(x,6));
                    new MnpBD().insertOperationMnp(operacao);
                }
                JOptionPane.showMessageDialog(null,"Operção finalizada!");
            }
        });

        JButtonClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTableAdd.setModel(new DefaultTableModel(new Object [][] {},new String [] {"Ativo", "Quantidade", "Preço", "Data", "Classe", "Operação","taxa",}));
            }
        });
    }
    public void clearData(){
        JTextFieldAtivo.setText("");
        JTextFieldQuantidade.setText("");
        JTextFieldQuantidade.setText("");
        JTextFieldPreco.setText("");
        JTextFieldTaxa.setText("");
        JTextFieldData.setText("");
        groupButtonClass.clearSelection();
        groupButtonOperacao.clearSelection();
    }

    public boolean statusEnableButtonAdd(){
        if(!JTextFieldAtivo.getText().isEmpty())
            if(!JTextFieldQuantidade.getText().isEmpty())
                if(!JTextFieldQuantidade.getText().isEmpty())
                    if(!JTextFieldPreco.getText().isEmpty())
                        if(!JTextFieldTaxa.getText().isEmpty())
                            if(!JTextFieldData.getText().isEmpty())
                                if(JRadioButtonAcao.isSelected() || JRadioButtonFII.isSelected())
                                    if(JRadioButtonBuy.isSelected() || JRadioButtonSell.isSelected())
                                        return true;
        return false;
    }

    public void insertTabel(Operacao operacao){
        DefaultTableModel defaultTableModel = (DefaultTableModel) JTableAdd.getModel();
        defaultTableModel.addRow(new Object [] {operacao.getAtivo(), operacao.getQuantidade(), operacao.getPreco(),
                                                operacao.getData(), operacao.getClasse(), operacao.getOperacao(),
                                                operacao.getTaxa()});
    }

    public String dateEN(String date){
        String [] dateSplit = date.split("/");
        return dateSplit[2]+"-"+dateSplit[1]+"-"+dateSplit[0];
    }

    public void confTable(){
        JTableAdd.setModel(new DefaultTableModel(new Object [][] {},new String [] {"Ativo", "Quantidade", "Preço", "Data", "Classe", "Operação","taxa",}));
        JTableAdd.getTableHeader().setReorderingAllowed (false);
    }

    public void actions(){

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
