package server.domain.model;

import server.domain.mediator.DataHandler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Class which represents a list of chauffeurs.
 *
 * @author IT-1Y-A16 Group 1
 */

public class ChauffeurList implements Serializable {

    private ArrayList<Chauffeur> chauffeurs;

    /**
     * Constructs a list of chauffeurs.
     */

    public ChauffeurList() {
        chauffeurs = new ArrayList<>();
    }

    /**
     * @return size of chauffeur list
     */

    public int getSize() {
        return chauffeurs.size();
    }

    /**
     * Adds a given chauffeur to the list.
     *
     * @param chauffeur chauffeur to add
     */

    public void add(Chauffeur chauffeur) {
        chauffeurs.add(chauffeur);
    }

    /**
     * Removes given chauffeur from the list.
     *
     * @param chauffeur chauffeur to remove
     */

    public void removeChauffeur(Chauffeur chauffeur) {
        for (int i = 0; i < chauffeurs.size(); i++) {
            if (chauffeurs.get(i).equals(chauffeur))
                chauffeurs.remove(chauffeur);
        }
    }

    /**
     * Finds chauffeur at the given index.
     *
     * @param index index to look at
     * @return chauffeur at given index
     */

    public Chauffeur getChauffeurByIndex(int index) {
        return chauffeurs.get(index);
    }

    /**
     * @return arraylist of all chauffeurs in the list
     */

    public ArrayList<Chauffeur> getArrayChauffeur() {
        return chauffeurs;
    }

    /**
     * Finds chauffeur with the given name.
     *
     * @param name name to look by
     * @return chauuffeur with given name
     */

    public Chauffeur getByName(String name) {
        for (Chauffeur chauffeur : chauffeurs) {
            if (chauffeur.getName().equals(name))
                return chauffeur;
        }
        return null;
    }

    /**
     * Checks if list contains given chauffeur.
     *
     * @param chauffeur chauffeur to check
     * @return true if list contains given chauffeur
     */

    private boolean contains(Chauffeur chauffeur) {
        return chauffeurs.contains(chauffeur);
    }

    /**
     * Finds all chauffeurs with given preferred distance.
     *
     * @param prefferedDistance distance to look by
     * @return ChauffeurList of all chauffeurs with given preferred distance
     */

    public ChauffeurList getAllByPrefferedDistance(int prefferedDistance) {
        ChauffeurList result = new ChauffeurList();
        for (Chauffeur chauffeur : chauffeurs) {
            if (!chauffeur.isVikar()) {
                if ((chauffeur.getPreferredDistance() != null) && !(chauffeur.getPreferredDistance().isEmpty())) {
                    for (int j = 0; j < chauffeur.getPreferredDistance().size(); j++) {
                        if (chauffeur.getPreferredDistance().get(j) > prefferedDistance) {
                            if (!result.contains(chauffeur))
                                result.add(chauffeur);
                        }
                    }
                } else if (chauffeur.getPreferredDistance().isEmpty()) {
                    result.add(chauffeur);
                }
            }
        }
        return result;
    }

    /**
     * Finds all chauffeurs with given preferred bustype.
     *
     * @param busType bustype to look by
     * @return ChauffeurList of all chauffeurs with given preferred bustype
     */

    public ChauffeurList getAllByPrefferedBus(String busType) {
        ChauffeurList result = new ChauffeurList();
        for (Chauffeur chauffeur : chauffeurs) {
            ArrayList<String> prefferedBuses = chauffeur.getArrayBusTypes();
            if (!chauffeur.isVikar()) {
                if (!prefferedBuses.isEmpty()) {
                    for (String prefferedBuse : prefferedBuses) {
                        if (busType.equals(prefferedBuse)) {
                            if (!result.contains(chauffeur))
                                result.add(chauffeur);
                        }
                    }
                } else
                    result.add(chauffeur);
            }
        }

        return result;
    }

    /**
     * @return ChauffeurList of all with vicar contract
     */

    public ChauffeurList getAllVicars() {
        ChauffeurList result = new ChauffeurList();
        for (Chauffeur chauffeur : chauffeurs) {
            if (chauffeur.isVikar()) {
                result.add(chauffeur);
            }
        }
        return result;
    }

    /**
     * Removes all vicars in the list.
     */

    public void removeAllVicars() {
        for (int i = 0; i < chauffeurs.size(); i++) {
            if (chauffeurs.get(i).isVikar()) {
                chauffeurs.remove(chauffeurs.get(i));
            }
        }
    }

    /**
     * @return List converted to String
     */

    public String toString() {
        String s = "";
        for (int i = 0; i < chauffeurs.size(); i++) {
            s += chauffeurs.get(i);
            if (i < chauffeurs.size() - 1)
                s += "\n";
        }
        return s;
    }

    /**
     * Finds all chauffeurs who are available in the given date interval.
     *
     * @param from start of date interval
     * @param to   end of date interval
     * @return ChauffeurList of all available in the given date interval
     */

    public ChauffeurList getAvailable(Date from, Date to) {
        ArrayList<Chauffeur> inTrips = new ArrayList<>();
        ChauffeurList result = new ChauffeurList();
        ProxyTripList trips = DataHandler.getInstance().getTrips();
        for (int i = 0; i < trips.getArrayTrip().size(); i++) {
            for (Chauffeur chauffeur : chauffeurs) {
                if (chauffeur.equals(trips.getArrayTrip().get(i).getChauffeur())) {
                    if (!inTrips.contains(chauffeur))
                        inTrips.add(chauffeur);
                    if (((from.before(trips.getArrayTrip().get(i).getDateObjStart())) && (to.before(trips.getArrayTrip().get(i).getDateObjStart())))
                            || ((from.after(trips.getArrayTrip().get(i).getDateObjEnd())) && (to.after(trips.getArrayTrip().get(i).getDateObjEnd())))) {
                   if(!result.contains(chauffeur))     result.add(chauffeur);
                    }
                }
            }
        }
        for (Chauffeur chauffeur : chauffeurs) {
            if (!inTrips.contains(chauffeur))
                result.add(chauffeur);
        }
        return result;
    }

    /**
     * Copies this list.
     *
     * @return Copy of this ChauffeurList
     */

    public ChauffeurList copy() {
        ChauffeurList chauffeurList = new ChauffeurList();
        for (Chauffeur chauffeur : chauffeurs) {
            chauffeurList.add(chauffeur);
        }
        return chauffeurList;
    }

}
