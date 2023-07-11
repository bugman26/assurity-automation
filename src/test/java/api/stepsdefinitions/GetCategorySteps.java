package api.stepsdefinitions;

import com.jayway.jsonpath.JsonPath;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.hc.core5.http.ParseException;
import org.junit.Assert;
import utilities.RestCalls;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetCategorySteps {

    private String endpoint;
    private String getCategoryResponse;

    @Given("Get category request endpoint: {string}")
    public void get_category_request_endpoint(String getCategoryEndpoint) throws InterruptedException {
        endpoint = getCategoryEndpoint;
    }

    @When("User sends request to get category")
    public void user_sends_request_to_get_category() throws IOException, ParseException {
        RestCalls restCalls = new RestCalls();
        getCategoryResponse = restCalls.getCall(endpoint);
    }

    @Then("Name value on the response is {string}")
    public void name_value_on_the_response(String expectedNameValue) throws IOException, ParseException {
        String nameActualValue = JsonPath.read(getCategoryResponse, "$.Name");
        Assert.assertEquals(nameActualValue, expectedNameValue);
    }

    @Then("CanRelist value on the response is True")
    public void canRelist_value_on_the_response() throws IOException, ParseException {
        Boolean canRelistActualValue = JsonPath.read(getCategoryResponse, "$.CanRelist");
        Assert.assertEquals(canRelistActualValue, Boolean.TRUE);
    }

    @Then("Promotions with the Name:Feature has description equal to {string}")
    public void promotions_with_the_name_feature_has_description_equal_to(String descExpectedValue) {
        List<String> descriptionActualValue = new ArrayList<String>();
        descriptionActualValue = JsonPath.read(getCategoryResponse, "$.Promotions[?(@.Name=='Feature')].Description");
        Assert.assertEquals(descriptionActualValue.get(0), descExpectedValue);
    }










}
