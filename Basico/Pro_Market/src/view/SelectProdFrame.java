package view;

import javax.swing.*;
import java.awt.*;



public class SelectProdFrame extends JFrame{
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
        this.setContentPane(JPanelSelecProd);
        this.setSize(new Dimension(500,(int)screenSize().getHeight()/2));
        iconSearch("C:\\Users\\jhonatan\\Documents\\GitProjectPessoal\\Projetos\\Basico\\Pro_Market\\out\\artifacts\\Pro_Market_jar\\icon\\iconSearch.png");
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
}
