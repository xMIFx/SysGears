package TaskFour;


import java.util.*;

/**
 * Created by Vlad on 19.04.2015.
 * Class for both variant
 */
class Point {
    int x;
    int y;


    static List<Point> createRandomList() {
        // parametrs for ranodm
        int minCoordinate = -100;
        int maxCoordinate = +100;
        int maxCountOfPoints = 100;

        //create arrayList
        int countOfPoint = 10 + (int) (Math.random() * ((maxCountOfPoints - 10) + 1));

        List<Point> newPointList = new ArrayList<>(countOfPoint);
        for (int i = 0; i < countOfPoint; i++) {
            Point newPoint = new Point(minCoordinate + (int) (Math.random() * ((maxCoordinate - minCoordinate) + 1))
                    , minCoordinate + (int) (Math.random() * ((maxCoordinate - minCoordinate) + 1))
            );
            // list mostn't have same point. it's not a problem, but strangely.
            while (true) {
                if (!newPointList.contains(newPoint)) {
                    break;
                } else {
                    newPoint.setX(minCoordinate + (int) (Math.random() * ((maxCoordinate - minCoordinate) + 1)));
                    newPoint.setY(minCoordinate + (int) (Math.random() * ((maxCoordinate - minCoordinate) + 1)));
                }
            }
            newPointList.add(newPoint);

        }

        return newPointList;
    }

    static void sortListOfPointByX(List<Point> oldListOfPoint) {
        Comparator<Point> comparator = (o1, o2) -> ((o1.getX() - o2.getX())!=0)?(o1.getX() - o2.getX()):(o1.getY()-o2.getY());
        Collections.sort(oldListOfPoint, comparator);

    }

    static void sortListOfPointByY(List<Point> oldListOfPoint) {
        Comparator<Point> comparator = new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return ((o1.getY() - o2.getY())!=0)?(o1.getY() - o2.getY()):(o1.getX() - o2.getX());
            }
        };
        Collections.sort(oldListOfPoint, comparator);

    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (x != point.x) return false;
        return y == point.y;

    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }


}
