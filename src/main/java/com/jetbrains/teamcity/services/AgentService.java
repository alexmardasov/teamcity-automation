package com.jetbrains.teamcity.services;

import com.jetbrains.teamcity.services.pojos.agent.AgentItem;
import com.jetbrains.teamcity.services.pojos.agent.GetAllAgentResponse;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AgentService {
    private static final Logger log = LoggerFactory.getLogger(AgentService.class);
    private static final String AGENT_PATH = "/app/rest/agents";

    public static String authorizeAgent(String agentName, boolean authorize) {
        log.info("Invoking http://localhost:8112/app/rest/projects endpoint..");
        var response = RestSpecifications.TEXT_REQUEST_SPEC
                .when()
                .body(authorize).contentType(ContentType.TEXT)
                .put(RestAssured.baseURI + AGENT_PATH + "/name:" + agentName + "/authorized");

        response.then().assertThat().statusCode(200).log().everything();

        return response.getBody().asPrettyString();
    }

    public static GetAllAgentResponse getAllUnAuthorizedAgents() {
        return RestSpecifications.JSON_REQUEST_SPEC
                .when()
                .get(RestAssured.baseURI + AGENT_PATH + "?locator=authorized:false")
                .getBody()
                .as(GetAllAgentResponse.class);
    }

    public static void authorizeAgents() {
        var agents = getAllUnAuthorizedAgents().getAgents();
        if (!agents.isEmpty()) {
            getAllUnAuthorizedAgents().getAgents()
                    .stream()
                    .map(AgentItem::getName)
                    .forEach(agentName -> authorizeAgent(agentName, true));
        }
    }
}
