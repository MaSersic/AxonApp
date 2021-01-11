package fer.hr.foodapp.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequestHelper {
    public static String hostURL = "http://localhost:8082/";

    public static String postRequest (String dest, Object body) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        String urlString = hostURL + dest;
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(urlString);

        String json = mapper.writeValueAsString(body);

        StringEntity requestEntity = new StringEntity(
                json,
                ContentType.APPLICATION_JSON
        );
        request.setEntity(requestEntity);

        try {
            HttpResponse response = client.execute(request);
            return Integer.toString(response.getStatusLine().getStatusCode());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getRequest (String dest, String urlParameters) {
        String urlString = hostURL + dest + urlParameters;
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(urlString);

        try {
            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();

            String content = EntityUtils.toString(entity);
            return content;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
