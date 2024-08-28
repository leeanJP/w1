package org.zerock.w1.todo.dao;

import lombok.Cleanup;
import org.zerock.w1.todo.domain.MemberVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLData;

public class MemberDAO {

    public MemberVO getWithPassword(String mid, String mpw) throws Exception{

        String sql = "select mid, mpw, mname from tbl_member" +
                " where mid = ? and mpw = ?";

        MemberVO memberVO = null;

        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, mid);
        pstmt.setString(2, mpw);

        @Cleanup ResultSet rs = pstmt.executeQuery();

        rs.next();

        memberVO = MemberVO.builder()
                .mid(rs.getString("mid"))
                .mpw(rs.getString("mpw"))
                .mname(rs.getString("mname"))
                .build();

        return memberVO;
    }

    public void updateUUID(String mid, String uuid) throws  Exception{
        String sql = "update tbl_member set uuid = ? where mid = ?";

        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, uuid);
        pstmt.setString(2, mid);

        pstmt.executeUpdate();

    }

    public MemberVO selectUUID(String uuid) throws  Exception{
        String sql = "select mid, mpw, mname, uuid from tbl_member where uuid = ? ";

        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, uuid);

        @Cleanup ResultSet rs = pstmt.executeQuery();

        rs.next();
        MemberVO memberVO = MemberVO.builder()
                .mid(rs.getString("mid"))
                .mpw(rs.getString("mpw"))
                .mname(rs.getString("mname"))
                .uuid(rs.getString("uuid"))
                .build();

        return memberVO;
    }


}
