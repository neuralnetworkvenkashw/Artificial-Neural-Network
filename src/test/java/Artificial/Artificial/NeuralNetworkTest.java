package Artificial.Artificial;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import Artificial.Matrix;
import Artificial.NeuralNetwork;

public class NeuralNetworkTest {
	
	@Test
	public void feedForwardTest() {
		double[][] val = new double[2][2];
		NeuralNetwork nn = new NeuralNetwork(2, 2, 1);
		Matrix weights_ih = new Matrix(2, 2);
		Matrix weights_ho = new Matrix(1, 2);	
		Matrix bias_h = new Matrix(2,1);
		Matrix bias_o = new Matrix(1,1);
		
		for(int i=0;i<2;i++) {
			for(int j=0;j<2;j++) {
				val[i][j] = 1;
			}
		}
		
		double[][] wih = new double[2][2];		
		for(int i=0;i<2;i++) {
			for(int j=0;j<2;j++) {
				wih[i][j] = .5;
			}
		}
		weights_ih.setData(wih);
		double[][] who = new double[2][2];		
		for(int i=0;i<2;i++) {
			for(int j=0;j<2;j++) {
				who[i][j] = .5;
			}
		}
		weights_ho.setData(who);
		double[][] bh = new double[2][2];	
		for(int i=0;i<2;i++) {
			for(int j=0;j<2;j++) {
				bh[i][j] = .1;
			}
		}
		bias_h.setData(bh);
		double[][] bo = new double[2][2];		
		for(int i=0;i<2;i++) {
			for(int j=0;j<2;j++) {
				bo[i][j] = 1;
			}
		}
		bias_o.setData(bo);
		nn.setBias_h(bias_h);
		nn.setBias_o(bias_o);
		nn.setWeights_ho(weights_ho);
		nn.setWeights_ih(weights_ih);
		double [] result = nn.feedforward(val);
		assertEquals(0.7, result[0],.1);
		
	}
	
	@Test
	public void backProbTest() {
		double[][] val = new double[2][2];
		double[][] target;
		NeuralNetwork nn = new NeuralNetwork(2, 2, 1);
		Matrix weights_ih = new Matrix(2, 2);
		Matrix weights_ho = new Matrix(1, 2);	
		Matrix bias_h = new Matrix(2,1);
		Matrix bias_o = new Matrix(1,1);
		
		double[] in = new double[]{1,1};
		Matrix mIn = new Matrix(in.length,1);
	    val = Matrix.fromArray(in, mIn.getData());
	    
		double[] tar = new double[]{1};
		mIn = new Matrix(tar.length,1);
	    target = Matrix.fromArray(tar, mIn.getData());
		
		double[][] wih = new double[2][2];		
		for(int i=0;i<2;i++) {
			for(int j=0;j<2;j++) {
				wih[i][j] = .5;
			}
		}
		weights_ih.setData(wih);
		double[][] who = new double[1][2];		
		for(int i=0;i<1;i++) {
			for(int j=0;j<2;j++) {
				who[i][j] = .5;
			}
		}
		weights_ho.setData(who);
		double[][] bh = new double[2][1];	
		for(int i=0;i<2;i++) {
			for(int j=0;j<1;j++) {
				bh[i][j] = .1;
			}
		}
		bias_h.setData(bh);
		double[][] bo = new double[1][1];		
		for(int i=0;i<1;i++) {
			for(int j=0;j<1;j++) {
				bo[i][j] = 1;
			}
		}
		bias_o.setData(bo);
		nn.setBias_h(bias_h);
		nn.setBias_o(bias_o);
		nn.setWeights_ho(weights_ho);
		nn.setWeights_ih(weights_ih);
		nn.train(val, target);
		double result [][]= nn.getWeights_ih().getData();
		assertEquals(0.5001, result[0][0],.1);
		assertEquals(0.5001, result[0][0],.1);
		assertEquals(0.5001, result[0][0],.1);
		assertEquals(0.5001, result[0][0],.1);
		
	}
}
