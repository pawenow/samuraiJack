package com.fairytales.samuraiJack2020.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BoardElement {
	private char sign;
	private Position position;
	
	public static char[] elementTypes  = {
		'@', '$', '#'};
	
	public static char[] playerTypes  = {
			'A', 'B', 'C', 'D','E','F'};

	public static List<Character> elementTypesList = Arrays.asList('@', '$', '#');

	public static List<Character> playersTypesList = Arrays.asList('A', 'B', 'C', 'D','E','F','G');

	
	
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
