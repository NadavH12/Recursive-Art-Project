/*

Nadav Horowitz CS211 5/9/22

This program draws a randomized tree using the StdDraw class and recursive methods
The goal of this program is to add randomization to the ModifiedTree program we wrote in part 1.
This program randomizes tree color one time, and draws the entire tree to be of that color.
The program randomizes branchRadius (branch length) at each recursive call, where each recursive call is 1 level of the tree.
The program features decrementing branchRadius at each level, to guarentee that the branches become shorter as they go up.
The program also decrements penRadius at each level, but penRadius is not randomized, it is a user input. So the branches become thinner as they go up.
The program features a randomization of the number of branches at each level, at each level, the tree can split either to 3 branches or to 5 branches.
The program also randomizes branchAngle at each level, where branchAngle is the angle between individual branches at the same level.

*/

import java.awt.Color;
import java.util.Random;
public class RandomizedRecursiveTree {

    public static void main(String[] args){
        int n = 8;
        Random rand = new Random();
        StdDraw.enableDoubleBuffering();
        StdDraw.setPenRadius(0.005);
        randomizeTreeColor(rand);
        drawTree(n, rand, 0.5, 0, Math.PI / 2, 0.3, 0.02);
        StdDraw.show();
    }

    //Generates random rgb values for treeColor
    public static void randomizeTreeColor(Random rand){
        int r = rand.nextInt(256);
        int g = rand.nextInt(256);
        int b = rand.nextInt(256);

        Color treeColor = new Color(r, g, b);
        StdDraw.setPenColor(treeColor);
    }

    //Recursive method, randomizes branchRadius, numBranches, and branchAngle each call
    public static void drawTree(int n, Random rand, double x, double y, double a, double branchRadius, double penRadius){
        double bendAngle   = Math.toRadians(5);
        double branchAngle = Math.toRadians(17);
        double branchRatio = 0.65;
        double penRatio = 0.5;

        //Randomize length & set end points
        double randomBranchRadius = branchRadius * (1 - (rand.nextDouble() - 0.3));
        double cx = x + Math.cos(a) * randomBranchRadius;
        double cy = y + Math.sin(a) * randomBranchRadius;

        //Branch thickness
        StdDraw.setPenRadius(penRadius);

        //Start point (x,y) & end point (cx,cy)
        StdDraw.line(x, y, cx, cy);

        //Base case
        if (n == 0){
            return;
        }

        //Decrement length and thickness
        penRadius *= penRatio;
        branchRadius *= branchRatio;
        a -= bendAngle;

        //Randomize number of branches
        int numBranches;
        double r = rand.nextDouble();
        if(r < 0.5) {
            numBranches = 3;
        } else {
            numBranches = 5;
        }

        //Randomize branchAngle
        branchAngle = branchAngle * (0.6 + rand.nextDouble() * 0.4);
        a -= ((numBranches - 1) / 2) * branchAngle;

        //Recursive case (each drawTree call draws 1 branch)
        for(int i = 0; i < numBranches; i++) {
            drawTree(n-1, rand, cx, cy, a, branchRadius, penRadius);
            a += branchAngle;
        }
    }

}