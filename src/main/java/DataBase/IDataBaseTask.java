package DataBase;

import java.util.ArrayList;

public interface IDataBaseTask {

    /**
     * Возвращает задания выбранного уровня.
     * @param level уровень выборки.
     * @return номер и текст (первые 50 символов).
     */
    ArrayList<String> getTaskByLevel(String level);

    /**
     * Возвращает задание по его номеру.
     * @param number номер задания.
     * @return Полная информация о задании.
     */
    ArrayList<String> getTaskByNumber(String number);

    /**
     * Возвращает все задания.
     * @return номер и текст (первые 50 символов).
     */
    ArrayList<String> getAllTasks();

    /**
     * Создает новое задание.
     * @param levelTask уровень задания.
     * @param points баллы за задание.
     * @param textTask текст задания.
     * @param textAnswer варианты ответов
     * @param correctAnswer номера верных вариантов ответа
     * @return создано ли задание.
     */
    boolean addTask(String levelTask, String points,
                    String textTask, String textAnswer, String correctAnswer);

    /**
     * Удаляет задания по его номеру.
     * @param numberTask номер удаляемого задания.
     * @return успешно ли удаления.
     */
    boolean removeTask(String numberTask);


}
