package carRentalAgency;

import namingService.INamingService;
import rental.NewRentalServer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CarRentalAgency implements ICarRentalAgency {


    /**
     * VARIABLES
     */

    private Map<Integer, ReservationSession> activeReservationSessions = new HashMap<>();

    private Map<Integer, ManagerSession> activeManagerSessions = new HashMap<>();

    private INamingService namingService;

    private int nextReservationSessionId = 0;

    private int nextManagerSessionId = 0;


    /**
     * CONSTRUCTOR
     */

    public CarRentalAgency(INamingService namingService) {
        this.namingService = namingService;
    }


    /**
     * METHODS INHERITED FROM THE INTERFACE
     */

    @Override
    public IReservationSession getNewReservationSession(String clientName) throws RemoteException {

        while (!isValidId(nextReservationSessionId, activeReservationSessions.keySet())) {
            incrementReservationSessionId();
        }

        // Geef een stub interface mee waarmee de client operaties op de remote object kan uitvoeren

        IReservationSession newSession = new ReservationSession(nextReservationSessionId, namingService, clientName);
        IReservationSession stub = (IReservationSession) UnicastRemoteObject.exportObject(newSession, NewRentalServer.PORT_NUMBER);

        return stub;
    }

    @Override
    public boolean stopReservationSession(int id) throws RemoteException {
        throw new UnsupportedOperationException("TODO");
        // TODO
    }

    @Override
    public IManagerSession getNewManagerSession(String managerName) throws RemoteException {
        //throw new UnsupportedOperationException("TODO");
        // TODO
        while (!isValidId(nextManagerSessionId, activeManagerSessions.keySet())) {
            incrementManagerSessionId();
        }

        // Geef een stub interface mee waarmee de manager operaties op de remote object kan uitvoeren

        IManagerSession newSession = new ManagerSession(nextManagerSessionId, namingService, managerName);
        IManagerSession stub = (IManagerSession) UnicastRemoteObject.exportObject(newSession, NewRentalServer.PORT_NUMBER);

        return stub;
    }

    @Override
    public boolean stopManagerSession(int id) throws RemoteException {
        throw new UnsupportedOperationException("TODO");
        // TODO
    }



    /**
     * HELPER METHODS
     */

    /**
     * Returns true if the given id is not yet used as a key in the give Map<int, ...>
     */
    private boolean isValidId(int id, Set<Integer> keySet) {
        return !keySet.contains(id);
    }

    private void incrementReservationSessionId() {
        this.nextReservationSessionId++;
    }

    private void incrementManagerSessionId() {
        this.nextManagerSessionId++;
    }

}
