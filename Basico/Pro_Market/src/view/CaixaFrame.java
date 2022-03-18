package view;

import cls.bd.MnpBD;
import cls.obj.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private JPanel JPanelConfPaySpace;
    private JPanel JPanelInfoCustomer;
    private JFormattedTextField JFormattedTextFieldDiscount;
    private JLabel JLabelIconSearchConsumer;
    private JCheckBox JCheckBoxDate;
    private JTextField JTextFieldDate;
    private ButtonGroup groupSell;
    private ButtonGroup groupPay;

    public CaixaFrame(){

        this.setContentPane(PanelCaixa);
        iconFolder("C:\\Users\\jhonatan\\Documents\\GitProjectPessoal\\Projetos\\Basico\\Pro_Market\\out\\artifacts\\Pro_Market_jar\\icon\\iconFolder.png");
        iconSearch("C:\\Users\\jhonatan\\Documents\\GitProjectPessoal\\Projetos\\Basico\\Pro_Market\\out\\artifacts\\Pro_Market_jar\\icon\\iconSearch.png");
        actions();
        confJTable();
        confJRadio();
        start();



    }

    public void start(){
        JTextFieldAddress.setEnabled(false);
        JTextFieldName.setEnabled(false);
        JTextFieldReference.setEnabled(false);
        JLabelIconSearchConsumer.setEnabled(false);
        JTextFieldDate.setText(dateNow());
    }

    public void iconFolder(String path){
        ImageIcon imageIcon = new ImageIcon(path);
        JLabelFolderProd.setSize(16,16);
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(JLabelFolderProd.getWidth(), JLabelFolderProd.getHeight(), 1));
        JLabelFolderProd.setIcon(imageIcon);
    }

    public void iconSearch(String path){
        ImageIcon imageIcon = new ImageIcon(path);
        JLabelIconSearchConsumer.setSize(16,16);
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(JLabelIconSearchConsumer.getWidth(), JLabelIconSearchConsumer.getHeight(), 1));
        JLabelIconSearchConsumer.setIcon(imageIcon);
    }

    public void confJTable(){
        JTableOrder.setModel(new DefaultTableModel(new Object [][] {},new String [] {"Cod","Produto", "Qtd.", "V. Unitario", "V. Total"}));
        JTableOrder.getTableHeader().setReorderingAllowed(false);
    }

    public void confJRadio(){
        groupSell = new ButtonGroup();
        groupSell.add(JRadioButtonDelivery);
        groupSell.add(JRadioButtonPresential);

        groupPay = new ButtonGroup();
        groupPay.add(JRadioButtonCash);
        groupPay.add(JRadioButtonCredit);
    }

    public void actions(){

        JCheckBoxDate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!JTextFieldDate.isEnabled())
                    JTextFieldDate.setEnabled(true);
                else
                    JTextFieldDate.setEnabled(false);
            }
        });

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
                        dmt.addRow(new String[] {String.valueOf(cod),product.getName(),String.valueOf(mount),String.valueOf(product.getValue()),String.valueOf(product.getValue()*mount)});


                    }catch (NumberFormatException ex){
                        JOptionPane.showMessageDialog(PanelCaixa,"Quantidade não é numero","Campo invalido",JOptionPane.INFORMATION_MESSAGE);
                    }catch (Exception exe){
                        JOptionPane.showMessageDialog(PanelCaixa,"Campo com erro","Campo invalido",JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });

        JRadioButtonDelivery.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextFieldAddress.setEnabled(true);
                JTextFieldName.setEnabled(true);
                JLabelIconSearchConsumer.setEnabled(true);
                JTextFieldReference.setEnabled(true);

                clearCustomer();
            }
        });

        JRadioButtonPresential.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(JRadioButtonCredit.isSelected()){
                    JTextFieldAddress.setEnabled(false);
                    JTextFieldName.setEnabled(true);
                    JLabelIconSearchConsumer.setEnabled(true);
                    JTextFieldReference.setEnabled(false);
                }
                else{
                    JTextFieldAddress.setEnabled(false);
                    JTextFieldName.setEnabled(false);
                    JLabelIconSearchConsumer.setEnabled(false);
                    JTextFieldReference.setEnabled(false);
                }
                clearCustomer();
            }
        });

        JRadioButtonCash.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(JRadioButtonDelivery.isSelected()){
                    JTextFieldAddress.setEnabled(true);
                    JTextFieldName.setEnabled(true);
                    JLabelIconSearchConsumer.setEnabled(true);
                    JTextFieldReference.setEnabled(true);
                }
                else{
                    JTextFieldAddress.setEnabled(false);
                    JTextFieldName.setEnabled(false);
                    JLabelIconSearchConsumer.setEnabled(false);
                    JTextFieldReference.setEnabled(false);
                }
                clearCustomer();
            }
        });

        JRadioButtonCredit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(JRadioButtonDelivery.isSelected()){
                    JTextFieldAddress.setEnabled(true);
                    JTextFieldName.setEnabled(true);
                    JLabelIconSearchConsumer.setEnabled(true);
                    JTextFieldReference.setEnabled(true);
                }
                else{
                    JTextFieldAddress.setEnabled(false);
                    JTextFieldName.setEnabled(true);
                    JLabelIconSearchConsumer.setEnabled(true);
                    JTextFieldReference.setEnabled(false);
                }
                clearCustomer();
            }
        });

        JLabelIconSearchConsumer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(JLabelIconSearchConsumer.isEnabled()) {
                    if(JRadioButtonDelivery.isSelected()){
                        SelectCustomer selectCustomer = new SelectCustomer(JTextFieldName,JTextFieldAddress,JTextFieldReference,JLabelIconSearchConsumer);
                        selectCustomer.setLocationRelativeTo(null);
                        selectCustomer.setVisible(true);
                    }
                    else{
                        SelectCustomer selectCustomer = new SelectCustomer(JTextFieldName,JLabelIconSearchConsumer);
                        selectCustomer.setLocationRelativeTo(null);
                        selectCustomer.setVisible(true);
                    }
                    JLabelIconSearchConsumer.setEnabled(false);
                }


            }
        });

        JButtonFinish.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try {
                    List<Product> productList = new ArrayList<>();
                    double total = 0;
                    double discount = Double.valueOf(JFormattedTextFieldDiscount.getText().replace(" ",""));
                    String type="";
                    String pay="";
                    String adress = JTextFieldAddress.getText().trim();
                    String ref = JTextFieldReference.getText().trim();
                    String name = JTextFieldName.getText().trim();
                    String date = JTextFieldDate.getText().trim();
                    if (JRadioButtonDelivery.isSelected())
                        type = "D";
                    else if (JRadioButtonPresential.isSelected())
                        type = "P";
                    if (JRadioButtonCash.isSelected())
                        pay = "V";
                    else if (JRadioButtonCredit.isSelected())
                        pay = "C";

                    for (int x = 0; x < JTableOrder.getRowCount(); x++) {
                        Product prod = new Product();
                        prod.setCod(Integer.valueOf(JTableOrder.getValueAt(x, 0).toString()));
                        prod.setName(JTableOrder.getValueAt(x, 1).toString());
                        prod.setAmount(Integer.valueOf(JTableOrder.getValueAt(x, 2).toString()));
                        prod.setValue(Double.valueOf(JTableOrder.getValueAt(x, 3).toString()));
                        total = total + Double.valueOf(JTableOrder.getValueAt(x, 4).toString());
                        productList.add(prod);
                    }

                    if(type=="")
                        JOptionPane.showMessageDialog(PanelCaixa,"Selecione o Tipo de Venda","Campo invalido",JOptionPane.INFORMATION_MESSAGE);
                    else if(pay=="")
                        JOptionPane.showMessageDialog(PanelCaixa,"Selecione a Forma de Pagamento","Campo invalido",JOptionPane.INFORMATION_MESSAGE);
                    else if(JTableOrder.getRowCount()==0)
                        JOptionPane.showMessageDialog(PanelCaixa,"Sem itens na tabela para realizar a venda","Campo invalido",JOptionPane.INFORMATION_MESSAGE);
                    else {
                        if(pay=="V"){
                            ConfirmSell confirmSell = new ConfirmSell(productList, total, discount, type, pay,adress,ref,date);
                            confirmSell.setVisible(true);
                            confirmSell.setLocationRelativeTo(PanelCaixa);
                        }
                        else if(pay=="C"){
                            try{
                                new MnpBD().executeSell(productList,total,discount,type,pay,"",adress,ref,"0",name,date);
                            }catch(Exception ex){

                            }
                        }
                        clearSell();
                    }


                }catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(PanelCaixa,"Desconto Invalido","Campo invalido",JOptionPane.INFORMATION_MESSAGE);
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(PanelCaixa,"ERRO: "+ex,"ERRO",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public void clearSell(){
        //limpa a tabela
        JTableOrder.setModel(new DefaultTableModel(new Object [][] {},new String [] {"Cod","Produto", "Qtd.", "V. Unitario", "V. Total"}));

        //Limpar os campos dos produtos
        JTextFielCod.setText("");
        JTextFieldProd.setText("");
        JTextFieldAmount.setText("");

        groupPay.clearSelection();
        groupSell.clearSelection();

        //Limpar os campos do cliente
        JTextFieldName.setText("");
        JTextFieldAddress.setText("");
        JTextFieldReference.setText("");


    }

    public String dateNow(){
        Date date = new Date();
        SimpleDateFormat formatter= new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(date);
    }

    public void clearCustomer(){
        JTextFieldAddress.setText("");
        JTextFieldName.setText("");
        JTextFieldReference.setText("");
    }

}
