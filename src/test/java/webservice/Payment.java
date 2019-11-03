package webservice;

import java.io.Serializable;

class Payment implements Serializable {

    private User user;
    private double value;

    Payment(User user, double value) {
        this.user = user;
        this.value = value;
    }

   User getUser() {
        return user;
    }

   double getValue() {
        return value;
    }

}
