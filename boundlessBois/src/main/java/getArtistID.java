import java.util.ArrayList;

// Gets artist ids
public class getArtistID {

    private static final int INDEX_SHIFTER = 400;
    private static final int TRACK_SHIFTER = 3;

    // Takes in string playlistData and returns ArrayList of artistID's
    public static ArrayList<String> findIndexes(String playlistData){
        String searchableString = playlistData;
        String keyword = "PlaylistTrack";
        ArrayList<String> playlistTracks = new ArrayList<>();
        ArrayList<String> artistID= new ArrayList<>();

        int index = searchableString.indexOf(keyword);
        while (index >= 0) {
            playlistTracks.add(searchableString.substring(index, index + INDEX_SHIFTER));
            index = searchableString.indexOf(keyword, index + keyword.length());
        }

        for(int i = 0; i < playlistTracks.size(); i++) {
            String playlistTrack = playlistTracks.get(i);
            String sub = playlistTrack.substring(playlistTrack.indexOf(("id=")) + TRACK_SHIFTER);
            String id = sub.substring(0, sub.indexOf(","));
            artistID.add(id);
        }
        return artistID;
    }
}