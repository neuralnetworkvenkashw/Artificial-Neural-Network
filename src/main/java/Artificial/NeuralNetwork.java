package Artificial;

import java.util.Random;

public class NeuralNetwork {
	private int Counter = 0;
	private Random random = new Random();
	private int inputNode;
	private int hiddenNode;
	private int outputNode;
	//Matrix matrix = new Matrix();
	private Matrix weights_ih; 
	private Matrix weights_ho;
	private Matrix bias_h;
	private Matrix bias_o;
	private double learningrate =.3;
	
	public NeuralNetwork(int inputNode, int hiddenNode, int outputNode) {
			this.inputNode = inputNode;
			this.hiddenNode = hiddenNode;
			this.outputNode = outputNode;
			this.weights_ih = new Matrix(hiddenNode, inputNode);
			this.weights_ho = new Matrix(outputNode, hiddenNode);
			weights_ih.random(random);
			weights_ho.random(random);
			this.bias_h = new Matrix(this.hiddenNode,1);
			this.bias_o = new Matrix(this.outputNode,1);
			bias_h.random(random);
			bias_o.random(random);
	}
	
	/* feedForward to guess the input*/
	public double[] feedforward(double[][] input) {
		double[][] result=weights_ih.multiply(input);
		bias_h.add(result);
		bias_h.map();
		double[][] out = weights_ho.multiply(bias_h.getData());
		bias_o.add(out);
		bias_o.map();
		return bias_o.toArray();
	}
	
	/* feedForward and back propagation to adjust the weights based on the difference between the output and the target*/
	public void train(double[][] input, double[][] targets) {
		double[][] hidden_inputs=weights_ih.multiply(input);
		bias_h.add(hidden_inputs);
		double [][] hidden_output = bias_h.map();
		double[][] output_inputs = weights_ho.multiply(hidden_output);
		bias_o.add(output_inputs);
		double [][] outputs = bias_o.map();		
		double[] out =  bias_o.toArray();
		//if((out[0]>.89 && category.equals("objectOne")) || (out[1]>.89 && category.equals("objectTwo")))
		//	System.out.println("output:"+out[0]+","+out[1]+"          count:"+ Counter++ +"       Category: "+category+"   File:"+cd);
		//Matrix m = new Matrix(out.length, 1);
		//double[][] output = Matrix.fromArray(out, m.data);
		
		//calculate Error
		double[][] outError= Matrix.subtract(targets, outputs);
		
		//System.out.println("Error:"+outError[0][0]+","+outError[1][0]+"          count:"+ Counter++);
		/*
		 * if((outError[0][0]<.2 && outError[0][0]>-.2) && (outError[1][0]<.2 &&
		 * outError[1][0]>-.2))
		 * System.out.println("Error:"+outError[0][0]+","+outError[1][0]
		 * +"          count:"+ Counter +"       Category: "+category+"   File:"+cd);
		 */
		//if(learningrate!=.1 && (outError[0][0]>1.0 || outError[0][0]<-1.0) && (outError[1][0]>1.0 || outError[1][0]<-1.0)) this.learningrate = .1;
		double [][] who_t = Matrix.transpose(weights_ho.getData()); 
		double[][] hidError = Matrix.multiply(who_t,outError);
		
		// calculate gradient
		double[][] gradients = Matrix.multiply(Matrix.multiply(Matrix.desigMap(outputs), outError),learningrate);
		
		//calculate hiddenGradient
		double[][] hiddenGradients = Matrix.multiply(Matrix.multiply(Matrix.desigMap(hidden_output), hidError),learningrate);
		
		//calculate delta
		double[][] hidden_T = Matrix.transpose(hidden_output);
		double[][] weight_ho_deltas = Matrix.multiply(gradients,hidden_T);
		weights_ho.add(weight_ho_deltas);
		bias_o.add(gradients);
		
		//calculate hidden delta
		double[][] hidden_input_T = Matrix.transpose(input);
		double[][] deltaW_hidden = Matrix.multiply(hiddenGradients,hidden_input_T);
		weights_ih.add(deltaW_hidden);
		bias_h.add(hiddenGradients);
		
				
	}

	public Matrix getWeights_ih() {
		return weights_ih;
	}

	public void setWeights_ih(Matrix weights_ih) {
		this.weights_ih = weights_ih;
	}

	public Matrix getWeights_ho() {
		return weights_ho;
	}

	public void setWeights_ho(Matrix weights_ho) {
		this.weights_ho = weights_ho;
	}

	public Matrix getBias_h() {
		return bias_h;
	}

	public void setBias_h(Matrix bias_h) {
		this.bias_h = bias_h;
	}

	public Matrix getBias_o() {
		return bias_o;
	}

	public void setBias_o(Matrix bias_o) {
		this.bias_o = bias_o;
	}
	
	

}
