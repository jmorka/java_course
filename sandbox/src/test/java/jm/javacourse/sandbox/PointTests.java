package jm.javacourse.sandbox;

import jm.javacourse.task2.Point;
import org.testng.annotations.Test;


public class PointTests {

  @Test
  public void testDistance1() {
    Point p1 = new Point(1, 2);
    Point p2 = new Point(2, 2);
    assert p1.distance(p2) == 1;
  }

  @Test
  public void testDistance2() {
    Point p1 = new Point(-1, -2);
    Point p2 = new Point(-2, -2);
    assert p1.distance(p2) == 1;
  }

  @Test
  public void testDistance3() {
    Point p1 = new Point(1.5, 2.5);
    Point p2 = new Point(2.0, 2.0);
    assert p1.distance(p2) == 0.7071067811865476;
  }
}
