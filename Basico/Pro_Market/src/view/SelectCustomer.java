package view;

import cls.bd.GetBD;
import cls.obj.Customer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectCustomer extends JFrame{
    private JPanel PanelMain;
    private JButton JButtonConfirmar;
    private JTextField JTextFieldSearch;
    private JComboBox JComboBoxFilter;
    private JTable JTableCustomerList;
    private JLabel JLabelFolder;
    private JLabel JLabelFilter;
    private JLabel JLabelIconSearch;
    private JScrollPane JScrollPaneBody;
    private JButton JButtonCadastrar;
    private JButton JButtonRefresh;
    private JPanel JPanelHeader;
    private JPanel JPanelFoot;
    private JButton JButtonExit;
    private JTextField JTextFieldBackName;
    private JTextField JTextFieldBackAddress;
    private JTextField JTextFieldBackReference;
    private JLabel JLabelIconBack;

    public SelectCustomer(JTextField JTextFieldInsertName,JLabel JLabelIcon ){
        this.setContentPane(PanelMain);
        this.setDefaultCloseOperation(RegisterCustomer.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setUndecorated(true);
        JTextFieldBackName = JTextFieldInsertName;
        JLabelIconBack = JLabelIcon;
        start();

        JButtonConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    JTextFieldBackName.setText(JTableCustomerList.getValueAt(JTableCustomerList.getSelectedRow(),1).toString());

                    JLabelIconBack.setEnabled(true);
                    dispose();
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(rootPane,"Selecione um item da tabela","Campo invalido",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

    }
    public SelectCustomer(JTextField JTextFieldInsertName, JTextField JTextFieldAddress, JTextField JTextFieldReference, JLabel JLabelIcon ){
        JTextFieldBackName = JTextFieldInsertName;
        JTextFieldBackAddress = JTextFieldAddress;
        JTextFieldBackReference = JTextFieldReference;
        JLabelIconBack = JLabelIcon;
        start();

        JButtonConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    JTextFieldBackName.setText(JTableCustomerList.getValueAt(JTableCustomerList.getSelectedRow(),1).toString());
                    JTextFieldBackAddress.setText(JTableCustomerList.getValueAt(JTableCustomerList.getSelectedRow(),3).toString());
                    JTextFieldBackReference.setText(JTableCustomerList.getValueAt(JTableCustomerList.getSelectedRow(),4).toString());

                    JLabelIconBack.setEnabled(true);
                    dispose();
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(rootPane,"Selecione um item da tabela","Campo invalido",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }


    public void start(){
        iconFolder("C:\\Users\\jhonatan\\Documents\\GitProjectPessoal\\Projetos\\Basico\\Pro_Market\\out\\artifacts\\Pro_Market_jar\\icon\\iconSearch.png");
        this.setContentPane(PanelMain);
        this.setSize(new Dimension(500,(int)screenSize().getHeight()/2));
        actions();
        confJTable();
    }

    public void iconFolder(String path){
        ImageIcon imageIcon = new ImageIcon(path);
        JLabelIconSearch.setSize(16,16);
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(JLabelIconSearch.getWidth(), JLabelIconSearch.getHeight(), 1));
        JLabelIconSearch.setIcon(imageIcon);
    }

    public void confJTable(){
        JTableCustomerList.setModel(new DefaultTableModel(new Object [][] {},new String [] {"Cod","Nome","Empresa", "Endereco", "Referencia", "Telefone"}));
        JTableCustomerList.getTableHeader().setReorderingAllowed (false);
        loadTableBd((DefaultTableModel) JTableCustomerList.getModel());

    }

    public void loadTableBd(DefaultTableModel table){
        table.setNumRows(0);
        for(Customer customer: new GetBD().locaCustomer()){
            table.addRow(new Object[] {customer.getCod(),customer.getName(),customer.getCompany(),customer.getAdress(),customer.getReference(),customer.getPhone()});
        }

    }
    public static Dimension screenSize(){
        GraphicsDevice Gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        return new Dimension(Gd.getDisplayMode().getWidth(),Gd.getDisplayMode().getHeight());
    }

    public void actions(){


        JButtonCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterCustomer registerCustomer = new RegisterCustomer(JButtonCadastrar);
                registerCustomer.setVisible(true);
                JButtonCadastrar.setEnabled(false);

            }
        });

        JButtonRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadTableBd((DefaultTableModel) JTableCustomerList.getModel());
            }
        });

        JButtonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JLabelIconBack.setEnabled(true);
                dispose();
            }
        });
    }

}
