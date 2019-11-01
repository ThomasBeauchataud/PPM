package managers;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;

@Default
@ApplicationScoped
public class PayPalManager implements PayPalManagerInterface {

    @Override
    public void pay(String email, double value) {
        //TODO implements
    }

}
