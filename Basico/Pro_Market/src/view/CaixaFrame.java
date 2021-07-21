package view;

import javax.swing.*;
import java.awt.*;

public class CaixaFrame extends JFrame{

    private JPanel PanelCaixa;
    private JComboBox JComboBoxProd;
    private JTextField JTextFieldAmount;
    private JButton JButtonAdd;
    private JPanel JPanelLeft;
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

    public CaixaFrame(Dimension dimension){
        this.setContentPane(PanelCaixa);
        this.JComboBoxProd.addItem("Void");
        this.JComboBoxProd.addItem("Void2");
    }


}
