package steganography;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

class Pixelsd {
   BufferedImage image;
   int width;
   int height;
   
   public Pixelsd() {
      try {
         File input = new File("abcd.jpg");
         image = ImageIO.read(input);
         width = image.getWidth();
         height = image.getHeight();
         System.out.println("width, height: " + width + ", " + height);
         int[][] pixelData = new int[width * height][3];
         int[] rgb;
         
         int counter = 0;
         for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                rgb = getPixelData(image, i, j);

                for(int k = 0; k < rgb.length; k++){
                    pixelData[counter][k] = rgb[k];
                }

                counter++;
            }
        }
        
         
          //to modify the pixelData
          int m = 0;
         int l = 0;
          int []g = {48,97,123,121,43};
          for(int item:g)
         {
         for(int i=0;i<8;i++)
         {
        	 
       	  	int k = item&128;
       	   if((k==128)&&(pixelData[l][m]&1)==0)
       	 {
   	  		pixelData[l][m]+=1;
         }
       	 else if((k==0)&&(pixelData[l][m]&1)==1)
    	  		pixelData[l][m]^=1;
       	  	if(m==2)
       	  	{
       	  		l++;
       	  		m=0;
       	  	}
       	  	else
       	  	{
       	  		m++;
       	  	}
       	  	item=item << 1;
       	  	
         }
         }
         for(int i=0;i<counter;i++){
             for(int j=0;j<3;j++){
                 System.out.print(" " + pixelData[i][j]);
                 }System.out.println(" ");
          }
          
      //return args;
         
      } catch (Exception e) {}
   }
   
   static public void main(String args[]) throws Exception 
   {
      Pixelsd obj = new Pixelsd();
      
   }
private static int[] getPixelData(BufferedImage image, int x, int y) {
int argb = image.getRGB(x, y);

int rgb[] = new int[] {
    (argb >> 16) & 0xff, //red
    (argb >>  8) & 0xff, //green
    (argb      ) & 0xff  //blue
};
return rgb;
}
}

