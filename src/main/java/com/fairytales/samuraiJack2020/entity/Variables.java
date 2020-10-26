package com.fairytales.samuraiJack2020.entity;

public class Variables {
	private char player;
	private int timeout;
	private int turns;
	private String order;
	private int shield;
	
	public Variables() {
		
	}

	public char getPlayer() {
		return player;
	}

	public void setPlayer(char player) {
		this.player = player;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public int getTurns() {
		return turns;
	}

	public void setTurns(int turns) {
		this.turns = turns;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public int getShield() {
		return shield;
	}

	public void setShield(int shield) {
		this.shield = shield;
	}

	@Override
	public String toString() {
		return "Variables [player=" + player + ", timeout=" + timeout + ", turns=" + turns + ", order=" + order
				+ ", shield=" + shield + "]";
	}
	
	
	

}
