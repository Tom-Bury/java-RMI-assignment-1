package carRentalAgency;


import carRentalCompanies.ICarRentalCompany;
import namingService.INamingService;
import rental.*;

import java.rmi.RemoteException;
import java.util.*;

public class ReservationSession extends Session implements IReservationSession {

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
    public Quote createQuote(ReservationConstraints reservationConstraints) throws RemoteException {
        List<String> companies = getNamingService().getAllRegisteredCarRentalCompanyNames();

        for (String companyName : companies) {
            ICarRentalCompany currCompany = getNamingService().getCarRentalCompany(companyName);
            try {
                Quote currQuote = currCompany.createQuote(reservationConstraints, this.clientName);
                this.currentQuotes.add(currQuote);
                return currQuote;
            } catch (ReservationException e) {
                System.out.println("    --> ReservationException @ReservationSession: could not create quote with company " + currCompany.getName());
                System.out.println();
            } catch (IllegalArgumentException e) {
                System.out.println("    --> IllegamArgumentException @Reservationsession: " + e.getMessage());
                System.out.println();
            }
        }

        return null;
    }

    @Override
    public List<Reservation> confirmQuotes() throws ReservationException, RemoteException {
        List<Reservation> reservations = new ArrayList<Reservation>();

        try {
            for (Quote q : this.currentQuotes) {
                ICarRentalCompany company = getNamingService().getCarRentalCompany(q.getRentalCompany());
                Reservation r = company.confirmQuote(q);
                reservations.add(r);
            }
        } catch (ReservationException e) {
            undoReservations(reservations);
            System.out.println("ERROR @Reservationsession: could not confirm quote");
            throw e;
        }

        return reservations;
    }

    private void undoReservations(List<Reservation> reservations) throws RemoteException {
        for (Reservation r : reservations) {
            ICarRentalCompany company = getNamingService().getCarRentalCompany(r.getRentalCompany());
            company.cancelReservation(r);
        }
    }

    @Override
    public Map<String, Set<CarType>> getAvailableCarTypes(Date start, Date end) throws RemoteException {

        // Map die CRC-namen mapt op lijsten die de CarTypes bevatten die available zijn
        Map<String, Set<CarType>> availableCarTypes = new HashMap<>();

        List<String> allCrcNames = new ArrayList<>();
        try {
            allCrcNames = getNamingService().getAllRegisteredCarRentalCompanyNames();
        } catch (RemoteException e) {
            System.out.println("--------> RemoteExc thrown when getting all crc Names");
        }


        for (String companyName : allCrcNames) {
            ICarRentalCompany currCrc = null;
            try {
                currCrc = getNamingService().getCarRentalCompany(companyName);
            } catch (RemoteException e) {
                System.out.println("---------> RemoteExc at getCarRentalCOmp");
            }
            Set<CarType> currAvCarTypes = currCrc.getAvailableCarTypes(start, end);

            availableCarTypes.put(companyName, currAvCarTypes);
        }

        return availableCarTypes;
    }

    @Override
    public String getCheapestCarType(Date start, Date end, String region) throws RemoteException {

        List<String> allCrcNames = new ArrayList<>();
        try {
            allCrcNames = getNamingService().getAllRegisteredCarRentalCompanyNames();
        } catch (RemoteException e) {
            System.out.println("--------> RemoteExc thrown when getting all crc Names");
        }

        String currCheapestCarType = null;
        double currCheapestPrice = Double.MAX_VALUE;

        for (String companyName : allCrcNames) {
            ICarRentalCompany currCrc = null;
            try {
                currCrc = getNamingService().getCarRentalCompany(companyName);
            } catch (RemoteException e) {
                System.out.println("---------> RemoteExc at getCarRentalCOmp");
            }

            if (currCrc.getRegions().contains(region)) {
                Set<CarType> currAvCarTypes = currCrc.getAvailableCarTypes(start, end);
                for (CarType carType : currAvCarTypes) {
                    if (carType.getRentalPricePerDay() < currCheapestPrice) {
                        currCheapestCarType = carType.getName();
                        currCheapestPrice = carType.getRentalPricePerDay();
                    }
                }
            }
        }

        return currCheapestCarType;
    }
}

