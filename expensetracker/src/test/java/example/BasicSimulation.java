package example;

import static io.gatling.javaapi.core.CoreDsl.*; // For core Gatling DSL methods
import static io.gatling.javaapi.http.HttpDsl.*; // For HTTP DSL methods

import io.gatling.javaapi.core.*; // For core Gatling classes, like Simulation
import io.gatling.javaapi.http.*;

public class BasicSimulation extends Simulation {
    HttpProtocolBuilder httpProtocolBuilder =
            http.baseUrl("REACT_APP_API_URL/user/register")
                    .acceptHeader("application/json")
                    .maxConnectionsPerHost(10)
                    .userAgentHeader("Gatling/Performance Test");
}
