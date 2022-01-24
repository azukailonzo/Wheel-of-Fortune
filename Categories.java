/*
    This file lists a couple of the categories in Wheel Of Fortune.
    Each category will have a couple of phrases (10).
    A category is chosen at random, and a phrase within the category is chosen at random
    The phrases are chosen from this site: https://wofanswers.com/
 */
import java.util.Random;

public class Categories {
    private static final String[] categories = {"What Are You Doing?", "Things", "Place", "Star & Role",
            "Song & Artist", "Title & Author", "Phrases", "Food & Drink"};

    private static final String[][] phrases = {
            {"What Are You Doing?", "BAKING COOKIES", "CHEWING GUM", "DRAWING INSPIRATION", "SLINGING MUD",
                    "BROWSING FOR ANTIQUES", "CRACKING A BULLWHIP", "RULING THE ROOST", "BATHING IN THE SUNSHINE",
                    "USING MY TURN SIGNAL", "WORKING TOWARDS A DEADLINE"},

            {"Things", "ADRENALINE RUSH", "BIPARTISAN POLITICS", "DIGITAL THERMOMETER", "GUILTY CONSCIENCE",
                    "ANIMATED FAIRY TALES", "WINNING LOTTERY NUMBERS", "YEARLY MAGAZINE SUBSCRIPTION", "MAIN SOURCE OF INCOME",
                    "ROUND THE CLOCK HOURS", "THE SMELLS OF SPRING"},

            {"Place", "BACHELOR PAD", "GALLERY SPACE", "HONOLULU HAWAII", "MIDNIGHT BUFFETS", "EXOTIC JUICE BAR",
                    "MAJESTIC MOUNTAIN RANGES", "TRENDY YOGA STUDIO", "MUSEUM OF MODERN ART", "UP ON THE ROOF",
                    "WHITE HOUSE CABINET ROOM"},

            {"Star & Role", "ZOE SALDANA AS NEYTIRI", "CHRISTIAN BALE AS BATMAN", "ELIZABETH TAYLOR AS CLEOPATRA",
                    "BEN KINGSLEY AS GANDHI", "ALEXANDER SKARSGARD AS TARZAN", "NATALIE PORTMAN AS NINA SAYERS",
                    "VIVIEN LEIGH AS SCARLETT OHARA", "GENE WILDER AS WILLY WONKA", "DENZEL WASHINGTON AS MALCOLM X",
                    "BRAD PITT AS BENJAMIN BUTTON"},

            {"Song & Artist", "AGAIN BY JANET JACKSON", "MARRY ME BY TRAIN", "TIME BY PINK FLOYD",
                    "YESTERDAY BY THE BEATLES", "HOLD UP BY BEYONCE", "TRUE COLORS BY CYNDI LAUPER",
                    "SMOKE BREAK BY CARRIE UNDERWOOD", "BLANK SPACE BY TAYLOR SWIFT", "WALLS BY KINGS OF LEON",
                    "STILL BREATHING BY GREEN DAY"},

            {"Title & Author", "AUTOBIOGRAPHY BY MORRISSEY", "EMMA BY JANE AUSTEN", "THE ODYSSEY BY HOMER",
                    "ALLEGIANT BY VERONICA ROTH", "INFERNO BY DAN BROWN", "NATURE BY RALPH WALDO EMERSON",
                    "MOBY DICK BY HERMAN MELVILLE", "THE HELP BY KATHRYN STOCKETT", "GAP CREEK BY ROBERT MORGAN",
                    "LES MISERABLE BY VICTOR HUGO"},

            {"Phrases", "CABIN FEVER", "CLOSE PROXIMITY", "PERMISSION GRANTED", "STRUCK SPEECHLESS",
                    "COUNT YOUR BLESSINGS", "FINISH YOUR THOUGHT", "POUR OVER ICE", "GETTING OUT OF DODGE",
                    "MADE IN THE USA", "SHARP AS A TACK"},

            {"Food & Drink", "BAKED ALASKA", "FRENCH LEMONADE", "HEALTHY SMOOTHIE", "SALTED ALMONDS",
                    "BISCOTTI WITH COFFEE", "FLAKES OF COCONUT", "MINI BREAKFAST QUICHE", "CHICKEN SOUP WITH NOODLES",
                    "MY FAVORITE DIPPING SAUCE", "TASTY BREADED CHICKEN FINGERS"}
    };

    private static final Random r = new Random();

    public static String getCategory(){
        int randomNumber = r.nextInt(8);
        return categories[randomNumber];
    }

    public static String getPhrase(String category){
        int row = 0; int randomNumber;
        for(int i = 0; i < 8; i++){
            if(category.equals(phrases[i][0])){
                row = i;
                break;
            }
        }
        randomNumber = r.nextInt(10) + 1;
        return phrases[row][randomNumber];
    }

}//end of class Categories






