import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import dataAccess.DataAccess;
import domain.Apostua;
import domain.Event;
import domain.Pronostikoa;
import domain.Question;
import test.dataAccess.TestDataAccess;

public class emaitzaIpiniDAWTest {

    //sut:system under test
    DataAccess sut = new DataAccess(true);

    //additional operations needed to execute the test
    TestDataAccess testDA = new TestDataAccess();


    private Event ev;
    private Question qu;
    private Pronostikoa pr;
    private Apostua ap;

    @Before
    public void init() {

    }

    @Test
    public void test1() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date oneDate=null;
        try {
            oneDate = sdf.parse("05/10/2022");
            testDA.open();
            ev = testDA.addEventWithQuestion("Proba ebentua", oneDate, "Proba galdera",(float)1.0);
            qu = ev.getQuestions().get(0);
            testDA.addPronostikoa(pr);
            testDA.close();
        }catch(ParseException e) {
            e.printStackTrace();
        }
        try {
            boolean emaitza = sut.emaitzaIpini(qu, pr);
            int esperotakoEmaitza = 0;
            assertTrue(emaitza);
        }catch(Exception e) {
            e.printStackTrace();
        }

    }
    @Test
    public void test2() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date oneDate=null;
        try {
            oneDate = sdf.parse("05/10/2022");
            testDA.open();
            ev = testDA.addEventWithQuestion("Proba ebentua", oneDate, "Proba galdera",(float)1.0);
            qu = ev.getQuestions().get(0);
            pr = testDA.addPronostikoaWithApustuak();
            testDA.close();
        }catch(ParseException e) {
            e.printStackTrace();
        }
        try {
            boolean emaitza = sut.emaitzaIpini(qu, pr);
            assertTrue(emaitza);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date oneDate=null;
        try {
            oneDate = sdf.parse("05/10/2022");
            testDA.open();
            ev = testDA.addEventWithQuestion("Proba ebentua", oneDate, "Proba galdera",(float)1.0);
            qu = ev.getQuestions().get(0);
            pr = testDA.addPronostikoaWithApustuak();
            testDA.close();
        }catch(ParseException e) {
            e.printStackTrace();
        }
        try {
            ap = pr.getApostuak().get(0);
            boolean emaitza = sut.emaitzaIpini(qu, pr);
            assertTrue(emaitza);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

}