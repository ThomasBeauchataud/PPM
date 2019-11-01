package models;

import beans.entities.OutTransaction;
import models.common.CommonDao;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Default
@ApplicationScoped
public class OutTransactionDao extends CommonDao<OutTransaction> implements OutTransactionDaoInterface, Serializable {

    @Override
    protected OutTransaction generateEntity(ResultSet resultSet) throws SQLException {
        return new OutTransaction(
                resultSet.getInt("index"),
                resultSet.getDouble("value"),
                resultSet.getString("app_name"),
                resultSet.getString("to")
        );
    }

    @Override
    public void insert(OutTransaction object) {
        super.insert(insert, new Object[] {object.getTo(), object.getValue(), object.getAppName()});
    }

    @Override
    public void update(OutTransaction object) { }

    @Override
    public OutTransaction getById(int id) {
        return super.getById(selectById, id);
    }

    @Override
    public List<OutTransaction> getAll() {
        return super.getAll(select);
    }

    @Override
    public void deleteById(int id) {
        super.deleteById(deleteById, id);
    }

    @Override
    public OutTransaction getByAppAndAppId(String appName, int appId) {
        return super.getOne(selectByAppAndAppId, new Object[]{appId, appId});
    }

    private static final String selectByAppAndAppId = "SELECT * out_transaction WHERE app_name = ? AND app_transaction_id = ?";
    private static final String insert = "INSERT INTO out_transaction (`to`, value, app_name) VALUES (?, ?, ?)";
    private static final String deleteById = "DELETE FROM out_transaction WHERE id = ?";
    private static final String select = "SELECT * FROM out_transaction";
    private static final String selectById = "SELECT * FROM out_transaction WHERE id = ?";

}
