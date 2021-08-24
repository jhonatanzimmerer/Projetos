package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class SelectCustomer extends JFrame{
    private JPanel PanelMain;
    private JButton JButtonConfirmar;
    private JTextField JTextFieldSearch;
    private JComboBox JComboBoxFilter;
    private JTable JTableCustomerList;
    private JLabel JLabelFolder;
    private JLabel JLabelFilter;
    private JLabel JLabelIconSearch;
    private JScrollPane JScrollPaneTable;
    private JTextField JTextFieldBackName;

    public SelectCustomer(JTextField JTextFieldInsertName ){
        iconFolder("C:\\Users\\jhonatan\\Documents\\GitProjectPessoal\\Projetos\\Basico\\Pro_Market\\out\\artifacts\\Pro_Market_jar\\icon\\iconSearch.png");
        this.setContentPane(PanelMain);
        this.setSize(new Dimension(500,(int)screenSize().getHeight()/2));
        JTextFieldBackName = JTextFieldInsertName;
        confJTable();

    }

    public void iconFolder(String path){
        ImageIcon imageIcon = new ImageIcon(path);
        JLabelIconSearch.setSize(16,16);
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(JLabelIconSearch.getWidth(), JLabelIconSearch.getHeight(), 1));
        JLabelIconSearch.setIcon(imageIcon);
    }

    public void confJTable(){
        JTableCustomerList.setModel(new DefaultTableModel(new Object [][] {},new String [] {"Nome", "Empresa", "Telefone"}));
    }

    public static Dimension screenSize(){
        GraphicsDevice Gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        return new Dimension(Gd.getDisplayMode().getWidth(),Gd.getDisplayMode().getHeight());
    }

}
