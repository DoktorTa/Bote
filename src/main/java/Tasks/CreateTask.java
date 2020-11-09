package Tasks;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Класс ответственен за создание задания.
 * Любое задание есть словарь.
 */
public class CreateTask {

    public static Map<String, Arrays> createTask(Arrays num, Arrays level, Arrays point, Arrays categories,
                                                 Arrays textTask, Arrays answerVariant, Arrays answerCorrect,
                                                 Arrays links){
        Map<String, Arrays> task = new HashMap<String, Arrays>();
        task.put("Number", num);
        task.put("Level", level);
        task.put("Point", point);
        task.put("Categories", categories);
        task.put("TextTask", textTask);
        task.put("AnswerVariant", answerVariant);
        task.put("AnswerCorrect", answerCorrect);
        task.put("Links", links);

        return task;
    }
}
