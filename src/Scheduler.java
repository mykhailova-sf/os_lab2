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

    // Функція для обчислення часу відгуку і часу обробки для Round Robin (RR)
    public static void calculateRR(int[] tasks, int timeSlice) {
        int n = tasks.length;
        int[] remainingTime = tasks.clone();
        int[] responseTime = new int[n];
        int[] turnaroundTime = new int[n];
        int currentTime = 0;

        // Ініціалізація responseTime для кожного завдання
        for (int i = 0; i < n; i++) {
            responseTime[i] = -1; // Час відгуку ще не встановлено
        }

        // Проводимо цикл до тих пір, поки всі завдання не завершаться
        while (true) {
            boolean allDone = true;
            for (int i = 0; i < n; i++) {
                if (remainingTime[i] > 0) {
                    allDone = false; // Якщо є незавершені завдання, то виходимо з циклу

                    // Час відгуку для кожного завдання встановлюється при його першому запуску
                    if (responseTime[i] == -1) {
                        responseTime[i] = currentTime; // Встановлюємо час відгуку
                    }

                    // Виконуємо поточне завдання на тайм-слайсі
                    int runTime = Math.min(remainingTime[i], timeSlice);
                    remainingTime[i] -= runTime;
                    currentTime += runTime; // Оновлюємо поточний час
                }
            }

            // Якщо всі завдання завершено, вийдемо з циклу
            if (allDone) {
                break;
            }
        }

        // Обчислюємо час обробки для кожного завдання
        for (int i = 0; i < n; i++) {
            turnaroundTime[i] = currentTime - (tasks[i] - remainingTime[i]);
        }

        // Виведення результатів
        double avgTurnaroundTime = 0;
        double avgResponseTime = 0;
        for (int i = 0; i < n; i++) {
            avgTurnaroundTime += turnaroundTime[i];
            avgResponseTime += responseTime[i];
        }

        avgTurnaroundTime /= n;
        avgResponseTime /= n;

        System.out.println("Round Robin (RR) - Середній час обробки: " + avgTurnaroundTime + ", Середній час відгуку: " + avgResponseTime);
    }


    public static void main(String[] args) {
        int[] tasks = {100, 200, 300};  // Довжини завдань

        // Обчислення для FIFO та SJF
        calculateTimes(tasks, "FIFO");
        calculateTimes(tasks, "SJF");

        // Викликаємо метод для Round Robin з тайм-слайсом 1
        calculateRR(tasks, 1);
    }
}
