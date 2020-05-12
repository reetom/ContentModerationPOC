package com.service;

import java.net.URI;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.Constants.SubscriptionKeys;


public class ImageModeration 
{
    public static void main(String[] args) 
    {
        HttpClient httpclient = HttpClients.createDefault();

        try
        {
            URIBuilder builder = new URIBuilder("https://reetom.cognitiveservices.azure.com/contentmoderator/moderate/v1.0/ProcessImage/Evaluate");

            builder.setParameter("CacheImage", "false");

            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);
            request.setHeader("Content-Type", "application/json");
            request.setHeader("Ocp-Apim-Subscription-Key", SubscriptionKeys.AZYRE_OCP_APIM_SUBSCRIPTION_KEY);


            // Request body
            StringEntity reqEntity = new StringEntity("{\n" + 
            		"  \"DataRepresentation\":\"URL\",\n" + 
            		"  \"Value\":\"https://i.pinimg.com/originals/fe/17/d1/fe17d1295bc8237bf72234cf877b0cd6.jpg\"\n" + 
            		"}");
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
