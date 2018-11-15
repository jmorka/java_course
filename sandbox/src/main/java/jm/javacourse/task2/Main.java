package jm.javacourse.task2;

public class Main {

  public static void main(String[] args) {
    double result = distance(new Point(1.0, 1.0), new Point(2.0, 2.0));
    System.out.println("Distance of two points = " + result);

    double result2 = new Point(1.0, 1.0).distance(new Point(2.0, 2.0));
    System.out.println("Distance of two points by method = " + result2);

  }

  public static double distance(Point p1, Point p2) {
    return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
  }
}
