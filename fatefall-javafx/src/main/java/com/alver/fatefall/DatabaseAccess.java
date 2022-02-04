package com.alver.fatefall;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.scryfall.api.models.Card;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

public class DatabaseAccess {

    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;

    protected Session getSession() {
        return getSessionFactory().openSession();
    }

    protected <T> T inSession(Function<Session, T> function) {
        try (Session session = getSession()) {
            T result = inTransaction(session, function);
            return result;
        } catch (Exception e) {
            throw e;
        }
    }

    public void inSession(Consumer<Session> consumer) {
        try (Session session = getSession()) {
            inTransaction(session, consumer);
        } catch (Exception e) {
            throw e;
        }
    }

    private <T> T inTransaction(Session session, Function<Session, T> function) {
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

    private void inTransaction(Session session, Consumer<Session> consumer) {
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

    protected static SessionFactory getSessionFactory() {
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
        return sessionFactory;
    }

    protected static void shutdown() {
        if (registry != null) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
