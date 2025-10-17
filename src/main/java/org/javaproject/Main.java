package org.javaproject;

import com.school.hibernate.entity.*;
import com.school.hibernate.service.*;

public class Main {
    public static void main(String[] args) {
        StudentService studentService = new StudentService();
        TeacherService teacherService = new TeacherService();
        CourseService courseService = new CourseService();
        AddressService addressService = new AddressService();

        // Create Teacher
        Teacher t1 = new Teacher("Mr. John", "Science");
        teacherService.saveTeacher(t1);

        // Create Course
        Course c1 = new Course("Physics");
        c1.setTeacher(t1);
        courseService.saveCourse(c1);

        // Create Student + Address
        Address addr = new Address("45 Main St", "Kigali", "Rwanda");
        addressService.saveAddress(addr);

        Student s1 = new Student("Alice", 14);
        s1.setAddress(addr);
        s1.getCourses().add(c1);
        studentService.saveStudent(s1);

        // Fetch and display student
        Student fetched = studentService.getStudent(s1.getId());
        System.out.println("Fetched student: " + fetched.getName() + " from " + fetched.getAddress().getCity());

        // Update
        fetched.setName("Alice N.");
        studentService.updateStudent(fetched);

        // Delete teacher (example)
        // teacherService.deleteTeacher(t1.getId());

        System.out.println("✅ All operations completed successfully!");
    }
}
