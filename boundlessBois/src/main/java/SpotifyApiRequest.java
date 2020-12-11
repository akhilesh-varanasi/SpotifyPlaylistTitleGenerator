import java.net.URI;
import java.awt.Desktop;
import java.util.Scanner;

// Makes a request to spotify api and sets a new playlist title
public class SpotifyApiRequest {

    private static final int PLAYLIST_ID_SHIFT = 34;

    public static void main(String[] args) throws Exception {
        String base_url = SpotifyAuthClient.authorizationCodeUri_Sync();

        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            Desktop.getDesktop().browse(new URI(base_url));
        }

        Scanner console = new Scanner(System.in); // enter redirect url and use it to get code
        System.out.println("Hi! Welcome to the TitleGenerator app! Please enter the entire redirect url in the console");
        String new_url = console.nextLine();
        new_url = new_url.substring(new_url.indexOf("=") + 1, new_url.indexOf("&"));

        String authToken = SpotifyAuthCode.authorizationCode_Sync(new_url);
        System.out.println("Please enter the link to the spotify playlist you want to rename!");
        Scanner console2 = new Scanner(System.in);
        String playlistID = console2.nextLine();
        playlistID = playlistID.substring(PLAYLIST_ID_SHIFT, playlistID.indexOf("?"));
        GetPlaylistTracks.getPlaylist_Sync(authToken, playlistID);
    }
}
