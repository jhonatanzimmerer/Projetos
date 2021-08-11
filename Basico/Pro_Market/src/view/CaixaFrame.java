package view;

import cls.bd.MnpBD;
import cls.obj.Product;
import com.sun.tools.javac.Main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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
    private JTextField JTextFielCod;
    private JFormattedTextField formattedTextField1;


    public CaixaFrame(){

        this.setContentPane(PanelCaixa);
        iconFolder("C:\\Users\\jhonatan\\Documents\\GitProjectPessoal\\Projetos\\Basico\\Pro_Market\\out\\artifacts\\Pro_Market_jar\\icon\\iconFolder.png");

        actions();
        confJTable();



    }

    public void iconFolder(String path){
        ImageIcon imageIcon = new ImageIcon(path);
        JLabelFolderProd.setSize(16,16);
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(JLabelFolderProd.getWidth(), JLabelFolderProd.getHeight(), 1));
        JLabelFolderProd.setIcon(imageIcon);
    }

    public void confJTable(){
        JTableOrder.setModel(new DefaultTableModel(new Object [][] {},new String [] {"Produto", "Qtd.", "Valor"}));
    }

    public void actions(){

        //Action para o icone Folder iniciar a interface SeçectProdFrame
        JLabelFolderProd.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(JLabelFolderProd.isEnabled()){
                    SelectProdFrame selectProdFrame = new SelectProdFrame(JTextFieldProd, JTextFielCod,JLabelFolderProd);
                    selectProdFrame.setLocationRelativeTo(null);
                    selectProdFrame.setVisible(true);
                    JLabelFolderProd.setEnabled(false);
                }
            }
        });

        //Action para o campo Buscar Codigo
        JTextFielCod.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                //Seta um valor no JTextFieldProd caso encontre o codigo ou o deixa vazio
                if(JTextFielCod.getText().replace(" ","")!="")
                    JTextFieldProd.setText(new MnpBD().searchProdCod(Integer.valueOf(JTextFielCod.getText().replace(" ",""))));
            }
        });

        //Action para o botao add
        JButtonAdd.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                //Verifica se os campos não estão em branco e retornar um erro cdaso esteja
                if(JTextFieldProd.getText().isBlank() || JTextFielCod.getText().isBlank() || JTextFieldAmount.getText().isBlank())
                    JOptionPane.showMessageDialog(PanelCaixa,"Prenecha todos os campos","Campo invalido",JOptionPane.INFORMATION_MESSAGE);
                else {
                    //Tenta tranforma/capturar os valores de nome, cod e quantidade e adiconar na tabela de pedido
                    try{
                        String prod = String.valueOf(JTextFieldProd.getText());
                        int cod = Integer.valueOf(JTextFielCod.getText().replace(" ", ""));
                        int mount = Integer.valueOf(JTextFieldAmount.getText().replace(" ",""));

                        Product product = new MnpBD().validateProd(cod,prod);

                        DefaultTableModel dmt = (DefaultTableModel) JTableOrder.getModel();
                        dmt.addRow(new String[] {product.getName(),String.valueOf(mount),String.valueOf(product.getValue())});


                    }catch (NumberFormatException ex){
                        JOptionPane.showMessageDialog(PanelCaixa,"Quantidade não é numero","Campo invalido",JOptionPane.INFORMATION_MESSAGE);
                    }catch (Exception exe){
                        JOptionPane.showMessageDialog(PanelCaixa,"Cmapo com erro","Campo invalido",JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
    }

}
