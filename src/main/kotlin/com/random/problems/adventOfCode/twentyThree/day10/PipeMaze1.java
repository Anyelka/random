package com.random.problems.adventOfCode.twentyThree.day10;

import com.random.util.InputUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PipeMaze1 extends PipeMaze {
  @Override
  protected String findFurthestPointInPipe(String input) {
    char[][] map = InputUtil.toCharMatrix(input);

    Tile startTile = getStart(map);

    return "" + getFurthestPointDistance(map, startTile);
  }

  private Tile getStart(char[][] map) {
    for(int i = 0 ; i < map.length; i++) {
      char[] row = map[i];
      for(int j = 0; j <  row.length; j++) {
        if(map[i][j] == 'S') {
          return new Tile(i,j, 'S');
        }
      }
    }
    return null;
  }

  private long getFurthestPointDistance(char[][] tiles, Tile startTile) {
    return getPipe(tiles, startTile).size() / 2;
  }

  private List<Tile> getPipe(char[][] tiles, Tile startTile) {
    List<Tile> pipe = new ArrayList<>();

    Tile prevTile = null;

    Tile currentTile = startTile;

    boolean loopClosed = false;
    while(!loopClosed) {
      for(int i = currentTile.row - 1; i <= currentTile.row + 1; i++) {
        if(i >= 0 && i < tiles.length) {
          for(int j = currentTile.column - 1; j <= currentTile.column + 1; j++) {
            char[] row = tiles[i];
            if(j >= 0 && j < row.length) {
              Tile nextTile = new Tile(i, j, tiles[i][j]);
              if(!(i == currentTile.row && j == currentTile.column)
                  && canStepToNextTile(prevTile, currentTile, nextTile)) {

                prevTile = currentTile;

                pipe.add(nextTile);

                currentTile = nextTile;

                if(currentTile.isStart()) {
                  loopClosed = true;
                }
              }
            }
          }
        }
      }
    }
    return pipe;
  }

  private boolean canStepToNextTile(Tile prevTile, Tile currentTile, Tile nextTile) {
    for(Direction forwardDirection: currentTile.getAllowedDirections()) {
      for(Direction backwardDirection: nextTile.getAllowedDirections()) {
        if(!isAllowedMove(currentTile, prevTile, forwardDirection)
            && isAllowedMove(currentTile, nextTile, forwardDirection)
            && isAllowedMove(nextTile, currentTile, backwardDirection)
        ) {
          return true;
        }
      }
    }
    return false;
  }

  private boolean isAllowedMove(Tile from, Tile to, Direction direction) {
    return to != null && isAllowedMove(from.row, from.column, to.row, to.column, direction);
  }

  private boolean isAllowedMove(int fromRow, int fromColumn, int toRow, int toColumn, Direction direction) {
    return fromRow + direction.rowMove == toRow &&
        fromColumn + direction.columnMove == toColumn;
  }

  private class Tile {
    int row;
    int column;
    char symbol;

    public Tile(int row, int column, char symbol) {
      this.row = row;
      this.column = column;
      this.symbol = symbol;
    }

    public boolean isStart() {
      return this.symbol == 'S';
    }

    public List<Direction> getAllowedDirections() {
      switch (this.symbol) {
        case 'F': return List.of(Direction.RIGHT, Direction.DOWN);
        case 'J': return List.of(Direction.LEFT, Direction.UP);
        case '7': return List.of(Direction.LEFT, Direction.DOWN);
        case 'L': return List.of(Direction.RIGHT, Direction.UP);
        case '|': return List.of(Direction.UP, Direction.DOWN);
        case '-': return List.of(Direction.RIGHT, Direction.LEFT);
        case 'S': return List.of(Direction.UP, Direction.RIGHT, Direction.DOWN, Direction.LEFT);
        default: return Collections.emptyList();
      }
    }
  }

  private enum Direction {
    UP(-1,0), RIGHT(0, 1), DOWN(1, 0), LEFT(0, -1);
    int rowMove;
    int columnMove;

    Direction(int rowMove, int columnMove) {
      this.rowMove = rowMove;
      this.columnMove = columnMove;
    }

    public static List<Direction> all() {
      return List.of(Direction.values());
    }

  }

  public List<Direction> getAllowedDirections(char character) {
    switch (character) {
      case 'F': return List.of(Direction.RIGHT, Direction.DOWN);
      case 'J': return List.of(Direction.LEFT, Direction.UP);
      case '7': return List.of(Direction.LEFT, Direction.DOWN);
      case 'L': return List.of(Direction.RIGHT, Direction.UP);
      case '|': return List.of(Direction.UP, Direction.DOWN);
      case '-': return List.of(Direction.RIGHT, Direction.LEFT);
      case 'S': return List.of(Direction.UP, Direction.RIGHT, Direction.DOWN, Direction.LEFT);
      default: return Collections.emptyList();
    }
  }

}
