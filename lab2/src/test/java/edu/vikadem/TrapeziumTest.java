package edu.vikadem;

import org.junit.Test;

import static org.junit.Assert.*;

/*
@author admin
@lab2
@class TrapeziumTest
@since 22.03.2025 - 01.13

*/public class TrapeziumTest {

    private Trapezium trapezium = new Trapezium(5, 10, 7, 8, 9  );

    @Test
    public void whenBase1_5_AndBase2_10_AndHeight_7_ThenArea_52_5() {
        assertEquals(52.5, trapezium.getArea(), 0.001);
    }

    @Test
    public void whenBase1_5_AndBase2_10_AndSide1_8_AndSide2_9_ThenPerimeter_32() {
        assertEquals(32, trapezium.getPerimeter(), 0.001);
    }

    @Test
    public void whenBase1_5_AndBase2_10_ThenMedian_7_5() {
        assertEquals(7.5, trapezium.getMedian(), 0.001);
    }

    @Test
    public void  whenSide1IsNotHeight_thenReturnFalse() {
        assertFalse(trapezium.isSideHeight());

    }

    @Test
    public void  whenSidesAreNotEqual_thenReturnFalse() {
        assertFalse(trapezium.isIsosceles());
    }
}