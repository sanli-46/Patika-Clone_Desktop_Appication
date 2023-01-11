package com.PatikaDev.View;

import com.PatikaDev.Helper.Helper;
import com.PatikaDev.Model.Usera;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SingUpGUI extends JFrame {
    private JPanel wrepper;
    private JTextField txt_student_name;
    private JPasswordField txt_student_pass;
    private JTextField txt_student_uname;
    private JButton btn_singup_;
    private JComboBox cmb_student_typee;
    private JButton btn_back;

    public SingUpGUI(){
        add(wrepper);
        setSize(300,600);
        setLocation(Helper.screeanCenter("x",getSize()),Helper.screeanCenter("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        btn_singup_.addActionListener(e -> {

            if (txt_student_uname.getText()==Usera.unameChack()){
                Helper.ShowMsg("Böyle bir kullanıcı adı mevcut .Başka bir kullanıcı adı giriniz !");
            }else {


            if (Helper.isFieldEmpty(txt_student_name)||Helper.isFieldEmpty(txt_student_uname)||Helper.isFieldEmpty(txt_student_pass)){
                Helper.ShowMsg("fill");
            }else {
                String name =txt_student_name.getText();
                String uname =txt_student_uname.getText();
                String pass =txt_student_pass.getText();
                String typee =cmb_student_typee.getSelectedItem().toString();
               if( Usera.add(name,uname,pass,typee)){
                   Helper.ShowMsg("done");
                   LoginGUI log =new LoginGUI();
                   dispose();
               }
            }
            }

        });
        btn_back.addActionListener(e -> {
         LoginGUI log =new LoginGUI();
         dispose();
        });
    }

}
