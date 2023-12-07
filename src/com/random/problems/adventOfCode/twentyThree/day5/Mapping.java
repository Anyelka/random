package com.random.problems.adventOfCode.twentyThree.day5;

public class Mapping {
  Long destinationRangeStart;
  Long sourceRangeStart;
  Long rangeLength;

  public Mapping(String destinationRangeStart, String sourceRangeStart, String rangeLength) {
    this.destinationRangeStart = Long.parseLong(destinationRangeStart);
    this.sourceRangeStart = Long.parseLong(sourceRangeStart);
    this.rangeLength = Long.parseLong(rangeLength);
  }

  boolean isInRange(Long value) {
    return this.sourceRangeStart <= value && value <= (this.sourceRangeStart + this.rangeLength - 1);
  }

  Long mapValue(Long value) {
    return value + (this.destinationRangeStart-this.sourceRangeStart);
  }
}
