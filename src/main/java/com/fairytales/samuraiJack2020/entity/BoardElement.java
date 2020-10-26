package com.fairytales.samuraiJack2020.entity;

public class BoardElement {
	private char sign;
	private Position position;
	
	public static char[] elementTypes  = {
		'@', '$', '#'};
	
	public static char[] playerTypes  = {
			'A', 'B', 'C', 'D'};
	
	
	public BoardElement(char theSign, Position thePosition) {
		this.sign = theSign;
		this.position = thePosition;
	}

	public char getSign() {
		return sign;
	}

	public void setSign(char sign) {
		this.sign = sign;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
	
	

}
