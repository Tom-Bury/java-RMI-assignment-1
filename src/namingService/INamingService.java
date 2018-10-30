package namingService;

import carRentalCompanies.ICarRentalCompany;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * NamingService keeps track of all registered CarRentalCompanies.
 */
public interface INamingService extends Remote {

    void registerCrc(String crcName, ICarRentalCompany crc) throws RemoteException;

    void unregisterCrc(String crcName) throws RemoteException;

    List<String> getAllRegisteredCarRentalCompanies() throws RemoteException;

    ICarRentalCompany getCarRentalCompany(String crcName) throws RemoteException;

}
