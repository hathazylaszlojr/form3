package com.form3.hometest;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class StepDefinitionIT extends SpringIT {

    @When("^the client calls /v1/payments$")
    public void the_client_issues_GET_payments() throws Throwable {
        executeGet("http://localhost:8080/api/v1/payments");
    }

    @Then("^the client receives status code of (\\d+)$")
    public void the_client_receives_status_code_of(int statusCode) throws Throwable {
        assertThat("status code is correct", latestResponse.getStatusCode().value(), is(statusCode));
    }

    @And("^the client receives payments response (.+)$")
    public void the_client_receives_server_version_body(String version) throws Throwable {
        assertThat("response body is matching", latestResponse.getBody(), is(version));
    }
}