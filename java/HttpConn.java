import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;


public class HttpConn {

    public static String CONSUMER_KEY = "g8d6XFVtAFl4Mee2RssJXBBvI";
    public static String CONSUMER_SECRET = "8ISTkHkriGys4b74iyz4pjXvon22RCoSjzPh01oyVSDaK7zFVS";
    public static String ACCESS_TOKEN = "1802014368399548416-JQBx90Mw1uPVzd9NvxHaqvSHak9xx1";
    public static String ACCESS_TOKEN_SECRET = "qkILtDZTrSPGVj1MMG4evoBnssSDCJ8VDNP1MBmONbHrn";

    public static void logout() {
        // Clear the tokens locally
        ACCESS_TOKEN = "";
        ACCESS_TOKEN_SECRET = "";
        System.out.println("Berhasil Log Out. Tokens dibersihkan.");
    }

    public static void sendTweet(String body) throws IOException {
        String tweetUrl = "https://api.twitter.com/2/tweets";

        // Generate the OAuth parameters
        Map<String, String> oauthParams = OAuth.generateOAuthParams();

        // Generate the OAuth signature
        String signature = OAuth.generateOAuthSignature(tweetUrl, "POST", oauthParams);

        // Build the OAuth authorization header
        String oauthHeader = OAuth.buildOAuthHeader(oauthParams, signature);

        //System.out.println(oauthHeader);


        URL url = new URL(tweetUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Authorization", oauthHeader);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
        writer.write(body);
        writer.flush();

        int responseCode = connection.getResponseCode();
        System.out.println("Response code: " + responseCode);


        writer.close();
        connection.disconnect();
    }


    public static String login() throws IOException {
        String authorizationUrl = "https://api.twitter.com/oauth/authorize";
        // Step 1: Obtain a request token
        String requestToken = OAuth.getRequestToken();

        // Step 2: Get authorization from the user
        String authorizationUrlWithToken = authorizationUrl + "?oauth_token=" + requestToken;
        System.out.println("Buka Url dan beri Akses Aplikasi:");
        System.out.println(authorizationUrlWithToken);

//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Enter the PIN / OAuth verifier: ");
//        String verifier = scanner.nextLine().trim();
//        scanner.close();

        // Step 3: Exchange the request token for an access token
//        String accessToken = OAuth.getAccessToken(requestToken, verifier);
//
//        // Step 4: Store the access token and secret for future use
//        ACCESS_TOKEN = accessToken.split("&")[0].split("=")[1];
//        ACCESS_TOKEN_SECRET = accessToken.split("&")[1].split("=")[1];

        System.out.println("Access token: " + ACCESS_TOKEN);
        System.out.println("Access token secret: " + ACCESS_TOKEN_SECRET);

        return authorizationUrl + "?oauth_token=" + requestToken;
    }


    public static void deleteTweet(String tweetId) throws IOException {
        String apiUrl = "https://api.twitter.com/2/tweets/" + tweetId;

        // Generate the OAuth parameters
        Map<String, String> oauthParams = OAuth.generateOAuthParams();

        // Generate the OAuth signature
        String signature = OAuth.generateOAuthSignature(apiUrl, "DELETE", oauthParams);

        // Build the OAuth authorization header
        String oauthHeader = OAuth.buildOAuthHeader(oauthParams, signature);

        // Make the DELETE request to delete the tweet
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("DELETE");
        connection.setRequestProperty("Authorization", oauthHeader);

        // Handle the response
        int responseCode = connection.getResponseCode();
        System.out.println("Response code: " + responseCode);


        connection.disconnect();
    }

    public static String getUserDetails() throws IOException {
        String apiUrl = "https://api.twitter.com/2/users/me";

        // Generate the OAuth parameters
        Map<String, String> oauthParams = OAuth.generateOAuthParams();

        // Generate the OAuth signature
        String signature = OAuth.generateOAuthSignature(apiUrl, "GET", oauthParams);

        // Build the OAuth authorization header
        String oauthHeader = OAuth.buildOAuthHeader(oauthParams, signature);

        // Make the request
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", oauthHeader);

        // Handle response
        int responseCode = connection.getResponseCode();
        if (responseCode < 299) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            System.out.println(responseCode);
            System.out.println("User details:");
            System.out.println(response);

            return response.toString();
        } else {
            return "Gagal mendapatkan detail user. Response code: " + responseCode;
        }
    }
}

