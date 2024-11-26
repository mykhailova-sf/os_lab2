import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Scheduler7 {

    // Функція для обчислення часу обробки та відгуку для FIFO та SJF
    public static void calculateTimes(int[] tasks, String scheduler) {
        int n = tasks.length;
        int[] responseTime = new int[n];
        int[] turnaroundTime = new int[n];
        int currentTime = 0;

        if (scheduler.equals("FIFO") || scheduler.equals("SJF")) {
            if (scheduler.equals("SJF")) {
                Arrays.sort(tasks); // Сортуємо масив для SJF
            }

            for (int i = 0; i < n; i++) {
                responseTime[i] = currentTime;
                turnaroundTime[i] = currentTime + tasks[i];
                currentTime += tasks[i];
            }
        }

        double avgTurnaroundTime = 0;
        double avgResponseTime = 0;
        for (int i = 0; i < n; i++) {
            avgTurnaroundTime += turnaroundTime[i];
            avgResponseTime += responseTime[i];
        }

        avgTurnaroundTime /= n;
        avgResponseTime /= n;

        System.out.println(scheduler + " - Середній час обробки (turnaround time): " + avgTurnaroundTime);
        System.out.println(scheduler + " - Середній час відгуку (response time): " + avgResponseTime);
        System.out.println();
    }

    // Round Robin (RR) алгоритм
    public static void calculateRR(int[] tasks, int timeSlice) {
        int n = tasks.length;
        int[] remainingTime = tasks.clone();
        int[] responseTime = new int[n];
        int[] turnaroundTime = new int[n];
        int currentTime = 0;

        for (int i = 0; i < n; i++) {
            responseTime[i] = -1;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            queue.add(i);
        }

        while (!queue.isEmpty()) {
            int taskIndex = queue.poll();
            if (remainingTime[taskIndex] > 0) {
                if (responseTime[taskIndex] == -1) {
                    responseTime[taskIndex] = currentTime;
                }

                int timeSpent = Math.min(remainingTime[taskIndex], timeSlice);
                remainingTime[taskIndex] -= timeSpent;
                currentTime += timeSpent;

                if (remainingTime[taskIndex] > 0) {
                    queue.add(taskIndex);
                } else {
                    turnaroundTime[taskIndex] = currentTime;
                }
            }
        }

        double avgTurnaroundTime = 0;
        double avgResponseTime = 0;
        for (int i = 0; i < n; i++) {
            avgTurnaroundTime += turnaroundTime[i];
            avgResponseTime += responseTime[i];
        }

        avgTurnaroundTime /= n;
        avgResponseTime /= n;

        System.out.println("Round Robin (RR) з тайм-слайсом " + timeSlice + " - Середній час обробки (turnaround time): " + avgTurnaroundTime);
        System.out.println("Round Robin (RR) з тайм-слайсом " + timeSlice + " - Середній час відгуку (response time): " + avgResponseTime);
        System.out.println();
    }

    public static void main(String[] args) {
        int[] tasks1 = {200, 200, 200};  // Завдання з однаковим часом виконання
        int[] tasks2 = {100, 200, 300};  // Завдання з різними тривалостями

        // Порівняння для FIFO, SJF і Round Robin для двох наборів
        System.out.println("Набір 1 (однаковий час виконання):");
        calculateTimes(tasks1, "FIFO");
        calculateTimes(tasks1, "SJF");
        calculateRR(tasks1, 1);  // Тайм-слайс 1 для Round Robin

        System.out.println("Набір 2 (різний час виконання):");
        calculateTimes(tasks2, "FIFO");
        calculateTimes(tasks2, "SJF");
        calculateRR(tasks2, 1);  // Тайм-слайс 1 для Round Robin
    }
}
