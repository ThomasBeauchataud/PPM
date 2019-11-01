package managers;

public interface PayPalManagerInterface {

    /**
     * Pay a PayPal user identified by his email
     * @param email String
     * @param value int
     */
    void pay(String email, double value);

}
