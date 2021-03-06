package database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            addAnnotatedClasses( configuration );
            addProperties( configuration );
            StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            sessionFactory = configuration.buildSessionFactory(ssrb.build());
        } catch (ExceptionInInitializerError ex) {
            System.err.println("Initial SessionFactory creation failed: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    private static void addAnnotatedClasses(Configuration configuration)
    {
        configuration.addAnnotatedClass( State.class );
    }

    private static void addProperties(Configuration configuration)
    {
        configuration.setProperty( "hibernate.connection.url", "jdbc:mysql://localhost:3306/robocode?useUnicode=yes&characterEncoding=utf8" )
                .setProperty( "hibernate.connection.username", "root" )
                .setProperty( "hibernate.connection.password", "neuser50" )
                .setProperty( "hibernate.connection.autocommit", "true" )
                .setProperty( "show_sql", "true" )
                .setProperty( "dialect", "org.hibernate.dialect.MySQLDialect" )
                .setProperty( "hibernate.c3p0.min_size", "2" )
                .setProperty( "hibernate.c3p0.max_size", "2" )
                .setProperty( "hibernate.c3p0.timeout", "1800" )
                .setProperty( "hibernate.c3p0.max_statements", "50" )
                .setProperty("hibernate.hbm2ddl.auto", "update");
    }

    /**
     * Gets hiberante session factory that was initialized at application startup.
     *
     * @return hibernate session factory
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static <T> T execute(Query query)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        T result = (T) query.execute(session);
        session.flush();
        session.close();

        return result;
    }

    public static void execute(Update update)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        update.execute(session);
        session.flush();
        session.close();
    }

    public static interface Query<T>
    {
        public <T> T execute(Session session);
    }

    public static interface Update
    {
        public void execute(Session session);
    }
}