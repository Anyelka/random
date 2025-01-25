package com.random.problems.adventOfCode.twentyThree.day6;

import java.util.stream.LongStream;

class Race {
  Long time;
  Long record;

  public Race(Long time, Long record) {
    this.time = time;
    this.record = record;
  }

  long countPossibleWinningWays() {
    return LongStream.range(1, this.time-1)
        .map(this::getDistanceForTimePressed)
        .filter(distance -> distance > this.record)
        .count();
  }

  private Long getDistanceForTimePressed(Long timePressed) {
    return timePressed * (this.time-timePressed);
  }
}
