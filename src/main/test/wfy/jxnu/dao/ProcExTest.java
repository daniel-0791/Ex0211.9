package wfy.jxnu.dao;

import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class ProcExTest {

    @Test
    public void callPro3() {
        ProcEx ex=new ProcEx();
        int bookNum=0;
        try {
            bookNum=ex.callPro3("工业技术");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    assertEquals(4,bookNum);

    }

    @Test
    public void callPro4() {
        ProcEx ex=new ProcEx();
        int bookNum=0;
        try {
            bookNum=ex.callPro4("工业技术");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertEquals(4,bookNum);

    }
}