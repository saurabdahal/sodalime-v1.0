
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;

public class StaticVariables {

    public static int minWidth = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
    public static int minHeight = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
    public static int menubarHeight = 150;
    public static int initialXcoordinates = 0;
    public static int initialYcoordinates = 0;
    public static int fontSize = 18;
    

    public static Color textareabgcolor = new Color(0.200f, 0.123f, 0.123f, .5f);
    public static Color linesColor = new Color(0.123f, 0.123f, 0.123f, .5f);

    public static ArrayList<Tab> tablist = new ArrayList<Tab>();
    public static ArrayList<String> unsavedfiles = new ArrayList<String>();
    public static ArrayList<String> openedfiles = new ArrayList<String>();
    public static ArrayList<String> fromOpenfile = new ArrayList<String>();

    public static Font btnEnteredFont = new Font("Times new Roman", Font.BOLD, 18);
    public static Font btnExitedFont = new Font("Times new Roman", Font.PLAIN, 18);
    public static Font tabUnsavedTitleFont = new Font("Times new Roman", Font.BOLD, 16);
    public static Font tabSavedTitleFont = new Font("Times new Roman", Font.PLAIN, 16);
    
}
