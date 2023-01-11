package com.PatikaDev.Model;

import com.PatikaDev.Helper.DBConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Content {
    int id;
     String name;
     String explanation;
     String yLink;
     String quizquestion;
     int course_id;

   public Content(){}

    public Content(int id,String name, String explanation, String yLink, String quizquestion, int course_id) {
       this.id=id;
        this.name = name;
        this.explanation = explanation;
        this.yLink = yLink;
        this.quizquestion = quizquestion;
        this.course_id = course_id;
    }

    public static ArrayList<Content> getList (int courseId){
        ArrayList<Content> contentList =new ArrayList<>();
        Content obj ;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs =st.executeQuery("SELECT *FROM content WHERE course_id ="+courseId);
            while(rs.next()){
                int id =rs.getInt("id");
                String name =rs.getString("name");
                String explanation =rs.getString("explanation");
                String yLink =rs.getString("yLink");
                String quizquestion =rs.getString("quizquestion");
                int course_id =rs.getInt("course_id");
                obj=new Content(id,name,explanation,yLink,quizquestion,course_id);
                contentList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return contentList;
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

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getyLink() {
        return yLink;
    }

    public void setyLink(String yLink) {
        this.yLink = yLink;
    }

    public String getQuizquestion() {
        return quizquestion;
    }

    public void setQuizquestion(String quizquestion) {
        this.quizquestion = quizquestion;
    }

    public int getcourse_id() {
        return course_id;
    }

    public void setcourse_id(int course_id) {
        this.course_id = course_id;
    }
}
