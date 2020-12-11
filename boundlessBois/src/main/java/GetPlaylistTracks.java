import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.*;
import com.wrapper.spotify.requests.data.artists.GetArtistRequest;
import com.wrapper.spotify.requests.data.playlists.ChangePlaylistsDetailsRequest;
import com.wrapper.spotify.requests.data.playlists.GetPlaylistRequest;
import org.apache.hc.core5.http.ParseException;
import java.io.IOException;
import java.util.ArrayList;

// Sends API request to Spotify for playlist information
public class GetPlaylistTracks {

    // Takes String token and String id,
    // Analyzes playlist and sets a new title
    // Catches IO, API request, and parsing exceptions
    public static void getPlaylist_Sync(String token, String id) {
        SpotifyApi spotifyApi = new SpotifyApi.Builder()
                .setAccessToken(token)
                .build();
        GetPlaylistRequest getPlaylistRequest = spotifyApi.getPlaylist(id)
                .fields("name, tracks")
                .build();

        try {
            Playlist playlist = getPlaylistRequest.execute();

            System.out.println("Old Name: " + playlist.getName());
            String jsonPlaylistInfo = playlist.getTracks().toString();
            ArrayList<String> artistIds = getArtistID.findIndexes(jsonPlaylistInfo);
            ArrayList<String> genreList = new ArrayList<>();

            for (String x : artistIds) {
                genreList.add(getArtist_Sync(x, spotifyApi));
            }

            titleGenerator title = new titleGenerator();
            String playlistName = title.nameGenerator(title.getMaxGenre(genreList));
            if (playlistName.equals("quit")) {
                System.out.println("Have a good day!");
            }
            else if (playlistName.equals("")) {
                System.out.println("Your playlist is empty! Please add songs and try again");
            }
            else {
                changePlaylistsDetails_Sync(playlistName, id, spotifyApi);
                System.out.println("New Name: " + playlistName);
            }

        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Takes String artistID and SpotifyApi object.
    // Builds artist object and returns first genre associated with artist.
    private static String getArtist_Sync(String artistID, SpotifyApi spotifyApi) {

        GetArtistRequest getArtistRequest = spotifyApi.getArtist(artistID)
                .build();
        try {
            Artist artist = getArtistRequest.execute();

            if (artist.getGenres().length > 0) {
                return artist.getGenres()[0];
            }

        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return "";
    }

    // Takes String playlist, String id, and SpotifyApi and changes playlist name
    private static void changePlaylistsDetails_Sync(String playlistName, String id, SpotifyApi spotifyApi) {
        ChangePlaylistsDetailsRequest changePlaylistsDetailsRequest = spotifyApi
                .changePlaylistsDetails(id)
                .name(playlistName)
                .build();
        try {
            changePlaylistsDetailsRequest.execute();
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}