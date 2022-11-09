package businessLogic;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import configuration.ConfigXML;
import dataAccess.DataAccess;

public class FactoryLaunch {

    ConfigXML c=ConfigXML.getInstance();

    public FactoryLaunch() {}

    public BLFacade createBLFacade() {
        BLFacade appFacadeInterface;
        try {
            if (c.isBusinessLogicLocal()) {

                //DataAccess-a FacadeImplementationWS-k sortzen du
                //appFacadeInterface=new BLFacadeImplementation();

                //DataAccess-a parametrizuatua sortzen du
                DataAccess da= new DataAccess(c.getDataBaseOpenMode().equals("initialize"));
                appFacadeInterface=new BLFacadeImplementation(da);
            }

            else { //If remote

                String serviceName= "http://"+c.getBusinessLogicNode() +":"+ c.getBusinessLogicPort()+"/ws/"+c.getBusinessLogicName()+"?wsdl";

                URL url = new URL(serviceName);


                //WSDL dokumentua eta zerbitzua
                QName qname = new QName("http://businessLogic/", "BLFacadeImplementationService");

                Service service = Service.create(url, qname);

                appFacadeInterface = service.getPort(BLFacade.class);
            }
            return appFacadeInterface;
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
