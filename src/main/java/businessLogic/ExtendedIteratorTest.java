package businessLogic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import domain.Event;

public class ExtendedIteratorTest {

    public static void main(String[] args) {

        // Facade objektua lortu lehendabiziko ariketa erabiliz
        FactoryLaunch factoryLaunch = new FactoryLaunch();
        BLFacade facade;
        facade = factoryLaunch.createBLFacade();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
        Date date;
        try {
            date = sdf.parse("01/12/2022");

            ExtendedIterator<Event> i = facade.getEvents(date);

            Event ev;
            System.out.println("ATZETIK AURRERA ORDENATUA");

            i.goLast();

            while (i.hasPrevious()) {
                ev = i.previous();
                System.out.println(ev.toString());
            }

            System.out.println("AURRETIK ATZERA ORDENATUA");

            i.goFirst();

            while (i.hasNext()) {
                ev = i.next();
                System.out.println(ev.toString());
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}