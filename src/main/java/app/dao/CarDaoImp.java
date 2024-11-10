package app.dao;


import app.model.Car;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CarDaoImp implements CarDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(Car car) {
        sessionFactory.getCurrentSession().save(car);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Car> listCars() {
        TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery("from Car");
        return query.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Car> listCars(String sortBy) {
        String hql = "FROM Car c ORDER BY c." + sortBy.toLowerCase() + " ASC";
        TypedQuery query = sessionFactory.getCurrentSession().createQuery(hql);
        return query.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Car> listCars(int count) {
        TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery("from Car");
        query.setMaxResults(count);
        return query.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Car> listCars(int count, String sortBy) {
        String hql = "FROM Car c ORDER BY c." + sortBy.toLowerCase() + " ASC";
        TypedQuery query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setMaxResults(count);
        return query.getResultList();
    }

}
