package managers;

import beans.entities.IncTransaction;
import models.internal.IncTransactionDaoInterface;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

@Default
@ApplicationScoped
public class IncTransactionManager implements IncTransactionManagerInterface {

    @Inject
    private IncTransactionDaoInterface incTransactionDao;

    @Override
    public void createIncTransaction(IncTransaction incTransaction) {
        if(!isPaidIncTransaction(incTransaction.getAppName(), incTransaction.getAppTransactionId())) {
            incTransactionDao.insert(incTransaction);
        }
    }

    @Override
    public boolean isPaidIncTransaction(String appName, int id) {
        return incTransactionDao.isPaidTransaction(id, appName);
    }

}
