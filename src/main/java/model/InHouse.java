package model;


/**
 * @author
 * Duncan Gwin
 * dgwin4@wgu.edu
 * 008698673
 */

/**
 *  The InHouse class is a subclass of the Part class.
 */
public class InHouse extends Part{

    public int machineId;

    /**
     * The constructor for the InHouse part class.
     * @param id Sets the Part ID.
     * @param name Sets the part name.
     * @param price Sets the part price.
     * @param stock Sets the part inventory level.
     * @param min Sets the minimum allowed inventory.
     * @param max Sets the maximum allowed inventory.
     * @param machineId Sets the MachineID the inhouse part will be manufactured on.
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        setMachineId(machineId);
    }

    /**
     * Method to get the Machine ID of inhouse part.
     * @return Returns Machine ID.
     */
    public int getMachineId() {
        return machineId;
    }

    /**
     * Method to set the Machine ID of inhouse part.
     * @param machineId The Machine ID to set.
     */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }
}
