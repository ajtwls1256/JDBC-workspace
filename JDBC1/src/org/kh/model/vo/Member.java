package org.kh.model.vo;

import java.sql.Date; // 자바랑 연결할 때 사용하는 라이브러리

public class Member
{
    private String memberId;
    
    private String memberPw;
    
    private String memberName;
    
    private String email;
    
    private int age;
    
    private String addr;
    
    private String gender;
    
    private Date enrollDate;
    
    public Member()
    {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public Member(String memberId, String memberPw, String memberName,
            String email, int age, String addr, String gender, Date enrollDate)
    {
        super();
        this.memberId = memberId;
        this.memberPw = memberPw;
        this.memberName = memberName;
        this.email = email;
        this.age = age;
        this.addr = addr;
        this.gender = gender;
        this.enrollDate = enrollDate;
    }
    
    public String getMemberId()
    {
        return memberId;
    }
    
    public void setMemberId(String memberId)
    {
        this.memberId = memberId;
    }
    
    public String getMemberPw()
    {
        return memberPw;
    }
    
    public void setMemberPw(String memberPw)
    {
        this.memberPw = memberPw;
    }
    
    public String getMemberName()
    {
        return memberName;
    }
    
    public void setMemberName(String memberName)
    {
        this.memberName = memberName;
    }
    
    public String getEmail()
    {
        return email;
    }
    
    public void setEmail(String email)
    {
        this.email = email;
    }
    
    public int getAge()
    {
        return age;
    }
    
    public void setAge(int age)
    {
        this.age = age;
    }
    
    public String getAddr()
    {
        return addr;
    }
    
    public void setAddr(String addr)
    {
        this.addr = addr;
    }
    
    public String getGender()
    {
        return gender;
    }
    
    public void setGender(String gender)
    {
        this.gender = gender;
    }
    
    public Date getEnrollDate()
    {
        return enrollDate;
    }
    
    public void setEnrollDate(Date enrollDate)
    {
        this.enrollDate = enrollDate;
    }
    
    public String toString()
    {
        return memberId + "\t" + memberName + "\t" + email + "\t" + age + "\t"
                + addr + "\t" + gender + "\t" + enrollDate;
    }
    
}
