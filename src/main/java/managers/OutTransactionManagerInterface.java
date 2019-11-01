package managers;

import beans.entities.OutTransaction;

public interface OutTransactionManagerInterface {

    /**
     * Create an OutTransaction
     * @param outTransaction OutTransaction
     */
    void createOutTransaction(OutTransaction outTransaction);

}
