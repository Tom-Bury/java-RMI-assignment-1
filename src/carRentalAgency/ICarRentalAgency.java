package carRentalAgency;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * CarRentalAgency is used by clients to start new manager/normal sessions, which they can use to do various queries.
 */
public interface ICarRentalAgency extends Remote {

    IReservationSession getNewReservationSession(int id, String clientName) throws RemoteException;

    boolean stopReservationSession(int id) throws RemoteException;

    IManagerSession getNewManagerSession(int id) throws RemoteException;

    boolean stopManagerSession(int id) throws RemoteException;

}
