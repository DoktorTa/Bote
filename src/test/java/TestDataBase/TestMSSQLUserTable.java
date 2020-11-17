package TestDataBase;

import DataBase.DataBaseMSSQL;
import DataBase.MSSQLUserTable;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Logger;

public class TestMSSQLUserTable {
    private MSSQLUserTable mssqlUserTable;

    @Before
    public void createDataBase(){
        Logger LOG = Logger.getLogger(TestMSSQLUserTable.class.getName());
        Statement stmt = new DataBaseMSSQL(LOG).connectDataBase();
        mssqlUserTable = new MSSQLUserTable(LOG, stmt);
    }

    /*
        Метод нарушает SRP т.к. после тестирования добавления требуется убрать за собой
        и можно протестировать другие методы.
     */
    @Test
    public void testAddUserToDataBaseAndGetUserToDataBaseAndRemoveUserToDataBase(){
        mssqlUserTable.addUserToDataBase("TestUser", "0", "00000000");

        ArrayList<String> testUser = new ArrayList<String>();
        testUser.add("TestUser");
        testUser.add("0");
        testUser.add("00000000");

        ArrayList<String> testUserAnswerAdd = mssqlUserTable.getUserToDataBase("TestUser");

        mssqlUserTable.removeUserToDataBase("TestUser");

        ArrayList<String> testUserAnswerRemove = mssqlUserTable.getUserToDataBase("TestUser");

        Assert.assertEquals(testUser, testUserAnswerAdd);
        Assert.assertEquals(new ArrayList<String>(), testUserAnswerRemove);
    }

    @Test
    public void testGetAdminIdentifier(){
        Assert.assertEquals("Tass_SysteamName", mssqlUserTable.getAdminIdentifier());
    }


}
