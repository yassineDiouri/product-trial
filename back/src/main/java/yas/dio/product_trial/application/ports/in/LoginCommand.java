package yas.dio.product_trial.application.ports.in;

public interface LoginCommand {
    /**
     * Try login user using given email/password
     * when success return valid user token
     * else throw LoginFailureException
     *
     * @param email user email
     * @param password user password
     * @return authentication token
     */
    String login(String email, String password);
}
