package org.kh.view;

import java.util.ArrayList;
import java.util.Scanner;

import org.kh.controller.MemberController;
import org.kh.model.vo.Member;

public class MemberView
{
    Scanner sc = new Scanner(System.in);
    
    
    public void mainMenu()

    {
        MemberController mc = new MemberController();
        
        while (true)
        {
            System.out.println("\n=== 회원 관리 프로그램 ===");
            System.out.println("1. 회원 정보 전체 조회");       // SELECT
            System.out.println("2. 회원 아이디 조회");          // SELECT
            System.out.println("3. 회원 이름으로 검색");        // SELECT
            System.out.println("4. 회원 가입");                 // INSERT
            System.out.println("5. 회원 정보 변경");            // UPDATE
            System.out.println("6. 회원 탈퇴");                 // DELETE
            System.out.println("0. 프로그램 종료");
            System.out.print("선택 > ");
            int choice = sc.nextInt();
            
            switch (choice)
            {
                case 1: mc.printAll();
                    break;
                case 2: mc.searchId(inputId("조회"));
                    break;
                case 3: mc.searchName(searchName());
                    break;
                case 4: mc.insertMember(insertMember());
                    break;
                case 5: 
                    // 아이디를 입력받아서 존재하는경우 변경정보 받아서 수정
                    // 이름, 이메일, 주소, 성별
                    mc.updateMember(inputId("수정"));
                    break;
                case 6: mc.deleteMember(inputId("삭제"));
                    break;
                case 0:
                    System.out.print("정말로 종료하시겠습니까?(y/n) : ");
                    String end = sc.next();
                    if (end.toUpperCase().charAt(0) == 'Y')
                    {
                        System.out.println("프로그램을 종료합니다.");
                        return;
                    }
                    
            }
            
        }
    }
    
   
    
    public String searchName()
    {
        // 이름 입력받아서 해당 이름이 있는지 조회
        // 이름이 포함되면 전부 ㄷ ㅏ 조회되어야함
        
        System.out.print("조회할 이름을 입력하세요 : ");
        String searchName = sc.next();
        
        return searchName;
    }
    
    public Member insertMember()
    {
        System.out.print("아이디 입력 : ");
        String memberId = sc.next();
        System.out.print("비밀번호 입력 : ");
        String memberPw = sc.next();
        System.out.print("이름 입력 : ");
        String memberName = sc.next();
        System.out.print("이메일 입력 : ");
        String email = sc.next();
        System.out.print("나이 입력 : ");
        int age = sc.nextInt();
        System.out.print("주소 입력 : ");
        String addr = sc.next();
        System.out.print("성별 입력 : ");
        String gender = sc.next();
  
        return new Member(memberId, memberPw, memberName, email, age, addr, gender, null);
    }
    
    // update -> 아이디를 입력받아 이름, 이메일, 주소, 성별 변경
    public Member updateMember()
    {
        Member m = new Member();
        
        System.out.print("변경할 이름 입력 : ");
        String memberName = sc.next();
        System.out.print("변경할 이메일 입력 : ");
        String email = sc.next();
        System.out.print("변경할 주소 입력 : ");
        String addr = sc.next();
        System.out.print("변경할 성별 입력 : ");
        String gender = sc.next();
        
        m.setMemberName(memberName);
        m.setEmail(email);
        m.setAddr(addr);
        m.setGender(gender);
        
        return m;
    }
    
    
    
    public String inputId(String str)
    {
        System.out.print(str + "할 아이디 입력 : ");
        String memberId = sc.next();
        
        return memberId;
    }
    
    
  
    public void printMsg(String msg)
    {
        System.out.println(msg);
    }
    
   
    
}
