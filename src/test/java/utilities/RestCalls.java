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
}
