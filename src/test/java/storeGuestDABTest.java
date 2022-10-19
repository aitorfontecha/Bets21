import dataAccess.DataAccess;
import org.junit.Test;
import test.dataAccess.TestDataAccess;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class storeGuestDABTest {
    //sut:system under test
    static DataAccess sut= new DataAccess(true);

    //additional operations needed to execute the test
    static TestDataAccess testDA=new TestDataAccess();
    @Test
    public void test1() {
        try {
            String username = "Aitor";
            String pass1 = "1234";
            String pass2 = "1234";
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date jaiotzeData=null;;
            try {
                jaiotzeData = sdf.parse("17/07/2000");
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            String kredituTxartela = "0123456789012345";

            testDA.open();
            Boolean aurkituta = sut.storeGuest(username, pass1, pass2, jaiotzeData, kredituTxartela);
            testDA.close();

            assertEquals(true, aurkituta);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void test2() {
        try {
            String pass1 = "1234";
            String pass2 = "1234";
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date jaiotzeData=null;;
            try {
                jaiotzeData = sdf.parse("17/07/2000");
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            String kredituTxartela = "0123456789012345";

           sut.storeGuest(null, pass1, pass2, jaiotzeData, kredituTxartela);
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    @Test
    public void test3() {
        try {
            String username = "Aitor";
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date jaiotzeData=null;;
            try {
                jaiotzeData = sdf.parse("17/07/2000");
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            String kredituTxartela = "0123456789012345";

            sut.storeGuest(username, "123", "321", jaiotzeData, kredituTxartela);
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    @Test
    public void test4() {
        try {
            String username = "Aitor";
            String pass1 = "1234";
            String pass2 = "1234";
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date jaiotzeData=null;;
            try {
                jaiotzeData = sdf.parse("17/07/2005");
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            String kredituTxartela = "0123456789012345";

            sut.storeGuest(username, pass1, pass2, jaiotzeData, kredituTxartela);
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    @Test
    public void test5() {
        try {
            String username = "Aitor";
            String pass1 = "1234";
            String pass2 = "1234";
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date jaiotzeData=null;;
            try {
                jaiotzeData = sdf.parse("17/07/2000");
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            sut.storeGuest(username, pass1, pass2, jaiotzeData, "012345678901234");
        } catch (Exception e) {
            assertTrue(true);
        }
    }
}