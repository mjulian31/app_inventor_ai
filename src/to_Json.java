
public class to_Json {
    private static String create_Json(String[] cancel_intent, String[] help_intent, String[] stop_intent, String[] connect_to_app_inventor){

        String out = "{" +
                "\n\t\"interaction model\":{" +
                "\n\t\t\"language model\":{" +
                "\n\t\t\t\"invocation name\": \"inventor codi\"" +
                "\n\t\t\t\"intents\": [" +
                "\n\t\t\t\t{" +
                "\n\t\t\t\t\t\"name\": \"AMAZON.CancelIntent\"," +
                "\n\t\t\t\t\t\"samples\": [";
        for(String c : cancel_intent){
            out += "\n\t\t\t\t\t\t\"" + c + "\",";
        }
        out = out.substring(0, out.length() - 2);
        out += "\n\t\t\t\t\t]" +
                "\n\t\t\t\t}," +
                "\n\t\t\t\t{" +
                "\n\t\t\t\t\t\"name\": \"AMAZON.HelpIntent\"," +
                "\n\t\t\t\t\t\"samples\": [";
        for(String h : help_intent){
            out += "\n\t\t\t\t\t\t\"" + h + "\",";
        }
        out = out.substring(0, out.length() - 2);
        out += "\n\t\t\t\t\t]" +
                "\n\t\t\t\t}," +
                "\n\t\t\t\t{" +
                "\n\t\t\t\t\t\"name\": \"AMAZON.StopIntent\"," +
                "\n\t\t\t\t\t\"samples\": [";
        for(String s : stop_intent){
            out += "\n\t\t\t\t\t\t\"" + s + "\",";
        }
        out = out.substring(0, out.length() - 2);
        out += "\n\t\t\t\t\t]" +
                "\n\t\t\t\t}," +
                "\n\t\t\t\t{" +
                "\n\t\t\t\t\t\"name\": \"ConnectToAppInventor\"," +
                "\n\t\t\t\t\t\"slots\": []," +
                "\n\t\t\t\t\t\"samples\": [";
        for(String a : connect_to_app_inventor){
            out += "\n\t\t\t\t\t\t\"" + a + "\",";
        }
        out = out.substring(0, out.length() - 2);
        out += "\n\t\t\t\t\t]" +
                "\n\t\t\t\t}" +
                "\n\t\t\t}," +
                "\n\t\t\t\"types\": []" +
                "\n\t\t}" +
                "\n\t}" +
                "\n}";

        return out;
    }

    public static void main(String[] args){
        String[] cancel = {
                "don't do anything",
                "don't connect",
                "stop",
                "cancel"};
        String[] help = {
                "what does inventor codi do",
                "what does this do",
                "how do I connect",
                "I don't understand",
                "help me",
                "help"};
        String[] stop = {
                "I'm done",
                "end",
                "end connection",
                "break",
                "stop"};
        String[] app_inventor = {
                "to do something with app inventor",
                "to start connecting",
                "to make a connection",
                "to start a connection",
                "to do something on the server",
                "to do something with cloud db",
                "to do something in app inventor",
                "to connect to the app inventor server",
                "to connect to the server",
                "to connect to app inventor",
                "to connect to cloud db",
                "to connect"};

        System.out.println(create_Json(cancel, help, stop, app_inventor));

    }
}
