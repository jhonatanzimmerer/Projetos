package view;

import javax.swing.*;

public class SelectProdFrame {
    private JTextField JTextFieldProd;
    private JTextField textField2;
    private JComboBox JComboBoxFiltro;
    private JPanel JPanelSelecProd;
    private JPanel JPanelHeader;
    private JPanel JPanelFoot;
    private JLabel JLabelIconSearch;
    private JLabel JLabelProd;
    private JLabel JLabelFiltro;
    private JPanel JPanelProdResult;
    private JButton JButtonConfirm;
    private JLabel JLabelProdResult;

    public SelectProdFrame(){
        iconSearch("C:\\Users\\jhonatan\\Documents\\GitProjectPessoal\\Projetos\\Basico\\Pro_Market\\out\\artifacts\\Pro_Market_jar\\icon\\iconSearch.png");
    }

    public void iconSearch(String path){
        ImageIcon imageIcon = new ImageIcon(path);
        JLabelIconSearch.setSize(24,24);
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(JLabelIconSearch.getWidth(), JLabelIconSearch.getHeight(), 1));
        JLabelIconSearch.setIcon(imageIcon);
    }
}
