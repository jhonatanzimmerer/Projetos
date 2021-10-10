package view;

import cls.bd.SetBD;
import cls.obj.Customer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

public class RegisterCustomer extends JFrame {
    private JPanel PanelMain;
    private JTextField JTextFieldName;
    private JTextField JTextFieldCompany;
    private JTextField JTextFieldAdress;
    private JTextField JTextFieldReference;
    private JTextField JTextFieldPhone;
    private JButton JButtonConfirm;

    public RegisterCustomer(JButton JButtonRegister){
        this.setContentPane(PanelMain);
        this.setDefaultCloseOperation(RegisterCustomer.DISPOSE_ON_CLOSE);
        this.setSize(400,300);
        this.setLocationRelativeTo(null);


        JButtonConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Customer customer = new Customer();
                    customer.setName(JTextFieldName.getText().trim());
                    customer.setCompany(JTextFieldCompany.getText().trim());
                    customer.setAdress(JTextFieldAdress.getText().trim());
                    customer.setReference(JTextFieldReference.getText().trim());
                    customer.setPhone(JTextFieldPhone.getText().trim());

                    if(customer.getName().equals(""))
                        JOptionPane.showMessageDialog(null,"Nome não pode estar em branco!");
                    else if(customer.getCompany().equals(""))
                        JOptionPane.showMessageDialog(null,"Empresa não pode estar em branco!");
                    else if(customer.getName().length()>30)
                        JOptionPane.showMessageDialog(null,"Nome muito grande, favor diminuir o tamanho!");
                    else if(customer.getCompany().length()>30)
                        JOptionPane.showMessageDialog(null,"Nome da empresa muito grande, favor diminuir o tamanho!");
                    else{
                        new SetBD().registerCustomer(customer);
                        JButtonRegister.setEnabled(true);
                        dispose();
                    }

                }catch(Exception ex){
                    JOptionPane.showMessageDialog(null,"Dados incorreto!");
                }

            }
        });
    }



}
