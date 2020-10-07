package User;

import org.telegram.telegrambots.meta.api.objects.User;

import java.util.HashSet;
import java.util.Set;

public class UsersGroupControl {
    /*
    * Класс является зааменой даунскому классу ананимус сервис, за который руки оторавать мало будет.
    *
    * Класс контролирует работу с группами 3 группами пользователей:
    * 1. Администраторы - принимают сообщения от всех пользователей, могут отправлять всем.
    * 2. Верефицированные пользователи - принимают и отправляют сообщения администраторам.
    * 3. Не верефецированые пользователи - возможен только запрос верификации.
    * */

    private final Set<UserBot> mAdmins;
    private final Set<UserBot> mVerifiedUsers;
    private final Set<UserBot> mUnverifiedUsers;

    public UsersGroupControl(){
        // TODO: Должен востанавливать пользователей с последнего запуска.
        mAdmins = new HashSet<>();
        mVerifiedUsers = new HashSet<>();
        mUnverifiedUsers = new HashSet<>();
    }

    public boolean addAdminUser(UserBot userBot){
        userBot.setStatus(0);
        return mAdmins.add(userBot);
    }

    public boolean addVerifiedUser(UserBot userBot){
        userBot.setStatus(1);
        return mVerifiedUsers.add(userBot);
    }

    public boolean removeAdminUser(User user) {
        return mAdmins.removeIf(a -> a.getUser().equals(user));
    }
}
