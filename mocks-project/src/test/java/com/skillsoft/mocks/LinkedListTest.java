package com.skillsoft.mocks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class LinkedListTest {

    @Spy
    LinkedList<String> linkedListSpy = new LinkedList<>();

    AutoCloseable closeable;

    @BeforeEach
    public void setupTests() {
        closeable = openMocks(this);
    }

    @AfterEach
    public void tearDownTests() throws Exception {
        closeable.close();
    }

    @Test
    public void testLinkedList() {

//        LinkedList<String> linkedList = new LinkedList<>();
//        LinkedList<String> linkedListSpy = spy(linkedList);

        linkedListSpy.add("Nora");
        linkedListSpy.add("Fred");
        linkedListSpy.add("Maureen");
        linkedListSpy.add("Ophelia");

        verify(linkedListSpy, times(4)).add(anyString());

        assertEquals(4, linkedListSpy.size());

        when(linkedListSpy.remove(anyString())).thenReturn(true);

        linkedListSpy.remove("Nora");
        linkedListSpy.remove("Ophelia");

        verify(linkedListSpy, times(2)).remove(anyString());

        assertEquals(4, linkedListSpy.size());

//        when(linkedListSpy.size()).thenReturn(1001);
//
//        assertEquals(1001, linkedListSpy.size());
//
//        linkedListSpy.remove("Maureen");
//
//        assertEquals(1001, linkedListSpy.size());

//        assertEquals("Nora", linkedListSpy.get(0));
//
//        linkedListSpy.remove("Maureen");
//
//        verify(linkedListSpy).get(0);
//        verify(linkedListSpy).remove("Maureen");

//        System.out.println("Contents of linked list: " + linkedList);
//        System.out.println("Contents of linked list spy: " + linkedListSpy);
//
//        assertNotEquals(linkedList.size(), linkedListSpy.size());

//        verify(linkedListSpy, times(3)).add(anyString());

//        assertEquals(3, linkedListSpy.size());

//        System.out.println("toString() on spy: " + linkedListSpy);
//
//        System.out.println("getClass() on spy: " + linkedListSpy.getClass());
//
//        System.out.println("linkedListSpy instanceof LinkedList: " +
//                (linkedListSpy instanceof LinkedList));
    }
}
