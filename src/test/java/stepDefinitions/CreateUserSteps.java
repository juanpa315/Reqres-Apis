package stepDefinitions;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.ArgumentMatchers.isNotNull;

import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.UserData;
import net.serenitybdd.rest.Ensure;
import net.serenitybdd.screenplay.Actor;
import questions.ResponseCode;
import tasks.RegisterUser;
import utils.BaseSteps;

public class CreateUserSteps {
    private Actor actor;

    @Before
    public void setUp() {
        actor = BaseSteps.createActor();
    }

    @When("He sends the username and job")
    public void heSendsTheUsernameAndJob() {
        UserData newUser = new UserData();

        newUser.setName("morpheusJP3");
        newUser.setJob("leader");

        actor.attemptsTo(
                RegisterUser.withInfo(newUser));
    }

    @Then("The API create his user with the data send by Juan")
    public void theAPICreateHisUserWithTheDataSendByJuan() {


        Ensure.that("The id of client was created", response -> response.body("id", isNotNull()));
        Ensure.that("The name of client was created", response -> response.body("name", isNotNull()));
        Ensure.that("The Job of client was created", response -> response.body("job", isNotNull()));

        actor.should(
                seeThat("The status code", ResponseCode.was(), equalTo(201)));

    }

}
