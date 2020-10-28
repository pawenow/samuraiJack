package com.fairytales.samuraiJack2020.manager;

import com.fairytales.samuraiJack2020.entity.Board;
import com.fairytales.samuraiJack2020.entity.Move;
import com.fairytales.samuraiJack2020.entity.Position;
import org.springframework.data.util.Pair;

import java.util.*;

public class BoardPathFinder {

    private static final int[][] DIRECTIONS = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };


    public List<Pair<Move,Position>> solve(Board board) {
        LinkedList<Position> nextToVisit = new LinkedList<>();
        Position start = board.getEntry();
        nextToVisit.add(start);

        while (!nextToVisit.isEmpty()) {
            Position cur = nextToVisit.remove();

            if (!board.isValidLocation(cur.getX(), cur.getY()) || board.isExplored(cur.getX(), cur.getY())) {
                continue;
            }

            if (board.isWall(cur.getX(), cur.getY())) {
                board.setVisited(cur.getX(), cur.getY(), true);
                continue;
            }

            if (board.isGoal(cur.getX(), cur.getY())) {
                return backtrackPath(cur);
            }

            for (int[] direction : DIRECTIONS) {
                Position position = new Position(cur.getX() + direction[0], cur.getY() + direction[1], cur);
                System.out.println(position.toString());
                nextToVisit.add(position);
                board.setVisited(cur.getX(), cur.getY(), true);
            }
        }
        return Collections.emptyList();
    }

    private List<Pair<Move,Position>> backtrackPath(Position cur) {
        List<Pair<Move,Position>> path = new ArrayList<>();
        Position prev = null;
        Position iter = cur;
        while (iter != null) {
            //path.add(iter);

            path.add(Pair.of(computeMove(iter, prev),iter));
            prev = iter;
            iter = iter.getParent();

        }

        return path;
    }

    private Move computeMove(Position parent, Position child ){
        //compute how to be parent when it's child
        Move move;
        if(child == null ) return null;
        if(parent.getX()!=child.getX()){
            if(parent.getY() - child.getY()>0){
                //UP
                move = new Move(Move.Action.Walk, Move.Direction.UP);
            }else{
                //DOWN
                move = new Move(Move.Action.Walk, Move.Direction.DOWN);
            }
        }else{
            if(parent.getX() - child.getX()>0){
                //UP
                move = new Move(Move.Action.Walk, Move.Direction.LEFT);
            }else{
                //DOWN
                move = new Move(Move.Action.Walk, Move.Direction.RIGHT);
            }

        }

        return move;

    }

}
