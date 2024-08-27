package org.zerock.w1.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.zerock.w1.todo.domain.Person;
import org.zerock.w1.todo.domain.TodoVO;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionTest {

    @Test
    public void connectionTest() throws Exception{
        Class.forName("org.mariadb.jdbc.Driver");

        Connection conn = DriverManager.getConnection(
                "jdbc:mariadb://localhost:3306/webdb",
                "webuser",
                "bitc5600"
        );

        Assertions.assertNotNull(conn);

        conn.close();

    }

    @Test
    public void personTest() throws Exception{

        Person person = new Person.PersonBuilder("John" , "Doe")
                .withAge(30)
                .withAddr("busan")
                .withPhoneNumber("123-4567")
                .build();

        System.out.println(person);
    }


    @Test
    public void testHikariCP() throws Exception{
        HikariConfig config = new HikariConfig();

        config.setDriverClassName("org.mariadb.jdbc.Driver");
        config.setJdbcUrl("jdbc:mariadb://localhost:3306/webdb");
        config.setUsername("webuser");
        config.setPassword("bitc5600");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        HikariDataSource ds = new HikariDataSource(config);
        Connection conn = ds.getConnection();

        System.out.println("conn : "+conn);

        conn.close();

    }
}
