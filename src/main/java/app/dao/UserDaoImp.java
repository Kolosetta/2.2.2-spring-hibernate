package app.dao;

import app.model.Car;
import app.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    public List<User> listUsers() {
        TypedQuery<User> query = entityManager.createQuery("from User", User.class);
        return query.getResultList();
    }

    @Override
    public User getUserByCar(Car car) {
        TypedQuery<User> query = entityManager.createQuery("from User u WHERE u.car.model = :model AND u.car.color = :color", User.class);
        query.setParameter("model", car.getModel());
        query.setParameter("color", car.getColor());

        return query.getResultStream().findFirst().orElse(null);
    }

}
