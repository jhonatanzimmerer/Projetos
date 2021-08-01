package view;

import cls.bd.GetBD;
import cls.bd.MnpBD;
import cls.obj.Product;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame{
    private JPanel PanelMain;
    private JPanel JPanelHead;
    private JPanel JPanelBody;
    private JPanel JPanelConfig;
    private JPanel JPanelFunc;
    private JPanel JPanelContentConfig;
    private JPanel JPanelContentStatus;
    private JLabel JLabelIconSell;
    private JLabel JLabelIconGear;
    private JLabel JLabelIconLogin;
    private JPanel JPanelContentSell;
    private JPanel JPanelContentSpace;
    private JPanel JPanelContentGear;
    private JLabel JLabelSell;
    private JLabel JLabelGear;
    private JPanel JPanelContentSpace2;
    private JLabel JLabelIconCaixa;
    private JLabel JLabelCaixa;
    private JPanel JPaneliconCaixa;
    private JPanel JPanelContentSpace3;


    public void start(){
        //seta o painel principal, define o fim da operação e define o tamanho 151pixel
        this.setContentPane(PanelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1280,720);

        //Carrega os icones do painel principal
        iconLogin("C:\\Users\\jhonatan\\Documents\\GitProjectPessoal\\Projetos\\Basico\\Pro_Market\\out\\artifacts\\Pro_Market_jar\\icon\\iconLogin.png");
        iconSell("C:\\Users\\jhonatan\\Documents\\GitProjectPessoal\\Projetos\\Basico\\Pro_Market\\out\\artifacts\\Pro_Market_jar\\icon\\iconSell.png");
        iconGear("C:\\Users\\jhonatan\\Documents\\GitProjectPessoal\\Projetos\\Basico\\Pro_Market\\out\\artifacts\\Pro_Market_jar\\icon\\iconGear.png");
        iconCaixa("C:\\Users\\jhonatan\\Documents\\GitProjectPessoal\\Projetos\\Basico\\Pro_Market\\out\\artifacts\\Pro_Market_jar\\icon\\iconCaixa.png");

        //Carrega o painel do frame que contem o caixa
        caixaPanel();

        //Carrega a lista de produtos
        List<Product> listProduct = new ArrayList<>();
        listProduct.addAll(new GetBD().loadProd());


        this.setVisible(true);
    }

    public void iconLogin(String path){
        ImageIcon imageIcon = new ImageIcon(path);
        JLabelIconLogin.setSize(24,24);
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(JLabelIconLogin.getWidth(), JLabelIconLogin.getHeight(),1));
        JLabelIconLogin.setIcon(imageIcon);
    }

    public void iconSell(String path){
        ImageIcon  imageIcon = new ImageIcon(path);
        JLabelIconSell.setSize(24,24);
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(JLabelIconSell.getWidth(), JLabelIconSell.getHeight(),1));
        JLabelIconSell.setIcon(imageIcon);
    }

    public void iconGear(String path){
        ImageIcon imageIcon = new ImageIcon(path);
        JLabelIconGear.setSize(24,24);
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(JLabelIconGear.getWidth(), JLabelIconGear.getHeight(), 1));
        JLabelIconGear.setIcon(imageIcon);
    }

    public void iconCaixa(String path){
        ImageIcon imageIcon = new ImageIcon(path);
        JLabelIconCaixa.setSize(128,128);
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(JLabelIconCaixa.getWidth(), JLabelIconCaixa.getHeight(), 1));
        JLabelIconCaixa.setIcon(imageIcon);
    }

    public void caixaPanel(){
        CaixaFrame caixaFrame = new CaixaFrame();
        this.JPanelBody.add(caixaFrame.getContentPane()).setSize(JPanelBody.getSize());
    }

    public void lockFrame(boolean lock){
        this.setEnabled(lock);
    }
}
