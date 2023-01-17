package ru.hilov.SpringBoot.dao;

import org.springframework.stereotype.Component;
import ru.hilov.SpringBoot.model.User;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Component
public class UserDaoImpl implements UserDao{

//    private static int USERS_COUNT;
//    private List<User> users;
//    {
//        users = new ArrayList<>();
//        users.add(new User(1,"Bob","Shelthon",23));
//        users.add(new User(2,"Lisa","Shelthon",24));
//        users.add(new User(3,"Masha","Shelthon",3));
//        users.add(new User(4,"Hovard","Shelthon",2));
//    }
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> index() {
        return entityManager.createQuery("FROM User", User.class).getResultList();
    }

    @Override
    public User show(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void update(int id, User updatedUser) {
//        User update
        entityManager.merge(updatedUser);
    }

    @Override
    public void delete(int id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }
}
