package namingService;

import carRentalCompanies.CarRentalCompany;
import carRentalCompanies.ICarRentalCompany;

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
     * CONSTRUCTOR
     */

    public NamingService(List<CarRentalCompany> initialCompanies) {

        // Register the given initial CarRentalCompanies
        for (CarRentalCompany crc : initialCompanies) {
            this.registeredCrcs.put(crc.getName(), crc);
        }
    }



    /**
     * INHERITED METHODS FROM INTERFACE
     */

    @Override
    public void registerCrc(String crcName, ICarRentalCompany crc) throws RemoteException {
        throw new UnsupportedOperationException("TODO");
        // TODO
    }

    @Override
    public void unregisterCrc(String crcName) throws RemoteException {
        throw new UnsupportedOperationException("TODO");
        // TODO
    }

    @Override
    public List<String> getAllRegisteredCarRentalCompanies() throws RemoteException {
        throw new UnsupportedOperationException("TODO");
        // TODO
    }

    @Override
    public ICarRentalCompany getCarRentalCompany(String crcName) throws RemoteException {
        throw new UnsupportedOperationException("TODO");
        // TODO
    }
}
