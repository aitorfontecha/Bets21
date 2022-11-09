package businessLogic;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import configuration.ConfigXML;
import dataAccess.DataAccess;

public class FactoryLaunch {

    public static BLFacade createBLFacade(int num) {
        if(num == 0) {
            ConfigXML c=ConfigXML.getInstance();
            DataAccess da= new DataAccess(c.getDataBaseOpenMode().equals("initialize"));
            return new BLFacadeImplementation(da);
        }
        if(num == 1) {
            try {
                ConfigXML c=ConfigXML.getInstance();
                String serviceName= "http://"+c.getBusinessLogicNode() +":"+ c.getBusinessLogicPort()+"/ws/"+c.getBusinessLogicName()+"?wsdl";
                URL url = new URL(serviceName);
                QName qname = new QName("http://businessLogic/", "BLFacadeImplementationService");
                Service service = Service.create(url, qname);
                return service.getPort(BLFacade.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }



}
