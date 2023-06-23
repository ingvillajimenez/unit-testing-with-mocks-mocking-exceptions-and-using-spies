package com.skillsoft.mocks;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import org.mockito.Spy;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeManagerTest {

    @Spy
    private Employee john = new Employee(
            "John", "VP, Sales", 134000);

    @Spy
    private Employee emily = new Employee(
            "Emily", "VP, Engineering", 150000);

    @Spy
    private Employee julia = new Employee(
            "Julia", "Director, Operations", 120000);

    @Spy
    private Employee fred = new Employee(
            "Fred", "Analyst, Operations", 68000);

    @Test
    public void testAddEmployee() {

        EmployeeManager employeeManager = new EmployeeManager("database_conn_string");

        when(john.saveToDatabase(anyString())).thenReturn(true);
        when(julia.saveToDatabase(anyString())).thenReturn(true);
        when(emily.saveToDatabase(anyString())).thenReturn(true);

        employeeManager.addEmployee(john);
        employeeManager.addEmployee(julia);
        employeeManager.addEmployee(emily);

        verify(john, only()).saveToDatabase("database_conn_string");
        verify(julia, only()).saveToDatabase("database_conn_string");
        verify(emily, only()).saveToDatabase("database_conn_string");

        assertTrue(employeeManager.employeeExists("John"));
        assertTrue(employeeManager.employeeExists("Emily"));
        assertTrue(employeeManager.employeeExists("Julia"));
//        assertFalse(employeeManager.employeeExists("Emily"));
    }

    @Test
    public void testRemoveEmployees() {

        EmployeeManager employeeManager = new EmployeeManager("database_conn_string");

        employeeManager.addEmployee(john);
        employeeManager.addEmployee(julia);
        employeeManager.addEmployee(emily);

        when(emily.deleteFromDatabase(anyString())).thenReturn(true);
        when(julia.deleteFromDatabase(anyString())).thenReturn(true);

        employeeManager.removeEmployeeByName("Julia");
        employeeManager.removeEmployeeByName("Emily");

        verify(julia, times(1))
                .deleteFromDatabase("database_conn_string");
        verify(emily, times(1))
                .deleteFromDatabase("database_conn_string");

        assertTrue(employeeManager.employeeExists("John"));
        assertFalse(employeeManager.employeeExists("Emily"));
        assertFalse(employeeManager.employeeExists("Julia"));
    }
}
