package com.random.problems.meetingRooms;

import com.random.util.Interval;

import java.util.Comparator;
import java.util.List;

public class Solution1 {
    //  TC: O(n * log(n))
    //  SC: O(1)
    public boolean canAttendMeetings(List<Interval> intervals) {
        intervals.sort(Comparator.comparingInt(i -> i.start));
        int maxEnd = 0;
        for(Interval interval: intervals) {
            if(interval.start < maxEnd) {
                return false;
            }
            if(interval.end > maxEnd) {
                maxEnd = interval.end;
            }
        }
        return true;
    }
}
