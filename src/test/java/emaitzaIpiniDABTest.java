//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//import static org.junit.Assert.fail;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import dataAccess.DataAccess;
//import domain.Apostua;
//import domain.Event;
//import domain.Pronostikoa;
//import domain.Question;
//import test.dataAccess.TestDataAccess;
//public class emaitzaIpiniDABTest{
//    private Pronostikoa pr;
//    private Event ev;
//    private Question qu;
//
//    //sut:system under test
//    DataAccess sut=new DataAccess(true);
//
//    //additional operations needed to execute the test
//    TestDataAccess testDA = new TestDataAccess();
//
//    @Test
//    public void test1() {
//        try {
//            ev = new Event(1,"proba ebentua", new Date());
//            qu = new Question(1,"proba galdera",1,ev);
//            pr = new Pronostikoa("Pronostikoa1",2);
//            qu.pronostikoaGehitu(pr);
//            testDA.open();
//            testDA.addEventWithQuestion("proba ebentua", new Date(), qu.getQuestion(), 1);
//            testDA.close();
//            sut.emaitzaIpini(qu, pr);
//            assertTrue(true);
//        }catch(Exception e) {
//            e.printStackTrace();
//        } finally {
//            testDA.open();
//            testDA.removeEvent(ev);
//            testDA.close();
//        }
//    }
//
//    @Test
//    public void test2() {
//        try {
//            sut.emaitzaIpini(null, null);
//            fail();
//        }catch(Exception e) {
//            assertTrue(true);
//        }
//    }
//
//    @Test
//    public void test3() {
//        try {
//            sut.emaitzaIpini(qu, pr);
//            fail();
//        }catch(Exception e) {
//            assertTrue(true);
//        }
//    }
//
//}