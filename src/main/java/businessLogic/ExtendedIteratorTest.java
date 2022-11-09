package businessLogic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import domain.Event;

public class ExtendedIteratorTest {

    public static void main(String[] args) {

        boolean isLocal=true;
        //Facade objektua lortu lehendabiziko ariketa erabiliz
        FactoryLaunch factoryLaunch = new FactoryLaunch();
        BLFacade facadeInterface;
        facadeInterface = factoryLaunch.createBLFacade(0);


        Date gaur = new Date();
        ExtendedIterator<Event> i=facadeInterface.getEvents(gaur);
        Event ev;
        i.goLast();
        while (i.hasPrevious()){
            ev = i.previous();
            System.out.println(ev.toString());
        }
        //Nahiz eta suposatu hasierara ailegatu garela, eragiketa egiten dugu.
        i.goFirst();
        while (i.hasNext()){
            ev = (Event) i.next();
            System.out.println(ev.toString());
        }
    }
}