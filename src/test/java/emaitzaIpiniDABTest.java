import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import configuration.UtilDate;
import domain.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dataAccess.DataAccess;
import test.dataAccess.TestDataAccess;
import domain.Bezero;
public class emaitzaIpiniDABTest{
    private Pronostikoa pr;
    private Event ev;
    private Question qu;

    //sut:system under test
    DataAccess sut=new DataAccess(true);

    //additional operations needed to execute the test
    TestDataAccess testDA = new TestDataAccess();

    Pertsona per1;
    Pertsona per2;

    Event event;


    @Before
    public void ireki(){
        sut.open(false);
        testDA.open();
    }

    @After
    public void itxi(){
        sut.close();
        testDA.close();
    }

    @Test
    public void test1() {
        Question q = null;
        Pronostikoa p1 = null;
        Bezero per1 = null;
        ArrayList<Pronostikoa> pronostikoak1 = new ArrayList<>();
        ArrayList<Pronostikoa> pronostikoak2 = new ArrayList<>();

        //Erabiltzaileak sortu
        try {
            sut.storeGuest("Proba1","Proba1", "Proba1",
                    UtilDate.newDate(2001, 01, 31), "1234567890123456");
            per1 = (Bezero)sut.getBezeroak().get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Galderak sortu
        event = testDA.addEventWithQuestion("E", new Date(), "Q", 0);
        q = event.getQuestions().get(0);

        //Pronostikoak sortu
        try {
            p1 = new Pronostikoa("A", 0.0);
            sut.pronostikoaIpini(q, p1);
            pronostikoak1.add(p1);
        } catch (Exception e) {
            e.printStackTrace();
        }


        //Apustuak sortu
        sut.apostuAnitzaEgin(pronostikoak1, 0.0, "Proba1");
        assertTrue(sut.emaitzaIpini(q, p1));
        assertEquals(2, per1.getMugimenduak().size()); //TODO assert-ak hobetu mugimenduaren hasierako string-a konparatzeko eta ez mugimendu kantitatea
    }

    @Test
    public void test2() {
        try {
            sut.emaitzaIpini(null, null);
            fail();
        }catch(Exception e) {
            assertTrue(true);
        }
    }

    @Test
    public void test3() {
        try {
            sut.emaitzaIpini(qu, pr);
            fail();
        }catch(Exception e) {
            assertTrue(true);
        }
    }

}