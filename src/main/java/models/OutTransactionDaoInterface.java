package models;

import beans.entities.OutTransaction;
import models.common.CommonDaoInterface;

public interface OutTransactionDaoInterface extends CommonDaoInterface<OutTransaction> {

    /**
     * Return an OutTransaction identified by his appName and his appTransactionId
     * @param appName String
     * @param appId int
     * @return OutTransaction
     */
    OutTransaction getByAppAndAppId(String appName, int appId);

}
