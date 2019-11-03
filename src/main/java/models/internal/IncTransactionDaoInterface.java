package models.internal;

import beans.entities.IncTransaction;
import com.github.ffcfalcos.commondao.CommonDaoInterface;

public interface IncTransactionDaoInterface extends CommonDaoInterface<IncTransaction> {

    /**
     * Return true if a Transaction identified by his app_transaction_id and appName exists
     * @param id int
     * @param appName String
     * @return boolean
     */
    boolean isPaidTransaction(int id, String appName);

}
