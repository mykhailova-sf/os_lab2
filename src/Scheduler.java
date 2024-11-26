public class Scheduler {
    // Функція для обчислення часу відгуку і часу обробки для FIFO та SJF
    public static void calculateTimes(int[] tasks, String scheduler) {
        int n = tasks.length;
        int[] responseTime = new int[n];
        int[] turnaroundTime = new int[n];
        int currentTime = 0;

        // FIFO та SJF: виконуються завдання по черзі, адже всі мають однакову довжину
        if (scheduler.equals("FIFO") || scheduler.equals("SJF")) {
            for (int i = 0; i < n; i++) {
                // Час відгуку: час, коли завдання починає виконуватись
                responseTime[i] = currentTime;
                // Час обробки: час від прибуття до завершення
                turnaroundTime[i] = currentTime + tasks[i];
                currentTime += tasks[i]; // наступне завдання почнеться після поточного
            }
        }

        // Виводимо для кожного завдання
        for (int i = 0; i < n; i++) {
            System.out.println("Завдання " + (i + 1) + " - Час відгуку: " + responseTime[i] + ", Час обробки: " + turnaroundTime[i]);
        }

    }
}
