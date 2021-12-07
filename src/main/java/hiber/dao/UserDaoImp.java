package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
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
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    // добавляем метод, поиск user по model и series
    @Override
    @SuppressWarnings("unchecked")
    public User getUser(String model, int series) {
        TypedQuery<User> query = sessionFactory.getCurrentSession()
                // создаем скл запрос
                // выводим user у которых есть машина с опеределенной model  и model
                .createQuery("select user from User user join user.car userCar where userCar.model= :model and userCar.series= :series")
                .setParameter("model", model)
                .setParameter("series", series);

// условие добавил, если
        if (query.getResultList().size() > 0) {
            return query.getResultList().get(0);
        } else {
            return null;
        }
    }

}