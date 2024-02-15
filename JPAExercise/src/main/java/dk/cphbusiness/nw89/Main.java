package dk.cphbusiness.nw89;

import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
        UnicornDAO unicornDAO = UnicornDAO.getInstance(emf);
        Unicorn unicorn = new Unicorn("Nicklas",21,99);

        // THROWS EXCEPTION IF CONSTRAINTS ARENT FOLLOWED:
        //Unicorn testUnicorn = new Unicorn("TestUnicorn",21,120);
        //unicornDAO.save(testUnicorn);


        /////////////////////////////////////////////////////// PERSIST //////////////////////////////////////////////////////////
        int id = unicornDAO.save(unicorn);
        unicornDAO.save(new Unicorn("Patrick",72,1));
        unicornDAO.save(new Unicorn("Christian",320,2));
        System.out.println("HHHHHHHHHHHHHHHHHHHHHHH "+id);

        /////////////////////////////////////////////////////// READ / FIND //////////////////////////////////////////////////////////
        Unicorn found = unicornDAO.findById(1);
        System.out.println("Found name: "+ found.getName());

        ////////////////////////////////////////////////////// UPDATE / MERGE /////////////////////////////////////////////////////

        found.setName("Peter");
        Unicorn updated = unicornDAO.update(found);
        System.out.println("Updated name: "+updated.getName());

        Unicorn found2 = unicornDAO.findById(1);
        found2.setName("TEST");
        Unicorn updated2 = unicornDAO.update(found2,found2.getId());
        System.out.println("Name for test is now: "+updated2.getName());

        ////////////////////////////////////////////////////// DELETE / REMOVE ////////////////////////////////////////////////////

        //unicornDAO.delete(3);


        ///////////////////////////////////////////////////// FIND ALL //////////////////////////////////////////////////////////

        List<Unicorn> unicorns = unicornDAO.findALl();
        unicorns.forEach(System.out::println);


        unicornDAO.close();


    }
}