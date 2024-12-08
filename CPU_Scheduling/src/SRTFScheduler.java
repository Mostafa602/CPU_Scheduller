import java.util.*;

public class SRTFScheduler {

    public List<timeLine> Schedule(List<Process> processes, int MaximumAge, int ContextSwitch) {

        List<timeLine> TimeLine = new ArrayList<timeLine>();

        processes.sort(Comparator.comparingInt(p -> p.ArrivalTime));

        PriorityQueue<Process> readyQueue = new PriorityQueue<>(
                Comparator.comparingInt((Process p) -> p.RemainingTime)
                        .thenComparingInt(p -> p.ArrivalTime));

        List<Integer> executionOrder = new ArrayList<>();
        int lst_time = 0;
        int currentTime = 0;
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;
        int executedCount = 0;
        Deque<Process> important = new LinkedList<>();

        while (executedCount < processes.size()) {
            for (Process p : processes) {
                if (p.ArrivalTime <= currentTime && p.RemainingTime > 0 && !readyQueue.contains(p)) {
                    readyQueue.add(p);
                }
            }

            Iterator<Process> iterator = readyQueue.iterator();
            while (iterator.hasNext()) {
                Process p = iterator.next();
                if (p.age >= MaximumAge) {
                    important.add(p);
                    iterator.remove();
                }
            }

            if (!important.isEmpty()) {
                // Execute aged processes first
                for (Process p : important) {
                    executionOrder.add(p.ProcessId);
                    currentTime += p.RemainingTime;

                    timeLine time = new timeLine(p,lst_time,currentTime);
                    TimeLine.add(time);
                    lst_time = currentTime;

                    p.RemainingTime = 0;

                    p.TurnaroundTime = currentTime - p.ArrivalTime;
                    p.WaitingTime = p.TurnaroundTime - p.BurstTime;

                    totalWaitingTime += p.WaitingTime;
                    totalTurnaroundTime += p.TurnaroundTime;
                    executedCount++;
                    if (currentTime > p.ArrivalTime) {
                        currentTime += ContextSwitch;
                    }
                }
                important.clear();
            } else if (!readyQueue.isEmpty()) {
                Process currentProcess = readyQueue.poll();

                // Check if the context switching is needed
                if (!executionOrder.isEmpty() && executionOrder.get(executionOrder.size() - 1) != currentProcess.ProcessId) {
                    currentTime += ContextSwitch;  // Add context switching time
                    lst_time = currentTime;
                }


                currentProcess.RemainingTime--;
                currentTime++;

                // Add process ID to execution order (only when it starts or switches)
                if (executionOrder.isEmpty() || executionOrder.get(executionOrder.size() - 1) != currentProcess.ProcessId) {
                    executionOrder.add(currentProcess.ProcessId);
                }

                // Increment the age of waiting processes
                for (Process p : readyQueue) {
                    p.age++;
                }

                // Check if the process is completed
                if (currentProcess.RemainingTime == 0) {
                    timeLine time = new timeLine(currentProcess,lst_time,currentTime);
                    TimeLine.add(time);
                    lst_time = currentTime;
                    executedCount++;
                    currentProcess.TurnaroundTime = currentTime - currentProcess.ArrivalTime;
                    currentProcess.WaitingTime = currentProcess.TurnaroundTime - currentProcess.BurstTime;

                    totalWaitingTime += currentProcess.WaitingTime;
                    totalTurnaroundTime += currentProcess.TurnaroundTime;
                } else {
                    timeLine time = new timeLine(currentProcess,lst_time,currentTime);
                    TimeLine.add(time);
                    lst_time = currentTime;
                    // Add the process back to the ready queue if not completed
                    readyQueue.add(currentProcess);
                }
            } else {
                // If no process is ready, increment time
                currentTime++;
            }
        }

        // Output Results
        System.out.println("######## Processes Execution Order ########");
        System.out.println("Processes Execution Order: " + executionOrder);
        System.out.println("\n######## Waiting Time and Turnaround Time ########");
        System.out.println("PID     Waiting Time    Turnaround Time");
        for (Process p : processes) {
            System.out.printf("P%d      %-14d %d\n", p.ProcessId, p.WaitingTime, p.TurnaroundTime);
        }

        double averageWaitingTime = (double) totalWaitingTime / processes.size();
        double averageTurnaroundTime = (double) totalTurnaroundTime / processes.size();

        System.out.println("\n######## Averages ########");
        System.out.printf("Average Waiting Time: %.2f\n", averageWaitingTime);
        System.out.printf("Average Turnaround Time: %.2f\n", averageTurnaroundTime);

        List<timeLine>format = new ArrayList<timeLine>();
        for(timeLine t : TimeLine) {
            if (format.isEmpty()) {
                format.add(t);
            } else {
                if (format.getLast().p.ProcessId == t.p.ProcessId) {
                    format.getLast().end = t.end;
                } else {
                    format.add(t);
                }
            }
        }

        return format;

    }

}
