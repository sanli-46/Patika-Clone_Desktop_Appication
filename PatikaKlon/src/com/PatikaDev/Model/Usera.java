package com.PatikaDev.Model;

import com.PatikaDev.Helper.DBConnector;
import com.PatikaDev.Helper.Helper;


import java.lang.constant.Constable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Usera {
    private int id;
    private String name;
    private static String uname;
    private String pass;
    private String typee;


    public Usera(){}


    public Usera(int id, String name, String uname, String pass, String typee) {
        this.id = id;
        this.name = name;
        this.uname = uname;
        this.pass = pass;
        this.typee = typee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getTypee() {
        return typee;
    }

    public void setTypee(String typee) {
        this.typee = typee;
    }

    public static ArrayList<Usera> getList(){
        ArrayList<Usera>userList =new ArrayList<>();
        String query =" Select * From usera";
        Usera obj ;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                obj=new Usera();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setUname(rs.getString("uname"));
                obj.setPass(rs.getString("pass"));
                obj.setTypee(rs.getString("typee"));
                userList.add(obj);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }



    public static boolean add(String name,String uname,String pass,String typee){

        String query =" INSERT INTO   usera (name,uname,pass,typee) VALUES (?,?,?,?) ";
          Usera findUser =Usera.getFetch(uname);
          if (findUser != null){
            Helper.ShowMsg("Bu kullanıcı adı zaten mevcut !! Lütfen başka bir kullanıcı adı giriniz. ");

              return false;
          }
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,name);
            pr.setString(2,uname);
            pr.setString(3,pass);
            pr.setString(4,typee);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
          return true;
    }

    public  static Usera getFetch(String uname){
        Usera obj =null;
        String query ="SELECT  * FROM usera  WHERE uname = ?";
        try {
            PreparedStatement pr =DBConnector.getInstance().prepareStatement(query);
           pr.setString(1,uname);
           ResultSet rs =pr.executeQuery();
         if (rs.next()){
             obj=new Usera();
             obj.setId(rs.getInt("id"));
             obj.setName(rs.getString("name"));
             obj.setUname(rs.getString("uname"));
             obj.setPass(rs.getString("pass"));
             obj.setTypee(rs.getString("typee"));

           }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  obj;
    }

    public  static Usera getFetch(String uname,String pass){
        Usera obj =null;
        String query ="SELECT  * FROM usera  WHERE uname = ? AND pass = ?";
        try {
            PreparedStatement pr =DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,uname);
            pr.setString(2,pass);
            ResultSet rs =pr.executeQuery();
            if (rs.next()){
                switch (rs.getString("typee")){
                    case "operator":
                        obj=new Operator();
                        break;
                    default:
                        obj=new Usera();
                }

                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setUname(rs.getString("uname"));
                obj.setPass(rs.getString("pass"));
                obj.setTypee(rs.getString("typee"));

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  obj;
    }



    public  static Usera getFetch(int id){
        Usera obj =null;
        String query ="SELECT  * FROM usera  WHERE id = ?";
        try {
            PreparedStatement pr =DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs =pr.executeQuery();
            if (rs.next()){
                obj=new Usera();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setUname(rs.getString("uname"));
                obj.setPass(rs.getString("pass"));
                obj.setTypee(rs.getString("typee"));

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  obj;
    }




    public static boolean delete(int id){
        String query =" DELETE FROM usera WHERE id =?";
      try{
        ArrayList<Course> courseList =Course.getListByUsera(id);
        for (Course c : courseList){
            Course.delete(c.getId());
        }
      }catch (Exception e){}

        try {
            PreparedStatement pr =DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            return pr.executeUpdate() != -1;


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

       return true;
    }

    public static  boolean update(int id ,String name , String uname, String pass , String typee){
        String query = "UPDATE usera SET name =? ,uname=?,pass=?,typee=? WHERE id = ?";

        Usera findUser =Usera.getFetch(uname);
        if (findUser != null && findUser.getId() != id){
            Helper.ShowMsg("Bu kullanıcı adı zaten mevcut !! Lütfen başka bir kullanıcı adı giriniz. ");
            return false;
        }

        try {
            PreparedStatement pr =DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,name);
            pr.setString(2,uname);
            pr.setString(3,pass);
            pr.setString(4,typee);
            pr.setInt(5,id);
            return pr.executeUpdate() != -1;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public static ArrayList<Usera> searchUserList(String query){
        ArrayList<Usera>userList =new ArrayList<>();
        Usera obj ;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                obj=new Usera();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setUname(rs.getString("uname"));
                obj.setPass(rs.getString("pass"));
                obj.setTypee(rs.getString("typee"));
                userList.add(obj);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }
    public static String searchQuery(String name , String uname ,String typee){
        String query="SELECT * FROM usera WHERE uname LIKE '%{{uname}}%'AND name LIKE '%{{name}}%' AND typee LIKE '%{{typee}}%' ";
        query =query.replace("{{uname}}",uname);
        query =query.replace("{{name}}",name);
      if (!typee.isEmpty() ){
          query =query.replace("{{typee}}",typee);
      }
       return query;
    }
    public static ArrayList<Usera> getListOnlyEducator(){
        ArrayList<Usera>userList =new ArrayList<>();
        String query =" Select * From usera WHERE typee='educator'";
        Usera obj ;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                obj=new Usera();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setUname(rs.getString("uname"));
                obj.setPass(rs.getString("pass"));
                obj.setTypee(rs.getString("typee"));
                userList.add(obj);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

    public static String unameChack(){
        String a = uname;
        return a;
    }
}
