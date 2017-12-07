package dod.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import dod.config.DodConfiguration;
import dod.dal.model.*;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import org.hibernate.SessionFactory;

@SuppressWarnings("unused")
public class DodModule extends AbstractModule {

    private HibernateBundle hibernateBundle = new HibernateBundle<DodConfiguration>(

     Offer.class, Tag.class, Listing.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(DodConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    public DodModule(Bootstrap<DodConfiguration> bootstrap) {
        bootstrap.addBundle(hibernateBundle);
    }

    @Override
    protected void configure() {

    }

    @Singleton
    @Provides
    public SessionFactory getSessionFactory() {
        return hibernateBundle.getSessionFactory();
    }

}