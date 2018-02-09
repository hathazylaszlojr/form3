package com.form3.hometest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.form3.hometest.domain.AccountVO;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class StepDefinitionIT extends SpringIT {

    private final String BASE_URL = "http://localhost:8080/api/";

    @Autowired
    private ObjectMapper objectMapper;

    @Given("^Accounts endpoint having account with id (\\d+)")
    public void accounts_endpoint_with_account(int accountId) throws JsonProcessingException {
        executePost(BASE_URL  + "v1/accounts", createAccount(accountId));
    }
    
    @When("^the client calls /(.+)$")
    @And("^the client is calling /(.+)$")
    public void the_client_issues_GET(String endpoint) throws Throwable {
        executeGet(BASE_URL + endpoint);
    }

    @Then("^the client receives status code of (\\d+)$")
    public void the_client_receives_status_code_of(int statusCode) throws Throwable {
        assertThat("status code is correct", latestResponse.getStatusCode().value(), is(statusCode));
    }

    @And("^the client receives payments response (.+)$")
    public void the_client_receives_payments_response(String paymentsResponse) throws Throwable {
        assertThat("response body is matching", latestResponse.getBody(), is(paymentsResponse));
    }
    

    @When("^the client calls update account with id (\\d+) to name (.+)")
    public void the_client_calls_update_account_with_id(int accountId, String newName) throws JsonProcessingException {
        executePut(BASE_URL + "v1/accounts/" + accountId, createAccount(accountId, newName));
    }

    @When("^the client calls delete account with id (\\d+)")
    public void the_client_calls_delete_account_with_id(int accountId) throws JsonProcessingException {
        executeDelete(BASE_URL + "v1/accounts/" + accountId);
    }
    
    
    @And("^the client receives (\\d+) accounts containing account with id (\\d+)")
    public void the_client_receives_accounts_response_with_id(int numberOfAccounts, int accountId) throws Throwable {
        List<AccountVO> accountVO = objectMapper.readValue(latestResponse.getBody(), new TypeReference<List<AccountVO>>() {});

        assertThat("Number of elements is matching", accountVO.size(), is(numberOfAccounts));
        assertThat("One of the accounts has the required id", accountVO.stream().anyMatch(account -> account.getAccountNumber() == accountId), is(true));
    }

    @And("^the client receives (\\d+) accounts containing account with name (.+)")
    public void the_client_receives_accounts_response_with_name(int numberOfAccounts, String name) throws Throwable {
        List<AccountVO> accountVO = objectMapper.readValue(latestResponse.getBody(), new TypeReference<List<AccountVO>>() {});

        assertThat("Number of elements is matching", accountVO.size(), is(numberOfAccounts));
        assertThat("One of the accounts has the required id", accountVO.stream().anyMatch(account -> account.getAccountName().equals(name)), is(true));
    }
    
    
    private AccountVO createAccount(int accountNumber) {
        return createAccount(accountNumber, null);
    }
    
    private AccountVO createAccount(int accountNumber, String name) {
        AccountVO accountVO = new AccountVO();
        accountVO.setAccountNumber(accountNumber);
        accountVO.setAccountName(name);
        return accountVO;
    }
    
}