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
        Mockito.verify(dataAccess, Mockito.times(1)).storeGuest(Mockito.any(String.class), Mockito.any(String.class), Mockito.any(String.class), Mockito.any(Date.class), Mockito.any(String.class));

    }

    @Test
    public void test2() {
        try {
            ArgumentCaptor<String> usernameCaptor = ArgumentCaptor.forClass(String.class);
            ArgumentCaptor<String> pass1Captor = ArgumentCaptor.forClass(String.class);
            ArgumentCaptor<String> pass2Captor = ArgumentCaptor.forClass(String.class);
            ArgumentCaptor<Date> jaiotzeDataCaptor = ArgumentCaptor.forClass(Date.class);
            ArgumentCaptor<String> kredituTxartelaCaptor = ArgumentCaptor.forClass(String.class);
            sut.storeGuest(username, pass1, pass2, jaiotzeData, kredituTxartela);
            Mockito.verify(dataAccess, Mockito.times(1)).storeGuest(usernameCaptor.capture(), pass1Captor.capture(), pass2Captor.capture(), jaiotzeDataCaptor.capture(), kredituTxartelaCaptor.capture());
            assertEquals(usernameCaptor.getValue(), username);
            assertEquals(pass1Captor.getValue(), pass1);
            assertEquals(pass2Captor.getValue(), pass2);
            assertEquals(jaiotzeDataCaptor.getValue(), jaiotzeData);
            assertEquals(kredituTxartelaCaptor.getValue(), kredituTxartela);
        } catch (Exception e) {
            fail();
        }

    }
}