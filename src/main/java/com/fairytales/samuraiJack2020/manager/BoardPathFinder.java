package com.fairytales.samuraiJack2020.manager;

import com.fairytales.samuraiJack2020.SamuraiUtils;
import com.fairytales.samuraiJack2020.entity.Board;
import com.fairytales.samuraiJack2020.entity.Move;
import com.fairytales.samuraiJack2020.entity.Position;
import org.springframework.data.util.Pair;

import java.util.*;

public class BoardPathFinder {

    public static final int[][] DIRECTIONS = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };


    public List<Pair<Move,Position>> solve(Board board) {
        LinkedList<Position> nextToVisit = new LinkedList<>();
        Position start = board.getEntry();
        nextToVisit.add(start);
        board.resetVisited();
        while (!nextToVisit.isEmpty()) {
            Position cur = nextToVisit.remove();

            if (!board.isValidLocation(cur.getW(), cur.getK()) || board.isExplored(cur.getW(), cur.getK())) {
                continue;
            }

            if (board.isWall(cur.getW(), cur.getK()) || board.isOtherPlayer(cur.getW(), cur.getK())) {
                board.setVisited(cur.getW(), cur.getK(), true);
                continue;
            }

            if (board.isGoal(cur.getW(), cur.getK())) {
                return backtrackPath(cur);
            }

            for (int[] direction : DIRECTIONS) {
                Position position = new Position(cur.getW() + direction[0], cur.getK() + direction[1], cur);
                nextToVisit.add(position);
                board.setVisited(cur.getW(), cur.getK(), true);
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
            if(prev!=null) {
                path.add(Pair.of(SamuraiUtils.computeMove(iter, prev), iter));
            }
            prev = iter;
            iter = iter.getParent();

        }

        return path;
    }



}
