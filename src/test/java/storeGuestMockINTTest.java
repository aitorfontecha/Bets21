import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(MockitoJUnitRunner.class)

public class storeGuestMockINTTest {
    String username = "Aitor";
    String pass1 = "1234";
    String pass2 = "1234";
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    Date jaiotzeData;

    {
        try {
            jaiotzeData = sdf.parse("17/07/2000");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    String kredituTxartela = "0123456789012345";
    DataAccess dataAccess = Mockito.mock(DataAccess.class);
    @InjectMocks
    BLFacade sut = new BLFacadeImplementation(dataAccess);

    @Test
    public void test1() {
        sut.storeGuest(username, pass1, pass2, jaiotzeData, kredituTxartela);
        Mockito.verify(dataAccess, Mockito.times(1)).storeGuest(username, pass1, pass2, jaiotzeData, kredituTxartela);
    }

    @Test
    public void test2() {
        sut.storeGuest(null, pass1, pass2, jaiotzeData, kredituTxartela);
        Mockito.verify(dataAccess, Mockito.times(1)).storeGuest(null, pass1, pass2, jaiotzeData, kredituTxartela);
    }
    @Test
    public void test3() {
        sut.storeGuest(username, null, null, jaiotzeData, kredituTxartela);
        Mockito.verify(dataAccess, Mockito.times(1)).storeGuest(username, null, null, jaiotzeData, kredituTxartela);
    }

    @Test
    public void test4() {
        sut.storeGuest(username, pass1, pass2, null, kredituTxartela);
        Mockito.verify(dataAccess, Mockito.times(1)).storeGuest(username, pass1, pass2, null, kredituTxartela);
    }
    @Test
    public void test5() {
        sut.storeGuest(username, pass1, pass2, jaiotzeData, null);
        Mockito.verify(dataAccess, Mockito.times(1)).storeGuest(username, pass1, pass2, jaiotzeData, null);
    }

    @Test
    public void test6() {
        sut.storeGuest(username, pass1, pass2, jaiotzeData, kredituTxartela);
        ArgumentCaptor<String> usernameCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> pass1Captor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> pass2Captor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Date> jaiotzeDataCaptor = ArgumentCaptor.forClass(Date.class);
        ArgumentCaptor<String> kredituTxartelaCaptor = ArgumentCaptor.forClass(String.class);
        Mockito.verify(dataAccess, Mockito.times(1)).storeGuest(usernameCaptor.capture(), pass1Captor.capture(), pass2Captor.capture(), jaiotzeDataCaptor.capture(), kredituTxartelaCaptor.capture());
        assertEquals(username, usernameCaptor.getValue());
        assertEquals(pass1, pass1Captor.getValue());
        assertEquals(pass2, pass2Captor.getValue());
        assertEquals(jaiotzeData, jaiotzeDataCaptor.getValue());
        assertEquals(kredituTxartela, kredituTxartelaCaptor.getValue());
    }

    @Test
    public void test7(){
        sut.storeGuest(username, pass1, pass2, jaiotzeData, kredituTxartela);
        Mockito.verify(dataAccess).storeGuest(username, pass1, pass2, jaiotzeData, kredituTxartela);
    }
}