import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import configuration.UtilDate;
import domain.*;
import org.junit.Before;
import org.junit.Test;

import dataAccess.DataAccess;
import test.dataAccess.TestDataAccess;

import javax.rmi.CORBA.Util;

public class emaitzaIpiniDAWTest {

    //sut:system under test
    DataAccess sut = new DataAccess(true);

    //additional operations needed to execute the test
    TestDataAccess testDA = new TestDataAccess();


    private Event ev;
    private Question qu;
    private Pronostikoa pr;
    private Apostua ap;
    private Bezero per1;
    private Bezero per2;


    @Test
    public void test1() {
        Question q = null;
        Pronostikoa p = null;

        ev = testDA.addEventWithQuestion("E", new Date(), "Q", 0);
        try {
            q = ev.getQuestions().firstElement();
            p = new Pronostikoa("D", 0.0);
            p = sut.pronostikoaIpini(q, p);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertTrue(sut.emaitzaIpini(q, p));
    }
    @Test
    public void test2() {
        Question q = null;
        Pronostikoa p1 = null;
        Pronostikoa p2 = null;
        ArrayList<Pronostikoa> pronostikoak = new ArrayList<>();
        try {
            sut.storeGuest("Proba1", "Proba1", "Proba1",
                    UtilDate.newDate(2001, 01,31), "1234567890123456");
            per1 = sut.getPertsona("Proba1");
        } catch (Exception e) {
            e.getStackTrace();
        }
        ev = testDA.addEventWithQuestion("E", new Date(), "Q", 0);
        q = ev.getQuestions().firstElement();
        try {
            p1 = new Pronostikoa("A", 0.0);
            p2 = new Pronostikoa("B", 0.0);
            p1 = sut.pronostikoaIpini(q, p1);
            p2 = sut.pronostikoaIpini(q, p2);
            pronostikoak.add(p1);
            pronostikoak.add(p2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        sut.apostuAnitzaEgin(pronostikoak, 0.0, "Proba1");
        assertTrue(sut.emaitzaIpini(q, p1));
    }

    @Test
    public void test3() {
        Question q = null;
        Pronostikoa p1 = null;
        per1 = null;
        ArrayList<Pronostikoa> pronostikoak = new ArrayList<>();

        //Erabiltzaileak sortu
        try {
            sut.storeGuest("Proba1", "Proba1", "Proba1",
                    UtilDate.newDate(2001, 1,31), "1234567890123456");
            per1 = sut.getPertsona("Proba1");
        } catch (Exception e) {
            e.getStackTrace();
        }

        //Galderak sortu
        ev = testDA.addEventWithQuestion("E", new Date(), "Q", 0);
        q = ev.getQuestions().firstElement();

        //Pronostikoak sortu
        try {
            p1 = new Pronostikoa("A", 0.0);
            p1 = sut.pronostikoaIpini(q, p1);
            pronostikoak.add(p1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Apustuak sortu (mugimendu bat sortzen da)
        sut.apostuAnitzaEgin(pronostikoak, 0.0, "Proba1");
        assertTrue(sut.emaitzaIpini(q, p1));
    }

    @Test
    public void test4(){
        Question q = null;
        Pronostikoa p1 = null;
        per1 = null;
        ArrayList<Pronostikoa> pronostikoak = new ArrayList<>();

        //Erabiltzaileak sortu
        try {
            sut.storeGuest("Proba1", "Proba1", "Proba1",
                    UtilDate.newDate(2001, 1,31), "1234567890123456");
            sut.storeGuest("Proba2", "Proba2", "Proba2",
                    UtilDate.newDate(1996, 10, 9), "6543210987654321");
            per1 = sut.getPertsona("Proba1");
            per2 = sut.getPertsona("Proba2");
        } catch (Exception e) {
            e.getStackTrace();
        }

        //Galderak sortu
        ev = testDA.addEventWithQuestion("E", new Date(), "Q", 0);
        q = ev.getQuestions().firstElement();

        //Pronostikoak sortu
        try {
            p1 = new Pronostikoa("A", 0.0);
            p1 = sut.pronostikoaIpini(q, p1);
            pronostikoak.add(p1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Apustuak sortu (mugimendu bat sortzen da)
        sut.apostuAnitzaEgin(pronostikoak, 0.0, "Proba1");
        sut.kopiatu(per1, per2);
        assertTrue(sut.emaitzaIpini(q, p1));
    }

}