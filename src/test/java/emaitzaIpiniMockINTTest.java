import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;
import domain.Pronostikoa;
import domain.Question;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class emaitzaIpiniMockINTTest{
    DataAccess dataAccess = Mockito.mock(DataAccess.class);
    //Event mockedEvent=Mockito.mock(Event.class);
    @InjectMocks
    BLFacade sut = new BLFacadeImplementation(dataAccess);

    @Test
    public void test1() {
        Pronostikoa pr = new Pronostikoa();
        Question qu = new Question();
        sut.emaitzaIpini(qu, pr);
        Mockito.verify(dataAccess, Mockito.times(1)).emaitzaIpini(qu,
                pr);
    }

    @Test
    public void test2() {
        Pronostikoa pr = new Pronostikoa();
        sut.emaitzaIpini(null, pr);
        Mockito.verify(dataAccess, Mockito.times(1)).emaitzaIpini(null, pr);
    }

    @Test
    public void test3() {
        Question qu = new Question();
        sut.emaitzaIpini(qu, null);
        Mockito.verify(dataAccess, Mockito.times(1)).emaitzaIpini(qu, null);
    }

    @Test
    public void test4() {
        Question qu = new Question();
        Pronostikoa pr = new Pronostikoa();
        sut.emaitzaIpini(qu, pr);
        Mockito.verify(dataAccess).emaitzaIpini(qu, pr);
    }

    @Test
    public void test5() {
        Question qu = new Question();
        Pronostikoa pr = new Pronostikoa();
        sut.emaitzaIpini(qu, pr);
        Mockito.verify(dataAccess).emaitzaIpini(qu, pr);
    }

}