package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;

public class CaixaFrame extends JFrame{

    private JPanel PanelCaixa;
    private JComboBox JComboBoxProd;
    private JTextField JTextFieldAmount;
    private JButton JButtonAdd;
    private JTable JTableOrder;
    private JRadioButton JRadioButtonDelivery;
    private JRadioButton JRadioButtonPresential;
    private JRadioButton JRadioButtonCredit;
    private JRadioButton JRadioButtonCash;
    private JButton JButtonFinish;
    private JTextField JTextFieldName;
    private JTextField JTextFieldAddress;
    private JTextField JTextFieldReference;
    private JPanel JPanelRight;
    private JPanel JPanelProd;
    private JPanel JPanelConf;
    private JPanel JPanelAdd;
    private JPanel JPanelProdWrite;
    private JPanel JPanelProdRead;
    private JLabel JLabelProd;
    private JLabel JLabelAmount;
    private JPanel JPanelFinish;
    private JPanel JPanelConfPay;
    private JPanel JPanelType;
    private javax.swing.JPanel JPanelTypeSell;
    private JPanel JPanelTypePay;
    private JLabel JLabelTypeSell;
    private JLabel JLabelTypePay;
    private JPanel JPanelClient;
    private JLabel JLabelClient;
    private JPanel JPanelClientRead;
    private JPanel JPanelClientWrite;
    private JLabel JLabelName;
    private JLabel JLabelAddress;
    private JLabel JLabelReference;
    private JTextField JTextFieldProd;
    private JPanel JPanelProdField;
    private JLabel JLabelFolderProd;
    private JPanel JPanelAmount;
    private javax.swing.JScrollPane JScrollPaneLeft;

    public CaixaFrame(){
        this.setContentPane(PanelCaixa);
        iconFolder("C:\\Users\\jhonatan\\Documents\\GitProjectPessoal\\Projetos\\Basico\\Pro_Market\\out\\artifacts\\Pro_Market_jar\\icon\\iconFolder.png");
        actions();

    }

    public void iconFolder(String path){
        ImageIcon imageIcon = new ImageIcon(path);
        JLabelFolderProd.setSize(16,16);
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(JLabelFolderProd.getWidth(), JLabelFolderProd.getHeight(), 1));
        JLabelFolderProd.setIcon(imageIcon);
        confJTable();
        //DefaultTableModel dtm = (DefaultTableModel) JTableOrder.getModel();
        //dtm.addRow(new String []{"10","Carne","200"});
    }

    public void confJTable(){
        JTableOrder.setModel(new DefaultTableModel(new Object [][] {},new String [] {"Qtd.", "Produto", "Valor"}));
    }

    public void actions(){
        JLabelFolderProd.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

            }
        });
    }



}
