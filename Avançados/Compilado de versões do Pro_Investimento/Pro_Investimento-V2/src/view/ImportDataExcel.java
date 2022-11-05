package view;

import cls.bd.MnpBD;
import cls.obj.Operacao;
import cls.xls.CarregarXml;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class ImportDataExcel extends JFrame{
    private JPanel panelMain;
    private JTextField JTextFieldPath;
    private JButton JButtonConfirmar;
    private JPanel JPanelHeader;
    private JPanel JPanelBody;
    private JLabel JLabelIconSearch;
    private JLabel JLabelImport;

    public ImportDataExcel() {
        this.setContentPane(panelMain);
        this.setSize(400,200);
        this.setDefaultCloseOperation(ImportDataExcel.DISPOSE_ON_CLOSE);
        action();
    }

    public void start() {
        this.setVisible(true);
    }

    public void action(){

        JButtonConfirmar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                MnpBD mnpBD = new MnpBD();

                for (Operacao operacao : new CarregarXml().load(JTextFieldPath.getText()))
                    mnpBD.insertOperationMnp(operacao);

                JOptionPane.showMessageDialog(null,"Operação concluida com sucesso");

            }
        });

        JLabelIconSearch.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                File file;

                JFileChooser fc = new JFileChooser();
                fc.setPreferredSize(new Dimension(750, 400));
                fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int res = fc.showOpenDialog(panelMain);

                if(res != JFileChooser.CANCEL_OPTION){
                    file = fc.getSelectedFile();
                    JTextFieldPath.setText(file.toString().trim());
                }
            }
        });
    }
}
