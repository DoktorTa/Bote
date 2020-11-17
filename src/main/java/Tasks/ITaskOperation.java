package Tasks;

public interface ITaskOperation {

    /**
     * Возвращает ответ на создание задания.
     * @param levelTask уровень задания.
     * @param points баллы за задание.
     * @param textTask текст задания.
     * @param textAnswer ответы на задание, если их нет заполнить как угодно.
     * @param correctAnswer номера верных ответов через пробел.
     * @return ответ на создание.
     */
    String createTask(String levelTask, String points, String textTask, String textAnswer, String correctAnswer);

    /**
     * Удаляет задание по номеру.
     * @param numberTask номер задания.
     * @return ответ на удаление.
     */
    String removeTaskByNumber(String numberTask);

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
}
