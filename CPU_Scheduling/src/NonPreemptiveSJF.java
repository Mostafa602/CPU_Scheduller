import java.util.*;

public class NonPreemptiveSJF {

    public void Schedule(List<Process> Processes, int GettingOlder) {
        // Prepare the Queue
        Processes.sort(Comparator.comparingInt(P -> P.ArrivalTime));
        PriorityQueue<Process> ReadyQueue = new PriorityQueue<>(
                Comparator.comparingInt((Process P) -> P.Priority)
                        .thenComparingInt(P -> P.ArrivalTime));

        List<Integer> ExecutionOrder = new ArrayList<>();
        int CurrentTime = 0;
        int ExecutedCount = 0;
        int TotalWaitingTime = 0;
        int TotalTurnaroundTime = 0;

        // Initialize priority with burst time for aging logic
        for (Process P : Processes) {
            P.Priority = P.BurstTime;
        }

        while (ExecutedCount < Processes.size()) {
            // Add processes to the ReadyQueue based on arrival time
            for (Process P : Processes) {
                if (P.ArrivalTime <= CurrentTime && P.RemainingTime > 0 && !ReadyQueue.contains(P)) {
                    ReadyQueue.add(P);
                }
            }

            // Apply Aging: Reduce priority of waiting processes if they wait too long
            for (Process P : ReadyQueue) {
                if (CurrentTime - P.ArrivalTime >= GettingOlder) {
                    P.Priority = Math.max(P.Priority - 1, 1); // Ensure priority doesn't go below 1
                }
            }

            // Execute the process with the highest priority
            if (!ReadyQueue.isEmpty()) {
                Process CurrentProcess = ReadyQueue.poll();

                int ExecutionTime = CurrentProcess.BurstTime;
                CurrentTime += ExecutionTime;
                CurrentProcess.RemainingTime = 0;

                // Calculate waiting time and turnaround time
                CurrentProcess.TurnaroundTime = CurrentTime - CurrentProcess.ArrivalTime;
                CurrentProcess.WaitingTime = CurrentProcess.TurnaroundTime - CurrentProcess.BurstTime;

                TotalWaitingTime += CurrentProcess.WaitingTime;
                TotalTurnaroundTime += CurrentProcess.TurnaroundTime;
                ExecutionOrder.add(CurrentProcess.ProcessId);
                ExecutedCount++;
            } else {
                CurrentTime++;
            }
        }

        // Display the results
        System.out.println("######## Processes Execution Order ########");
        System.out.println("Processes Execution Order: " + ExecutionOrder);
        System.out.println("\n######## Waiting Time and Turnaround Time ########");
        System.out.println("\nPID     Waiting Time    Turnaround Time");
        for (Process P : Processes) {
            System.out.printf("P%d      %-14d %d\n", P.ProcessId, P.WaitingTime, P.TurnaroundTime);
        }

        double AverageWaitingTime = (double) TotalWaitingTime / Processes.size();
        double AverageTurnaroundTime = (double) TotalTurnaroundTime / Processes.size();

        System.out.println("\n######## Averages ########");
        System.out.printf("Average Waiting Time: %.2f\n", AverageWaitingTime);
        System.out.printf("Average Turnaround Time: %.2f\n", AverageTurnaroundTime);
    }
}
