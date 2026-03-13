package utils;

public class OSValidator {
/*. It is marked private to encapsulate the data, static so it is evaluated only once when the framework boots,
and final because the operating system will never change during a single execution run.*/
        public static final String os = System.getProperty("os.name").toLowerCase();
//Returning true or false allows other parts of your framework to make immediate, clean decisions using simple if statements.
    public static boolean isWindows()
    {

        return os.contains("win");
    }
    public static boolean isMac()
    {

        return os.contains("win");
    }
    public static boolean isUnix()
    {

        return os.contains("nix") || os.contains("nux") || os.contains("aix");
    }
}
