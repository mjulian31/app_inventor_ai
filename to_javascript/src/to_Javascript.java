public class to_Javascript {
    private static String create_javascript(String skill_name, String launch_message, String help_message, String stop_message, String success_output){
        String out = "/**\n" +
                "* This sample demonstrates a simple skill built with the Amazon Alexa Skills\n" +
                "* nodejs skill development kit.\n" +
                "*\n" +
                "* This skill can connect with App Inventor\n" +
                "**/\n" +
                "\n" +
                "'use strict';\n" +
                "\n" +
                "//=========================================================================================================================================\n" +
                "//Constants:\n" +
                "//=========================================================================================================================================\n" +
                "\n" +
                "// Replace with your app ID (OPTIONAL).  You can find this value at the top of\n" +
                "// your skill's page on http://developer.amazon.com.  Make sure to enclose your\n" +
                "// value in quotes, like this: const APP_ID =\n" +
                "// 'amzn1.ask.skill.bb4045e6-b3e8-4133-b650-72923c5980f1';\n" +
                "const APP_ID = undefined;\n" +
                "\n" +
                "// for speech and alexa app:\n" +
                "const SKILL_NAME = '" + skill_name + "';\n" +
                "const LAUNCH_MESSAGE = '" + launch_message + "';\n" +
                "const HELP_MESSAGE = '" + help_message + "';\n" +
                "const HELP_REPROMPT = 'What can I help you with?';\n" +
                "const STOP_MESSAGE = '" + stop_message + "';\n" +
                "const SUCCESS_OUTPUT = '" + success_output + "';\n" +
                "\n" +
                "// for App Inventor and CloudDB:\n" +
                "const urlHostPort = 'rediss://clouddb.appinventor.mit.edu:6381';\n" +
                "const authKey = require('./authKey');\n" +
                "const redis = require('redis');\n" +
                "const SET_SUB_SCRIPT_SHA1 = '765978e4c340012f50733280368a0ccc4a14dfb7';\n" +
                "const SET_SUB_SCRIPT = 'local key = KEYS[1];' +\n" +
                "\t'local value = ARGV[1];' +\n" +
                "\t'local topublish = cjson.decode(ARGV[2]);' +\n" +
                "\t'local project = ARGV[3];' +\n" +
                "\t'local newtable = {};' +\n" +
                "\t'table.insert(newtable, key);' +\n" +
                "\t'table.insert(newtable, topublish);' +\n" +
                "\t'redis.call(\"publish\", project, cjson.encode(newtable));' +\n" +
                "\t'return redis.call(\\'set\\', project .. \":\" .. key, value);';\n" +
                "\n" +
                "// for connecting with Alexa:\n" +
                "const Alexa = require('alexa-sdk');\n" +
                "\n" +
                "//=========================================================================================================================================\n" +
                "// Editing anything below this line might break your skill.\n" +
                "//=========================================================================================================================================\n" +
                "\n" +
                "const handlers = {\n" +
                "\t'LaunchRequest': function() {\n" +
                "\t\tthis.response.speak(LAUNCH_MESSAGE);\n" +
                "\t\tthis.emit(':responseReady');\n" +
                "\t},\n" +
                "\t'ConnectToAppInventor': function() {\n" +
                "\t\t// for lambda redis\n" +
                "\t\tlet client = redis.createClient(\n" +
                "\t\t\turlHostPort, {'password': authKey.getAuthKey(), 'tls': {}}); // todo\n" +
                "\n" +
                "\t\tlet response;\n" +
                "\t\tlet error;\n" +
                "\t\tlet tag = 'alexa';\n" +
                "\t\tlet value = 'alexaCalledAppInventor';\n" +
                "\t\tlet projectName = 'Lambda_CloudDB_Redis_Test';\n" +
                "\t\t// tests setting and PUBLISHING in clouddb (this will be noticed by App\n" +
                "\t\t// Inventor components subscribed to the updates)\n" +
                "\t\tclient.eval(\n" +
                "\t\t\t// Calling convention: tag, value, json encoded list of values, project,\n" +
                "\t\t\t// ...\n" +
                "\t\t\tSET_SUB_SCRIPT, 1, tag, value, JSON.stringify([value]), projectName,\n" +
                "\t\t\tfunction(e, r) {\n" +
                "\t\t\t\tif (e) {\n" +
                "\t\t\t\t\tconsole.error('Something went wrong with client.eval: ', e);\n" +
                "\t\t\t\t\terror = e;\n" +
                "\t\t\t\t} else {\n" +
                "\t\t\t\t\tif (r) {\n" +
                "\t\t\t\t\t\tresponse = r;\n" +
                "\t\t\t\t\t}\n" +
                "\t\t\t\t}\n" +
                "\n" +
                "\t\t\t\t// quit redis:\n" +
                "\t\t\t\tclient.end(function(err) {\n" +
                "\t\t\t\t\tif (err) {\n" +
                "\t\t\t\t\t\tconsole.error('Error when quitting redis: ', err);\n" +
                "\t\t\t\t\t}\n" +
                "\t\t\t\t});\n" +
                "\t\t\t});\n" +
                "\t\t// Feedback for the user:\n" +
                "\t\tlet voiceOutput = '';\n" +
                "\t\tlet cardOutput = '';\n" +
                "\t\tif (error) {\n" +
                "\t\t\tvoiceOutput = 'There was an error connecting. Please try again later. ' +\n" +
                "\t\t\t\t'If this problem persists, please post on the App Inventor forums, ' +\n" +
                "\t\t\t\t'which can be found in the Resources menu on the App Inventor website, ' +\n" +
                "\t\t\t\t'\"App Inventor dot MIT dot EDU\". ';\n" +
                "\t\t\tcardOutput = voiceOutput + '(appinventor.mit.edu) ' +\n" +
                "\t\t\t\t' You may include the following error code with your post on the forums: ' +\n" +
                "\t\t\t\terror;\n" +
                "\t\t} else {\n" +
                "\t\t\tvoiceOutput = SUCCESS_OUTPUT;\n" +
                "\t\t\tcardOutput = voiceOutput +\n" +
                "\t\t\t\t'If the \"when Alexa.sendsSignal\" block was not ' +\n" +
                "\t\t\t\t'triggered in App Inventor, check the tutorials section ' +\n" +
                "\t\t\t\t'(http://appinventor.mit.edu/explore/ai2/tutorials.html) or the forums ' +\n" +
                "\t\t\t\t'(https://groups.google.com/forum/#!forum/mitappinventortest) ' +\n" +
                "\t\t\t\t'on the App Inventor website.';\n" +
                "\t\t}\n" +
                "\t\t// render a card in the alexa app:\n" +
                "\t\tthis.response.cardRenderer(SKILL_NAME, cardOutput);\n" +
                "\t\t// voice output from alexa:\n" +
                "\t\tthis.response.speak(voiceOutput);\n" +
                "\t\tthis.emit(':responseReady');\n" +
                "\t},\n" +
                "\t'AMAZON.HelpIntent': function() {\n" +
                "\t\tconst speechOutput = HELP_MESSAGE;\n" +
                "\t\tconst reprompt = HELP_REPROMPT;\n" +
                "\n" +
                "\t\tthis.response.speak(speechOutput).listen(reprompt);\n" +
                "\t\tthis.emit(':responseReady');\n" +
                "\t},\n" +
                "\t'AMAZON.CancelIntent': function() {\n" +
                "\t\tthis.response.speak(STOP_MESSAGE);\n" +
                "\t\tthis.emit(':responseReady');\n" +
                "\t},\n" +
                "\t'AMAZON.StopIntent': function() {\n" +
                "\t\tthis.response.speak(STOP_MESSAGE);\n" +
                "\t\tthis.emit(':responseReady');\n" +
                "\t},\n" +
                "};\n" +
                "\n" +
                "exports.handler = function(event, context, callback) {\n" +
                "\tconst alexa = Alexa.handler(event, context, callback);\n" +
                "\talexa.APP_ID = APP_ID;\n" +
                "\talexa.registerHandlers(handlers);\n" +
                "\talexa.execute();\n" +
                "};\n";
        return(out);
    }

    public static void main(String[] args){
        String skill_name = "App Inventor Connect";
        String launch_message = "Hello! My name is Codi, and I am here to help you connect to App Inventor! " +
                "If you would like to connect, you can say, \"Alexa, ask Inventor Codi to connect.\"";
        String help_message = "If you want to connect with App Inventor, you can say, \"Ask Inventor Codi to connect\". " +
                "If the connection did not work correctly, try checking your code in App Inventor, " +
                "or getting help from the online tutorials you can find at " +
                "App Inventor dot MIT dot EDU slash explore slash AI2 slash tutorials dot html. " +
                "If you are still having trouble, feel free to post on the App Inventor forums. " +
                "You can find the forums in the Resources menu on the App Inventor website.";
        String stop_message = "Closing App Inventor Connect. Goodbye!";
        String success_output = "Successfully connected to App Inventor!";

        System.out.println(create_javascript(skill_name, launch_message, help_message, stop_message, success_output));

    }
}