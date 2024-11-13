package app.dao;

import app.model.Car;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarDaoImp implements CarDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(Car car) {
        entityManager.persist(car);
    }

    @Override
    public List<Car> listCars() {
        TypedQuery<Car> query = entityManager.createQuery("Select c from Car c", Car.class);
        return query.getResultList();
    }

    @Override
    public List<Car> listCars(String sortBy) {
        String hql = "FROM Car c ORDER BY c." + sortBy.toLowerCase() + " ASC";
        TypedQuery<Car> query = entityManager.createQuery(hql, Car.class);
        return query.getResultList();
    }

    @Override
    public List<Car> listCars(int count) {
        TypedQuery<Car> query = entityManager.createQuery("from Car", Car.class);
        query.setMaxResults(count);
        return query.getResultList();
    }

    @Override
    public List<Car> listCars(int count, String sortBy) {
        String hql = "FROM Car c ORDER BY c." + sortBy.toLowerCase() + " ASC";
        TypedQuery<Car> query = entityManager.createQuery(hql, Car.class);
        query.setMaxResults(count);
        return query.getResultList();
    }

}

