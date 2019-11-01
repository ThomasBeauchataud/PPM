package beans.entities;

import java.io.Serializable;

public class OutTransaction implements Serializable {

    private int id;
    private double value;
    private String appName;
    private String to;

    public OutTransaction(double value, String appName, String to) {
        this.value = value;
        this.appName = appName;
        this.to = to;
    }

    public OutTransaction(int id, double value, String appName, String to) {
        this.id = id;
        this.value = value;
        this.appName = appName;
        this.to = to;
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

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

}
