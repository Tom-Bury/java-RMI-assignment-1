package carRentalAgency;

import carRentalCompanies.ICarRentalCompany;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

/**
 * A ManagerSession is used by a manager to (un)register CarRentalCompanies and make manager specific queries.
 */
public interface IManagerSession extends Remote {

    void registerCRC(String crcName, ICarRentalCompany crc) throws RemoteException;

    void unregisterCRC(String crcName) throws RemoteException;

    List<String> getAllRegisterdCrcs() throws RemoteException;

    List<String> getAllCarTypesOfCrc(String crcName) throws RemoteException;

    int getNbOfReservationsForCarTypeInCompany(String carType, String crcName) throws RemoteException;

    String getBestCustomer()throws RemoteException;

    String getMostPopularCarType(String crcName, Date startingDate) throws RemoteException;

    int getNbOfReservationsBy(String clientName) throws RemoteException;

}
