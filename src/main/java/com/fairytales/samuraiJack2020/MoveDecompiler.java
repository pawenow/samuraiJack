package com.fairytales.samuraiJack2020;

import com.fairytales.samuraiJack2020.entity.Move;

public class MoveDecompiler {

	
	public static String compile(Move move) {
		String stringAction = transformActionToChar(move.getAction());
		String stringDir = transformDirectionToChar(move.getDirection());
		return stringAction.concat(stringDir);
	}
	
	public static Move decompile(String stringMove) {
		// nothing as for now not needed
		return null;
	}
	
    public static String transformActionToChar(Move.Action moveAction) {

        switch (moveAction) {
            case Walk : return "W";
            case Fire : return "F";
            case Take : return "T";
            case Freeze : return "ZZ";
            case Nothing : return "NO";
            case PostExit : return "EX";
            default: return "W";
        }
    }
    
    public static String transformDirectionToChar(Move.Direction moveDirection) {

        switch (moveDirection) {
            case UP : return "U";
            case DOWN : return "D";
            case LEFT : return "L";
            case RIGHT : return "R";
            case NoDirection : return "";
            default: return "";
        }
    }
	
	/*
	 * Directive actions:
	 * o W = Walk
	 * o F = Fire a Freeze ray
	 * o T = Take
	 * o All will be accompanied by on of: U,D,L,R (corresponding to
	 * Up,Down,Left,Right)
	 * o For example: WU = Walk Up
	 * 
	 * · Any other action will be ignored, but for clarity we recommend:
	 * o ZZ = sleep (if you were hit)
	 * o NO = explicit do nothing
	 * o EX = after exit
	 */
}
