class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        java.util.ArrayList<Integer>[] graph = new java.util.ArrayList[numCourses];

        for (int i = 0; i < numCourses; i++) {
            graph[i] = new java.util.ArrayList<>();
        }

        int[] indegree = new int[numCourses];

        for (int[] p : prerequisites) {
            int course = p[0];
            int prerequisite = p[1];

            graph[prerequisite].add(course);
            indegree[course]++;
        }

        java.util.Queue<Integer> queue = new java.util.LinkedList<>();

        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        int[] order = new int[numCourses];
        int index = 0;

        while (!queue.isEmpty()) {
            int course = queue.poll();
            order[index++] = course;

            for (int next : graph[course]) {
                indegree[next]--;

                if (indegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        if (index != numCourses) {
            return new int[0];
        }

        return order;
    }
}