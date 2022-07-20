package desafios.aula1;

public class terminalEnfeitado {
    public static String prettyPrint(String title, String info) {
        if (title.equalsIgnoreCase("nota")) info = starRating(info);
        title = (title.isEmpty())? "" :titleFormat(title) + ": ";

        return title + "\u001b[1m" + info + "\u001b[m";
    }

    private static String titleFormat(String title) {
        if (title.isEmpty()) return "";

        return title.substring(0,1).toUpperCase() + title.substring(1).toLowerCase();
    }

    private static String starRating(String info) {
        int nota = Integer.parseInt(info.substring(0, info.indexOf(".")));

        return "\u2B50".repeat(Math.max(0, nota)) +"";
    }
}
