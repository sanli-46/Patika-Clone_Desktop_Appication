package com.PatikaDev.View;

import com.PatikaDev.Helper.Config;
import com.PatikaDev.Helper.Helper;

import javax.swing.*;

public class StudentGUI extends JFrame {
    private JPanel wrapper;
    public StudentGUI(){
        add(wrapper);
        setSize(500,400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setLocation(Helper.screeanCenter("x",getSize()),Helper.screeanCenter("y",getSize()));
        setVisible(true);
        setResizable(false);

    }
}
