package models;

import beans.entities.IncTransaction;
import models.common.CommonDao;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Default
@ApplicationScoped
public class IncTransactionDao extends CommonDao<IncTransaction> implements IncTransactionDaoInterface {

    @Override
    protected IncTransaction generateEntity(ResultSet resultSet) throws SQLException {
        return new IncTransaction(
                resultSet.getDouble("value"),
                resultSet.getString("app_name"),
                resultSet.getInt("app_transaction_id")
        );
    }

    @Override
    public void insert(IncTransaction object) {
        super.insert(insert, new Object[]{object.getValue(), object.getAppName(), object.getAppTransactionId()});
    }

    @Override
    public void update(IncTransaction object) { }

    @Override
    public IncTransaction getById(int id) {
        return super.getById(selectById, id);
    }

    @Override
    public List<IncTransaction> getAll() {
        return super.getAll(select);
    }

    @Override
    public void deleteById(int id) {
        super.deleteById(deleteById, id);
    }

    @Override
    public boolean isPaidTransaction(int id, String appName) {
        IncTransaction incTransaction = super.getOne(selectByIdAndAppName, new Object[]{appName, id});
        return incTransaction != null;
    }

    private static final String insert = "INSERT INTO inc_transaction (value, app_name, app_transaction_id) VALUES (?, ?, ?)";
    private static final String selectById = "SELECT * FROM inc_transaction WHERE id = ?";
    private static final String selectByIdAndAppName = "SELECT * FROM inc_transaction WHERE app_name = ? and app_transaction_id = ?";
    private static final String deleteById = "DELETE FROM inc_transaction WHERE id = ?";
    private static final String select = "SELECT * FROM inc_transaction";

}
