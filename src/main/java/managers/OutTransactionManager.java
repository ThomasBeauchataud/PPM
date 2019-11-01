package managers;

import beans.entities.OutTransaction;
import models.OutTransactionDaoInterface;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

@Default
@ApplicationScoped
public class OutTransactionManager implements OutTransactionManagerInterface {

    @Inject
    private OutTransactionDaoInterface outTransactionDao;

    @Override
    public void createOutTransaction(OutTransaction outTransaction) {
        outTransactionDao.insert(outTransaction);
    }

}
