package client;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;
import java.util.List;
import java.util.Set;

import rental.CarType;
import rental.Quote;
import rental.Reservation;

import rental.CarRentalCompanyInterface;

public class Client extends AbstractTestBooking {

    private final String registryName = CarRentalCompanyInterface.class.getName();
    private CarRentalCompanyInterface carRentalCompanyInterface;


	
	/********
	 * MAIN *
	 ********/
	
	public static void main(String[] args) throws Exception {
		
		String carRentalCompanyName = "Hertz";

		Client client = null;

		// An example reservation scenario on car rental company 'Hertz' would be...
		client = new Client("simpleTrips", carRentalCompanyName);

		System.out.println("\nINFO: client object succesfully created.");
		client.run();
	}
	
	/***************
	 * CONSTRUCTOR *
	 ***************/
	
	public Client(String scriptFile, String carRentalCompanyName) {
		super(scriptFile);


		this.carRentalCompanyInterface = null;

        // LOOKUP
        Registry registry;
        try {
        	System.setSecurityManager(null);
            registry = LocateRegistry.getRegistry(1100);
            this.carRentalCompanyInterface = (rental.CarRentalCompanyInterface) registry.lookup(registryName);

        } catch (Exception e) {
            System.err.println("ERROR: Lookup error: " + e.toString());
            e.printStackTrace();
        }

        // TODO Auto-generated method stub
//		throw new UnsupportedOperationException("TODO");
	}
	
	/**
	 * Check which car types are available in the given period
	 * and print this list of car types.
	 *
	 * @param 	start
	 * 			start time of the period
	 * @param 	end
	 * 			end time of the period
	 * @throws 	Exception
	 * 			if things go wrong, throw exception
	 */
	@Override
	protected void checkForAvailableCarTypes(Date start, Date end) throws Exception {
		// TODO Auto-generated method stub

		System.out.println("\n========================= CHECK FOR AVAILABLE CAR TYPES =========================\n");
        Set<CarType> result = this.carRentalCompanyInterface.getAvailableCarTypes(start, end);

        System.out.println(result);
		System.out.println("\n");

//		throw new UnsupportedOperationException("TODO");
	}

	/**
	 * Retrieve a quote for a given car type (tentative reservation).
	 * 
	 * @param	clientName 
	 * 			name of the client 
	 * @param 	start 
	 * 			start time for the quote
	 * @param 	end 
	 * 			end time for the quote
	 * @param 	carType 
	 * 			type of car to be reserved
	 * @param 	region
	 * 			region in which car must be available
	 * @return	the newly created quote
	 *  
	 * @throws 	Exception
	 * 			if things go wrong, throw exception
	 */
	@Override
	protected Quote createQuote(String clientName, Date start, Date end,
			String carType, String region) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("TODO");
	}

	/**
	 * Confirm the given quote to receive a final reservation of a car.
	 * 
	 * @param 	quote 
	 * 			the quote to be confirmed
	 * @return	the final reservation of a car
	 * 
	 * @throws 	Exception
	 * 			if things go wrong, throw exception
	 */
	@Override
	protected Reservation confirmQuote(Quote quote) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("TODO");
	}
	
	/**
	 * Get all reservations made by the given client.
	 *
	 * @param 	clientName
	 * 			name of the client
	 * @return	the list of reservations of the given client
	 * 
	 * @throws 	Exception
	 * 			if things go wrong, throw exception
	 */
	@Override
	protected List<Reservation> getReservationsByRenter(String clientName) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("TODO");
	}

	/**
	 * Get the number of reservations for a particular car type.
	 * 
	 * @param 	carType 
	 * 			name of the car type
	 * @return 	number of reservations for the given car type
	 * 
	 * @throws 	Exception
	 * 			if things go wrong, throw exception
	 */
	@Override
	protected int getNumberOfReservationsForCarType(String carType) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("TODO");
	}
}