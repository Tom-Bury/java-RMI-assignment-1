package rental;

import java.util.*;

public class Car {

    private int id;
    private CarType type;
    private List<Reservation> reservations;

    /***************
     * CONSTRUCTOR *
     ***************/
    
    public Car(int uid, CarType type) {
    	this.id = uid;
        this.type = type;
        this.reservations = new ArrayList<Reservation>();
    }

    /******
     * ID *
     ******/
    
    public int getId() {
    	return id;
    }
    
    /************
     * CAR TYPE *
     ************/
    
    public CarType getType() {
        return type;
    }

    public boolean isType(String carType) {
        return this.type.getName().equals(carType);
    }

    /****************
     * RESERVATIONS *
     ****************/

    public boolean isAvailable(Date start, Date end) {
        if(!start.before(end))
            throw new IllegalArgumentException("Illegal given period");

        for(Reservation reservation : reservations) {
            if(reservation.getEndDate().before(start) || reservation.getStartDate().after(end))
                continue;
            return false;
        }
        return true;
    }
    
    public void addReservation(Reservation res) {
        reservations.add(res);
    }
    
    public void removeReservation(Reservation reservation) {
        // equals-method for Reservation is required!
        reservations.remove(reservation);
    }

    public List<Reservation> getReservations(String carRenter) {
        List<Reservation> renterReservations = new ArrayList<>();

        for (Reservation reservation : this.reservations) {
            if (reservation.getCarRenter().equals(carRenter)) {
                renterReservations.add(reservation);
            }
        }

        return renterReservations;
    }

    public int getNbReservations() {
        return this.reservations.size();
    }

    public int getNbReservationsInYear(int year) {
        int counter = 0;
        for (Reservation r : this.reservations) {
            if (r.isInYear(year)) {
                counter++;
            }
        }

        return counter;
    }


    public Map<String, Integer> getAllCustomersAndReservationNbs() {
        Map<String, Integer> allCusAndResvNbs = new HashMap<>();

        for (Reservation r : this.reservations) {
            String currCust = r.getCarRenter();
            int oldNbRes = allCusAndResvNbs.getOrDefault(currCust, 0);
            allCusAndResvNbs.put(currCust, oldNbRes + 1);
        }

        return allCusAndResvNbs;
    }

    @Override
    public String toString() {
        return "ID: " + id + " - " + type.getName();
    }


}