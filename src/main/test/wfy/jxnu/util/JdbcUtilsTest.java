package wfy.jxnu.util;

import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class JdbcUtilsTest {

    @Test
    public void getConn() {
        Connection connection=null;
        try {
            connection=JdbcUtils.getConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        assertNotNull(connection);

    }
}