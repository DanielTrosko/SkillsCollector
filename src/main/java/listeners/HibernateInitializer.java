package listeners;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
@WebListener
public class HibernateInitializer implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Logger logger = Logger.getLogger(getClass().getName());
        try {
            Configuration configuration = new Configuration();

            Properties hbnProperties = new Properties();
            hbnProperties.put(Environment.DRIVER, "org.mariadb.jdbc.Driver");
            hbnProperties.put(Environment.URL, "jdbc:mariadb://51.38.133.27:3306/skill?useSSL=false");
            // Nazwę użytkownika dostosuj do swojej instalacji MySQL
            hbnProperties.put(Environment.USER, "skill");
            // Hasło użytkownika dostosuj do swojej instalacji MySQL
            hbnProperties.put(Environment.PASS, "jajo123.");
            hbnProperties.put(Environment.DIALECT, "org.hibernate.dialect.MariaDB103Dialect");
            hbnProperties.put(Environment.SHOW_SQL, "true");
            hbnProperties.put(Environment.FORMAT_SQL, "true");
            hbnProperties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
            // W przypadku gdy silnik Hibernate ma tworzyć schemat bazy danych, to poniżej
            // użyj opcji create-drop albo update
//            hbnProperties.put(Environment.HBM2DDL_AUTO, "validate");
            hbnProperties.put(Environment.HBM2DDL_AUTO, "update");
            configuration.setProperties(hbnProperties);

            // Odkomentuj poniższe instrukcje po utworzeniu klas encji (kolejne zadania)

//            configuration.addAnnotatedClass(User.class);
//            configuration.addAnnotatedClass(Source.class);
//            configuration.addAnnotatedClass(Skill.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Błąd konfiguracji Hibernate!", e);
        }
    }
}
