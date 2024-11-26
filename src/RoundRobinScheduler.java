import java.util.LinkedList;
import java.util.Queue;


public class RoundRobinScheduler {

    // Функція для обчислення часів обробки та відгуку для Round Robin
    public static void calculateRR(int[] tasks, int timeSlice) {
        int n = tasks.length;
        int[] remainingTime = tasks.clone();  // Копія масиву для відстеження залишкового часу
        int[] responseTime = new int[n];
        int[] turnaroundTime = new int[n];
        int currentTime = 0;

        // Ініціалізація масиву для часу відгуку
        for (int i = 0; i < n; i++) {
            responseTime[i] = -1;  // Час відгуку ще не обчислено
        }

        // Черга для завдань
        Queue<Integer> queue = new LinkedList<>();

        // Додаємо всі завдання в чергу
        for (int i = 0; i < n; i++) {
            queue.add(i);
        }

        // Виконання завдань за допомогою Round Robin
        while (!queue.isEmpty()) {
            int taskIndex = queue.poll();  // Отримуємо завдання з черги
            if (remainingTime[taskIndex] > 0) {
                if (responseTime[taskIndex] == -1) {
                    responseTime[taskIndex] = currentTime;  // Фіксуємо час відгуку при першому запуску
                }

                int timeSpent = Math.min(remainingTime[taskIndex], timeSlice);  // Виконання на тайм-слайсі
                remainingTime[taskIndex] -= timeSpent;
                currentTime += timeSpent;

                // Якщо завдання ще не завершилось, додаємо його назад у чергу
                if (remainingTime[taskIndex] > 0) {
                    queue.add(taskIndex);
                } else {
                    turnaroundTime[taskIndex] = currentTime;  // Час обробки (час завершення завдання)
                }
            }
        }

        // Обчислюємо середній час обробки (turnaround time) і середній час відгуку
        double avgTurnaroundTime = 0;
        double avgResponseTime = 0;
        for (int i = 0; i < n; i++) {
            avgTurnaroundTime += turnaroundTime[i];
            avgResponseTime += responseTime[i];
        }

        avgTurnaroundTime /= n;
        avgResponseTime /= n;

        // Виводимо результати
        System.out.println("Round Robin з тайм-слайсом " + timeSlice + ":");
        System.out.println("Середній час обробки (turnaround time): " + avgTurnaroundTime);
        System.out.println("Середній час відгуку (response time): " + avgResponseTime);
        System.out.println();
    }

    public static void main(String[] args) {
        // Набір завдань з різними тривалостями виконання
        int[] tasks = {100, 200, 300};  // Завдання з тривалістю 100, 200 та 300 одиниць часу

        // Оцінка ефективності для різних тайм-слайсів
        int[] timeSlices = {1, 5, 10};  // Тайм-слайси для порівняння

        for (int timeSlice : timeSlices) {
            calculateRR(tasks, timeSlice);
        }
    }

}
