package beans.entities;

import java.io.Serializable;

public class IncTransaction implements Serializable {

    private int id;
    private double value;
    private String appName;
    private int appTransactionId;

    public IncTransaction(double value, String appName, int appTransactionId) {
        this.value = value;
        this.appName = appName;
        this.appTransactionId = appTransactionId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public int getAppTransactionId() {
        return appTransactionId;
    }

    public void setAppTransactionId(int appTransactionId) {
        this.appTransactionId = appTransactionId;
    }
}
