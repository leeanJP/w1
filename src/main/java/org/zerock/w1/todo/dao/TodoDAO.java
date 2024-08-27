package org.zerock.w1.todo.dao;

import lombok.Cleanup;
import org.zerock.w1.todo.domain.TodoVO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TodoDAO {

    public void insert(TodoVO vo) throws Exception{
        String sql = "insert into tbl_todo(title, dueDate, finished) " +
                "values (?,?,?)";

        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, vo.getTitle());
        pstmt.setDate(2, Date.valueOf(vo.getDueDate()));
        pstmt.setBoolean(3, vo.isFinished());

        pstmt.executeUpdate();


    }

    public List<TodoVO> selectAll() throws Exception{
        String sql = "select * from tbl_todo";
        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
        @Cleanup ResultSet rs = pstmt.executeQuery();

        List<TodoVO> list = new ArrayList<>();

        while (rs.next()){
            TodoVO vo = TodoVO.builder()
                    .tno(rs.getLong("tno"))
                    .title(rs.getString("title"))
                    .dueDate(rs.getDate("dueDate").toLocalDate())
                    .finished(rs.getBoolean("finished"))
                    .build();
            list.add(vo);
        }
        return list;

    }

    public TodoVO selectOne(Long tno) throws Exception{
        String sql = "select * from tbl_todo where tno = ?";

        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setLong(1,tno);

        @Cleanup ResultSet rs = pstmt.executeQuery();

        rs.next();

        TodoVO vo = TodoVO.builder()
                .tno(rs.getLong("tno"))
                .title(rs.getString("title"))
                .dueDate(rs.getDate("dueDate").toLocalDate())
                .finished(rs.getBoolean("finished"))
                .build();

        return vo;
    }

    public void updateOne (TodoVO todoVO) throws Exception{
        String sql = "update tbl_todo set title =? , dueDate =? , finished = ?" +
                " where tno = ?";
        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1,todoVO.getTitle());
        pstmt.setDate(2, Date.valueOf(todoVO.getDueDate()));
        pstmt.setBoolean(3, todoVO.isFinished());
        pstmt.setLong(4, todoVO.getTno());

        pstmt.executeUpdate();


    }

    public void deleteOne(Long tno) throws Exception{
        String sql = "delete from tbl_todo where tno = ?";
        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setLong(1,tno);
        pstmt.executeUpdate();
    }


    public String getTime(){

        String now = null;
        //try - with - resources
        //괄호안에서 쓰인 자원을 자동으로 반납해주는 문법
        try(Connection conn = ConnectionUtil.INSTANCE.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("select now()");
            ResultSet rs = pstmt.executeQuery();
            ){
            rs.next();
            now = rs.getString(1);

        }catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    public String getTime2() throws Exception{
        //@Cleanup
        //@Cleanup이 추가된 변수는 해당 메소드가 끝날 때 close()가 호출되는 것을 보장한다.

        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement("select now()");
        @Cleanup ResultSet rs = pstmt.executeQuery();

        rs.next();

        String now = rs.getString(1);

        return now;
    }




}
