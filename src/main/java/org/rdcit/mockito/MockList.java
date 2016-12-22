/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rdcit.mockito;

import java.util.List;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.mockito.InOrder;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.inOrder;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/**
 *
 * @author sa841
 */
public class MockList {

    @Ignore
    @Test
    public void test() {
        List mockList = mock(List.class);

        mockList.add("one");
        System.out.println("Verify " + verify(mockList).add("one"));
        mockList.clear();
        System.out.println("Verify 2 " + verify(mockList).add("one"));
        mockList.add("two");
    }

    @Test
    public void testStub() {
        List mockList = mock(List.class);
        when(mockList.get(0)).thenReturn("First one");
        when(mockList.get(1)).thenReturn("Second one");
        System.out.println(mockList.get(1));
        verify(mockList);
    }

    @Ignore
    @Test
    public void testThenAnswer() {
        List mockList = mock(List.class);
        when(mockList.get(0)).thenAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments(); //To change body of generated methods, choose Tools | Templates.
                Object mock = invocation.getMock();
                return "called with arguments " + args.length + " mock  " + mock.getClass();
            }
        });
        System.out.println(mockList.get(0));
    }

    @Ignore
    @Test
    public void testAnswer2() {
        String[] strAr = {"d", "s"};
        String[] mockStr = mock(String[].class);
        when(mockStr.length).thenAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                Object mock = invocation.getMock();
                return "argument length " + args.length;
            }
        });
    }

    @Ignore
    @Test
    public void tryAny() {
        List mockList = mock(List.class);

        when(mockList.get(anyInt())).thenReturn("Any any");
        when(mockList.get(3)).thenThrow(new RuntimeException("My exception"));
        mockList.clear();
        System.out.println(mockList.get(4));

        verify(mockList);
    }

    @Ignore
    @Test
    public void testArgthat() {
        List mockList = mock(List.class);
        doThrow(new RuntimeException()).when(mockList).clear();
        mockList.clear();
    }

    @Ignore
    @Test
    public void testInorder() {
        List mockList = mock(List.class);
        mockList.add(0);
        mockList.add(1);

        InOrder inorder = inOrder(mockList);

        inorder.verify(mockList).add(0);
        inorder.verify(mockList).add(1);

        List firstMockList = mock(List.class);
        firstMockList.add("Was called first");

        List secondMockList = mock(List.class);
        secondMockList.add("was called second");
        firstMockList.add(anyString());
        secondMockList.add("dd");
        InOrder ino = inOrder(secondMockList, firstMockList);

        ino.verify(firstMockList).add("Was called first");
        ino.verify(secondMockList).add("was called second");
        ino.verify(secondMockList).add("dd");

    }

    @Ignore
    @Test
    public void testcheckOrder() {
        List mockList1 = mock(List.class);
        List mockList2 = mock(List.class);
        mockList1.add(0);
        // mockList2.add(0);
        //mockList2.add(1);
        //mockList2.add(2);
        verify(mockList1, never()).add(5);
        verifyNoMoreInteractions(mockList2);

    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(MockList.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.getMessage());

        }
        System.out.println(result.wasSuccessful());

    }

}
