//public class TestPendingVerCommand {
//
//    @Ignore
//    @Test
//    public void testUserIsAdmin() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
//        User user1 = new User(123, "Peta", false, "Ivanov", "Jopa", "rus");
//        User user2 = new User(123, "Vova", false, "Ivanov", "MAGU", "rus");
//        Chat chat = new Chat();
//        UserBot userBot = new UserBot(user1, chat);
//        VerUserBot verUsersGroup = new VerUserBot();
//        NoVerUserBot noVerUsersGroup = new NoVerUserBot();
//        PendingVerCommand pendingCom = new PendingVerCommand(verUsersGroup, noVerUsersGroup);
//
//        verUsersGroup.setAdmin(userBot);
//
//        Method method = PendingVerCommand.class.getDeclaredMethod("userIsAdmin", User.class, Chat.class);
//        method.setAccessible(true);
//        Boolean outputGood = (Boolean) method.invoke(pendingCom, user1, chat);
//
//        Boolean outputBad = (Boolean) method.invoke(pendingCom, user2, chat);
//
//        Assert.assertTrue(outputGood);
//        Assert.assertFalse(outputBad);
//    }
//
//    @Ignore
//    @Test
//    public void testGetBeadrollNoVerUser() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
//        UserBot user1 = new UserBot(new User(123, "Peta", false, "Ivanov", "Jopa", "rus"), new Chat());
//        UserBot user2 = new UserBot(new User(123, "Vova", false, "Ivanov", "MAGU", "rus"), new Chat());
//        VerUserBot verUsersGroup = new VerUserBot();
//        NoVerUserBot noVerUsersGroup = new NoVerUserBot();
//        PendingVerCommand pendingCom = new PendingVerCommand(verUsersGroup, noVerUsersGroup);
//        String answer = "[0]|PetaJopa|Jopa\n[1]|VovaMAGU|MAGU\n";
//
//        noVerUsersGroup.addUserBot(user1);
//        noVerUsersGroup.addUserBot(user2);
//
//        Method method = PendingVerCommand.class.getDeclaredMethod("getBeadrollNoVerUser");
//        method.setAccessible(true);
//        String outputGood = (String) method.invoke(pendingCom);
//
//        Assert.assertEquals(answer, outputGood);
//    }
//}
