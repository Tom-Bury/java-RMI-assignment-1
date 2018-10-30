package namingService;

import carRentalAgency.ICarRentalAgency;
import carRentalCompanies.CarRentalCompany;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class NamingServiceServer {

    private static final String REMOTE_NAME = INamingService.class.getName();

    public static void startServer(int port, List<CarRentalCompany> initialCompanies) {

        NamingService namingService = new NamingService(initialCompanies);

        // BINDING WITH RMI_REGISTRY
        try {
            System.setSecurityManager(null);
            INamingService iNamingService = (INamingService) UnicastRemoteObject.exportObject(namingService, port);

            Registry registry = LocateRegistry.getRegistry();

            // Rebind will replace any existing binding for the name within rmiregistry.
            // If there was no match, the object will be bound to the name within the registry as usual.
            registry.rebind(REMOTE_NAME, iNamingService);
            System.err.println("INFO: NamingServiceServer ready");
        } catch (Exception e) {
            System.err.println("\nERROR @NamingServiceServer: Binding error: " + e.toString());
            e.printStackTrace();
        }
    }

    public static String getRemoteName() {
        return REMOTE_NAME;
    }
}
