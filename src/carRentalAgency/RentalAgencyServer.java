package carRentalAgency;

import namingService.INamingService;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RentalAgencyServer {

    private final static String REMOTE_NAME = ICarRentalAgency.class.getName();

    /**
     * Creates the RentalAgency remote object and registers it in the RMI Registry
     * @param port                      The port that will be used
     * @param host                      Should be "localhost"
     * @param namingServiceRMIregName   The name under what the remote NamingService was registered in the RMI Registry
     */
    public void startServer(int port, String host, String namingServiceRMIregName) {

        INamingService iNamingService = getINamingService(host, namingServiceRMIregName);
        CarRentalAgency agency = new CarRentalAgency(iNamingService);

        // BINDING WITH RMI_REGISTRY
        try {
            System.setSecurityManager(null);
            ICarRentalAgency iCarRentalAgency = (ICarRentalAgency) UnicastRemoteObject.exportObject(agency, port);

            Registry registry = LocateRegistry.getRegistry();

            // Rebind will replace any existing binding for the name within rmiregistry.
            // If there was no match, the object will be bound to the name within the registry as usual.
            registry.rebind(REMOTE_NAME, iCarRentalAgency);
            System.out.println("INFO: CarRentalAgencyServer bound to RMI Registry");
        } catch (Exception e) {
            System.err.println("\nERROR @CarRentalAgency: Binding error: " + e.toString());
            e.printStackTrace();
        }
    }

    /**
     * This method gets the INamingService from the RMI Registry
     * @param host                      Should be "localhost"
     * @param namingServiceRMIregName   The name under what the remote NamingService was registered in the RMI Registry
     * @return                          The INamingService interface that refers to the remote NamingService object
     */
    private INamingService getINamingService(String host, String namingServiceRMIregName) {

        // RMI_REGISTRY LOOKUP

        try {
            System.setSecurityManager(null);
            Registry registry = LocateRegistry.getRegistry(host);
            INamingService iNamingService = (INamingService) registry.lookup(namingServiceRMIregName);

            return iNamingService;
        } catch (Exception e) {
            System.err.println("\nERROR @RentalAgencyServer: NamingService lookup error: " + e.toString());
            e.printStackTrace();
        }

        return null;
    }



}
