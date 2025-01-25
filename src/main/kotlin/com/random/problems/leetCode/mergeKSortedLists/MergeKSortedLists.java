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

        ListNode[] transformedInput = transformAll(inputLists);

        ListNode listNode = mergeKLists(transformedInput);

        print(listNode);
    }

    private static void print(ListNode listNode) {
        System.out.print("\nResult: (");
        printNode(listNode);
        System.out.print(")\n");
    }

    private static void printNode(ListNode listNode) {
        System.out.print(listNode.val + ",");
        if(listNode.next != null) {
            printNode(listNode.next);
        }
    }

    private static ListNode[] transformAll(List<List<Integer>> inputLists) {
        return inputLists.stream().map(MergeKSortedLists::transform).toList().toArray(ListNode[]::new);
    }

    private static ListNode transform(List<Integer> inputLists) {
        List<ListNode> listNodes = new ArrayList<>();
        ListNode currentNode;
        ListNode previousNode = null;
        for(Integer current: inputLists) {
            currentNode = new ListNode(current);
            if(previousNode != null) {
                previousNode.next = currentNode;
            }
            listNodes.add(currentNode);
            previousNode = currentNode;
        }
        return listNodes.get(0);
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        if(lists.length < 1) {
            return null;
        }
        LinkedList<ListNode> result = new LinkedList<>();

        LinkedList<ListNode> queue = new LinkedList<>(Arrays.asList(lists));

        while (!queue.isEmpty()) {
            ListNode nextNode = null;
            for (ListNode currentNode : queue) {
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

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
