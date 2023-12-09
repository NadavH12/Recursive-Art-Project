/*

Nadav Horowitz CS211 5/8/2022

This program simulates what one may see while falling through a wormhole in spacetime.
This program draws a recursive wormhole graphic using the StdDraw class.
The program features an ellipse which shrinks in size upon each recursion.
The program alternates between the Major axis being larger than the minor axis, and vice versa in each recursion.
The program uses the random class to generate pseudorandom ints which are used as rgb values to decide the color of the ellipse within each recursion.
The thickness' of the arcs composing the ellipse also decrease upon each recursion.
Just before recursion ends, the program draws a filled black circle which represents the infinitely distant end of the wormhole.

*/

import java.awt.Color;
import java.util.Random;
public class MyRecursiveArt{

   public static void main(String[] args){
      Random rand = new Random();
      int n = 200;        
      wormhole(n, rand, 0.001, 20, 0.01);            
   }

   //Recursive method draws wormhole
   //Includes input valid check
   //n is recursion parameter
   //Rand used to randomize colors
   //thickFactor scales ellipse thickness
   //growthFactor changes size of ellipse, used to change density of ellipses in drawing
   //centerSize controls size of black sphere at wormhole end
   public static void wormhole(int n, Random rand, double thickFactor, int growthFactor, double centerSize){
                
      //Ensure input validity
      if(n<0){
         System.out.println("recursion parameter n must be larger than or equal to 0");
         System.out.println("Please adjust parameter n and then try again");
         System.exit(0);
      }  
      if(thickFactor<=0.0||growthFactor<=0.0||centerSize<0.0){
         System.out.println("All scaling factors must be larger than 0.)");
         System.out.println("Please adjust scaling factors and then try again");
         System.exit(0);
      }      
        
      //Base Case
      if(n==0){
         //Draw end of wormhole     
         StdDraw.setPenColor(Color.BLACK);
         StdDraw.filledCircle(0.5,0.5,centerSize);
         return;
      }
      
      //Recursive Case
      else{          
         //Change ellipse size
         double axisScale = ((double) n)/growthFactor;
         double  majorAxisFactor = 1 + rand.nextDouble(); 
         double  minorAxisFactor = 1 + rand.nextDouble();               
                   
         //Alternate major/minor axis size
         if(n%2==0)
            majorAxisFactor+=1;
         if(n%2==1)
            minorAxisFactor+=1;
              
         //Set ellipse thickness
         StdDraw.setPenRadius(thickFactor * n);
         
         //Randomize Color
         int r = rand.nextInt(256);
         int g = rand.nextInt(256);
         int b = rand.nextInt(256);
         StdDraw.setPenColor(r,g,b);
         
         //Draw ellipse
         StdDraw.ellipse(0.5,0.5,0.1*majorAxisFactor*axisScale,0.05*minorAxisFactor*axisScale);
         
         //Recursive call
         wormhole(n-1, rand, thickFactor, growthFactor, centerSize);
         
      } 
                     
   }  
    
}