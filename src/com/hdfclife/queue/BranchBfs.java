package com.hdfclife.queue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Set;

public final class BranchBfs {

    private BranchBfs() {
    }

    public static String traverse() {

        Map<String, List<String>> graph = new HashMap<String, List<String>>();

        graph.put("MUMBAI", listOf("PUNE", "DELHI"));

        graph.put("PUNE", listOf("HYDERABAD"));

        graph.put("DELHI", listOf("KOLKATA"));

        graph.put("HYDERABAD", listOf("CHENNAI"));

        graph.put("KOLKATA", listOf());

        graph.put("CHENNAI", listOf());

        Queue<String> queue = new LinkedList<String>();

        Set<String> visited = new HashSet<String>();

        queue.offer("MUMBAI");
        visited.add("MUMBAI");

        StringBuilder result = new StringBuilder();

        while (!queue.isEmpty()) {

            String branch = queue.poll();

            if (result.length() > 0) {
                result.append(", ");
            }

            result.append(branch);

            List<String> neighbors = graph.get(branch);

            for (int i = 0; i < neighbors.size(); i++) {

                String next = neighbors.get(i);

                if (visited.add(next)) {
                    queue.offer(next);
                }
            }
        }

        return result.toString();
    }

    private static List<String> listOf(String... values) {

        List<String> result = new ArrayList<String>();

        for (int i = 0; i < values.length; i++) {

            result.add(values[i]);
        }

        return result;
    }
}