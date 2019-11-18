package javaee;

import lombok.Data;

public class Test {

    //引用传递实际上也是值传递

    @Data
    static class Student {
        String name;
        String email;

        public Student(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }

    public static void main(String[] args) {
        Integer var1 = new Integer(1);
        Integer var2 = var1;
        doSomething(var2);
        System.out.println(var1.intValue());
        System.out.println(var1.hashCode());
        System.out.println(var2.hashCode());
        System.out.print(var1 == var2);

        System.out.println("-----");

        Student s1 = new Student("xiao", "eee");
        Student s2 = s1;
        System.out.println(s1.hashCode());
        doSomething(s2);
        System.out.println(s1.getName());
        System.out.println(s2.getName());
        System.out.println(s1 == s2);

    }


    public static void doSomething(Integer integer) {
        integer = new Integer(2);
    }

    public static void doSomething(Student student) {
        //student = new Student("222", "qqq");
        System.out.println(student.hashCode());
        student.setName("222");
    }
}