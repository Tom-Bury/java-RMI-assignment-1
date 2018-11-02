package carRentalCompanies;

import rental.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import java.util.Set;

public interface ICarRentalCompany extends Remote {

    String getName() throws RemoteException;

    Set<CarType> getAvailableCarTypes(Date start, Date end) throws RemoteException;

    Quote createQuote(ReservationConstraints constraints, String client) throws RemoteException, ReservationException;

    Reservation confirmQuote(Quote quote) throws ReservationException, RemoteException;

    void cancelReservation(Reservation res) throws RemoteException;

    List<Reservation> getAllReservationsFrom(String carRenter) throws RemoteException;

    int getNbReservations(String cartype) throws RemoteException;

    String getCheapestCarType() throws RemoteException;

    String getBestCustomer() throws RemoteException;

    String getMostPopularCarType() throws RemoteException;

    List<String> getRegions() throws RemoteException;

    void cancelReservation(Reservation res) throws RemoteException;

}
