package com.example.springboot;

import com.example.springboot.jdbc.JdbcUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JdbcUtilTest {

    @Test
    public void test1() {
        try {
            Connection conn = JdbcUtils.getConnection();
            String sql = "select * from user where name like ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + "霸" + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                System.out.println(name + "--" + email);
            }
            JdbcUtils.close(rs, ps, conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        try {
            Connection conn = JdbcUtils.getConnection();
            String sql = "delete from user where manager_id is null ";
            PreparedStatement ps = conn.prepareStatement(sql);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("删除成功，影响行数：" + rows);
            }
            JdbcUtils.close(null, ps, conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3() {
        try {
            Connection conn = JdbcUtils.getConnection();
            String sql = "update user set email = ?, update_time = ? where name like ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,"123@qq.com");
            ps.setString(2,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            ps.setString(3,"%雨%");
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("更新成功，影响行数：" + rows);
            }
            JdbcUtils.close(null, ps, conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
