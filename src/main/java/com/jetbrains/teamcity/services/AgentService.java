package com.jetbrains.teamcity.services;

import com.jetbrains.teamcity.platform.Awaits;
import com.jetbrains.teamcity.platform.Timeouts;
import com.jetbrains.teamcity.services.pojos.agent.AgentItem;
import com.jetbrains.teamcity.services.pojos.agent.GetAllAgentResponse;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sound.midi.SysexMessage;

public class AgentService {
    private static final Logger log = LoggerFactory.getLogger(AgentService.class);
    private static final String AGENT_PATH = "/app/rest/agents";

    public static String authorizeAgent(String agentName, boolean authorize) {
        log.info("Invoking 'Authorize an agent' method with parameters {}, {}..", agentName, authorize);
        var response = RestSpecifications.getAcceptTextRequestSpec()
                .when()
                .body(authorize).contentType(ContentType.TEXT)
                .put(RestAssured.baseURI + AGENT_PATH + "/name:" + agentName + "/authorized");

        response.then().assertThat().statusCode(200).log().everything();

        return response.getBody().asPrettyString();
    }

    public static GetAllAgentResponse getAllUnAuthorizedAgents() {
        var locator = "locator=authorized:false";
        log.info("Invoking 'Get all agents with locator {}..", locator);
        var response = RestSpecifications.getAcceptJsonRequestSpec()
                .when()
                .get(RestAssured.baseURI + AGENT_PATH + "?" + locator);

        response.then().assertThat().statusCode(200).log().ifError();
        return response.getBody().as(GetAllAgentResponse.class);
    }

    public static void authorizeAgents() {
        // Despite the message "Teamcity server is started" in logs, server can be unavailable due-to some initialisation
        // In this case we get the error attempting to get list of agents
        // Thus we must ignore it and retry the operation until the API will be available
        boolean operationSucceed = false;
        var startTime = System.currentTimeMillis();

        while (!operationSucceed || System.currentTimeMillis() - startTime < Timeouts.SYSTEM_INIT_TIMEOUT) {
            try {
                var agents = getAllUnAuthorizedAgents().getAgents();
                if (!agents.isEmpty()) {
                    getAllUnAuthorizedAgents().getAgents()
                            .stream()
                            .map(AgentItem::getName)
                            .forEach(agentName -> authorizeAgent(agentName, true));
                }
                operationSucceed = true;
            } catch (Throwable e) {
                log.warn("Couldn't get agents list, server is not initialized, try one more time..\n{}", e.getMessage());
                Awaits.doSleep();
            }
        }
    }
}
