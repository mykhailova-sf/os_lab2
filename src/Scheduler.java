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

        // Обчислення середнього часу відгуку і часу обробки
        double avgTurnaroundTime = 0;
        double avgResponseTime = 0;
        for (int i = 0; i < n; i++) {
            avgTurnaroundTime += turnaroundTime[i];
            avgResponseTime += responseTime[i];
        }

        avgTurnaroundTime /= n;
        avgResponseTime /= n;

        System.out.println(scheduler + " - Середній час обробки: " + avgTurnaroundTime + ", Середній час відгуку: " + avgResponseTime);
    }
    public static void main(String[] args) {
        int[] tasks = {200, 200, 200};  // Довжини завдань

        // Обчислення для FIFO та SJF
        calculateTimes(tasks, "FIFO");
        calculateTimes(tasks, "SJF");
    }
}
