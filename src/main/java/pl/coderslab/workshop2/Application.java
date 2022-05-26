package pl.coderslab.workshop2;

import pl.coderslab.workshop2.dao.UserDao;
import pl.coderslab.workshop2.entity.User;

public class Application {

    public static void main(String[] args) {

        UserDao userDao = new UserDao();

//        Create users:
        User user1 = new User("aneta.skok@onet.pl", "AnetaSkok", "anetaskok");
        User user2 = new User("szymon.okno@gmail.com", "SzymonOkno", "szymonokno");
        User user3 = new User("marcelina.klops@gmail.com", "MarcelinaKlops", "marcelinaklops");
        User user4 = new User("marek.rutkowski@coderslab.pl", "MarekRutkowski", "marekrutkowski");
        User user5 = new User("maria.gonczewska@coderslab.pl", "MariaGonczewska", "mariagonczewska");
        userDao.create(user1);
        userDao.create(user2);
        userDao.create(user3);
        userDao.create(user4);
        userDao.create(user5);

//        Read data:
        User user = userDao.read(3);
        System.out.println(user.toString());

//        Update data:
        user.setUserName("KlopsMarcelina");
        userDao.update(user);

//        Delete data:
        userDao.delete(4);

    }
}