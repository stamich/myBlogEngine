package pl.codecity.perun.account.repository;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.codecity.perun.account.model.PersistentLogin;

import java.util.Date;

@Transactional
@Repository("tokenDao")
public class HibernateTokenRepository implements PersistentTokenRepository {

    @Autowired
    private SessionFactory sessionFactory;

    protected Session currentSession(){
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void createNewToken(PersistentRememberMeToken persistentRememberMeToken) {
        PersistentLogin persistentLogin = new PersistentLogin();

        persistentLogin.setNickName(persistentRememberMeToken.getUsername());
        persistentLogin.setId(persistentRememberMeToken.getSeries());
        persistentLogin.setToken(persistentRememberMeToken.getTokenValue());
        persistentLogin.setLastUsed(persistentRememberMeToken.getDate());
        //persistEntity(persistentLogin);
        currentSession().persist(persistentLogin);
    }

    @Override
    public void updateToken(String seriesId, String tokenValue, Date lastUsed) {
        //PersistentLogin persistentLogin = getByKey(seriesId);
        PersistentLogin persistentLogin = currentSession().get(PersistentLogin.class, seriesId);
        persistentLogin.setToken(tokenValue);
        persistentLogin.setLastUsed(lastUsed);
        //updateEntity(persistentLogin);
        currentSession().update(persistentLogin);
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        try {
            //Criteria criteria = createEntityCriteria();
            Criteria criteria = currentSession().createCriteria(PersistentLogin.class);
            criteria.add(Restrictions.eq("series", seriesId));
            PersistentLogin persistentLogin = (PersistentLogin) criteria.uniqueResult();

            return new PersistentRememberMeToken(persistentLogin.getNickName(), persistentLogin.getId(),
                    persistentLogin.getToken(), persistentLogin.getLastUsed());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void removeUserTokens(String username) {
        //Criteria criteria = createEntityCriteria();
        Criteria criteria = currentSession().createCriteria(PersistentLogin.class);
        criteria.add(Restrictions.eq("username", username));
        PersistentLogin persistentLogin = (PersistentLogin) criteria.uniqueResult();
        if (persistentLogin != null) {
            //deleteEntity(persistentLogin);
            currentSession().delete(persistentLogin);
        }
    }
}
