package dk.cphbusiness.nw89.JPQLQueries;

import dk.cphbusiness.nw89.HibernateConfig;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class Main {

    private static EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();

    public static void main(String[] args) {

        List<Employee> employees = getAllEmployees();
        employees.forEach(System.out::println);

        List<Employee> employeesWithSalaryHigherThanFiftyK = getAllEmployeesBySalary(60000);
        employeesWithSalaryHigherThanFiftyK.forEach(System.out::println);

        List<Employee> employeesInDepartment = getAllEmployeesByDepartment("HR");
        employeesInDepartment.forEach(System.out::println);

        List<Employee> employeesByFirstLetter = getAllEmployeesByFirstLetter("J");
        employeesByFirstLetter.forEach(System.out::println);

        updateSalary(70000,1);
        List<Employee> updatedEmployees = getAllEmployees();
        updatedEmployees.forEach(System.out::println);

        updateDepartment("HR",2);
        List<Employee> updatedEmployees2 = getAllEmployees();
        updatedEmployees2.forEach(System.out::println);

        System.out.println(getAverageSalary());

        System.out.println(getSumOfSalary());

    }

    public static List<Employee> getAllEmployees(){
        try(var em = emf.createEntityManager()){
            TypedQuery<Employee> query = em.createNamedQuery("Employee.findAll", Employee.class);
            return query.getResultList();
        }
    }

    public static List<Employee> getAllEmployeesBySalary(double in){
        try(var em = emf.createEntityManager()){
            TypedQuery<Employee> query = em.createNamedQuery("Employee.getAllBySalary", Employee.class);
            query.setParameter("value",in);
            return query.getResultList();
        }
    }

    public static List<Employee> getAllEmployeesByDepartment(String in){
        try(var em = emf.createEntityManager()){
            TypedQuery<Employee> query = em.createNamedQuery("Employee.getAllByDepartment",Employee.class);
            query.setParameter("value",in);
            return query.getResultList();
        }
    }

    public static List<Employee> getAllEmployeesByFirstLetter(String in){
        try(var em = emf.createEntityManager()){
            TypedQuery<Employee> query = em.createNamedQuery("Employee.getByLetterInFirstName",Employee.class);
            query.setParameter("value",in);
            return query.getResultList();
        }
    }

    public static void updateSalary(double in, int id){
        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            Query query = em.createNamedQuery("Employee.updateSalary");
            query.setParameter("value",in);
            query.setParameter("value2",id);
            query.executeUpdate();
            em.getTransaction().commit();
            System.out.println("Updated salary successfully for id: "+id);
        }
    }

    public static void updateDepartment(String in, int id){
        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            Query query = em.createNamedQuery("Employee.updateDepartment");
            query.setParameter(1,in);
            query.setParameter(2,id);
            query.executeUpdate();
            em.getTransaction().commit();
            System.out.println("Updated department successfully for id: "+id);
        }
    }

    public static double getAverageSalary(){
        try(var em = emf.createEntityManager()){
            Query query = em.createNamedQuery("Employee.getAverageSalary");
            return Double.parseDouble(query.getSingleResult().toString());
        }
    }

    public static double getSumOfSalary(){
        try(var em = emf.createEntityManager()){
            Query query = em.createNamedQuery("Employee.getSumSalary");
            return Double.parseDouble(query.getSingleResult().toString());
        }
    }

}
