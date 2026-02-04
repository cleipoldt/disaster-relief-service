/**
 * @author      Carl Leipoldt <a href="mailto:carl.leipoldt@ucalgary.ca">carl.leipoldt@ucalgary.ca</a>
 * @version     1.3
 * @since       1.0
 */

package edu.ucalgary.oop;

import java.util.Arrays;

public class Location {
    private String name;
    private String address;
    private DisasterVictim[] occupants;
    private Supply[] supplies;

    public Location(String name, String address){
        this.name = name;
        this.address = address;
    }

    public String getName(){
        return this.name;
    }
    
    public void setName(String name){
        this.name = name;
    }

    public String getAddress(){
        return this.address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public DisasterVictim[] getOccupants(){
        return this.occupants;
    }

    public void setOccupants(DisasterVictim[] occupants){
        this.occupants = occupants;
    }

    public Supply[] getSupplies(){
        return this.supplies;
    }

    public void setSupplies(Supply[] supplies){
        this.supplies = supplies;
    }

    public void addOccupant(DisasterVictim occupant){
        if (this.occupants != null) {
            int origLength = this.occupants.length;
            DisasterVictim[] newOccupants = Arrays.copyOf(this.occupants, origLength + 1);
            newOccupants[origLength] = occupant;
            this.occupants = newOccupants;
        } else {
            int origLength = 0;
            DisasterVictim[] newOccupants = new DisasterVictim[1];
            newOccupants[origLength] = occupant;
            this.occupants = newOccupants;
        }
    }

    public void removeOccupant(DisasterVictim occupant){
        int origLength = this.occupants.length;
        int newIndex = 0;
        DisasterVictim[] newOccupants = new DisasterVictim[origLength - 1];
        for (int i = 0; i < origLength; i++) {
            if (this.occupants[i] != occupant) {
                newOccupants[newIndex] = this.occupants[i];
                newIndex++;
            }
        }
        this.occupants = newOccupants;
    }

    public void addSupply(Supply supply){
        if (this.supplies != null) {
            int origLength = this.supplies.length;
            Supply[] newSupplies = Arrays.copyOf(supplies, origLength + 1);
            newSupplies[origLength] = supply;
            this.supplies = newSupplies;
        } else {
            int origLength = 0;
            Supply[] newSupplies = new Supply[1];
            newSupplies[origLength] = supply;
            this.supplies = newSupplies;
        }
    }

    public void removeSupply(Supply supply){
        int origLength = this.supplies.length;
        int newIndex = 0;
        Supply[] newSupplies = new Supply[origLength - 1];
        for (int i = 0; i < origLength; i++) {
            if (this.supplies[i] != supply) {
                newSupplies[newIndex] = this.supplies[i];
                newIndex++;
            }
        }
        this.supplies = newSupplies;
    }
}
