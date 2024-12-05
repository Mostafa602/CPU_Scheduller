import java.util.*;
public class SRTFScheduler {

    public void Schedule(List<Process> Processes) {

        Processes.sort(Comparator.comparingInt(P -> P.ArrivalTime));

        PriorityQueue<Process> ReadyQueue = new PriorityQueue<>(
                Comparator.comparingInt((Process P) -> P.RemainingTime)
                        .thenComparingInt(P -> P.ArrivalTime));

        List<Integer> ExecutionOrder = new ArrayList<>();
        int CurrentTime = 0;
        int TotalWaitingTime = 0;
        int TotalTurnaroundTime = 0;
        int ExecutedCount = 0;
        int LastProcessId = -1; // Track the last executed process to avoid repetition

        while (ExecutedCount < Processes.size()) {

            for (Process P : Processes) {
                if (P.ArrivalTime <= CurrentTime && P.RemainingTime > 0 && !ReadyQueue.contains(P)) {
                    ReadyQueue.add(P);
                }
            }

            if (!ReadyQueue.isEmpty()) {

                Process CurrentProcess = ReadyQueue.poll();


                CurrentProcess.RemainingTime--;
                CurrentTime++;

                if (CurrentProcess.ProcessId != LastProcessId) {
                    ExecutionOrder.add(CurrentProcess.ProcessId);
                    LastProcessId = CurrentProcess.ProcessId;
                }


                if (CurrentProcess.RemainingTime == 0) {
                    ExecutedCount++;
                    CurrentProcess.TurnaroundTime = CurrentTime - CurrentProcess.ArrivalTime;
                    CurrentProcess.WaitingTime = CurrentProcess.TurnaroundTime - CurrentProcess.BurstTime;
                    TotalWaitingTime += CurrentProcess.WaitingTime;
                    TotalTurnaroundTime += CurrentProcess.TurnaroundTime;
                } else {
                    // If the process is not finished, add it back to the queue
                    ReadyQueue.add(CurrentProcess);
                }
            } else {
                // If no process is ready, increment time
                CurrentTime++;
            }
        }


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