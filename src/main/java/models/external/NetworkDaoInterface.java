package models.external;

public interface NetworkDaoInterface {

    /**
     * Update a Token for an Application
     * @param token String
     * @param appName String
     */
    void updateToken(String token, String appName);

    /**
     * Return the Token of an Application
     * @param appName String
     * @return String
     */
    String getToken(String appName);

}
