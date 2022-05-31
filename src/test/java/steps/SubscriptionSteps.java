package steps;

import base.BaseApi;
import base.model.ClientTransaction;
import base.services.SubscriptionService;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

import java.util.List;
import java.util.Map;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.assertj.core.api.Assertions.assertThat;

public class SubscriptionSteps {

    @Steps
    SubscriptionService subscriptionService;

    @Given("I get the response from the endpoint {string}")
    public void iGetTheResponseFromTheEndpoint(String endpoint) {
        subscriptionService.sendGetRequest(endpoint);
    }

    @Given("I get the response from the endpoint")
    public void iGetTheResponseFromTheEndpoint() {
        subscriptionService.sendGetRequest("https://628c1544a3fd714fd02c7a3e.mockapi.io/api/v1/client");
    }


    @Given("I get the response from the endpoint file with key {string}")
    public void iGetTheResponseFromTheEndpointWithKey(String key) {
        subscriptionService.sendGetRequest(new BaseApi().getEndpointByKey(key));
    }

    @Given("I get the endpoint by resource {string}")
    public void iGetEndpointByResource(String key) {
        subscriptionService.sendRequestByGet(new BaseApi().getEndpointByKey(key));
    }

    @When("I compare following data against client")
    public void iCompareDataWithSubscribedUsers(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        List<ClientTransaction> user_list = subscriptionService.getUserListFromService();

        for (Map<String, String> columns : rows) {
            assertThat(user_list.stream().anyMatch(userFound -> userFound.getName().equals(columns.get("name"))));
            assertThat(user_list.stream().anyMatch(userFound -> userFound.getEmail().equals(columns.get("lastName"))));
            assertThat(user_list.stream().anyMatch(userFound -> String.valueOf(userFound.isActive()).equals(columns.get("amount"))));
        }
    }

    @Then("I get the response code equals to {}")
    public void iGetTheResponseCodeEqualsTo(int responseCode) {
        restAssuredThat(response -> response.statusCode(responseCode));
    }

    @When("I create a new user using POST request body based on data table")
    public void iSendAPOSTQueryToCreateANewUserFromDataTable(DataTable dataTable) {
        ClientTransaction clientTransacBody = new ClientTransaction();
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> columns : rows) {
            clientTransacBody.setName(columns.get("name"));
            clientTransacBody.setEmail(columns.get("email"));
            clientTransacBody.setActive(Boolean.parseBoolean(columns.get("active")));

            subscriptionService.sendPostQueryWithBody(clientTransacBody);
        }
    }

    @When("I create a new user using POST request body string {string}")
    public void iSendAPOSTQueryToCreateANewUser(String requestBody) {
        subscriptionService.sendPostQuery(requestBody);
    }

    @When("I create a new user using resources with key {string}")
    public void iSendTheResponseFromTheEndpointFileWithKey(String key) {
        subscriptionService.sendPostQueryWithKey("create", key);
    }

    @When("I DELETE a user by id")
    public void iDeleteUserByIdDataTable(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> columns : rows) {
            assertThat(subscriptionService.sendDeleteQueryById(Integer.parseInt(columns.get("id"))).equals("200"));
        }
    }

    @Then("I DELETE the last user created")
    public void iDeleteTheLastUserCreated() {
        assertThat(subscriptionService.sendDeleteQueryById(subscriptionService.getLastCreatedUser().getId()).equals("200"));
    }

    @Then("I UPDATE the user by id with information")
    public void iUpdateTheUserByIdWithInformation(DataTable dataTable) {
        ClientTransaction userBody = new ClientTransaction();
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> columns : rows) {
            userBody.setName(columns.get("name"));
            userBody.setEmail(columns.get("email"));
            userBody.setActive(Boolean.parseBoolean(columns.get("subscription")));
            userBody.setId(Integer.parseInt(columns.get("id")));

            assertThat(subscriptionService.updateUserById(userBody, Integer.parseInt(columns.get("id"))).equals("200"));
        }
    }

    //here
    @When("I clean all transactions from the endpoint")
    public void iCleanAllTransactionsFromTheEndpoint(){
        //subscriptionService.sendGetRequest("200");
        subscriptionService.deleteAllClientTransactionsFromService();
    }



    //
    @Then("I check the list is empty")
    public void iCheckTheEndpointIsEmpty() {
        assertThat(subscriptionService.getUserListFromService().isEmpty()).isTrue();
    }
    /*
    @Given("I check the list of Transactions are available")
    public void iVerifyDataListAreAvailable(){
        //List<Map<String,String>> list = dataTable.asMaps(String.class, String.class);
        //List<ClientTransaction> clientTransactions = subscriptionService.getUserListFromService();

        subscriptionService.getClientListFromService(subscriptionService.getUserListFromService());
        assertThat(subscriptionService.getUserListFromService().isEmpty()).isTrue();
    }*/
}
