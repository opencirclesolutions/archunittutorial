package org.ocs.archunit.tutorial.services;

import org.hibernate.Session;
import org.ocs.archunit.tutorial.domain.Country;

import java.util.Optional;

public class CountryServiceHibernateImpl implements CountryService{

    Session session;

    @Override
    public Optional<Country> findByName(String name) {
        Optional<Country> result = Optional.empty();
        session.beginTransaction();
        try{
            result = session
                    .createQuery("from Country where name = :name",Country.class)
                    .setParameter("name",name)
                    .getResultStream()
                    .findFirst();
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
        }finally{
            session.close();
        }
        return result;
    }
}
