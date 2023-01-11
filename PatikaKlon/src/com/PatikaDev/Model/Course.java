package com.PatikaDev.Model;

import com.PatikaDev.Helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Course {
    private int id;
    private int usera_id;
    private int patikalar_id;
    private String name;
    private String lang;

    private Patikalar patikalar;
    private Usera usera;

    public Course(){}
    public Course(int id, int usera_id, int patikalar_id, String name, String lang) {
        this.id = id;
        this.usera_id = usera_id;
        this.patikalar_id = patikalar_id;
        this.name = name;
        this.lang = lang;
        this.patikalar = Patikalar.getFetch(patikalar_id);
        this.usera=Usera.getFetch(usera_id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsera_id() {
        return usera_id;
    }

    public void setUsera_id(int usera_id) {
        this.usera_id = usera_id;
    }

    public int getPatikalar_id() {
        return patikalar_id;
    }

    public void setPatikalar_id(int patikalar_id) {
        this.patikalar_id = patikalar_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public Patikalar getPatikalar() {
        return patikalar;
    }

    public void setPatika(Patikalar patikalar) {
        this.patikalar = patikalar;
    }

    public Usera getUser() {
        return usera;
    }

    public void setUser(Usera user) {
        this.usera = usera;
    }


    public static ArrayList<Course> getList(){
       ArrayList<Course> courseList =new ArrayList<>();
       Course obj;
         String query="SELECT * FROM course";
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs =st.executeQuery(query);
            while (rs.next()){
                obj =new Course();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setLang(rs.getString("lang"));
                obj.setPatikalar_id(rs.getInt("patikalar_id"));
                obj.setUsera_id(rs.getInt("usera_id"));
                courseList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return courseList;
    }
    public static ArrayList<Course> getListByUsera(int usera_id){
        ArrayList<Course> courseList =new ArrayList<>();
        Course obj;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs =st.executeQuery("SELECT * FROM course WHERE usera_id =" + usera_id);
            while (rs.next()){

               int id =rs.getInt("id");
               int useraID = rs.getInt("usera_id");
               int patikalar_id =rs.getInt("patikalar_id");
               String name =rs.getString("name");
               String lang =rs.getString("lang");
                obj =new Course(id,useraID,patikalar_id,name,lang);
                courseList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return courseList;
    }

    public static ArrayList<Course> getListByPatikalar(int patikalar_id){
        ArrayList<Course> courseList =new ArrayList<>();
        Course obj;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs =st.executeQuery("SELECT * FROM course WHERE patikalar_id =" + patikalar_id);
            while (rs.next()){

                int id =rs.getInt("id");
                int useraID = rs.getInt("usera_id");
                int patikalarID =rs.getInt("patikalar_id");
                String name =rs.getString("name");
                String lang =rs.getString("lang");
                obj =new Course(id,useraID,patikalarID,name,lang);
                courseList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return courseList;
    }


    public static  boolean add(int usera_id, int patikalar_id, String name, String lang){
        String query ="INSERT INTO course ( patikalar_id, usera_id ,name, lang) VALUES (?,?,?,?)";

        try{
            PreparedStatement pr =DBConnector.getInstance().prepareStatement(query);
           pr.setInt(2,usera_id);
           pr.setInt(1,patikalar_id);
           pr.setString(3, name);
           pr.setString(4,lang);
            return pr.executeUpdate() != -1;
        }catch (Exception e){
           e.printStackTrace();
        }
        return true;
    }

   public static boolean delete(int id){

       String query = "DELETE FROM course WHERE id = ? ;";
       try {
           PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
           pr.setInt(1,id);
           return pr.executeUpdate() != -1 ;

       } catch (SQLException e) {

            e.printStackTrace();
       }
        return true;
   }



}



