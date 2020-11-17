package TestTasks;

import DataBase.MSSQLTaskTable;
import Tasks.TaskOperation;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

public class TestTaskOperation {

    @Test
    public void testCreateTask(){
        MSSQLTaskTable mssqlTaskTable = Mockito.mock(MSSQLTaskTable.class);
        Mockito.when(mssqlTaskTable.addTask("1", "1", "Text", "Answer", "Good")).thenReturn(true);
        TaskOperation taskOperation = new TaskOperation(mssqlTaskTable);

        Assert.assertEquals("Task create!", taskOperation.createTask("1", "1", "Text", "Answer", "Good"));

        Mockito.when(mssqlTaskTable.addTask("1", "1", "Text", "Answer", "Good")).thenReturn(false);

        Assert.assertEquals("Error task create!", taskOperation.createTask("1", "1", "Text", "Answer", "Good"));

    }

    @Test
    public void testRemoveTaskByNumber(){
        MSSQLTaskTable mssqlTaskTable = Mockito.mock(MSSQLTaskTable.class);
        Mockito.when(mssqlTaskTable.removeTask("0")).thenReturn(true);
        TaskOperation taskOperation = new TaskOperation(mssqlTaskTable);

        Assert.assertEquals("Task delete, number 0 is clear", taskOperation.removeTaskByNumber("0"));

        Mockito.when(mssqlTaskTable.removeTask("0")).thenReturn(false);

        Assert.assertEquals("Task not delete, number: 0", taskOperation.removeTaskByNumber("0"));
    }

    @Test
    public void testGetTaskByNumber(){
        String answer = "Number: 0 Level: 1 Points: 1\n" +
                "Какие из перечисленных классов реализуют Map в Java?\n" +
                "\n" +
                "    1. RoadMap \n" +
                "    2. Globus \n" +
                "    3. MapHash \n" +
                "    4. HashMap";

        ArrayList<String> sqlOut = new ArrayList<>();
        sqlOut.add("0");
        sqlOut.add("1");
        sqlOut.add("1");
        sqlOut.add("Какие из перечисленных классов реализуют Map в Java?\n");
        sqlOut.add("1. RoadMap 2. Globus 3. MapHash 4. HashMap");
        sqlOut.add("4");

        MSSQLTaskTable mssqlTaskTable = Mockito.mock(MSSQLTaskTable.class);
        Mockito.when(mssqlTaskTable.getTaskByNumber("0")).thenReturn(sqlOut);
        TaskOperation taskOperation = new TaskOperation(mssqlTaskTable);

        Assert.assertEquals(answer, taskOperation.getTaskByNumber("0"));

    }

    @Test
    public void testGetTasksByLevel(){
        String answer = "Level all task this table: 1\n" + "1. Какие из перечисленных методов должны быть у Ma\n";
        ArrayList<String> sqlOut = new ArrayList<>();
        sqlOut.add("1. Какие из перечисленных методов должны быть у Ma");

        MSSQLTaskTable mssqlTaskTable = Mockito.mock(MSSQLTaskTable.class);
        Mockito.when(mssqlTaskTable.getTaskByLevel("1")).thenReturn(sqlOut);
        TaskOperation taskOperation = new TaskOperation(mssqlTaskTable);

        Assert.assertEquals(answer, taskOperation.getTasksByLevel("1"));
    }

    @Test
    public void testGetAllTasks(){
        String answer = "1. Какие из перечисленных методов должны быть у Ma\n";
        ArrayList<String> sqlOut = new ArrayList<>();
        sqlOut.add("1. Какие из перечисленных методов должны быть у Ma");

        MSSQLTaskTable mssqlTaskTable = Mockito.mock(MSSQLTaskTable.class);
        Mockito.when(mssqlTaskTable.getAllTasks()).thenReturn(sqlOut);
        TaskOperation taskOperation = new TaskOperation(mssqlTaskTable);

        Assert.assertEquals(answer, taskOperation.getAllTasks());
    }
}
