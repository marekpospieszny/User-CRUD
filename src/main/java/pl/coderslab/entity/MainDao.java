package pl.coderslab.entity;

import pl.coderslab.entity.User;

public class MainDao {

    public static void main(String[] args) {

//        Created User1:

//        User user1 = new User();
//        user1.setEmail("test@gmail.com");
//        user1.setUserName("testerGmail");
//        user1.setPassword("testerPasswordGmail");
//        UserDao userDao1 = new UserDao();
//        userDao1.create(user1);

//        Created User2:

        User user2 = new User();
        user2.setEmail("test2@onet.pl");
        user2.setUserName("testerOnet");
        user2.setPassword("passOnet");
        UserDao userDao2 = new UserDao();
        userDao2.create(user2);
        System.out.println(user2.getId());



    }


}
