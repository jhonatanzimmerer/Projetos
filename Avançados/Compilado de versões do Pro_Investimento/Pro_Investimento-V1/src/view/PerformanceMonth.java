package view;

import cls.bd.MnpBD;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PerformanceMonth extends JFrame {
    private JComboBox JComboBoxMonth;
    private JTextField JTextFieldYear;
    private JLabel JLabelPerformance;
    private JLabel JLabelTax;
    private JLabel JLabelTotalToPay;
    private JButton JTextButtonConf;
    private JLabel JLabelTitle;
    private JRadioButton JRadioButtonFII;
    private JRadioButton JRadioButtonAcao;
    private JPanel PanelMain;
    private JPanel JPanelHead;
    private JPanel JPanelBody;
    private ButtonGroup JButtonGroupClass;

    public PerformanceMonth() {
        setContentPane(PanelMain);
        setSize(900,800);
        setDefaultCloseOperation(PerformanceMonth.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        loadComponents();
        start();

        actions();

    }

    public void actions(){
        JTextButtonConf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int month;
                String year;
                String clas="";

                month = JComboBoxMonth.getSelectedIndex();
                year = JTextFieldYear.getText().replaceAll(" ","");
                if(JRadioButtonAcao.isSelected())
                    clas = "A";
                else if(JRadioButtonFII.isSelected())
                    clas = "F";
                Double performance = new MnpBD().loadMonthPerformance(month,year,clas);

                JLabelTitle.setText("Desempenho somente de "+ clas + " do mês de "+JComboBoxMonth.getSelectedItem());
                JLabelPerformance.setText(String.valueOf(performance));
                JLabelTax.setText("20%");
                if(performance>0)
                    JLabelTotalToPay.setText(String.valueOf(performance*0.20));
                else
                    JLabelTotalToPay.setText("Sem Lucro");
            }
        });
    }


    public void loadComponents(){
        JButtonGroupClass = new ButtonGroup();
        JButtonGroupClass.add(JRadioButtonAcao);
        JButtonGroupClass.add(JRadioButtonFII);
    }

    public void start(){
        this.setVisible(true);
    }




}
