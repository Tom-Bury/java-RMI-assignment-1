package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;
import java.util.List;
import java.util.Set;

import rental.*;

public abstract class Client extends AbstractTestBooking {
	public Client(String scriptFile) {
		super(scriptFile);
	}

//
//    private static final String REGISTRY_NAME = CarRentalCompanyInterface.class.getName();
//    private CarRentalCompanyInterface carRentalCompanyInterface;
//    private static final String CLIENT_INFO = "INFO CLIENT: ";
//	private static final String CLIENT_ERROR = "ERROR CLIENT: ";
//
//
//
//	/********
//	 * MAIN *
//	 ********/
//
//	public static void main(String[] args) throws Exception {
//
//		String carRentalCompanyName = "Hertz";
//
//		Client client = null;
//
//		// An example reservation scenario on car rental company 'Hertz' would be...
//		client = new Client("simpleTrips", carRentalCompanyName);
//
//		System.out.println("\nINFO: client object succesfully created.");
//		client.run();
//	}
//
//	/***************
//	 * CONSTRUCTOR *
//	 ***************/
//
//	public Client(String scriptFile, String carRentalCompanyName) {
//		super(scriptFile);
//
//
//		this.carRentalCompanyInterface = null;
//
//        // LOOKUP
//        Registry registry;
//        try {
//        	System.setSecurityManager(null);
//            registry = LocateRegistry.getRegistry("localhost");
//			this.carRentalCompanyInterface = (CarRentalCompanyInterface) registry.lookup(REGISTRY_NAME);
//
//        } catch (Exception e) {
//            System.err.println("ERROR: Lookup error: " + e.toString());
//            e.printStackTrace();
//        }
//	}
//
//	/**
//	 * Check which car types are available in the given period
//	 * and print this list of car types.
//	 *
//	 * @param 	start
//	 * 			start time of the period
//	 * @param 	end
//	 * 			end time of the period
//	 * @throws 	Exception
//	 * 			if things go wrong, throw exception
//	 */
//	@Override
//	protected void checkForAvailableCarTypes(Date start, Date end) throws Exception {
//
//		System.out.println("\n========================= Client: CHECK FOR AVAILABLE CAR TYPES =========================\n");
//
//        Set<CarType> result = this.carRentalCompanyInterface.getAvailableCarTypes(start, end);
//
//		System.out.println(CLIENT_INFO + "Cars available from " + start + " to " + end);
//        for (CarType type : result) {
//			System.out.println(type);
//		}
//	}
//
//	/**
//	 * Retrieve a quote for a given car type (tentative reservation).
//	 *
//	 * @param	clientName
//	 * 			name of the client
//	 * @param 	start
//	 * 			start time for the quote
//	 * @param 	end
//	 * 			end time for the quote
//	 * @param 	carType
//	 * 			type of car to be reserved
//	 * @param 	region
//	 * 			region in which car must be available
//	 * @return	the newly created quote
//	 *
//	 * @throws 	Exception
//	 * 			if things go wrong, throw exception
//	 */
//	@Override
//	protected Quote createQuote(String clientName, Date start, Date end,
//			String carType, String region) throws Exception {
//
//		System.out.println("\n========================= Client: CREATE QUOTE =========================\n");
//
//		ReservationConstraints reservationConstraints = new ReservationConstraints(start, end, carType, region);
//		Quote result = carRentalCompanyInterface.createQuote(reservationConstraints, clientName);
//
//		System.out.println(CLIENT_INFO + "@createQuote\n		" + reservationConstraints.toString());
//
//		return result;
//	}
//
//	/**
//	 * Confirm the given quote to receive a final reservation of a car.
//	 *
//	 * @param 	quote
//	 * 			the quote to be confirmed
//	 * @return	the final reservation of a car
//	 *
//	 * @throws 	Exception
//	 * 			if things go wrong, throw exception
//	 */
//	@Override
//	protected Reservation confirmQuote(Quote quote) throws Exception {
//
//		System.out.println("\n========================= Client: CONFIRM QUOTE =========================\n");
//
//		Reservation reservation = carRentalCompanyInterface.confirmQuote(quote);
//
//		System.out.println(CLIENT_INFO + "@confirmQuote\n		" + reservation.toString());
//
//		return reservation;
//	}
//
//	/**
//	 * Get all reservations made by the given client.
//	 *
//	 * @param 	clientName
//	 * 			name of the client
//	 * @return	the list of reservations of the given client
//	 *
//	 * @throws 	Exception
//	 * 			if things go wrong, throw exception
//	 */
//	@Override
//	protected List<Reservation> getReservationsByRenter(String clientName) throws Exception {
//
//		System.out.println("\n========================= Client: GET RESERVATIONS BY RENTER =========================\n");
//
//		List<Reservation> reservations = carRentalCompanyInterface.getAllReservationsFrom(clientName);
//
//		if (reservations.isEmpty()) {
//			System.out.println(CLIENT_INFO + "Client " + clientName + " has no reservations.");
//			return reservations;
//		}
//		else {
//			System.out.println(CLIENT_INFO + "Client " + clientName + " has the following reservations:\n        ");
//			for (Reservation res : reservations) {
//				System.out.println(res.toString() + "\n        ");
//			}
//			return reservations;
//		}
//	}
//
//	/**
//	 * Get the number of reservations for a particular car type.
//	 *
//	 * @param 	carType
//	 * 			name of the car type
//	 * @return 	number of reservations for the given car type
//	 *
//	 * @throws 	Exception
//	 * 			if things go wrong, throw exception
//	 */
//	@Override
//	protected int getNumberOfReservationsForCarType(String carType) throws Exception {
//
//		System.out.println("\n========================= Client: GET NB OF RESERVATIONS FOR CAR TYPE =========================\n");
//
//		int nbReservations = this.carRentalCompanyInterface.getNbReservations(carType);
//
//		System.out.println(CLIENT_INFO + carType + " has " + nbReservations + " reservations.");
//
//		return nbReservations;
//	}

}