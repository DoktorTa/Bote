package TestCommandKernel;

import CommandKernel.ComStart;
import CommandKernel.ComTelegramStart;
import org.junit.Test;
import org.junit.Assert;



public class TestComStart {

    @Test
    public void testGetTextMsgTelegram(){
        ComTelegramStart com = new ComTelegramStart();

        String msgGoodAnswer = "Ваш пользователь создан.\n" +
                "Теперь мы можете общяться с администратором бота!";
        String msgBadAnswer = "Ваш пользователь был уже создан ранее создан," +
                " идите к китайцам с подобными предложениями.";


    }

}
