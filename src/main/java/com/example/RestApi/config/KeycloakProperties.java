package com.example.RestApi.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class KeycloakProperties {

    private final String testingKeycloakUrl = "http://localhost:8085/testingAdmin";

    private final String loginKeycloakUrl = "http://localhost:8085/login";

//    private String logoutKeycloakUrl = "http://localhost:8085/logout";

    private final String registerKeycloakUrl = "http://localhost:8085/register";

    private final String registerTestingKeycloakUrl = "http://localhost:8085/registerTesting";

    private final String logoutRestapiUrl= "http://localhost:8085/logout";

    private final String makeTransaksiUrl = "http://localhost:8085/transaksi";

    private final String deleteUserUrl = "http://localhost:8085/delete";

    private final String editUserUrl =  "http://localhost:8085/editPassword";

}
