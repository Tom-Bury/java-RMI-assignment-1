package carRentalCompanies;

import rental.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ICarRentalCompany extends Remote {

    String getName() throws RemoteException;

    Set<CarType> getAvailableCarTypes(Date start, Date end) throws RemoteException;

    CarType getMostPopularCarType(int year) throws RemoteException;

    Set<String> getAllCarTypesNames() throws RemoteException;

    Quote createQuote(ReservationConstraints constraints, String client) throws RemoteException, ReservationException;

    Reservation confirmQuote(Quote quote) throws ReservationException, RemoteException;

    void cancelReservation(Reservation res) throws RemoteException;

    List<Reservation> getAllReservationsFrom(String carRenter) throws RemoteException;

    int getNbReservations(String cartype) throws RemoteException;

    List<String> getRegions() throws RemoteException;

    Map<String, Integer> getAllCustomersAndReservationNumbers() throws RemoteException;
}
