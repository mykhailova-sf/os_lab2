# Lab 2: Operating Systems - Process Scheduling Algorithms 🖥️🔧

This lab focuses on the implementation and comparison of process scheduling algorithms. The work involves simulating and analyzing the performance of the **FIFO**, **SJF**, and **Round Robin (RR)** algorithms for calculating key scheduling metrics such as **response time**, **turnaround time**, and **waiting time**.

## Description 📋

The goal of this lab is to:
- Compare process scheduling algorithms in operating systems.
- Calculate the main scheduling parameters for different algorithms.
- Perform simulations for various sets of input tasks.

## Key Files 📂

### [Mykhailova_Sofiia_lab2.pdf](./Mykhailova_Sofiia_lab2.pdf) 📖
This file contains the theoretical part of the lab, including the problem description, the scheduling algorithms, and the mathematical formulas for calculating response time, turnaround time, and waiting time.

### [Mykhailova_Sofiia_lab2_os_scheduler.py.pdf](./src/Mykhailova_Sofiia_lab2_os_scheduler.py.pdf) 💻
This file contains the programmatic implementation of the scheduling algorithms in Python. The algorithms implemented in the code:
- FIFO (First In, First Out) 🚶‍♂️
- SJF (Shortest Job First) ⏱️
- RR (Round Robin) 🔄

The program allows simulating different scheduling scenarios and comparing their effectiveness.

## Running the Program 🏃‍♂️💨

### Instructions for Running 🛠️

1. **Clone the repository**:
   ```bash
   git clone https://github.com/your_username/lab2_os.git
Results 📊

The program will output the following statistics for each algorithm:
1. Response Time ⏲️
The Response Time is the amount of time that passes between the submission of a job and the moment it starts execution.
It represents how long a job waits before its first execution.
3. Turnaround Time 🔄
The Turnaround Time is the total time it takes for a job to complete its execution.
It’s the sum of the response time and the time it spends executing. This is calculated from the time the job arrives until the moment it finishes.
5. Wait Time ⏳
The Wait Time is the total amount of time a job spends in the queue, waiting for the CPU.
This is calculated by subtracting the time spent executing from the total turnaround time.
