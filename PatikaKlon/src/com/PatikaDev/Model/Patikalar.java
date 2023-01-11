package com.PatikaDev.Model;

import com.PatikaDev.Helper.DBConnector;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Patikalar {
    private int id;
    private String name;

    public Patikalar(){}

    public Patikalar(int id, String name) {
        this.id = id;
        this.name = name;
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
    public static ArrayList<Patikalar> getList() {
        ArrayList<Patikalar> patikaList =new ArrayList<>();
        Patikalar obj;

        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM patikalar");
        while (rs.next()){
            obj=new Patikalar(rs.getInt("id"),rs.getString("name"));
            patikaList.add(obj);
        }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return patikaList;
    }
    public static boolean add(String name){
        String query ="INSERT INTO patikalar (name) VALUES (?)";

        try{
            PreparedStatement pr =DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, name);
            return pr.executeUpdate() != -1;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return true;
    }

    public static boolean update(int id,String name){
        String query= "UPDATE patikalar SET name =? WHERE id =?";
        try {
            PreparedStatement pr =DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,name);
            pr.setInt(2,id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }


    public  static Patikalar getFetch(int id) {
        Patikalar obj = null;
        String query = "SELECT  * FROM patikalar  WHERE id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                obj = new Patikalar();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
            }
        }catch (Exception e){
           e.getStackTrace();
        }
        return  obj;
    }
      public static boolean delete (int id){

        String query = "DELETE FROM patikalar WHERE id =?";

          ArrayList<Course> courseList =Course.getListByPatikalar(id);
          for (Course c : courseList){
              Course.delete(c.getId());
          }
          try {
              PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
                   pr.setInt(1,id);
                   return pr.executeUpdate() != -1 ;
          } catch (SQLException e) {
              System.out.println(e.getMessage());
          }
           return true;
      }

}
