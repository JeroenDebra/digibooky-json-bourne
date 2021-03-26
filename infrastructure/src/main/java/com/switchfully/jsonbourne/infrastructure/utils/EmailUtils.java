package com.switchfully.jsonbourne.infrastructure.utils;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.regex.Pattern;

public class EmailUtils {

    private static final Pattern VALID_EMAIL_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);


    public static boolean isValidEmail(String email) {
        StringBuffer response = new StringBuffer();
        try {
            URL urlForGetRequest = new URL("https://api.trumail.io/v2/lookups/json?email=" + email);
            String readLine = null;
            HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
            conection.setRequestMethod("GET");
            int responseCode = conection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conection.getInputStream()));
                while ((readLine = in.readLine()) != null) {
                    response.append(readLine);
                }
                in.close();
            } else {
                //error
            }
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> map = mapper.readValue(response.toString(), Map.class);
            var result = (boolean)map.get("validFormat");

        } catch (Exception ex) {

        }
        return VALID_EMAIL_REGEX.matcher(email).matches();
    }

}
