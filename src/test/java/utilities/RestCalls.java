package utilities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;

import com.jayway.jsonpath.JsonPath;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.junit.Assert;
import org.apache.hc.core5.http.io.entity.StringEntity;
import java.util.List;
public class RestCalls {


    public String getCall(String endpoint) throws IOException, ParseException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(endpoint);
        httpGet.setHeader("Content-type", "application/json");
        CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpGet);
        Assert.assertEquals(200,  closeableHttpResponse.getCode());
        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
        httpClient.close();
        return responseString;
    }

    public static void main(String args[]) throws IOException, ParseException {
        System.out.println("Jesus our Lord!");

        RestCalls restCalls = new RestCalls();
        String getCategoryResponse = restCalls.getCall("https://api.tmsandbox.co.nz/v1/Categories/6329/Details.json?catalogue=false");
        System.out.println(getCategoryResponse);

        String nameActualValue = JsonPath.read(getCategoryResponse, "$.Name");
        Boolean canRelistActualValue = JsonPath.read(getCategoryResponse, "$.CanRelist");

        List<String> descriptionActualValue = new ArrayList<String>();
        descriptionActualValue = JsonPath.read(getCategoryResponse, "$.Promotions[?(@.Name=='Feature')].Description");
        System.out.println("name: " + nameActualValue);
        System.out.println("CanRelist: " + canRelistActualValue);
        System.out.println("descriptionActualValue: " + descriptionActualValue.get(0));


    }

//    public String postCallWithBasicAuth(String endpoint, String body) throws IOException, ParseException {
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        HttpPost httpPost = new HttpPost(endpoint);
//
//        httpPost.setHeader("Accept", "application/json");
//        httpPost.setHeader("Content-type", "application/json");
//
//        //Set Body
//        String json = "{\"message\":\"" + body + "\"}";
//        StringEntity entity = new StringEntity(json);
//        System.out.println(entity);
//        httpPost.setEntity(entity);
//
//        CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpPost);
//        Assert.assertEquals(200,  closeableHttpResponse.getCode());
//        String responseString =  EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
//        httpClient.close();
//        return  responseString;
//    }
//
//    public String postCallWithBasicAuthentication(String endpoint, String requestBody) throws IOException, ParseException, org.json.simple.parser.ParseException {
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        HttpPost httpPost = new HttpPost(endpoint);
//        //Setting Header
//        String encoding = Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
//        httpPost.setHeader(HttpHeaders.AUTHORIZATION, "Basic " + encoding);
//        httpPost.setHeader("Accept", "application/json");
//        httpPost.setHeader("Content-type", "application/json");
//        StringEntity entity = new StringEntity(requestBody);
//        httpPost.setEntity(entity);
//        CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpPost);
//        String responseString =  EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
//        httpClient.close();
//        System.out.println("responseString: " + responseString);
//        Assert.assertEquals(200,  closeableHttpResponse.getCode());
//        return responseString;
//    }
}
