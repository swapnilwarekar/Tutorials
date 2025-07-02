package in.swapnilwarekar;

import java.util.*;
import java.util.stream.Collectors;

public class StreamsOps {

    public static void main(String[] args) {


        // Sort the list using length of the element.
        List<String> fruitList = Arrays.asList("apple", "kiwi", "banana", "mango", "pineapple");
        fruitList.sort((s1, s2) -> Integer.compare(s1.length(), s2.length()));
        System.out.println("Sorted List : "+ fruitList);


        List<Employee> employees = Arrays.asList(
                new Employee(1, "A", "IT", 60000d),
                new Employee(2, "B", "HR", 75000d),
                new Employee(3, "C", "IT", 80000d),
                new Employee(4, "D", "HR", 80000d),
                new Employee(5, "E", "IT", 90000d)
        );

        System.out.println("Employees : "+employees);
        List<Double> topSalaries = employees.stream()
                .map(Employee::getSalary)
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .limit(2)
                .toList();

        System.out.println("Top Salaries : "+topSalaries);

        Map<String, Long> deptCount = employees.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
        System.out.println("Dept Count : "+deptCount);

        List<Integer> integerList = Arrays.asList(13,14,13,30,15,30,18,20,30,20,40,80,70,23,24,25);
        var counts = integerList.stream().collect(Collectors.groupingBy(i->i, Collectors.counting()));
        System.out.println("Integer Count : " + counts);


        //2nd highest salary in each department.

       Map<String, List<Employee>> secondHighestSalary = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                                Collectors.collectingAndThen(Collectors.toList(),
                                        list -> list.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed()).skip(1).limit(1).toList())
                        )
                );
        System.out.println("Second Highest Salary : "+ secondHighestSalary);

       //Highest Paid employee
       Map<String, Optional<Employee>> highestPaidEmployee = employees.stream().collect(Collectors.groupingBy(Employee::getDepartment,
               Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary))));
       System.out.println("Highest paid employee : "+ highestPaidEmployee);

       // Return comma separated employee names

        List<String> employeeNames = employees.stream().map(Employee::getName).toList();
        System.out.println("Employee Names : "+ employeeNames);

        // Basic Stream operations,

        List<String> names = List.of("Alice", "Bob", "Charlie");
        names.stream()
                .filter(name -> name.length() > 3) // Intermediate operation - Filter, Map, FlatMap, Sorted, Distinct, Limit
                .map(String::toUpperCase)
                .forEach(System.out::println); // Terminal Operation - Collect, forEach, reduce, count, findFirst, anyMatch, allMatch
    }
}

class Employee {
    int id;
    String name;
    String department;
    Double salary;

    public Employee() {
    }

    public Employee(int id, String name, String department, Double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", salary=" + salary +
                '}';
    }
}
