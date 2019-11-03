package webservice;

import java.io.Serializable;

class User implements Serializable {

    private String email;

    User() { }

    String getEmail() {
        return email;
    }

    void setEmail(String email) {
        this.email = email;
    }

}
