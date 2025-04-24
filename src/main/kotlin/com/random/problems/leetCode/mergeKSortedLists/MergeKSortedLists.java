package com.random.problems.leetCode.mergeKSortedLists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MergeKSortedLists {
    public static void main(String[] args) {
        List<List<Integer>> inputLists = List.of(
                List.of(1,4,5),
                List.of(1,3,4),
                List.of(2,6)
        );

        ListNode1[] transformedInput = transformAll(inputLists);

        ListNode1 listNode = mergeKLists(transformedInput);

        print(listNode);
    }

    private static void print(ListNode1 listNode) {
        System.out.print("\nResult: (");
        printNode(listNode);
        System.out.print(")\n");
    }

    private static void printNode(ListNode1 listNode) {
        System.out.print(listNode.val + ",");
        if(listNode.next != null) {
            printNode(listNode.next);
        }
    }

    private static ListNode1[] transformAll(List<List<Integer>> inputLists) {
        return inputLists.stream().map(MergeKSortedLists::transform).toList().toArray(ListNode1[]::new);
    }

    private static ListNode1 transform(List<Integer> inputLists) {
        List<ListNode1> listNodes = new ArrayList<>();
        ListNode1 currentNode;
        ListNode1 previousNode = null;
        for(Integer current: inputLists) {
            currentNode = new ListNode1(current);
            if(previousNode != null) {
                previousNode.next = currentNode;
            }
            listNodes.add(currentNode);
            previousNode = currentNode;
        }
        return listNodes.get(0);
    }

    public static ListNode1 mergeKLists(ListNode1[] lists) {
        if(lists.length < 1) {
            return null;
        }
        LinkedList<ListNode1> result = new LinkedList<>();

        LinkedList<ListNode1> queue = new LinkedList<>(Arrays.asList(lists));

        while (!queue.isEmpty()) {
            ListNode1 nextNode = null;
            for (ListNode1 currentNode : queue) {
                int currentValue = currentNode.val;
                if (nextNode == null || currentValue < nextNode.val) {
                    nextNode = currentNode;
                }
            }

            if(!result.isEmpty()) {
                result.getLast().next = nextNode;
            }
            result.add(nextNode);

            queue.remove(nextNode);
            if(nextNode.next != null) {
                queue.add(nextNode.next);
            }
        }

        return result.get(0);
    }

}

class ListNode1 {
    int val;
    ListNode1 next;
    ListNode1() {}
    ListNode1(int val) { this.val = val; }
    ListNode1(int val, ListNode1 next) { this.val = val; this.next = next; }
}
