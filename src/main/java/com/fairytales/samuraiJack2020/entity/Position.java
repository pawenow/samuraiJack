package com.fairytales.samuraiJack2020.entity;

public class Position {
	int x;
	int y;
	Position parent;
	
	public Position() {
		
	}
	
	

	public Position(int x, int y) {
		this.x = x;
		this.y = y;
		this.parent = null;
	}

	public Position(int x, int y, Position parent) {
		this.x = x;
		this.y = y;
		this.parent = parent;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
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
				"x=" + x +
				", y=" + y +
				", parent=" + parent +
				'}';
	}
}
