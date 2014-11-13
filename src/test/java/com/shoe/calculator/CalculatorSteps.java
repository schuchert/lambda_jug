package com.shoe.calculator;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.Arrays;

import static junit.framework.TestCase.assertNull;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class CalculatorSteps {

    private Calculator calculator;
    private RuntimeException lastException;

    @Before
    public void init() {
        calculator = new Calculator();
    }

    @After
    public void verifyCleanRun() {
        assertNull("Unexpected exception happened during execution", lastException);
    }

    @Given("^a user enters ([- ,\\d]+)$")
    public void a_user_enters(String numbers) throws Throwable {
        a_newly_created_calculator();
        Arrays.stream(numbers.split(", ")).map(String::trim).mapToInt(Integer::valueOf).forEach(calculator::enter);
    }

    @When("^the user executes (.*)$")
    public void the_user_executes(String operatorName) throws Throwable {
        try {
            calculator.execute(operatorName);
        } catch (RuntimeException e) {
            lastException = e;
        }
    }

    @Then("^the result should be ([-\\d]+)$")
    public void the_result_should_be(int expected) throws Throwable {
        assertThat(calculator.value(), is(expected));
    }

    @Given("^a newly created calculator$")
    public void a_newly_created_calculator() throws Throwable {
        lastException = null;
    }

    @Then("^the next result should be ([-\\d]+)$")
    public void the_next_result_should_be(int expected) throws Throwable {
        the_user_executes("drop");
        the_result_should_be(expected);
    }

    @Then("^the error should include \"(.*?)\"$")
    public void the_error_should_include(String message) throws Throwable {
        assertNotNull("Expected an exception with a message, but I don't have one", lastException);
        assertThat(lastException.getMessage(), containsString(message));
        lastException = null;
    }
}
