package pl.coderslab.entity;

import pl.coderslab.entity.User;

import java.util.Arrays;

public class MainDao {

    public static void main(String[] args) {

        UserDao userDao = new UserDao();

//        Created User1:

//        User user1 = new User();
//        user1.setEmail("test@gmail.com");
//        user1.setUserName("testerGmail");
//        user1.setPassword("testerPasswordGmail");
//        userDao.create(user1);

//        Created User2:

//        User user2 = new User();
//        user2.setEmail("test2@onet.pl");
//        user2.setUserName("testerOnet");
//        user2.setPassword("passOnet");
//        userDao.create(user2);
//        System.out.println(user2.getId());

//        Pobieranie danych:

//        User user3 = userDao.read(5);
//        System.out.println(user3.toString());
//
//        User user4 = userDao.read(6);
//        System.out.println(user4.toString());
//
//        User user5 = userDao.read("test2@wp.pl");
//        System.out.println(user5.toString());
//
//        User user6 = userDao.read("test@test.pl");
//        System.out.println(user6.toString());

//        Update data:

//        User user7 = userDao.read(2);
//        user7.setEmail("updatedAgain@test.pl");
//        user7.setUserName("updatedUser");
//        user7.setPassword("updatedPassword");
//        userDao.update(user7);

//        Delete data:

//        userDao.delete(3);

//        User user8 = new User();
//        user8.setEmail("email@email.pl");
//        user8.setUserName("Username");
//        user8.setPassword("Password");
//        userDao.create(user8);

        System.out.println(Arrays.toString(userDao.findAll()));


    }


}
