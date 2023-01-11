package com.PatikaDev.View;

import com.PatikaDev.Helper.Config;
import com.PatikaDev.Helper.Helper;
import com.PatikaDev.Model.Content;
import com.PatikaDev.Model.Course;
import com.PatikaDev.Model.Educator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class EducatorGUI extends JFrame {
    private JPanel wrapper;
    private JButton btn_ex;
    private JLabel labl_login;
    private JTable tbl_educator_contentList;
    private JPanel pnl_content;
    private JTextField txt_edu_name;
    private JTextField txt_edu_explan;
    private JTextField txt_edu_ylink;
    Educator educator=new Educator();
    Course course =new Course();
    private DefaultTableModel mdl_countent_list;
    private Object[] row_countent_list;

    public EducatorGUI(Educator educator){
        add(wrapper);
        setSize(500,400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setLocation(Helper.screeanCenter("x",getSize()),Helper.screeanCenter("y",getSize()));
        setVisible(true);
        setResizable(false);

        labl_login.setText("Hoşgeldiniz "+ educator.getName());
        mdl_countent_list =new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
               if (column ==0){
                   return false;
               }
                return super.isCellEditable(row, column);
            }
        };

        Object[]col_contList ={"ID","Başlık","Açıklama","Youtube Link","Quiz Soruları","course_id"};
        mdl_countent_list.setColumnIdentifiers(col_contList);
        row_countent_list=new Object[col_contList.length];
        tbl_educator_contentList.getTableHeader().setReorderingAllowed(true);

         loadContentModel();



        btn_ex.addActionListener(e -> {
            LoginGUI log=new LoginGUI();
            dispose();
        });
    }
    public void loadContentModel(){
        DefaultTableModel clearModel =(DefaultTableModel) tbl_educator_contentList.getModel();
        clearModel.setRowCount(0);
        int i;
        int id =this.course.getId();
        for (Content obj :Content.getList(id)){
            i=0;
            row_countent_list[i++]=obj.getId();
            row_countent_list[i++]=obj.getName();
            row_countent_list[i++]=obj.getExplanation();
            row_countent_list[i++]=obj.getyLink();
            row_countent_list[i++]=obj.getQuizquestion();
            row_countent_list[i++]=obj.getcourse_id();
            mdl_countent_list.addRow(row_countent_list);

        }
    }

    public static void main(String[] args) {
        Educator ed =new Educator();
        EducatorGUI edu =new EducatorGUI(ed);
    }
}
