package TaskFour;

import java.util.*;

/**
 * Created by Vlad on 19.04.2015.
 * If the line must be closed, we need:
 * 1. to sort array of points in ascending  by coordinate x
 * 2. take first point, then iterate array and find point with less Y or min Y (if can't find less).
 * Other point we need to add in some array and sort it in descending by coordinate X
 * 3. unit  the rest of points, and then unit array with discarded points.
 */
public class SecondVersionDrawLine {
    private static final List<Point> pointList = Point.createRandomList();

    public static void main(String[] args) {
        Point.sortListOfPointByX(pointList);
       // System.out.println("Point list size = " + (pointList.size() + 1));
        List<Point> resultList = getRightOrderList();
       // System.out.println("After unit size = " + resultList.size());
       /* for (Point mPoint : pointList) {
            if (!resultList.contains(mPoint)) {
                System.out.println("We lost this " + mPoint.toString());
            }

        }*/
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
        //System.out.println("first: " + firstPoint.toString());
       // System.out.println("last: " + lastPoint.toString());
        pointList.remove(firstPoint);
        List<Point> discardedPoints = new ArrayList<>();
        resultList.add(firstPoint);

        int indexLastAddedPoint = 0;
        for (int i = 0; i < pointList.size(); i++) {
            Point point = pointList.get(i);
            if (i < indexLastAddedPoint) {
                // it will be also sorted by X
                discardedPoints.add(point);
            } else if (i == indexLastAddedPoint && i != 0) {/*NOP*/} else if (point.getY() <= resultList.get(resultList.size() - 1).getY()) {
                indexLastAddedPoint = i;
                resultList.add(point);
            } else {
                indexLastAddedPoint = addNextPointWithLessMinY(resultList, pointList, indexLastAddedPoint + 1);
                if (!point.equals(resultList.get(resultList.size() - 1))) {
                    discardedPoints.add(point);
                }
            }

        }
        if (!discardedPoints.isEmpty()) {
            Collections.reverse(discardedPoints);
            resultList.addAll(discardedPoints);
        }
        resultList.add(firstPoint);// for closing

        return resultList;
    }


    private static int addNextPointWithLessMinY(List<Point> resultList, List<Point> points, int indexLastAddedPoint) {
        Point pointResult = null;
        Point lastAddedPoint = resultList.get(resultList.size() - 1);
        int result = indexLastAddedPoint;
        //find less Y
        for (int j = indexLastAddedPoint; j < points.size(); j++) {
            Point point = points.get(j);
            if (point.getY() < lastAddedPoint.getY()) {
                result = j;
                pointResult = point;
                break;
            }
        }
        if (pointResult == null) {
            //find min Y
            pointResult = points.get(indexLastAddedPoint);
            result = indexLastAddedPoint;
            for (int j = indexLastAddedPoint; j < points.size(); j++) {
                Point point = points.get(j);
                if (point.getY() < pointResult.getY()) {
                    result = j;
                    pointResult = point;
                }
            }
        }
        resultList.add(pointResult);
        return result;
    }
}
