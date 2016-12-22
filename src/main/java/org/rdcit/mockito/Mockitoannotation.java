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
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author sa841
 */
@RunWith(MockitoJUnitRunner.class)
public class Mockitoannotation {

    @Mock
    private List mockList;

    @Ignore
    @Test
    public void testconsecutiveStubbing1() {
        when(mockList.get(0))
                .thenReturn("First Element added")
                .thenThrow(new RuntimeException("Mu exception"));
        System.out.println(mockList.get(0));
        mockList.get(0);
    }

    @Test
    public void testconsecutiveSubbling2() {
        when(mockList.get(0)).thenReturn("First return", "Second return", "third return");
        System.out.println(mockList.get(0));
        System.out.println(mockList.get(0));
        System.out.println(mockList.get(0));
        System.out.println(mockList.get(0));
        System.out.println(mockList.get(0));
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(Mockitoannotation.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.getMessage());
        }
        System.out.println(result.wasSuccessful());
    }
}
