import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.SpotifyHttpManager;
import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;
import java.net.URI;

// Builds SpotifyApi object and authorization code
public class SpotifyAuthClient {
    private static final String clientId = "7fa56d70997340aea273965aed0c7dca";
    private static final String clientSecret = "15244ca2ac9a425fa2e2958211421a0c";
    private static final URI redirectUri = SpotifyHttpManager.makeUri("https://httpbin.org/");

    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setClientId(clientId)
            .setClientSecret(clientSecret)
            .setRedirectUri(redirectUri)
            .build();
    private static final AuthorizationCodeUriRequest authorizationCodeUriRequest = spotifyApi.authorizationCodeUri()
            .state("x4xkmn9pu3j6ukrs8n")
            .scope("playlist-modify-public, playlist-modify-private, playlist-read-private")
            .build();

    // Returns string representation of uri with code
    public static String authorizationCodeUri_Sync() {
        URI uri = authorizationCodeUriRequest.execute();
        return uri.toString();
    }
}