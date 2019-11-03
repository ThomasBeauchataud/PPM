package models.internal;

import beans.entities.OutTransaction;
import com.github.ffcfalcos.commondao.CommonDaoInterface;

public interface OutTransactionDaoInterface extends CommonDaoInterface<OutTransaction> {

    /**
     * Return an OutTransaction identified by his appName and his appTransactionId
     * @param appName String
     * @param appId int
     * @return OutTransaction
     */
    OutTransaction getByAppAndAppId(String appName, int appId);

}
