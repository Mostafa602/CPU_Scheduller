import java.util.*;

public class NonPreemptivePrioritySchedulerUsingContextSwitching {


    public void Schedule(List<Process> Processes, int SwitchTime) {
        Processes.sort(Comparator.comparingInt((Process P) -> P.ArrivalTime)
                .thenComparingInt(P -> P.Priority));
        List<Integer> ExecutionOrder = new ArrayList<>();
        int CurrentTime = 0;
        int TotalWaitingTime = 0;
        int TotalTurnaroundTime = 0;



        for (Process P : Processes) {
            // wait for it to arrive
            if (CurrentTime < P.ArrivalTime) {
                CurrentTime = P.ArrivalTime;
            }
            //if not first add switch time
            if (CurrentTime > P.ArrivalTime) {
                CurrentTime += SwitchTime;
            }


            int ExecutionTime = P.RemainingTime;
            CurrentTime += ExecutionTime;
            P.RemainingTime -= ExecutionTime;



            P.TurnaroundTime = CurrentTime - P.ArrivalTime;
            P.WaitingTime = P.TurnaroundTime - P.BurstTime;
            TotalWaitingTime += P.WaitingTime;
            TotalTurnaroundTime += P.TurnaroundTime;
            ExecutionOrder.add(P.ProcessId);
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
    }
}
