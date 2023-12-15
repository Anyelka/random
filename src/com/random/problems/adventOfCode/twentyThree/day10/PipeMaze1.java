package com.random.problems.adventOfCode.twentyThree.day10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PipeMaze1 extends PipeMaze {
  @Override
  protected String findFurthestPointInPipe(String input) {
    char[][] map = getMap(input);
    List<List<Tile>> tiles = getTiles(map);
    Tile start = getStart(tiles);
    return "" + getFurthestPointDistance(tiles, start);
  }

  private long getFurthestPointDistance(List<List<Tile>> tiles, Tile start) {
    return getDistance(0, tiles, start) / 2;
  }

  private long getDistance(int i, List<List<Tile>> tiles, Tile currentTile) {
    Tile nextTile = getNextTile(tiles, currentTile);
    if(nextTile.isStart()) {
      return 1;
    }
    return 1 + getDistance(i+1, tiles, nextTile);
  }

  private Tile getNextTile(List<List<Tile>> tiles, Tile currentTile) {
    int centerRow = currentTile.row;
    int centerColumn = currentTile.column;
    for(int i = centerRow - 1 ; i <= centerRow + 1; i++) {
      for(int j = centerColumn - 1 ; j <= centerColumn + 1; j++) {
        if(i >= 0 && i < tiles.size()) {
          List<Tile> row = tiles.get(i);
          if(j >= 0 && j < row.size()) {
            Tile adjacentTile = row.get(j);
            if(!currentTile.equals(adjacentTile) && currentTile.canStepToTile(adjacentTile)) {
              return adjacentTile;
            }
          }
        }
      }
    }
    return null;
  }

  private Tile getStart(List<List<Tile>> tiles) {
    return tiles.stream().flatMap(Collection::stream).filter(tile -> tile.character == 'S').findFirst().get();
  }

  private List<List<Tile>> getTiles(char[][] map) {
    return IntStream.range(0, map.length).mapToObj(
        i -> IntStream.range(0, map[i].length)
            .mapToObj(j -> getTile(i, j, map))
            .collect(Collectors.toList()))
        .collect(Collectors.toList());
  }

  private Tile getTile(int i, int j, char[][] map) {
    char character = map[i][j];
    return new Tile(i, j, character);
  }

  private char[][] getMap(String input) {
    return Arrays.stream(input.split("\n")).map(String::toCharArray).toArray(char[][]::new);
  }

  class Tile {
    int row;
    int column;
    char character;
    List<Direction> allowedDirections;

    public Tile(int row, int column, char character) {
      this.row = row;
      this.column = column;
      this.character = character;
      this.allowedDirections = new ArrayList<>(initAllowedDirections());
    }

    public List<Direction> initAllowedDirections() {
      switch (this.character) {
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

    public List<Direction> getAllowedDirections() {
      return this.allowedDirections;
    }

    public boolean isStart() {
      return this.character == 'S';
    }
    
    public List<Direction> getForwardDirections(Tile previousTile) {
      return previousTile == null ? Direction.all() : this.getAllowedDirections().stream()
          .filter(direction -> isMoveToDirection(previousTile, direction))
          .collect(Collectors.toList());
    }

    private boolean isMoveToDirection(Tile previousTile, Direction direction) {
      return this.column == previousTile.column + direction.columnMove
          && this.row == previousTile.row + direction.rowMove;
    }

    private boolean isAdjacentTo(int i, int j) {
      return this.row == i && (this.column <= j +1 && this.column >= j -1) ||
          this.column == j && (this.row <= i +1 && this.row >= i -1);
    }

    private boolean isAdjacentTo(Tile adjacentTile) {
      return isAdjacentTo(adjacentTile.row, adjacentTile.column);
    }

    public boolean canStepToTile(Tile adjacentTile) {
      if(this.isAdjacentTo(adjacentTile)) {
        for(Direction direction : this.getAllowedDirections()){
          boolean canStepOnNextTile = this.row + direction.rowMove == adjacentTile.row &&
              this.column + direction.columnMove == adjacentTile.column;
          if(canStepOnNextTile) {
            for(Direction adjacentTileDirection : adjacentTile.getAllowedDirections()){
              boolean canAcceptStep = adjacentTile.row + adjacentTileDirection.rowMove == this.row &&
                  adjacentTile.column + adjacentTileDirection.columnMove == this.column;
              if(canAcceptStep) {
                adjacentTile.removeAllowedDirection(adjacentTileDirection);
                return true;
              }
            }
          }
        }
      }
      return false;
    }

    private boolean canMoveInDirectionOfTile(Tile adjacentTile) {
      for(Direction direction1 : this.getAllowedDirections()){
        for(Direction direction2 : adjacentTile.getAllowedDirections()){
          boolean canStepTo = direction1.canStepTo(direction2);
          if (canStepTo) {
            adjacentTile.removeAllowedDirection(direction2);
          }
          return canStepTo;
        }
      }
      return false;
    }

    private void removeAllowedDirection(Direction direction) {
      try {
        this.getAllowedDirections().remove(direction);

      } catch (StackOverflowError e) {
        System.out.println("stackoverflow: " + e);
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

    public boolean canStepTo(Direction next) {
      return (this.rowMove == next.rowMove && this.columnMove == -next.columnMove) ||
          (this.rowMove == -next.rowMove && this.columnMove == next.columnMove);
    }
  }

}
