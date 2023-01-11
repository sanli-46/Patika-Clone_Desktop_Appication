package com.PatikaDev.Helper;

import javax.swing.*;
import java.awt.*;

public class Helper {



    public static void setLayout(){
        for (UIManager.LookAndFeelInfo info :UIManager.getInstalledLookAndFeels()){
          //  System.out.println(info.getName() +"  -->  " +info.getClassName() );
            try {
                UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            } catch (ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException |
                     InstantiationException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public static boolean isFieldEmpty(JTextField field){
        return field.getText().trim().isEmpty();
    }

    public static void ShowMsg(String str){
     optionPaneTR();
       String msg;
        String title;
        switch (str){
            case "fill":
                msg ="Lütfen tüm alanları doldurunuz !!!";
                title="Hata!";
                break;
            case "done":
                msg = "İşlem Başarılı.";
                title="Sonuç";
                break;
            case "error":
                msg ="Bir hata oluştu";
                title ="Hata!";
                break;
            default:
                title="Mesaj";
                msg=str;
        }
        JOptionPane.showMessageDialog(null,msg,title,JOptionPane.INFORMATION_MESSAGE);
    }



    public static  int screeanCenter(String axis, Dimension size){
        return switch (axis) {
            case "x" -> (Toolkit.getDefaultToolkit().getScreenSize().width - size.width) / 2;
            case "y" -> (Toolkit.getDefaultToolkit().getScreenSize().height - size.height) / 2;
            default -> 0;
        };
    }
public  static boolean confirm(String str){
        String msg ;
    optionPaneTR();
        switch (str){
            case "sure":
                msg ="Bu işlemi gerçekleştirmek istediginize eminmiziniz ?";
                break;
            default:
                msg=str;
        }
       return JOptionPane.showConfirmDialog(null, msg,"Son Kararın Mı ?",JOptionPane.YES_NO_OPTION) == 0;

}
 public static void optionPaneTR(){
        UIManager.put("OptionPane.okButtonText","Tamam");
        UIManager.put("OptionPane.yesButtonText","Evet");
        UIManager.put("OptionPane.noButtonText","Hayır");
}
}
