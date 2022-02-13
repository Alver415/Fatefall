package com.alver.fatefall.database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

@Component
public class DatabaseManager {

    private StandardServiceRegistry registry;
    private SessionFactory sessionFactory;

    public <T> T inSession(Function<Session, T> function) {
        try (Session session = getSession()) {
            return inTransaction(session, function);
        }
    }

    public void inSession(Consumer<Session> consumer) {
        try (Session session = getSession()) {
            inTransaction(session, consumer);
        }
    }

    protected <T> T inReadOnlySession(Function<Session, T> function) {
        try(Session readOnlySession = getSessionFactory().openSession()) {
            readOnlySession.setDefaultReadOnly(true);
            return function.apply(readOnlySession);
        }
    }

    protected void inReadOnlySession(Consumer<Session> consumer) {
        try(Session readOnlySession = getSessionFactory().openSession()) {
            readOnlySession.setDefaultReadOnly(true);
            consumer.accept(readOnlySession);
        }
    }

    protected <T> T inTransaction(Session session, Function<Session, T> function) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            T result = function.apply(session);
            transaction.commit();
            return result;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }


    protected void inTransaction(Session session, Consumer<Session> consumer) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            consumer.accept(session);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public <T> T save(T entity) {
        return inSession(session -> {
            return (T) session.merge(entity);
        });
    }
    public void delete(Object entity) {
        inSession(session -> {
            session.delete(entity);
        });
    }

    public <T> List<T> loadAll(Class<T> type) {
        return inSession(session -> {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<T> criteria = builder.createQuery(type);
            criteria.from(type);
            return session.createQuery(criteria).getResultList();
        });
    }

    protected Session getSession() {
        if (getSessionFactory().getCurrentSession() == null) {
            return getSessionFactory().openSession();
        }
        return getSessionFactory().getCurrentSession();
    }

    protected SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            startup();
        }
        return sessionFactory;
    }

    public synchronized void startup() {
        if (sessionFactory == null) {
            try {
                // Create registry
                registry = new StandardServiceRegistryBuilder().configure().build();

                // Create MetadataSources
                MetadataSources sources = new MetadataSources(registry);

                // Create Metadata
                Metadata metadata = sources.getMetadataBuilder().build();

                // Create SessionFactory
                sessionFactory = metadata.getSessionFactoryBuilder().build();

            } catch (Exception e) {
                e.printStackTrace();
                if (registry != null) {
                    StandardServiceRegistryBuilder.destroy(registry);
                }
            }
        }
    }

    public void stop() {
        if (registry != null) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
