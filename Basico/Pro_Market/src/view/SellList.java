package view;

import cls.bd.GetBD;
import cls.obj.Sell;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SellList extends JFrame {
    private JPanel PanelMain;
    private JTable JTableSell;
    private JComboBox JComboBoxSell;
    private JTextField JTextFieldDateStart;
    private JTextField JTextFieldDateEnd;
    private JButton JButtonLoad;
    private JButton sairButton;
    private JComboBox JComboBoxSelect;
    private JTextField JTextFieldSelect;
    private JButton JButtonSelect;

    public SellList() {
        this.setContentPane(PanelMain);
        this.setSize(1000,800);
        this.setLocationRelativeTo(null);
        actions();
        confJTable();



    }

    public void actions(){

        sairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        JButtonLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(JComboBoxSell.getSelectedIndex()==0)
                    tableSell();
                else if(JComboBoxSell.getSelectedIndex()==1)
                    tableSellCash();
                else if(JComboBoxSell.getSelectedIndex()==3)
                    tableSellCredit();
            }
        });

        //Faz um sele3ct basico na tabela
        JButtonSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = JComboBoxSelect.getSelectedIndex();
                System.out.println(index);
                for(int x=0;x<JTableSell.getRowCount();x++){
                    if(JTableSell.getValueAt(x,index).toString().toLowerCase().contains(JTextFieldSelect.getText().trim().toLowerCase())) {
                        JTableSell.setRowSelectionInterval(x, x);
                        break;
                    }
                }
            }
        });


    }

    public void confJTable(){
        JTableSell.setModel(new DefaultTableModel(new Object [][] {},new String [] {"Cod","Date","Total", "Tipo", "Pagamento", "Endereço","Referencia","Desconto","Pg_Total"}));
        JTableSell.getTableHeader().setReorderingAllowed(false);
    }

    public void tableSell(){
        JTableSell.setModel(new DefaultTableModel(new Object [][] {},new String [] {"Cod","Date","Total", "Tipo", "Pagamento", "Endereço","Referencia","Desconto","Pg_Total"}));
        DefaultTableModel dmt = (DefaultTableModel) JTableSell.getModel();
        dmt.setNumRows(0);
        for(Sell sell:new GetBD().loadSell()){
            dmt.addRow(new Object[]{
                    sell.getCod(),
                    convertDate(sell.getDate()),
                    sell.getTotal(),
                    sell.getType(),
                    sell.getPayment(),
                    sell.getAdress(),
                    sell.getReference(),
                    sell.getDiscount(),
                    sell.getPayTotal()
            });
        }
    }

    public void tableSellCash(){
        JTableSell.setModel(new DefaultTableModel(new Object [][] {},new String [] {"Cod","Produto","Quantidade","Preco_Unitario"}));
        DefaultTableModel dmt = (DefaultTableModel) JTableSell.getModel();
        dmt.setNumRows(0);
    }

    public void tableSellCredit(){

    }
    public String convertDate(String date){
        String [] dateSplit = date.split("-");
        return dateSplit[2] + "-" + dateSplit[1] + "-" + dateSplit[0];
    }
}
