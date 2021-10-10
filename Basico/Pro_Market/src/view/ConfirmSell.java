package view;

import cls.bd.MnpBD;
import cls.obj.Product;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class ConfirmSell extends JFrame {
    private JButton JButtonConfirm;
    private JButton JButtonCancel;
    private JPanel PanelMain;
    private JPanel JPanelFoot;
    private JPanel JPanelBody;
    private JLabel JLabelValueTotal;
    private JLabel JLabelTotal;
    private JLabel JLabelCashBack;
    private JLabel JLabelBack;
    private JLabel JLabelCash;
    private JFormattedTextField JFormattedTextFieldCash;
    private JButton JButtonCalc;
    private JRadioButton JRadioButtonCash;
    private JRadioButton JRadioButtonCredit;
    private JRadioButton JRadioButtonDebt;
    private JComboBox JComboBoxPayment;
    private ButtonGroup groupPayment;

    public ConfirmSell(List<Product> productList, Double total,double discount,String type,String pay,String adress, String ref){
        this.setContentPane(PanelMain);
        this.setSize(400,200);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        JLabelTotal.setText(total.toString());
        JButtonConfirm.setEnabled(false);
        JButtonCalc.setEnabled(false);
        JFormattedTextFieldCash.setEnabled(false);
        action();
        confRadio();
        //Inserir no banco de dados. Esta fora do padrão pra evitar ficar repetindo variavel
        JButtonConfirm.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(JButtonConfirm.isEnabled()){
                    String payType;
                    switch (JComboBoxPayment.getSelectedItem().toString()){
                        case "Dinheiro":
                            payType = "M";
                            break;
                        case "Debito":
                            payType = "D";
                            break;
                        case "Credito":
                            payType = "C";
                            break;
                        case "Dinheiro/Debito":
                            payType = "MD";
                            break;
                        case "Dinheiro/Credito":
                            payType = "MC";
                            break;
                        case "Debito/Credito":
                            payType = "DC";
                            break;
                        default:
                            payType = "";
                            JOptionPane.showMessageDialog(null, "Selecione uma Forma de pagamento");

                    }
                    if(payType!="") {
                        new MnpBD().executeSell(productList, total, discount, type, pay, payType, adress, ref);
                        dispose();
                    }
                }
            }
        });

    }

    public void action(){
        JButtonCalc.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(JButtonCalc.isEnabled())
                {
                    try{
                        calcularTroco();
                    }catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Troco invalido, favor verificar se o dinheito entregue foi digitado corretamente");
                    }
                }

            }
        });

        JButtonCancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                dispose();
            }
        });

        JComboBoxPayment.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                switch (JComboBoxPayment.getSelectedItem().toString()){
                    case "Dinheiro":
                        JButtonCalc.setEnabled(true);
                        JButtonConfirm.setEnabled(false);
                        JFormattedTextFieldCash.setEnabled(true);
                        JFormattedTextFieldCash.setText("");
                        break;
                    case "Debito":
                        JButtonCalc.setEnabled(false);
                        JButtonConfirm.setEnabled(false);
                        JFormattedTextFieldCash.setEnabled(false);
                        JFormattedTextFieldCash.setText("");
                        break;
                    case "Credito":
                        JButtonCalc.setEnabled(false);
                        JButtonConfirm.setEnabled(true);
                        JFormattedTextFieldCash.setEnabled(false);
                        JFormattedTextFieldCash.setText("");
                        break;
                    case "Dinheiro/Debito":
                        JButtonCalc.setEnabled(true);
                        JButtonConfirm.setEnabled(false);
                        JFormattedTextFieldCash.setEnabled(true);
                        JFormattedTextFieldCash.setText("");
                        break;
                    case "Dinheiro/Credito":
                        JButtonCalc.setEnabled(true);
                        JButtonConfirm.setEnabled(false);
                        JFormattedTextFieldCash.setEnabled(true);
                        JFormattedTextFieldCash.setText("");
                        break;
                    case "Debito/Credito":
                        JButtonCalc.setEnabled(false);
                        JButtonConfirm.setEnabled(true);
                        JFormattedTextFieldCash.setEnabled(false);
                        JFormattedTextFieldCash.setText("");
                        break;
                }

            }
        });

    }

    public void calcularTroco(){
        JLabelBack.setText(String.valueOf(Double.valueOf(JFormattedTextFieldCash.getText().trim()) - Double.valueOf(JLabelTotal.getText().trim())));
        JButtonConfirm.setEnabled(true);
    }

    public void confRadio(){
        groupPayment = new ButtonGroup();
        groupPayment.add(JRadioButtonCash);
        groupPayment.add(JRadioButtonCredit);
        groupPayment.add(JRadioButtonDebt);
    }


}
