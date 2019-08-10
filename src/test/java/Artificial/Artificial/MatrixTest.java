package Artificial.Artificial;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import Artificial.Matrix;

public class MatrixTest {

	@Test
    public void testSubtract() {
		double[][] in1 = new double[2][2] ;
		for(int i=0;i<2;i++) {
			for(int j=0;j<2;j++) {
				in1[i][j]=2;
			}
		}
		double[][] in2 = new double[2][2] ;
		for(int i=0;i<2;i++) {
			for(int j=0;j<2;j++) {
				in2[i][j]=1;
			}
		}
       double [][] result = Matrix.subtract(in1, in2);   
       assertEquals(1.0, result[0][0],0);
       assertEquals(1.0, result[1][0],0);
       assertEquals(1.0, result[0][1],0);
       assertEquals(1.0, result[1][1],0);
	}
        
	
	@Test
    public void testMultiply() {
		double[][] in1 = new double[2][2] ;
		for(int i=0;i<2;i++) {
			for(int j=0;j<2;j++) {
				in1[i][j]=2;
			}
		}
		double[][] in2 = new double[2][2] ;
		for(int i=0;i<2;i++) {
			for(int j=0;j<2;j++) {
				in2[i][j]=1;
			}
		}
       double [][] result = Matrix.multiply(in1, in2);     
       assertEquals(2.0, result[0][0],0);
       assertEquals(2.0, result[1][0],0);
       assertEquals(2.0, result[0][1],0);
       assertEquals(2.0, result[1][1],0);
    }
	
	@Test
    public void testDotMultiply() {
		double[][] in1 = new double[1][2] ;
		for(int i=0;i<1;i++) {
			for(int j=0;j<2;j++) {
				in1[i][j]=2;
			}
		}
		double[][] in2 = new double[2][1] ;
		for(int i=0;i<2;i++) {
			for(int j=0;j<1;j++) {
				in2[i][j]=1;
			}
		}
       double [][] result = Matrix.multiply(in1, in2);
       assertEquals(4.0, result[0][0],0);
        
    }
	
	@Test
    public void testDSigmoid() {
		double[][] in1 = new double[2][2] ;
		for(int i=0;i<2;i++) {
			for(int j=0;j<2;j++) {
				in1[i][j]=2;
			}
		}
		
       double [][] result = Matrix.desigMap(in1);  
       assertEquals(-2.0, result[0][0],0);
       assertEquals(-2.0, result[1][0],0);
       assertEquals(-2.0, result[0][1],0);
       assertEquals(-2.0, result[1][1],0);
	}
	
	@Test
    public void testTranspose() {
		double[][] in1 = new double[2][1] ;
		for(int i=0;i<2;i++) {
			for(int j=0;j<1;j++) {
				in1[i][j]=2;
			}
		}
		
       double [][] result = Matrix.transpose(in1);  
       assertEquals(2.0, result[0][0],0);
       assertEquals(2.0, result[0][1],0);
       
	}
	
	@Test
    public void testFromArray() {
		double[] in1 = new double[] {1.0,2.0} ;
		
       double [][] result = Matrix.fromArray(in1,new double[2][2]);  
       assertEquals(1.0, result[0][0],0);
       assertEquals(2.0, result[1][0],0);
       
	}
	
	@Test
    public void testDiv() {
		double[][] in1 = new double[2][2] ;
		for(int i=0;i<2;i++) {
			for(int j=0;j<2;j++) {
				in1[i][j]=255;
			}
		}
		
       double [][] result = Matrix.divMap(in1);  
       assertEquals(1.0, result[0][0],0);
       assertEquals(1.0, result[1][0],0);
       assertEquals(1.0, result[0][1],0);
       assertEquals(1.0, result[1][1],0);
       
	}
	
}
