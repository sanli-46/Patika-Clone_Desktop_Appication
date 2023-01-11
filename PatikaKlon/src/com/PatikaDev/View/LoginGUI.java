package com.PatikaDev.View;

import com.PatikaDev.Helper.Config;
import com.PatikaDev.Helper.Helper;
import com.PatikaDev.Model.Educator;
import com.PatikaDev.Model.Operator;
import com.PatikaDev.Model.Usera;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JFrame{
    private JPanel wrapper;
    private JPanel twrapp;
    private JTextField txt_username;
    private JPasswordField txt_pass;
    private JButton btn_login;
    private JPanel dwrapp;
    private JButton btn_login_singUp;

    public  LoginGUI (){

        add(wrapper);
        setSize(500,400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setLocation(Helper.screeanCenter("x",getSize()),Helper.screeanCenter("y",getSize()));
        setVisible(true);
        setResizable(false);

        btn_login.addActionListener(e -> {
          if (Helper.isFieldEmpty(txt_username)||Helper.isFieldEmpty(txt_pass)){
              Helper.ShowMsg("fill");
          }else {
              Usera u = Usera.getFetch(txt_username.getText(),txt_pass.getText()) ;
              if (u == null){
                  Helper.ShowMsg("Kullanıcı bulunamadı !!");
              }else{
            switch (u.getTypee()){
                case "operator":
                    OperatorGUI opGUI =new OperatorGUI((Operator) u);
                    break;
                case "educator":
                    EducatorGUI eduGUI =new EducatorGUI((Educator) u);
                    break;
                case "student":
                    StudentGUI stdGUI =new StudentGUI();
                    break;
            }
            dispose();
              }
          }
        });
        btn_login_singUp.addActionListener(e -> {
        SingUpGUI singUpGUI =new SingUpGUI();
        dispose();
        });
    }

    public static void main(String[] args) {
        LoginGUI log =new LoginGUI();
    }
}
