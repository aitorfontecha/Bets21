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
    public void test1notinDB() {
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

            assertEquals(false, aurkituta);
        } catch (Exception e) {
            e.printStackTrace();
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

            Boolean aurkituta= sut.storeGuest(null, pass1, pass2, jaiotzeData, kredituTxartela);
            assertEquals(false, aurkituta);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3() {
        try {
            String username = "test3";
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date jaiotzeData=null;;
            try {
                jaiotzeData = sdf.parse("17/07/2000");
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            String kredituTxartela = "0123456789012345";

            Boolean aurkituta=sut.storeGuest(username, null, null, jaiotzeData, kredituTxartela);
            assertEquals(false, aurkituta);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void test4() {
        try {
            String username = "test4";
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date jaiotzeData=null;;
            try {
                jaiotzeData = sdf.parse("17/07/2000");
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            String kredituTxartela = "0123456789012345";

           Boolean aurkituta = sut.storeGuest(username, "1234", "4321", jaiotzeData, kredituTxartela);
           assertEquals(false, aurkituta);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test5() {
        try {
            String username = "test5";
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

            Boolean aurkituta = sut.storeGuest(username, pass1, pass2, jaiotzeData, kredituTxartela);
            assertEquals(false, aurkituta);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test6() {
        try {
            String username = "test6";
            String pass1 = "1234";
            String pass2 = "1234";
            Boolean aurkituta = sut.storeGuest(username, pass1, pass2, null, "0123456789012345");
            assertEquals(false, aurkituta);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void test7() {
        try {
            String username = "test7";
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date jaiotzeData=null;;
            try {
                jaiotzeData = sdf.parse("17/07/2000");
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Boolean aurkituta = sut.storeGuest(username, "1234", "1234", jaiotzeData, null);
            assertEquals(false, aurkituta);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}