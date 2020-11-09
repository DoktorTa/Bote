//package Tasks;
//
//import DataBase.DataBaseTask;
//import DataBase.IDataBaseTask;
//
//import java.util.Arrays;
//import java.util.Map;
//
//public class TaskManager {
//
//    private final IDataBaseTask dataBaseTask = new DataBaseTask();
//
//    public TaskManager(){
//        createTables();
//        int[] num = {1};
//        int[] level = {1};
//        int[] point = {1};
//        String[] categories = {"re", "method", "code answer"};
//        String[] textTask = {"Дан код: m = rе.sеаrсh(r‘\\w+(\\w+), аbс‘).Какой вариант из перечисленных равен аbс?"};
//        String[] answerVariant = {"() никакой", "() m.group(0)", "() m", "() m.match", "() m.groups()", "() другое"};
//        int[] answer = {0};
//        String[] link = {""};
//
////        createNewTask(num, level, point, categories, textTask, answerVariant, answer, link);
//    }
//
////    private
//
//    private void createTables(){
//        dataBaseTask.createGroup();
//    }
//
//    public void createNewTask(Arrays num, Arrays level, Arrays point, Arrays categories, Arrays textTask,
//                              Arrays answerVariant, Arrays answerCorrect, Arrays links){
//        Map<String, Arrays> newTask = CreateTask.createTask(num, level, point, categories, textTask, answerVariant,
//                answerCorrect, links);
//        dataBaseTask.addTask(newTask);
//    }
//
//}
