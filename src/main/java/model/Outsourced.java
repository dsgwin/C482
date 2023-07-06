package model;

/**
 * @author
 * Duncan Gwin
 * dgwin4@wgu.edu
 * 008698673
 */
public class Outsourced extends Part{

    public String companyName;

    /**
     * The constructor for the outsourced part class.
     * @param id Sets the Part ID.
     * @param name Sets the part name.
     * @param price Sets the part price.
     * @param stock Sets the part inventory level.
     * @param min Sets the minimum allowed inventory.
     * @param max Sets the maximum allowed inventory.
     * @param companyName Sets the Company Name the outsourced part is purchase from.
     */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        setCompanyName(companyName);
    }

    /**
     * Method to get the Company Name of outsourced part.
     * @return Returns Company Name.
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * Method to set the Company name of outsourced part.
     * @param companyName The Company Name o set.
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
