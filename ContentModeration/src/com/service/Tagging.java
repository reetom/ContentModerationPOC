package com.service;

import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.Constants.SubscriptionKeys;
public class Tagging {
	
	// **********************************************
    // *** Update or verify the following values. ***
    // **********************************************

    // Add your Computer Vision subscription key and endpoint to your environment variables.
    // After setting, close and then re-open your command shell or project for the changes to take effect.
    //static String subscriptionKey = System.getenv("COMPUTER_VISION_SUBSCRIPTION_KEY");
    //static String endpoint = ("COMPUTER_VISION_ENDPOINT");
	

	//For local setup
    static String subscriptionKey = SubscriptionKeys.COMPUTER_VISION_SUBSCRIPTION_KEY1;
    static String endpoint = "https://westcentralus.api.cognitive.microsoft.com/vision";
    private static final String uriBase = endpoint + 
            "/v2.1/analyze";
    //private static final String imageToAnalyze =
          //  "https://upload.wikimedia.org/wikipedia/commons/1/12/Broadway_and_Times_Square_by_night.jpg";
            
    private static final String imageToAnalyze = "https://slack-imgs.com/?c=1&o1=ro&url=https%3A%2F%2Fcdn-fsly.yottaa.net%2F5c8fa1e52bb0ac74cc828a3e%2Fa954f3508a360137f0e97e3461d3e37f.90bd97202bb20137fdc3123dfe2baf36.yottaa.net%2Fv~4b.2a9%2Fadaptivemedia%2Frendition%2F89628.tif%3Fid%3D8122fc9e37c3fa6829f18e3716850bd2016ac1f8%26ht%3D650%26wd%3D1004%26version%3D1%26clid%3Dpim%26yocs%3D_" ;     
            
    public static void main(String[] args) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        try {
            URIBuilder builder = new URIBuilder(uriBase);

            // Request parameters. All of them are optional.
            builder.setParameter("visualFeatures", "Categories,Description,Color");
            builder.setParameter("language", "en");

            // Prepare the URI for the REST API method.
            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);

            // Request headers.
            request.setHeader("Content-Type", "application/json");
            request.setHeader("Ocp-Apim-Subscription-Key", subscriptionKey);

            // Request body.
            StringEntity requestEntity =
                    new StringEntity("{\"url\":\"" + imageToAnalyze + "\"}");
            request.setEntity(requestEntity);

            // Call the REST API method and get the response entity.
            HttpResponse response = httpClient.execute(request);
            System.out.println(response.toString());
            HttpEntity entity =  response.getEntity();
            if (entity != null) {
                // Format and display the JSON response.
                String jsonString = EntityUtils.toString(entity);
                JSONObject json = new JSONObject(jsonString);
                System.out.println("REST Response:\n");
                System.out.println(json.toString(2));
            }

        } catch (Exception e) {
            // Display error message.
            System.out.println(e.getMessage());

        }
    }

}
