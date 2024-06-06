package com.example.atividades.atividade06;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class PointTest {

    @Test
    public void testDistanceToSamePoint() {
        Point p1 = new Point(0, 0);
        assertEquals(0, p1.distanceTo(p1), 0.0001);
    }

    @Test
    public void testDistanceToDifferentPoints() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(3, 4);
        assertEquals(5, p1.distanceTo(p2), 0.0001);
    }

    @Test
    public void testDistanceToNegativeCoordinates() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(-3, -4);
        assertEquals(5, p1.distanceTo(p2), 0.0001);
    }

    @Test
    public void testDistanceToPointWithDecimals() {
        Point p1 = new Point(1.5, 1.5);
        Point p2 = new Point(4.5, 5.5);
        assertEquals(5, p1.distanceTo(p2), 0.0001);
    }

    @Test
    public void testDistanceToItself() {
        Point p1 = new Point(2, 3);
        assertEquals(0, p1.distanceTo(p1), 0.0001);
    }

    @Test
    public void testDistanceToNull() {
        Point p1 = new Point(2, 3);
        assertThrows(IllegalArgumentException.class, () -> p1.distanceTo(null));
    }
}
