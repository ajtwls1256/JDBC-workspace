package org.kh.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.kh.model.vo.Member;

public class MemberDao
{
    
    public ArrayList<Member> printAll()
    {
        
        // 조회 결과를 담을 list객체 생성
        ArrayList<Member> list = new ArrayList<Member>();
        
        // DBMS연결 객체
        Connection conn = null;
        // SQL구문 사용 객체
        Statement stmt = null;
        // 전송할 쿼리문
        String query = "select * from member"; // 전송할 쿼리는 자동으로 ; 를 넣어주기때문에 넣으면 오류남
        // SELECT 결과 저장 객체
        ResultSet rset = null;
        
        try
        {
            // 1. 사용할 DB에 대한 드라이버 등록(클래스 등록)
            Class.forName("oracle.jdbc.driver.OracleDriver"); // oracle.jdbc.driver = 패키지명,
                                                              // OracleDriver = 클래스명
            
            // 2. 등록된 클래스를 이용해서 DB와 연결 -> 접속 실패 시 null
            conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.40.11:1521:xe","jdbc", "1234");
            // "jdbc:dbms명:접속방식:@호스트명(내컴퓨터):포트번호:무료버전", "계정이름", "비밀번호"
            
            // 3. 쿼리문을 실행할 Statement 객체를 생성
            stmt = conn.createStatement();
            
            // 4. 쿼리문 전송하고 실행결과 받기
            rset = stmt.executeQuery(query);
            
            while (rset.next()) // 아래 행으로 넘어가고 값이 있으면 true, 없으면 false반환
            {
                String memberId = rset.getString("member_id");
                String memberPw = rset.getString("member_pw");
                String memberName = rset.getString("member_name");
                String email = rset.getString("email");
                int age = rset.getInt("age");
                String addr = rset.getString("addr");
                String gender = rset.getString("gender");
                Date enrollDate = rset.getDate("enroll_date");
                Member m = new Member(memberId, memberPw, memberName, email, age, addr, gender, enrollDate);
                list.add(m);
            }
            
        }
        catch (ClassNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally
        {
            try
            {
                // 사용 순서 역순
                rset.close();
                stmt.close();
                conn.close();
            }
            catch (SQLException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        }
        
        return list;
    }
    
    
    public Member searchId(String searchId)
    {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rset = null;
        Member m = null;
        String query = "select * from member where member_id='" + searchId+ "'";  // member_id가 문자형이라 '' 붙여야함.
        
        try
        {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "jdbc", "1234");
            stmt = conn.createStatement();
            rset = stmt.executeQuery(query);
            
            if(rset.next())
            {
                m = new Member();
                m.setMemberId(rset.getString("member_id"));
                m.setMemberPw(rset.getString("member_pw"));
                m.setMemberName(rset.getString("member_name"));
                m.setAddr(rset.getString("addr"));
                m.setAge(rset.getInt("age"));
                m.setEmail(rset.getString("email"));
                m.setEnrollDate(rset.getDate("enroll_date"));
                m.setGender(rset.getString("gender"));
                
            }
        }
        catch (ClassNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally
        {
            try
            {
                rset.close();
                stmt.close();
                conn.close();
            }
            catch (SQLException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
        
        return m;
    }
    
    
    public ArrayList<Member> searchName(String searchName)
    {
        
   
        ArrayList<Member> list = new ArrayList<Member>();
        
        Connection conn = null;
        Statement stmt = null;
        String query = "select * from member where member_name like '%" + searchName + "%'"; 
        ResultSet rset = null;
        
        try
        {
            
            Class.forName("oracle.jdbc.driver.OracleDriver"); 
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","jdbc", "1234");
            stmt = conn.createStatement();
            rset = stmt.executeQuery(query);
            
            while (rset.next()) 
            {
                String memberId = rset.getString("member_id");
                String memberPw = rset.getString("member_pw");
                String memberName = rset.getString("member_name");
                String email = rset.getString("email");
                int age = rset.getInt("age");
                String addr = rset.getString("addr");
                String gender = rset.getString("gender");
                Date enrollDate = rset.getDate("enroll_date");
                Member m = new Member(memberId, memberPw, memberName, email, age, addr, gender, enrollDate);
                list.add(m);
            }
            
        }
        catch (ClassNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally
        {
            try
            {
                rset.close();
                stmt.close();
                conn.close();
            }
            catch (SQLException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        }
        
        return list;
    }
    
    
    
    public int insertMember(Member m)
    {
        int result = 0; // DML 은 결과로 n개행이 추가/변경/삭제 되었다고 뜨는데 저 n이 반환됨.
        Connection conn = null;
        Statement stmt = null;
   
        
        String query = "insert into member values("
                + "'" + m.getMemberId() + "',"
                + "'" + m.getMemberPw() + "',"
                + "'" + m.getMemberName() + "',"
                + "'" + m.getEmail() + "',"
                + m.getAge() + ","
                + "'" + m.getAddr() + "',"
                + "'" + m.getGender() + "',"
                + "sysdate)";
        
        try
        {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "jdbc", "1234");
            stmt = conn.createStatement();
            result = stmt.executeUpdate(query);                 // DML는 excuteUpdate()를 이용하고 행 개수 반환
            
            
            // 결과에 따라 반드시 커밋, 롤백 처리를 해야함.
            if(result > 0)
            {
                conn.commit();                  // 커넥션 객체에서 실행
            }
            else
            {
                conn.rollback();
            }
            
        }
        catch (ClassNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally
        {
            try
            {
                stmt.close();
                conn.close();
            }
            catch (SQLException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return result;
    }
    
    

    
    public int updateMember(Member m)
    {
        int result = 0; // DML 은 결과로 n개행이 추가/변경/삭제 되었다고 뜨는데 저 n이 반환됨.
        Connection conn = null;
        Statement stmt = null;
   
        
        String query = "update member set "
               + "email = '" + m.getEmail() + "', "
               + "addr = '" + m.getAddr() + "', "
               + "gender = '" + m.getGender() + "' "
               + "where member_id = '" + m.getMemberId() + "'";
        
        try
        {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "jdbc", "1234");
            stmt = conn.createStatement();
            result = stmt.executeUpdate(query);                 // DML는 excuteUpdate()를 이용하고 행 개수 반환
            
            
            // 결과에 따라 반드시 커밋, 롤백 처리를 해야함.
            if(result > 0)
            {
                conn.commit();                  // 커넥션 객체에서 실행
            }
            else
            {
                conn.rollback();
            }
            
        }
        catch (ClassNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally
        {
            try
            {
                stmt.close();
                conn.close();
            }
            catch (SQLException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return result;
    }
    
    
    
    public int deleteMember(String memberId)
    {
        int result = 0; // DML 은 결과로 n개행이 추가/변경/삭제 되었다고 뜨는데 저 n이 반환됨.
        Connection conn = null;
        Statement stmt = null;
   
        
        String query = "delete from member "
               + "where member_id = '" + memberId + "'";
        
        try
        {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "jdbc", "1234");
            stmt = conn.createStatement();
            result = stmt.executeUpdate(query);                 // DML는 excuteUpdate()를 이용하고 행 개수 반환
            
            
            // 결과에 따라 반드시 커밋, 롤백 처리를 해야함.
            if(result > 0)
            {
                conn.commit();                  // 커넥션 객체에서 실행
            }
            else
            {
                conn.rollback();
            }
            
        }
        catch (ClassNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally
        {
            try
            {
                stmt.close();
                conn.close();
            }
            catch (SQLException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return result;
    }
    
    
    
    
}
