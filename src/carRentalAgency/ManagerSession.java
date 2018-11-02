package carRentalAgency;

import carRentalCompanies.ICarRentalCompany;
import namingService.INamingService;
import rental.CarType;
import rental.Quote;

import java.rmi.RemoteException;
import java.util.*;

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
        getNamingService().registerCrc(crcName, crc);
    }

    @Override
    public void unregisterCRC(String crcName) throws RemoteException {
        getNamingService().unregisterCrc(crcName);
    }

    @Override
    public List<String> getAllRegisterdCrcs() throws RemoteException {
        return getNamingService().getAllRegisteredCarRentalCompanyNames();
    }

    @Override
    public Set<String> getAllCarTypesOfCrc(String crcName) throws RemoteException {
        return getNamingService().getCarRentalCompany(crcName).getAllCarTypesNames();
    }

    @Override
    public int getNbOfReservationsForCarTypeInCompany(String carType, String crcName) throws RemoteException {
        ICarRentalCompany currCompany = getNamingService().getCarRentalCompany(crcName);
        return currCompany.getNbReservations(carType);

    }

    @Override
    public int getNbOfReservationsBy(String clientName) throws RemoteException {
        List<String> companies = getNamingService().getAllRegisteredCarRentalCompanyNames();
        int counter = 0;
        for (String companyName : companies) {
            ICarRentalCompany currCompany = getNamingService().getCarRentalCompany(companyName);
            counter += currCompany.getAllReservationsFrom(clientName).size();
        }
        return counter;
    }

    @Override
    public Set<String> getBestCustomers() throws RemoteException {

        Map<String, Integer> allCustomers = new HashMap<>();
        List<String> companies = getNamingService().getAllRegisteredCarRentalCompanyNames();

        for (String crcName : companies) {
            ICarRentalCompany carRentalCompany = getNamingService().getCarRentalCompany(crcName);
            Map<String, Integer> currCustomers = carRentalCompany.getAllCustomersAndReservationNumbers();

            for (String currCust : currCustomers.keySet()) {
                int currNbRes = currCustomers.get(currCust);
                int oldNbRes = allCustomers.getOrDefault(currCust, 0);
                allCustomers.put(currCust, oldNbRes + currNbRes);
            }
        }

        Set<String> bestCustomers = new HashSet<>();
        int bestNbRes = 0;

        for (String cus : allCustomers.keySet()) {
            int currNbRes = allCustomers.get(cus);

            if (currNbRes > bestNbRes) {
                bestCustomers.clear();
                bestCustomers.add(cus);
                bestNbRes = currNbRes;
            }
            else if (currNbRes == bestNbRes) {
                bestCustomers.add(cus);
            }
        }

        return bestCustomers;
    }

    @Override
    public CarType getMostPopularCarType(String crcName, int year) throws RemoteException {
        ICarRentalCompany crc = getNamingService().getCarRentalCompany(crcName);
        return crc.getMostPopularCarType(year);
    }
}
