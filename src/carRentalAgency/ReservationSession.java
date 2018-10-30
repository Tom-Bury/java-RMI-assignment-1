package carRentalAgency;


import carRentalCompanies.ICarRentalCompany;
import namingService.INamingService;
import rental.CarType;
import rental.Quote;
import rental.Reservation;
import rental.ReservationException;

import java.rmi.RemoteException;
import java.util.*;

public class ReservationSession extends Session implements IReservationSession  {

    /**
     * VARIABLES
     */

    private String clientName;

    private List<Quote> currentQuotes = new ArrayList<>();


    /**
     * CONSTRUCTOR
     */

    public ReservationSession(int id, INamingService namingService, String clientName) {
        super(id, namingService);
        this.clientName = clientName;
    }


    /**
     * INHERITED METHODS OF INTERFACE
     */

    @Override
    public Quote createQuote(Date start, Date end, String carType, String region) throws RemoteException {
        throw new UnsupportedOperationException("TODO");
        // TODO
    }

    @Override
    public List<Reservation> confirmQuotes() throws ReservationException, RemoteException {
        throw new UnsupportedOperationException("TODO");
        // TODO
    }

    @Override
    public Map<String, Set<CarType>> getAvailableCarTypes(Date start, Date end) throws RemoteException {

        // Map die CRC-namen mapt op lijsten die de CarTypes bevatten die available zijn
        Map<String, Set<CarType>> availableCarTypes = new HashMap<>();

        List<String> allCrcNames = getNamingService().getAllRegisteredCarRentalCompanies();

        for (String companyName : allCrcNames) {
            ICarRentalCompany currCrc = getNamingService().getCarRentalCompany(companyName);
            Set<CarType> currAvCarTypes = currCrc.getAvailableCarTypes(start, end);

            availableCarTypes.put(companyName, currAvCarTypes);
        }

        return availableCarTypes;
    }

    @Override
    public String getCheapestCarType(Date start, Date end, String region) throws RemoteException {
        throw new UnsupportedOperationException("TODO");
        // TODO
    }
}
