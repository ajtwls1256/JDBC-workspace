package org.kh.controller;

import java.util.ArrayList;

import org.kh.model.dao.MemberDao;
import org.kh.model.vo.Member;
import org.kh.view.MemberView;

public class MemberController
{
    
    MemberDao md = new MemberDao();
    MemberView mv = new MemberView();
    
    public void printAll()
    {
        ArrayList<Member> list = md.printAll();

        mv.printMsg("ID\tName\temail\t\tage\taddr\tgender\tenrollDate");
        mv.printMsg("------------------------------------------------------------------");
        
        for (Member m : list)
        {
            mv.printMsg(m.toString());
        }
    }
    
    public void searchId(String searchId)
    {
        Member m = md.searchId(searchId);
        
        if(m != null)
        {
            mv.printMsg("ID\tName\temail\t\tage\taddr\tgender\tenrollDate");
            mv.printMsg("------------------------------------------------------------------");
            mv.printMsg(m.toString());
        }
        else
            mv.printMsg("회원정보가 없습니다.");
    }
    
    public void searchName(String searchName)
    {
        ArrayList<Member> list = md.searchName(searchName);

        if(list.isEmpty())
        {
            mv.printMsg("회원정보가 없습니다.");
        }
        else
        {
            mv.printMsg("ID\tName\temail\t\tage\taddr\tgender\tenrollDate");
            mv.printMsg("------------------------------------------------------------------");
            
            for (Member m : list)
            {
                mv.printMsg(m.toString());
            }
        }
    }
    
    public void insertMember(Member m)
    {
        int result = md.insertMember(m);
        
        
        if(result > 0)
            mv.printMsg("회원가입 성공");
        else
            mv.printMsg("회원가입 실패");
    }
    
    public void updateMember(String searchId)
    {
        Member m = md.searchId(searchId);
        
        if(m == null)
        {
            mv.printMsg("회원 정보가 없습니다.");
            return;
        }

        Member updateMember = mv.updateMember();
        updateMember.setMemberId(searchId);
        int result = md.updateMember(updateMember);
        
  
        mv.printMsg("회원 정보 수정 성공");

    }
    
    
    
    public void deleteMember(String memberId)
    {
        int result = md.deleteMember(memberId);
        
        
        if(result > 0)
            mv.printMsg("회원 탈퇴 성공");
        else
            mv.printMsg("회원 정보가 없습니다.");
    }

    
    
    
    
}
