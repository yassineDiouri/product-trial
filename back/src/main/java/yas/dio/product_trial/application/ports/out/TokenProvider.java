package yas.dio.product_trial.application.ports.out;

import java.util.Map;

public interface TokenProvider {
    String generateToken(String subject, Map<String, Object> claims);
}
