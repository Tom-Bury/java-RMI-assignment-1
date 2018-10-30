package namingService;

import carRentalCompanies.CarRentalCompany;
import carRentalCompanies.ICarRentalCompany;
import rental.NewRentalServer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
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

    public NamingService(List<ICarRentalCompany> initialCompanies) throws RemoteException{

        // Register the given initial CarRentalCompanies
        for (ICarRentalCompany crc : initialCompanies) {
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
    public List<String> getAllRegisteredCarRentalCompanyNames() throws RemoteException {

        List<String> result = new ArrayList<>();
        result.addAll(this.registeredCrcs.keySet());

        return result;
    }

    @Override
    public ICarRentalCompany getCarRentalCompany(String crcName) throws RemoteException {
//        ICarRentalCompany requestedCrc = this.registeredCrcs.get(crcName);
//        ICarRentalCompany stub = (ICarRentalCompany) UnicastRemoteObject.exportObject(requestedCrc, NewRentalServer.PORT_NUMBER);
//        // TODO: is deze stubs doorgeven ok?
//
//        return stub;

        if (!this.registeredCrcs.keySet().contains(crcName)) {
            throw new IllegalArgumentException("The company " + crcName + " is not registered in the NamingService");
        }

        return this.registeredCrcs.get(crcName);
    }
}
