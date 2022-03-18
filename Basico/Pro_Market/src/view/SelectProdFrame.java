package view;

import cls.bd.GetBD;
import cls.bd.MnpBD;
import cls.obj.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class SelectProdFrame extends JFrame{
    private JTextField JTextFieldProd;
    private JComboBox JComboBoxFiltro;
    private JPanel JPanelSelecProd;
    private JPanel JPanelHeader;
    private JPanel JPanelFoot;
    private JLabel JLabelIconSearch;
    private JLabel JLabelProd;
    private JLabel JLabelFiltro;
    private JPanel JPanelProdResult;
    private JButton JButtonConfirm;
    private JTable JTableProdList;
    private JScrollPane JScrollPaneProdList;
    private JButton JButtonAdd;
    private JButton JButtonExit;
    //Usadaos para interagir com outra janela
    private JTextField JTextFieldBackProd;
    private JTextField JTextFieldBackCod;
    private JLabel JLabelBackFolderProd;

    public SelectProdFrame(JTextField JTextFieldBackProd,JTextField JTextFieldBackCod, JLabel JLabelBackFolderProd){
        //Definindo variaveis e tela que sera mostrada
        this.setContentPane(JPanelSelecProd);
        this.setSize(new Dimension(500,(int)screenSize().getHeight()/2));
        this.JTextFieldBackProd = JTextFieldBackProd;
        this.JTextFieldBackCod = JTextFieldBackCod;
        this.JLabelBackFolderProd = JLabelBackFolderProd;
        this.setUndecorated(true);

        //Carregando icones e dados
        iconSearch("C:\\Users\\jhonatan\\Documents\\GitProjectPessoal\\Projetos\\Basico\\Pro_Market\\out\\artifacts\\Pro_Market_jar\\icon\\iconSearch.png");
        confTable();
        actions();

        JButtonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }


    public void iconSearch(String path){
        ImageIcon imageIcon = new ImageIcon(path);
        JLabelIconSearch.setSize(16,16);
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(JLabelIconSearch.getWidth(), JLabelIconSearch.getHeight(), 1));
        JLabelIconSearch.setIcon(imageIcon);
    }

    public static Dimension screenSize(){
        GraphicsDevice Gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        return new Dimension(Gd.getDisplayMode().getWidth(),Gd.getDisplayMode().getHeight());
    }

    public void confTable(){

        JTableProdList.setModel(new DefaultTableModel(new Object[][]{}, new String[] {"Cod", "Produto", "Qtd.", "Valor"}));
        JTableProdList.getTableHeader().setReorderingAllowed(false);
        DefaultTableModel dmt = (DefaultTableModel) JTableProdList.getModel();

        for (Product prod : new GetBD().loadProd()) {
            dmt.addRow(new String[] {String.valueOf(prod.getCod()), prod.getName(), String.valueOf(prod.getAmount()), String.valueOf(prod.getValue())});
        }

        TableRowSorter tableSorter = new TableRowSorter(JTableProdList.getModel());
        JTableProdList.setRowSorter(tableSorter);
        tableSorter.toggleSortOrder(1);

    }

    public void actions(){

        //Action para o Icone Search
        JLabelIconSearch.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                TableRowSorter tableSorter = new TableRowSorter(JTableProdList.getModel());
                JTableProdList.setRowSorter(tableSorter);
                tableSorter.toggleSortOrder(1);
                for(int x=0;x<JTableProdList.getRowCount();x++){
                    if(JTableProdList.getValueAt(x,1).toString().toLowerCase().contains(JTextFieldProd.getText().toLowerCase())) {
                        JTableProdList.setRowSelectionInterval(x, x);
                        break;
                    }
                }

            }
        });

        //Action para o botão confirmar
        JButtonConfirm.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try{
                    JTextFieldBackCod.setText(JTableProdList.getValueAt(JTableProdList.getSelectedRow(),0).toString());
                    JTextFieldBackProd.setText(JTableProdList.getValueAt(JTableProdList.getSelectedRow(),1).toString());
                    JLabelBackFolderProd.setEnabled(true);
                    dispose();
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(rootPane,"Selecione um item da tabela","Campo invalido",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        JButtonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JLabelBackFolderProd.setEnabled(true);
                dispose();
            }
        });
    }
}
