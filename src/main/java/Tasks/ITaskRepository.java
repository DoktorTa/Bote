package Tasks;

public interface ITaskRepository {

    /**
     * Возвращает задание по его номеру.
     * @param numberTask номер задания.
     * @return строка задания с текстом баллами уровнем номерами вариантами ответов.
     */
    String getTaskByNumber(String numberTask);

    /**
     * Возвращает список заданий в качестве одной строки, линия строки представляет из себя 51 символ.
     * @param levelTasks уровень заданий.
     * @return список заданий в качестве одной строки.
     */
    String getTasksByLevel(String levelTasks);

    /**
     * Возвращает список заданий в качестве одной строки, линия строки представляет из себя 51 символ.
     * @return список заданий в качестве одной строки.
     */
    String getAllTasks();

    /**
     * Возвращает номера ответов или их текст.
     */
    String getAnswerTask(String num);
}
