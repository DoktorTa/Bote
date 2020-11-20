package Commands;

import java.util.concurrent.ConcurrentHashMap;

public class LastUserQuery {

    private final ConcurrentHashMap<String, String> lastQuery;

    public LastUserQuery(){
        lastQuery = new ConcurrentHashMap<String, String>();
    }

    public void addQuery(String chatId, String nextCommand){
        lastQuery.put(chatId, nextCommand);
    }

    public String getCommand(String chatId){
        String command = lastQuery.get(chatId);
        removeQuery(chatId);
        return command;
    }
    private void removeQuery(String chatId){
        lastQuery.remove(chatId);
    }

}
