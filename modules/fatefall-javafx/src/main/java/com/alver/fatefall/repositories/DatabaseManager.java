package com.alver.fatefall.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

@Repository
public class DatabaseManager {

    private static final String HIBERNATE_CONFIG = "/hibernate/hibernate.cfg.xml";

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
                registry = new StandardServiceRegistryBuilder()
                        .configure(HIBERNATE_CONFIG).build();

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
