package Artificial;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

public class Main {
	private static final int NUMBER_OF_INPUTS = 28;   
	private NeuralNetwork nn;
	private double [] target = new double[2];
	private Matrix mT = new Matrix(target.length, 1);

		
	public static void main(String[] args) {
		Main main = new Main();
		ArrayList<String> imagesOne = new ArrayList();
        ArrayList<String> imagesTwo = new ArrayList();
        ArrayList<String> testImageOne = new ArrayList();
        ArrayList<String> testImageTwo = new ArrayList();
        System.out.println("Collecting data...");
        int i = 0;
        	for(int j=0;j<100;j++)
        		imagesOne.add(args[i]+"a ("+j+").bmp");

   
        //String objOne = args[1];
        //String objTwo = args[i++];
        	i++;
        	for(int j=0;j<100;j++)
        		imagesTwo.add(args[i]+"1 ("+j+").jpg");
        
        	i++;
        	for(int j=0;j<25;j++)
        		testImageOne.add(args[i]+"\\a ("+j+").bmp");
        
        	i++;
        	for(int j=0;j<25;j++)
        		testImageTwo.add(args[i]+"\\1 ("+j+").jpg");
        
        //String imageThree = args[i];
        //String imageFour = args[i++];

        main.feedPicture(imagesOne, imagesTwo, testImageOne, testImageTwo);
		//main.trigger();
		//for(double val:main.output)
		//System.out.println(val);
	}
	
	/* Preparing and passing the input train and test data*/
	private void feedPicture(ArrayList<String> imagesOne, ArrayList<String> imagesTwo, ArrayList<String> testImageOne,ArrayList<String> testImageTwo) {
		nn = new NeuralNetwork(NUMBER_OF_INPUTS, 350, 2);
		Random random = new Random();		
		int imageOneCounter = 0;
		int imageTwoCounter = 0;
		double[] data;
		double[][] inputs;
		double[][] divOut;
		int key =0;
		System.out.println("Started training...");
		for(int l=0;l<250;l++)
		{			
			imageOneCounter = 0;
			imageTwoCounter = 0;
		for(int k=0;k<200;k++)
		{
			key =random.nextInt(2);
			Matrix mIn;
			if(imagesOne.size()<=imageOneCounter)
				key = 1;
			if(imagesTwo.size()<=imageTwoCounter)
				key = 0;
			if(imagesTwo.size()<=imageTwoCounter && imagesOne.size()<=imageOneCounter)
				key =2;
			
				switch(key) {
					case 0:	data = openImage(imagesOne.get(imageOneCounter));
							mIn = new Matrix(data.length,1);
						    inputs = Matrix.fromArray(data, mIn.getData());
						    divOut = Matrix.divMap(inputs);
						    double[] tar = new double[]{1, 0};
						    double[][] targets = Matrix.fromArray(tar, mT.getData());
						    nn.train(divOut, targets);
						    imageOneCounter++;
							break;
							
					case 1:	data =  openImage(imagesTwo.get(imageTwoCounter));
							mIn = new Matrix(data.length,1);
							inputs = Matrix.fromArray(data, mIn.getData());
							divOut = Matrix.divMap(inputs);
							tar = new double[]{0, 1};
							targets = Matrix.fromArray(tar, mT.getData());
							nn.train(divOut, targets);
							imageTwoCounter++;
							break;					
				}
		  	}		
		}
		System.out.println("End of training...");
		
		
		double[] out;
		int correctlyPredicted =0;
		imageOneCounter =0;
		imageTwoCounter =0;
		System.out.println("Started testing...");
		for(int k=0;k<50;k++)
		{			
			key =random.nextInt(2);
			Matrix mIn;
			if(testImageOne.size()<=imageOneCounter)
				key = 1;
			if(testImageTwo.size()<=imageTwoCounter)
				key = 0;
			if(testImageTwo.size()<=imageTwoCounter && testImageOne.size()<=imageOneCounter)
				key =2;
			
				switch(key) {
					case 0:	data =  openImage(testImageOne.get(imageOneCounter));
									mIn = new Matrix(data.length,1);
									inputs = Matrix.fromArray(data, mIn.getData());
									divOut = Matrix.divMap(inputs);
									out = nn.feedforward(divOut);
									imageOneCounter++;
									 
									if(out[0]>.89 && out[1]<.2)
										correctlyPredicted++;
									break;
							
					case 1:	data = openImage(testImageTwo.get(imageTwoCounter));
							mIn = new Matrix(data.length,1);
							inputs = Matrix.fromArray(data, mIn.getData());
							divOut = Matrix.divMap(inputs);
							out = nn.feedforward(divOut);
							imageTwoCounter++;
							 
							if(out[0]<.2 && out[1]>.89)
								correctlyPredicted++;
							break;
							
					case 2: break;
						
				}
		  }
		System.out.println("End of testing...");
		System.out.println("Prediction rate:"+correctlyPredicted+"/"+50);
    }
	
	private int getBin(int value) {
        int bin = 1;
        if (value < 8)
            bin = 1;
        else if (value < 16)
            bin = 2;
        else if (value < 24)
            bin = 3;
        else if (value < 32)
            bin = 4;
        else if (value < 40)
            bin = 5;
        else if (value < 48)
            bin = 6;
        else if (value < 56)
            bin = 7;
        else if (value < 64)
            bin = 8;
        else if (value < 72)
            bin = 9;
        else if (value < 80)
            bin = 10;
        else if (value < 88)
            bin = 11;
        else if (value < 96)
            bin = 12;
        else if (value < 104)
            bin = 13;
        else if (value < 112)
            bin = 14;
        else if (value < 120)
            bin = 15;
        else if (value < 128)
            bin = 16;
        else if (value < 136)
            bin = 17;
        else if (value < 144)
            bin = 18;
        else if (value < 152)
            bin = 19;
        else if (value < 160)
            bin = 20;
        else if (value < 168)
            bin = 21;
        else if (value < 176)
            bin = 22;
        else if (value < 184)
            bin = 23;
        else if (value < 192)
            bin = 24;
        else if (value < 200)
            bin = 25;
        else if (value < 208)
            bin = 26;
        else if (value < 216)
            bin = 27;
        else if (value <= 225)
            bin = 28;
        return bin;
    }
	
	 /* converts to grayscale*/
    private void convertToGrayscale(BufferedImage img, int width, int height, int[][] grayImage) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int p = img.getRGB(x, y);
                int r = (p>>16)&0xff;
                int g = (p>>8)&0xff;
                int b = p&0xff;
                int avg = (r + g + b) / 3;
                grayImage[x][y] = avg;
            }
        }
    }
    
    /* normalizes the pic to make it suitable for passing to the perceptron */
    private void normalize(int width, int height, int[][] grayImg, double[] normalized) {
        int[] bins = new int[NUMBER_OF_INPUTS];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (0 != getBin(grayImg[x][y])) {
                    int bin = getBin(grayImg[x][y]);
                    bins[bin-1] += 1;
                }
            }
        }

        double all = 0;
        for (int i = 0; i < NUMBER_OF_INPUTS; i++) {
            all += bins[i];
        }

        for (int i = 0; i < NUMBER_OF_INPUTS; i++) {
            normalized[i] = bins[i] / all;
        }
    }
    
    /* opens an image with passed path and returns a normalized array of that image */
    private double[] openImage(String image_path) {
        try {
            //System.out.println(image_path);
            BufferedImage img = ImageIO.read(new File(image_path));
            int width = img.getWidth();
            int height = img.getHeight();
            int[][] grayImg = new int[width][height];
            convertToGrayscale(img, width, height, grayImg);
            double[] normalized = new double[NUMBER_OF_INPUTS];
            normalize(width, height, grayImg, normalized);
            return normalized;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
