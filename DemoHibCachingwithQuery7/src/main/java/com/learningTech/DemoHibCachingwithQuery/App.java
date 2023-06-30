package com.learningTech.DemoHibCachingwithQuery;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;import com.mysql.cj.sasl.ScramSha1SaslClient;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
     	Alien a = new Alien();
    	
    	
    	Configuration config = new Configuration().configure().addAnnotatedClass(Alien.class);
    	ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
    	SessionFactory sf = config.buildSessionFactory(reg);
    	Session session =  sf.openSession();
    	session.beginTransaction();
    	
    	Query q1 = session.createQuery("from Alien where aid = 101");
    	q1.setCacheable(true);
    	Alien  q11=(Alien) q1.uniqueResult();
    	System.out.println(q11);
        session.getTransaction().commit();
    	session.close();
    	
    	Session session1 =  sf.openSession();
    	session1.beginTransaction();
    	Query q2 = session1.createQuery("from Alien where id = 101");
    	q2.setCacheable(true);
    	Alien q22 = (Alien) q2.uniqueResult();
    	System.out.println(q22);
    	session1.getTransaction().commit();
    	session1.close();
    }
    
}
