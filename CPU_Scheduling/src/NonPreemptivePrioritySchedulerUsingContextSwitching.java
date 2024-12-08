import java.util.*;

public class NonPreemptivePrioritySchedulerUsingContextSwitching {

    public List<timeLine> Schedule(List<Process> Processes, int SwitchTime) {
        List<timeLine> TimeLine = new ArrayList<timeLine>();

        Processes.sort(Comparator.comparingInt((Process P) -> P.ArrivalTime)
                .thenComparingInt(P -> P.Priority));
        List<Integer> ExecutionOrder = new ArrayList<>();

        int lst_time = 0;
        int CurrentTime = 0;
        int TotalWaitingTime = 0;
        int TotalTurnaroundTime = 0;

        for (Process P : Processes) {
            lst_time = CurrentTime;
            // wait for it to arrive
            if (CurrentTime < P.ArrivalTime) {
                CurrentTime = P.ArrivalTime;
            }

            int ExecutionTime = P.RemainingTime;
            CurrentTime += ExecutionTime;
            P.RemainingTime -= ExecutionTime;

            timeLine t = new timeLine(P,lst_time,CurrentTime);
            TimeLine.add(t);

            P.TurnaroundTime = CurrentTime - P.ArrivalTime;
            P.WaitingTime = P.TurnaroundTime - P.BurstTime;
            TotalWaitingTime += P.WaitingTime;
            TotalTurnaroundTime += P.TurnaroundTime;
            ExecutionOrder.add(P.ProcessId);
            if (CurrentTime > P.ArrivalTime) {
                CurrentTime += SwitchTime;
            }
        }

        System.out.println("######## Processes Execution Order ########");

        System.out.println("Processes Execution Order: " + ExecutionOrder);
        System.out.println("\n######## Waiting Time and Turnaround Time ########");
        System.out.println("\nPID     Waiting Time    Turnaround Time");
        Processes.sort(Comparator.comparingInt((Process P) -> P.ProcessId));
        for (Process P : Processes) {
            System.out.printf("P%d      %-14d %d\n", P.ProcessId, P.WaitingTime, P.TurnaroundTime);
        }

        double AverageWaitingTime = (double) TotalWaitingTime / Processes.size();
        double AverageTurnaroundTime = (double) TotalTurnaroundTime / Processes.size();

        System.out.println("\n######## Averages ########");
        System.out.printf("Average Waiting Time: %.2f\n", AverageWaitingTime);
        System.out.printf("Average Turnaround Time: %.2f\n", AverageTurnaroundTime);

        return TimeLine;
    }
}
