package dk.cphbusiness.nw89.JPQLQueries;

import jakarta.persistence.*;
import lombok.*;

@NamedQueries({
        @NamedQuery(name="Employee.findAll", query = "select e from Employee e"),
        @NamedQuery(name="Employee.getAllBySalary", query = "select e from Employee e where e.salary >= :value"),
        @NamedQuery(name="Employee.getAllByDepartment", query = "select e from Employee e where e.department = :value"),
        @NamedQuery(name="Employee.getByLetterInFirstName", query = "select e from Employee e where e.firstName like concat(:value, '%')"),
        @NamedQuery(name="Employee.updateSalary",query = "update Employee e set e.salary = :value where e.id = :value2"),
        @NamedQuery(name="Employee.updateDepartment", query = "update Employee e set e.department = ?1 where e.id = ?2"),
        @NamedQuery(name="Employee.getAverageSalary", query = "select avg(e.salary) from Employee e"),
        @NamedQuery(name="Employee.getSumSalary",query = "select sum(e.salary) from Employee e")
})
@Entity
@ToString
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Table(name="employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name ="firstName")
    private String firstName;

    @Column(name ="lastName")
    private String lastName;

    @Column(name="email", unique = true)
    private String email;

    @Column(name="salary")
    private double salary;

    @Column(name="department")
    private String department;




}
