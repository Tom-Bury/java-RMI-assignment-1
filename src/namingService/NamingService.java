package namingService;

import carRentalCompanies.ICarRentalCompany;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NamingService implements INamingService {

    /**
     * VARIABLES
     */

    private Map<String, ICarRentalCompany> registeredCrcs = new HashMap<>();


    /**
     * INHERITED METHODS FROM INTERFACE
     */

    @Override
    public void registerCrc(String crcName, ICarRentalCompany crc) throws RemoteException {
        throw new NotImplementedException();
        //TODO
    }

    @Override
    public void unregisterCrc(String crcName) throws RemoteException {
        throw new NotImplementedException();
        //TODO
    }

    @Override
    public List<String> getAllRegisteredCarRentalCompanies() throws RemoteException {
        throw new NotImplementedException();
        //TODO
    }

    @Override
    public ICarRentalCompany getCarRentalCompany(String crcName) throws RemoteException {
        throw new NotImplementedException();
        //TODO
    }
}
