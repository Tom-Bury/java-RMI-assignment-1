package carRentalAgency;

import carRentalCompanies.ICarRentalCompany;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

public class ManagerSession implements IManagerSession {

    /**
     * INHERITED METHODS FROM INTERFACE
     */

    @Override
    public void registerCRC(String crcName, ICarRentalCompany crc) throws RemoteException {
        throw new UnsupportedOperationException("TODO");
        // TODO
    }

    @Override
    public void unregisterCRC(String crcName) throws RemoteException {
        throw new UnsupportedOperationException("TODO");
        // TODO
    }

    @Override
    public List<String> getAllRegisterdCrcs() throws RemoteException {
        throw new UnsupportedOperationException("TODO");
        // TODO
    }

    @Override
    public List<String> getAllCarTypesOfCrc(String crcName) throws RemoteException {
        throw new UnsupportedOperationException("TODO");
        // TODO
    }

    @Override
    public int getNbOfReservationsForCarTypeInCompany(String carType, String crcName) throws RemoteException {
        throw new UnsupportedOperationException("TODO");
        // TODO
    }

    @Override
    public String getBestCustomer() throws RemoteException {
        throw new UnsupportedOperationException("TODO");
        // TODO
    }

    @Override
    public String getMostPopularCarType(String crcName, Date startingDate) throws RemoteException {
        throw new UnsupportedOperationException("TODO");
        // TODO
    }
}
