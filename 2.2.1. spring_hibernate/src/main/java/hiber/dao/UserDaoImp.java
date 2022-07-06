package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> findByCar(String model, int series) {
      List<User> list = new ArrayList<>();
      TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery("select car from Car car where model = :carModel and series = :carSeries");
      query.setParameter("carModel", model);
      query.setParameter("carSeries", series);
      query.getResultList().forEach(car -> list.add(car.getUser()));
      return list;
   }

}
