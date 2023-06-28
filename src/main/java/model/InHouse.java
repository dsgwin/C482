package model;

public class InHouse extends Part{

    public int machineId;
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
    }

    public int getCompanyName() {
        return machineId;
    }

    public void setCompanyName(int machineId) {
        this.machineId = machineId;
    }
}
