package com.PatikaDev.View;

import com.PatikaDev.Helper.*;
import com.PatikaDev.Model.Course;
import com.PatikaDev.Model.Operator;
import com.PatikaDev.Model.Patikalar;
import com.PatikaDev.Model.Usera;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class OperatorGUI extends JFrame {

    private final Operator operator;


    private JPanel wrapper;
    private JTabbedPane pnl_user_course;
    private JLabel lbl_welcome;
    private JButton btn_out;
    private JPanel pnl_user_Lıst;
    private JScrollPane jscrl_user_list;
    private JTable tbl_user_list;
    private JPanel pnl_user_form;
    private JLabel lbl_name_surname;
    private JTextField txt_name_surename;
    private JLabel lbl_uname;
    private JTextField txt_uname;
    private JLabel lbl_password;
    private JPasswordField fld_password;
    private JLabel lbl_mtype;
    private JComboBox cmb_user_type;
    private JButton ekleButton;
    private JLabel lbl_del;
    private JTextField txt_user_id;
    private JButton btn_user_del;
    private JTextField txt_sh_user_name;
    private JTextField txt_sh_user_uname;
    private JComboBox cmb_sh_user_typee;
    private JButton btn_sh;
    private JScrollPane scrl_patika_list;
    private JTable tbl_patika_list;
    private JTextField txt_patika_name;
    private JButton btn_patika_add;
    private JTable tbl_cours_list;
    private JPanel pnl_course_add;
    private JTextField txt_course_name;
    private JTextField txt_course_lang;
    private JButton btn_course_add;
    private JComboBox cmb_course_patika;
    private JComboBox cmb_course_user;
    private JTextField txt_course_delete;
    private JButton btn_course_delete;

    private DefaultTableModel mdl_patika_list;
   private Object[] row_patika_list;
    private DefaultTableModel mdl_user_list;
    private Object[] row_user_list;
    private DefaultTableModel mdl_course_list;
    private Object[] row_course_list;
    private JPopupMenu patikaMenu;



    public OperatorGUI(Operator operator){
        this.operator =operator;

        Helper.setLayout();
        add(wrapper);
        setSize(1200,800);
        setLocation(Helper.screeanCenter("x",getSize()),Helper.screeanCenter("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);

        lbl_welcome.setText("Hoşgeldiniz "+operator.getName());

        //ModelUserList
        mdl_user_list=new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
               if (column == 0)
                   return false;

                return super.isCellEditable(row, column);
            }
        };
        Object[] col_user_list = {"ID","Ad Soyad","Kullanıcı Adı","Şifre","Üyelik Tipi"};
        mdl_user_list.setColumnIdentifiers(col_user_list);

        row_user_list = new Object[col_user_list.length];
        //   Object[] firstRow={"2","AlperenŞanlıı","Alperen","123","operator"};
       // mdl_user_list.addRow(firstRow);

      loadUseraModel();
       loadEducatorCombo();


        tbl_user_list.setModel(mdl_user_list);
        tbl_user_list.getTableHeader().setReorderingAllowed(false);

        tbl_user_list.getSelectionModel().addListSelectionListener(e -> {
           try {
               String select_user_id = tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(),0).toString();
               txt_user_id.setText(select_user_id);
           }catch (Exception ex){

           }
        });

       tbl_user_list.getModel().addTableModelListener(e -> {
           if (e.getType() == TableModelEvent.UPDATE){
               int user_id = Integer.parseInt(tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(),0).toString());
               String user_name =tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(),1).toString();
               String user_uname =tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(),2).toString();
               String user_pass =tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(),3).toString();
               String user_typee =tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(),4).toString();

            if(Usera.update(user_id,user_name,user_uname,user_pass,user_typee)){
                Helper.ShowMsg("done");

            }

            loadUseraModel();
            loadEducatorCombo();
           loadCourseModel();
           }
       });
       //# Model UserList

       //Patika List
        patikaMenu =new JPopupMenu();
        JMenuItem updateMenu =new JMenuItem("Güncelle");
        JMenuItem deleteMenu =new JMenuItem("Sil");
        patikaMenu.add(updateMenu);
        patikaMenu.add(deleteMenu);

        updateMenu.addActionListener(e -> {
                 int select_id =Integer.parseInt(tbl_patika_list.getValueAt(tbl_patika_list.getSelectedRow(),0).toString());
                 UpdatePatikaGUI updateGUI = new UpdatePatikaGUI(Patikalar.getFetch(select_id));
                updateGUI.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                       loadPatikaaModel();
                       loadPatikaCombo();
                       loadCourseModel();
                    }
                });

        });

        deleteMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              if (Helper.confirm("sure")){
                  int select_id=Integer.parseInt(tbl_patika_list.getValueAt(tbl_patika_list.getSelectedRow(),0).toString());

                  if (Patikalar.delete(select_id)){
                      Helper.ShowMsg("done");

                      loadPatikaaModel();
                      loadPatikaCombo();
                      loadCourseModel();
                  }else {
                      Helper.ShowMsg("error");
                  }

              }
            }
        });

       mdl_patika_list =new DefaultTableModel();
       Object[] col_patika_list ={"ID","Patika Adı"};
       mdl_patika_list.setColumnIdentifiers(col_patika_list);
       row_patika_list =new Object[col_patika_list.length];
       loadPatikaaModel();
        loadPatikaCombo();

        tbl_patika_list.setModel(mdl_patika_list);
        tbl_patika_list.setComponentPopupMenu(patikaMenu);

        tbl_patika_list.getTableHeader().setReorderingAllowed(false);
        tbl_patika_list.getColumnModel().getColumn(0).setMaxWidth(60);

        tbl_patika_list.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Point point =e.getPoint();
                int selected_row = tbl_patika_list.rowAtPoint(point);
                tbl_patika_list.setRowSelectionInterval(selected_row,selected_row);
            }
        });

        //#PATİKA List

        //Course list
        mdl_course_list=new DefaultTableModel();
        Object [] col_courseLıst ={"ID","Dersin Adı","Programlama Dili","Patika","Eğitmen"};
        mdl_course_list.setColumnIdentifiers(col_courseLıst);
        row_course_list =new  Object[col_courseLıst.length];
        loadCourseModel();


        tbl_cours_list.setModel(mdl_course_list);
        tbl_cours_list.getColumnModel().getColumn(0).setMaxWidth(60);
        tbl_cours_list.getTableHeader().setReorderingAllowed(false);

         loadPatikaCombo();
        loadEducatorCombo();



        //## CourseList





        ekleButton.addActionListener(e -> {
            if (Helper.isFieldEmpty(txt_name_surename)||Helper.isFieldEmpty(txt_uname)||Helper.isFieldEmpty(fld_password)){
                Helper.ShowMsg("fill");

            }else {
               String name =txt_name_surename.getText();
               String uname=txt_uname.getText();
               String pass =fld_password.getText();
               String typee=cmb_user_type.getSelectedItem().toString();
               if (Usera.add(name,uname,pass,typee)){
                   Helper.ShowMsg("done");


                loadUseraModel();
                loadEducatorCombo();
                loadCourseModel();

                    txt_name_surename.setText(null);
                   txt_uname.setText(null);
                   fld_password.setText(null);
               }

            }
        });
        btn_user_del.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Helper.isFieldEmpty(txt_user_id)){
                    Helper.ShowMsg("fill");
                }else{
                   if (Helper.confirm("sure")){
                       int usera_id =Integer.parseInt(txt_user_id.getText());
                       if (Usera.delete(usera_id)){
                           Helper.ShowMsg("done");

                            loadUseraModel();
                           loadEducatorCombo();
                            loadCourseModel();

                        txt_user_id.setText(null);
                       }else {
                        Helper.ShowMsg("error");
                       }
                   }
                }
            }
        });

        btn_sh.addActionListener(e -> {
            String name = txt_sh_user_name.getText();
            String uname = txt_sh_user_uname.getText();
            String typee= cmb_sh_user_typee.getSelectedItem().toString();
            String query=Usera.searchQuery(name,uname,typee);
            ArrayList<Usera>searchingUser =Usera.searchUserList(query);
            loadUseraModel(searchingUser);
            loadEducatorCombo();
            loadCourseModel();
        });

        btn_out.addActionListener(e -> {
            dispose();
        });



        btn_patika_add.addActionListener(e -> {
            if (Helper.isFieldEmpty(txt_patika_name)){
                Helper.ShowMsg("fill");

            }else {
                String name = txt_patika_name.getText();
                 if (Patikalar.add(name)){
                    Helper.ShowMsg("done");
                    loadPatikaaModel();
                    loadPatikaCombo();
                    loadCourseModel();
                    txt_patika_name.setText(null);
                }else {
                     Helper.ShowMsg("error");
                 }

            }
        });
        btn_course_add.addActionListener(e -> {
         Item patikalarItem = (Item) cmb_course_patika.getSelectedItem();
         Item useraItem = (Item) cmb_course_user.getSelectedItem();
         if (Helper.isFieldEmpty(txt_course_name) || Helper.isFieldEmpty(txt_course_lang)){
             Helper.ShowMsg("fill");
         }else {
             if (Course.add(useraItem.getKey(), patikalarItem.getKey(), txt_course_name.getText(),txt_course_lang.getText())){
                 Helper.ShowMsg("done");
                 loadCourseModel();
                 loadPatikaCombo();

                 txt_course_lang.setText(null);
                 txt_course_name.setText(null);
             }else {
                 Helper.ShowMsg("error");
             }
         }



        });
        btn_course_delete.addActionListener(e -> {
            if (Helper.isFieldEmpty(txt_course_delete)){
                Helper.ShowMsg("fill");
            }else{
                if (Helper.confirm("sure")){

                    int course_id =Integer.parseInt(txt_course_delete.getText());

                    if (Course.delete(course_id)){
                        Helper.ShowMsg("done");
                         loadCourseModel();

                        loadUseraModel();
                        loadEducatorCombo();


                    }else {
                        Helper.ShowMsg("error");
                    }
                    txt_course_delete.setText(null);
                }
            }
        });
    }

    private void loadCourseModel
() {

        DefaultTableModel clearModel = (DefaultTableModel) tbl_cours_list.getModel();
        clearModel.setRowCount(0);
        int i=0;
        for (Course obj : Course.getList()){
            i=0;
            row_course_list[i++]=obj.getId();
            row_course_list[i++]=obj.getName();
            row_course_list[i++]=obj.getLang();
            row_course_list[i++]=obj.getPatikalar_id();
            row_course_list[i++]=obj.getUsera_id();
            mdl_course_list.addRow(row_course_list);


        }
    }

    private void loadPatikaaModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_patika_list.getModel();
        clearModel.setRowCount(0);
        int i=0;
        for (Patikalar obj : Patikalar.getList()){
         i=0;
         row_patika_list[i++]=obj.getId();
         row_patika_list[i++]=obj.getName();
        mdl_patika_list.addRow(row_patika_list);

        }
    }

    public void  loadUseraModel(){
        DefaultTableModel clearModel =(DefaultTableModel) tbl_user_list.getModel();
        clearModel.setRowCount(0);

        int i;
        for (Usera obj:Usera.getList()){
             i=0;
            row_user_list[i++]=obj.getId();
            row_user_list[i++]=obj.getName();
            row_user_list[i++]=obj.getUname();
            row_user_list[i++]=obj.getPass();
            row_user_list[i++]=obj.getTypee();

            mdl_user_list.addRow(row_user_list);
        }
    }

    public void  loadUseraModel(ArrayList<Usera> list){
        DefaultTableModel clearModel =(DefaultTableModel) tbl_user_list.getModel();
        clearModel.setRowCount(0);


        for (Usera obj: list){
            int i=0;
            row_user_list[i++]=obj.getId();
            row_user_list[i++]=obj.getName();
            row_user_list[i++]=obj.getUname();
            row_user_list[i++]=obj.getPass();
            row_user_list[i++]=obj.getTypee();

            mdl_user_list.addRow(row_user_list);
        }
    }


    public void loadEducatorCombo(){
        cmb_course_user.removeAllItems();
        for (Usera obj : Usera.getListOnlyEducator()){
         //   if (obj.getTypee().equals("educator")) {
                cmb_course_user.addItem(new Item(obj.getId(), obj.getName()));
        //    }
        }
    }
    public void loadPatikaCombo(){

        cmb_course_patika.removeAllItems();
        for (Patikalar obj : Patikalar.getList()){
            cmb_course_patika.addItem(new Item(obj.getId(),obj.getName()));
        }
    }

    public static void main(String[] args) {
        Operator op =new Operator();
        OperatorGUI opGUI=new OperatorGUI(op);


    }


}
