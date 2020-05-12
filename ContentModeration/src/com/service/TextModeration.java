package com.service;

 // This sample uses the Apache HTTP client from HTTP Components (http://hc.apache.org/httpcomponents-client-ga/)
import java.net.URI;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.Constants.SubscriptionKeys;

public class TextModeration

{
    public static void main(String[] args) 
    {
        HttpClient httpclient = HttpClients.createDefault();

        try
        {
            URIBuilder builder = new URIBuilder("https://reetom.cognitiveservices.azure.com/contentmoderator/moderate/v1.0/ProcessText/Screen");

            builder.setParameter("autocorrect", "true");
            builder.setParameter("PII", "true");
            builder.setParameter("listId", "");
            builder.setParameter("classify", "true");
            builder.setParameter("language", "eng");

            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);
            request.setHeader("Content-Type", "text/plain");
            request.setHeader("Ocp-Apim-Subscription-Key", SubscriptionKeys.AZYRE_OCP_APIM_SUBSCRIPTION_KEY);
    


            // Request body
            StringEntity reqEntity = new StringEntity("This is a FUCKING SHITTY CRAPPY REVIEW... Shit show...  someone@gmail.com.. There is a spell!ng m!stake heree.");
            request.setEntity(reqEntity);

            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null) 
            {
                System.out.println(EntityUtils.toString(entity));
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
