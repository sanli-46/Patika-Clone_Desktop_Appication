package com.PatikaDev.View;

import com.PatikaDev.Helper.Config;
import com.PatikaDev.Helper.Helper;
import com.PatikaDev.Model.Patikalar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdatePatikaGUI extends  JFrame {
    private JPanel wrapper;
    private JTextField txt_patika_name;
    private JButton btn_update;
    private Patikalar patika;

    public UpdatePatikaGUI(Patikalar patika){
        this.patika=patika;
        add(wrapper);
        setSize(300,150);
        setLocation(Helper.screeanCenter("x",getSize()),(Helper.screeanCenter("y",getSize())));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);



        txt_patika_name.setText(patika.getName());
        btn_update.addActionListener(e -> {
            if (Helper.isFieldEmpty(txt_patika_name)){
                Helper.ShowMsg("fill");
            }else {
                if (Patikalar.update(patika.getId(),txt_patika_name.getText())){
                    Helper.ShowMsg("done");
                }
                dispose();


            }
        });
    }

}
