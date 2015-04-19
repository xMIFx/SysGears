package TaskFour;

import java.util.List;


/**
 * Created by Vlad on 19.04.2015.
 */

// There was nothing said about the line must be closed, so we can only sort array of points in ascending  by coordinate x
//if line must be closed, look at the second variant
public class FirstVersionDrawLine {
    private static final List<Point> pointList = Point.createRandomList();

    public static void main(String[] args) {
        Point.sortListOfPointByX(pointList);
        System.out.println("order of points:");
        for (Point point : pointList) {
            System.out.println(point+";");
        }


    }
}
