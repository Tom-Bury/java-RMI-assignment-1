package carRentalAgency;

import carRentalCompanies.ICarRentalCompany;
import namingService.INamingService;
import rental.Quote;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ManagerSession extends Session implements IManagerSession {


    /**
     * VARIABLES
     */

    private String clientName;

    // private List<Quote> currentQuotes = new ArrayList<>();


    /**
     * CONSTRUCTOR
     */

    public ManagerSession(int id, INamingService namingService, String managerName) {
        super(id, namingService);
        this.clientName = managerName;
    }

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
        //throw new UnsupportedOperationException("TODO");
        // TODO
        ICarRentalCompany currCompany = getNamingService().getCarRentalCompany(crcName);
        return currCompany.getNbReservations(carType);

    }

    @Override
    public int getNbOfReservationsBy(String clientName) throws RemoteException {
        //throw new UnsupportedOperationException("TODO");
        // TODO
        List<String> companies = getNamingService().getAllRegisteredCarRentalCompanyNames();
        int counter = 0;
        for (String companyName : companies) {
            ICarRentalCompany currCompany = getNamingService().getCarRentalCompany(companyName);
            counter += currCompany.getAllReservationsFrom(clientName).size();
        }
        return counter;
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
