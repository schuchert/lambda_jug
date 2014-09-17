package com.shoe.calculator;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.Arrays;

public class CalculatorSteps {
    @Given("^a user enters ([- ,\\d]+)$")
    public void a_user_enters(String numbers) throws Throwable {
        Arrays.stream(numbers.split(", ")).map(String::trim).mapToInt(Integer::valueOf).forEach(v->{});
        throw new PendingException();
    }

    @When("^the user executes (.*)$")
    public void the_user_executes(String operatorName) throws Throwable {
        throw new PendingException();
    }

    @Then("^the result should be ([-\\d]+)$")
    public void the_result_should_be(int expected) throws Throwable {
        throw new PendingException();
    }

    @Given("^a newly created calculator$")
    public void a_newly_created_calculator() throws Throwable {
        throw new PendingException();
    }

    @Then("^the next result should be ([-\\d]+)$")
    public void the_next_result_should_be(int expected) throws Throwable {
        // drop a value
        the_result_should_be(expected);
    }

    @Then("^the error should include \"(.*?)\"$")
    public void the_error_should_include(String message) throws Throwable {
        throw new PendingException();
    }
}
