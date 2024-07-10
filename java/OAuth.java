import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class OAuth {

    public static String getRequestToken() throws IOException {
        String requestUrl = "https://api.twitter.com/oauth/request_token";

        // Generate the OAuth parameters
        Map<String, String> oauthParams = generateOAuthParams();
        oauthParams.put("oauth_callback", "oob"); // Out-of-band callback for command-line apps

        // Generate the OAuth signature
        String signature = generateOAuthSignature(requestUrl, "POST", oauthParams);

        // Build the OAuth authorization header
        String oauthHeader = buildOAuthHeader(oauthParams, signature);

        // Make the request to obtain request token
        URL url = new URL(requestUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Authorization", oauthHeader);
        connection.setDoOutput(true);

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        // Parse the response to extract the request token
        String responseStr = response.toString();
        String[] tokenParts = responseStr.split("&");
        String requestToken = tokenParts[0].split("=")[1]; // oauth_token
        return requestToken;
    }

    public static String getAccessToken(String requestToken, String verifier) throws IOException {
        String requestUrl = "https://api.twitter.com/oauth/access_token";

        // Generate the OAuth parameters
        Map<String, String> oauthParams = generateOAuthParams();
        oauthParams.put("oauth_token", requestToken);
        oauthParams.put("oauth_verifier", verifier);

        // Generate the OAuth signature
        String signature = generateOAuthSignature(requestUrl, "POST", oauthParams);

        // Build the OAuth authorization header
        String oauthHeader = buildOAuthHeader(oauthParams, signature);

        // Make the request to obtain access token
        URL url = new URL(requestUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Authorization", oauthHeader);
        connection.setDoOutput(true);

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        // Return the access token and secret
        return response.toString();
    }

    public static Map<String, String> generateOAuthParams() {
        Map<String, String> params = new HashMap<>();
        params.put("oauth_consumer_key", HttpConn.CONSUMER_KEY);
        params.put("oauth_token", HttpConn.ACCESS_TOKEN);
        params.put("oauth_signature_method", "HMAC-SHA1");
        params.put("oauth_timestamp", String.valueOf(System.currentTimeMillis() / 1000));
        params.put("oauth_nonce", generateNonce());
        params.put("oauth_version", "1.0");
        return params;
    }

    public static String generateOAuthSignature(String requestUrl, String requestMethod, Map<String, String> oauthParams) throws IOException {
        // Combine the request parameters and OAuth parameters
        Map<String, String> allParams = new HashMap<>(oauthParams);

        // Sort the parameters alphabetically by key
        String[] sortedKeys = allParams.keySet().toArray(new String[0]);
        Arrays.sort(sortedKeys);

        // Construct the parameter string
        StringBuilder paramBuilder = new StringBuilder();
        for (String key : sortedKeys) {
            if (paramBuilder.length() > 0) {
                paramBuilder.append("&");
            }
            paramBuilder.append(key).append("=").append(allParams.get(key));
        }
        String parameterString = paramBuilder.toString();

        // Construct the base string
        String baseString = requestMethod + "&" + encode(requestUrl) + "&" + encode(parameterString);

        // Construct the signing key
        String signingKey = encode(HttpConn.CONSUMER_SECRET) + "&" + encode(HttpConn.ACCESS_TOKEN_SECRET);

        // Generate the HMAC-SHA1 signature
        try {
            Mac mac = Mac.getInstance("HmacSHA1");
            SecretKeySpec secret = new SecretKeySpec(signingKey.getBytes(), "HmacSHA1");
            mac.init(secret);
            byte[] digest = mac.doFinal(baseString.getBytes());
            return Base64.getEncoder().encodeToString(digest);
        } catch (Exception e) {
            throw new IOException("Failed to generate HMAC : " + e.getMessage());
        }
    }

    public static String buildOAuthHeader(Map<String, String> oauthParams, String signature) {
        StringBuilder authHeader = new StringBuilder("OAuth ");

        for (String key : oauthParams.keySet()) {
            authHeader.append(encode(key))
                    .append("=\"")
                    .append(encode(oauthParams.get(key)))
                    .append("\", ");
        }

        authHeader.append("oauth_signature=\"")
                .append(encode(signature))
                .append("\"");

        return authHeader.toString();
    }

    public static String encode(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("Cannot encode parameter", e);
        }
    }

    public static String generateNonce() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 32; i++) {
            sb.append((char)('a' + random.nextInt(26)));
        }
        return sb.toString();
    }
}
