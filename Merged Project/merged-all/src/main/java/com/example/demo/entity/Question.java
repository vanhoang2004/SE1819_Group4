package com.example.demo.entity;

import jakarta.persistence.*;

import java.awt.*;
import java.util.Map;

@Entity
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "questionid")
    private Integer questionid;

    @Column(name = "questiontitle")
    private String questiontitle;

    @Column(name = "image")
    private String image;

    @Column(name = "option1")
    private String option1;

    @Column(name = "option2")
    private String option2;

    @Column(name = "option3")
    private String option3;

    @Column(name = "option4")
    private String option4;

    @Column(name = "answer")
    private String answer;

    @Column(name = "subjectid")
    private Integer subjectid;

    @Column(name = "chapterid")
    private Integer chapterid;

    @Column(name = "levelid")
    private Integer levelid;

    @Column(name = "status")
    private Integer status;

    public Question() {
    }


    public Question(Integer questionid, String questiontitle, String image, String option1, String option2, String option3, String option4, String answer, Integer subjectid, Integer chapterid, Integer levelid, Integer status) {
        this.questionid = questionid;
        this.questiontitle = questiontitle;
        this.image = image;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.answer = answer;
        this.subjectid = subjectid;
        this.chapterid = chapterid;
        this.levelid = levelid;
        this.status = status;
    }

    public Integer getQuestionid() {
        return questionid;
    }

    public void setQuestionid(Integer questionid) {
        this.questionid = questionid;
    }

    public String getQuestiontitle() {
        return questiontitle;
    }

    public void setQuestiontitle(String questiontitle) {
        this.questiontitle = questiontitle;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getSubjectid() {
        return subjectid;
    }

    public void setSubjectid(Integer subjectid) {
        this.subjectid = subjectid;
    }

    public Integer getChapterid() {
        return chapterid;
    }

    public void setChapterid(Integer chapterid) {
        this.chapterid = chapterid;
    }

    public Integer getLevelid() {
        return levelid;
    }

    public void setLevelid(Integer levelid) {
        this.levelid = levelid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    public static Question fromMap(Map<String, String> data) {
        Question question = new Question();

        // Kiểm tra và gán các giá trị từ Map
        if (data.containsKey("questiontitle")) {
            question.setQuestiontitle(data.get("questiontitle"));
        }
        if (data.containsKey("image")) {
            question.setImage(data.get("image"));
        }
        if (data.containsKey("option1")) {
            question.setOption1(data.get("option1"));
        }
        if (data.containsKey("option2")) {
            question.setOption2(data.get("option2"));
        }
        if (data.containsKey("option3")) {
            question.setOption3(data.get("option3"));
        }
        if (data.containsKey("option4")) {
            question.setOption4(data.get("option4"));
        }
        if (data.containsKey("answer")) {
            question.setAnswer(data.get("answer"));
        }
        if (data.containsKey("subjectid")) {
            question.setSubjectid(parseIntSafely(data.get("subjectid")));
        }
        if (data.containsKey("chapterid")) {
            question.setChapterid(parseIntSafely(data.get("chapterid")));
        }
        if (data.containsKey("levelid")) {
            question.setLevelid(parseIntSafely(data.get("levelid")));
        }
        if (data.containsKey("status")) {
            question.setStatus(parseIntSafely(data.get("status")));
        }

        return question;
    }

    // Phương thức tiện ích để chuyển đổi chuỗi thành số nguyên một cách an toàn
    private static Integer parseIntSafely(String value) {
        try {
            return (int) Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return null; // Hoặc xử lý lỗi theo cách khác nếu cần thiết
        }
    }
    @Transient
    public String getQuesImagePath(){
        if(image==null||questionid==null ) return null;
        return "/questionbank/"+questionid+"/"+image;
    }
}

