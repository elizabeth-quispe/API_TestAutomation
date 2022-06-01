package steps;

import base.BaseApi;
import base.model.ClientTransaction;
import base.services.TransactionService;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import org.assertj.core.api.SoftAssertions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.assertj.core.api.Assertions.assertThat;

public class TransactionSteps {

    @Steps
    TransactionService subscriptionService;

    @Given("I get the response from the endpoint {string}")
    public void iGetTheResponseFromTheEndpoint(String endpoint) {
        subscriptionService.sendGetRequest(endpoint);
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
    public void iCompareDataWithSubscribedUsers(List<Map<String, String>>rows) {

        List<ClientTransaction> user_list = subscriptionService.getUserListFromService();

        for (Map<String, String> columns : rows) {
            assertThat(user_list.stream().anyMatch(userFound -> userFound.getName().equals(columns.get("name"))));
            assertThat(user_list.stream().anyMatch(userFound -> userFound.getEmail().equals(columns.get("email"))));
            assertThat(user_list.stream().anyMatch(userFound -> String.valueOf(userFound.isActive()).equals(columns.get("active"))));
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
            clientTransacBody.setLastName(columns.get("lastname"));
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
            assertThat(subscriptionService.sendDeleteQueryById((columns.get("id"))).equals("200"));
        }
    }

    @Then("I DELETE the last user created")
    public void iDeleteTheLastUserCreated() {
        assertThat(subscriptionService.sendDeleteQueryById(subscriptionService.getLastCreatedUser().getId()).equals("200"));
    }

    @Then("I UPDATE client by id with valid account number")
    public void iUpdateTheClientByIdWithValidAccountNumber(DataTable dataTable) {
        ClientTransaction userBody = new ClientTransaction();
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        List<ClientTransaction> clientTransactionList= subscriptionService.getUserListFromService();
        for (Map<String, String> columns : rows) {
            userBody.setId((columns.get("id")));
            userBody.setAccountNumber(columns.get("accountNumber"));
            userBody.setName(columns.get("name"));
            userBody.setLastName(clientTransactionList.get(1).getLastName());//setLastName(subscriptionService.iGetTheListOfUsersFromService().getLastName());
            userBody.getEmail();

            assertThat(subscriptionService.updateUserById(userBody, Integer.parseInt(columns.get("id"))).equals("200"));
        }
    }

    @Given("I clean all transactions from the endpoint")
    public void iCleanAllTransactionsFromTheEndpoint(){
        subscriptionService.deleteAllClientTransactionsFromService();
    }

    @Then("I get the list of transactions")
    public void iGetTheListOfUsersFromService(){
        assertThat(subscriptionService.iGetTheListOfUsersFromService().equals(13)).isTrue();
    }

    //
    @Then("I check the list is empty")
    public void iCheckTheEndpointIsEmpty() {
        assertThat(subscriptionService.getUserListFromService().isEmpty()).isTrue();
    }

    @When("I create new transactions {int}")
    public  void iCreateNewTransactions(int number) {

        for (int i = 1; i < number + 1; i++) {
            int x = subscriptionService.getSizeList() + 1;
            ClientTransaction userBody = new ClientTransaction();
            userBody.setName("name " + x);
            userBody.setLastName("lastName " + x);
            userBody.setActive(true);
            userBody.setAccountNumber("6484-6880-9148-353");
            userBody.setAmount((123 * i) + x);
            userBody.setTransactionType("withdrawal " + x);
            userBody.setEmail("user" + x + "@gmail.com");
            userBody.setCountry("Perú");
            userBody.setTelephone("582.340.2765 x298");

            if(subscriptionService.getSizeList()!=0){
                assertThat(subscriptionService.iGetTheListOfUsersFromService().getEmail()).
                        isNotEqualTo("user" + x + "@gmail.com");
            }

            subscriptionService.sendPostQueryWithBody(userBody);

        }
    }

    @When("I Verify email")
    public void iVerifyEmail(){

        int y = subscriptionService.getSizeList() + 1;
        for (int x=1;x<y;x++){
            assertThat(subscriptionService.iGetTheListOfUsersFromService().getEmail()).
                    isNotEqualTo("user" + x + "@gmail.com");
        }

    }
}
