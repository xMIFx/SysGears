package TaskFour;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Vlad on 19.04.2015.
 * If the line must be closed, we need:
 * 1. to sort array of points in ascending  by coordinate x
 * 2. take first point, then discarded all point to some array, which have coordinate Y >= first point Y. Sort  this new array in descending by coordinate X
 * 3. unit  the rest of points, and then unit array with discarded points.
 */
public class SecondVersionDrawLine {
    private static final List<Point> pointList = Point.createRandomList();

    public static void main(String[] args) {
        Point.sortListOfPointByX(pointList);
        List<Point> resultList = getRightOrderList();
        System.out.println("order of points:");
        for (Point point : resultList) {
            System.out.println(point + ";");
        }
    }

    private static List<Point> getRightOrderList() {
        List<Point> resultList = new ArrayList<>(pointList.size());
        // get smallest and bigest point by x. remove its from array
        Point firstPoint = pointList.get(0);
        Point lastPoint = pointList.get(pointList.size() - 1);
        pointList.remove(firstPoint);
        pointList.remove(lastPoint);
        List<Point> discardedPoints = new ArrayList<>();
        resultList.add(firstPoint);
        pointList.remove(0);
        Iterator<Point> iter = pointList.iterator();
        while (iter.hasNext()) {
            Point point = iter.next();
            if (point.getY() >= resultList.get(resultList.size() - 1).getY()) {
                // This array will be sorted by X too, so we only revert it at next step
                discardedPoints.add(point);
            } else {
                //We can only add, because they are sorted by X
                resultList.add(point);
            }
            iter.remove();
        }
        resultList.add(lastPoint);
        Collections.reverse(discardedPoints);
        resultList.addAll(discardedPoints);
        resultList.add(firstPoint);// for closing

        return resultList;
    }
}
