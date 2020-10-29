package com.fairytales.samuraiJack2020.entity;

public class Position {
	int w;
	int k;
	Position parent;
	
	public Position() {
		
	}
	
	

	public Position(int w, int k) {
		this.w = w;
		this.k = k;
		this.parent = null;
	}

	public Position(int w, int k, Position parent) {
		this.w = w;
		this.k = k;
		this.parent = parent;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getK() {
		return k;
	}

	public void setK(int k) {
		this.k = k;
	}

	public Position getParent(){
		return  parent;
	}

	public void setParent(Position parent){
		this.parent = parent;
	}

	@Override
	public String toString() {
		return "Position{" +
				"w=" + w +
				", k=" + k +
				", parent=" + parent +
				'}';
	}
}
