package com.example.demo.entity;

public class ChangePass {
    private String oldpassword;
    private String newpassword;
    private String reenter;

    public ChangePass() {
    }

    public ChangePass(String oldpassword, String newpassword, String reenter) {
        this.oldpassword = oldpassword;
        this.newpassword = newpassword;
        this.reenter = reenter;
    }

    public String getOldpassword() {
        return oldpassword;
    }

    public void setOldpassword(String oldpassword) {
        this.oldpassword = oldpassword;
    }

    public String getNewpassword() {
        return newpassword;
    }

    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }

    public String getReenter() {
        return reenter;
    }

    public void setReenter(String reenter) {
        this.reenter = reenter;
    }
}
