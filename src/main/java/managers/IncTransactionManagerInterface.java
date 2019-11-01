package managers;

import beans.entities.IncTransaction;

public interface IncTransactionManagerInterface {

    /**
     * Create an IncTransaction
     * @param incTransaction IncTransaction
     */
    void createIncTransaction(IncTransaction incTransaction);

    /**
     * Return true if a IncTransaction exists
     * @param appName String
     * @param id int
     * @return boolean
     */
    boolean isPaidIncTransaction(String appName, int id);

}
