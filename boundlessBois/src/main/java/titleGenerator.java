import java.util.*;
import java.util.HashMap;

// Generates a title for a playlist using the artists' related genres
public class titleGenerator {
    private Map<String, String[]> genrePlaylistName;
    private Map<String, Integer> genreCount;

    // Constructs titleGenerator that stores the titles for the genres
    public titleGenerator() {
        genrePlaylistName = new HashMap<>();
        genreCount = new HashMap<>();
        genreCount.put("pop", 0);
        genreCount.put("rock", 0);
        genreCount.put("indie", 0);
        genreCount.put("hip hop", 0);
        genreCount.put("jazz", 0);
        genreCount.put("other", 0);
        genrePlaylistName.put("pop", ("Missing Flow, Same Songs Different Notes, Natural Life, Cheery Wonders, " +
                "Lighter Remix, generic af, Your Lies in April").split(", "));
        genrePlaylistName.put("rock", ("Sound of Fusion, Extreme Lofi, Psychedelic State, Untamed Danger," +
                " Buried Vibrations, Liftoff, Fake Protest").split(", "));
        genrePlaylistName.put("indie", ("Rupi Kaur, New Soda, Slow Burn, Sad Number Ones, " +
                "Rocky Mountain High, my guitar will go on").split(", "));
        genrePlaylistName.put("hip hop", ("Sicko Mode, Numb, Feeling Gangsta, Dope, Get Litttt stay Littt," +
                " str8 vibin").split(", "));
        genrePlaylistName.put("jazz", ("Jazzed Up, Stormy Heaven, ~Jazz Hands~, Mellow Mystery, Coffee," +
                " Diles Mavis").split(", "));
        genrePlaylistName.put("other", (" Gamer Morning, Blue Skies, Take a Hike, Depression, Acid Trip, VIBE").split(", "));
    }

    // Returns the genre with the most songs associated with it
    // Takes in a list of strings
    public String getMaxGenre(ArrayList<String> genreList) {
        for (String genre : genreList) {
            boolean contains = false;
            for (String rootGenre : genreCount.keySet()) {
                if (genre.contains(rootGenre)) {
                    genreCount.put(rootGenre, genreCount.get(rootGenre) + 1);
                    contains = true;
                    break;
                }
            }
            if (!contains) {
                genreCount.put("other", genreCount.get("other") + 1);
            }
        }
        String maxGenre = "";
        int maxInt = 0;
        for (String genre : genreCount.keySet()) {
            if (genreCount.get(genre) > maxInt) {
                maxInt = genreCount.get(genre);
                maxGenre = genre;
            }
        }
        return maxGenre;
    }

    // Chooses a title of the playlist based on the genre string
    // Parameter and return the string title
    public String nameGenerator(String genre) {
        Random rnd = new Random();
        String[] genreNames = genrePlaylistName.get(genre);
        int randomInt = 0;
        String name = null;
        if (genreNames == null) {
            return "";
        }
        else {
            while (name == null) {
                randomInt = rnd.nextInt(genreNames.length);
                String potentialName = genreNames[randomInt];
                System.out.println("Do you like the name " + potentialName + "? If yes, type 'Y'. Otherwise, type 'N' for a new name. If you want to quit, type 'quit'");
                Scanner console = new Scanner(System.in);
                String ans = console.nextLine();
                if (ans.equalsIgnoreCase("quit")) {
                    return "quit";
                }
                else if (ans.equalsIgnoreCase("y")) {
                    name = potentialName;
                }
                else if (ans.equalsIgnoreCase("n")) {
                    name = null;
                }
            }
        }
        return name;
    }

}

