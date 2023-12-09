/*

Nadav Horowitz CS211 5/9/22

This program draws a tree using the StdDraw class and a recursive method.
The goal of this program is to match the sample output provided in the Assignment2 Part 1 Specifications.

*/

import java.awt.Color;
public class ModifiedTree {

    public static void main(String[] args){
        int n = 8;
        StdDraw.enableDoubleBuffering();
        tree(n, 0.5, 0, Math.PI / 2, 0.3, 0.02);
        StdDraw.show();
    }

    //Color rgbs given in specification
    static Color darkGreen = new Color(52, 124, 44);
    static Color lightGreen = new Color(178, 194, 72);
    static Color brown = new Color(43, 29, 20);

    //Recursive method to draw tree
    public static void tree(int n, double x, double y, double a, double branchRadius, double penRadius){
        double bendAngle   = Math.toRadians(5);
        double branchAngle = Math.toRadians(17);
        double branchRatio = 0.65;
        double penRatio = 0.5;
        double cx = x + Math.cos(a) * branchRadius;
        double cy = y + Math.sin(a) * branchRadius;

        //Branch thickness
        StdDraw.setPenRadius(penRadius);

        StdDraw.setPenColor(brown);
        if(n < 5) {
            StdDraw.setPenColor(darkGreen);
        }
        if(n == 1) {
            StdDraw.setPenColor(lightGreen);
        }

        //Start point (x,y) & end point (cx,cy)
        StdDraw.line(x, y, cx, cy);

        //Base case
        if (n == 0) {
            return;
        }

        //Recursive case
        penRadius *= penRatio;
        branchRadius *= branchRatio;
        a -= bendAngle;
        a -= 2 * branchAngle;

        //Draws 5 branches from end of each branch 
        for(int i = 0; i < 5; i++) {
            tree(n-1, cx, cy, a, branchRadius, penRadius);
            a += branchAngle;
        }
    }

}
