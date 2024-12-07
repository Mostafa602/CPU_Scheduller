import java.util.*;

public class FcaiFactor {

    public void Schedule(List<Process> Processes) {
        System.out.println("######## Waiting Time and Turnaround Time ########");
        System.out.println("\nPID     Waiting Time    Turnaround Time");
        Processes.sort(Comparator.comparingInt(P -> P.ArrivalTime));
        int P_Size = Processes.size();
        double v1 = 0,v2 = 0;
        for (Process p : Processes) {
            v1 = Math.max(v1,p.ArrivalTime);
            v2 = Math.max(v2,p.RemainingTime);
        }
        v1 /= 10;
        v2 /= 10;
        for (Process p : Processes) {
            p.Calc_FcaiFactor(v1,v2);
        }
        int CurrentTime = 0;
        int ExecutedCount = 0;
        int TotalWaitingTime = 0;
        int TotalTurnaroundTime = 0;
        Deque<Process> ready = new LinkedList<>();
        List<Integer>executionOrder = new ArrayList<Integer>();
        boolean is_completed = false;
        while(ExecutedCount < P_Size) {
            Iterator<Process> it = Processes.iterator();
            while(it.hasNext()) {
                Process p = it.next();
                if(p.ArrivalTime <= CurrentTime) {
                    ready.add(p);
                    it.remove();
                }
            }
            if (ready.isEmpty()) {
                CurrentTime++;
                continue;
            }
            Process currentProcess = ready.getFirst();
            if (!is_completed) {
                for(Process p : ready) {
                    if (p.fcaiFactor < currentProcess.fcaiFactor) {
                        currentProcess = p;
                    }
                }
            }
            ready.remove(currentProcess);
            int exect_time = (int)Math.ceil((double)currentProcess.quantum*0.4);
            exect_time = Math.min(exect_time,currentProcess.RemainingTime);
            CurrentTime += exect_time;
            currentProcess.RemainingTime -= exect_time;
            int unused_quantum = currentProcess.quantum - exect_time;
            boolean preempted = false;

            int diff = Math.min(currentProcess.RemainingTime,unused_quantum);
            for (int i = 0 ; i < diff; i++) {
                Iterator<Process> it2 = Processes.iterator();
                while(it2.hasNext()) {
                    Process p = it2.next();
                    if(p.ArrivalTime <= CurrentTime) {
                        ready.add(p);
                        it2.remove();
                    }
                }
                Iterator<Process> it3=ready.iterator();
                while(it3.hasNext()){
                    Process p = it3.next();
                    if(p.fcaiFactor < currentProcess.fcaiFactor) {
                        preempted=true;
//                        System.out.println("Process name: "+currentProcess.ProcessId+" Remaining time: "+currentProcess.RemainingTime+" Quantum: "+currentProcess.quantum+" current FCAIFactor: "+currentProcess.fcaiFactor);
                        currentProcess.Calc_FcaiFactor(v1,v2);
                        if(currentProcess.RemainingTime > 0) {
                            ready.add(currentProcess);
                        }
                        break;
                    }
                }
                if (preempted) break;
                exect_time++;
                CurrentTime++;
                currentProcess.RemainingTime--;
            }
            if (preempted) {
                is_completed = false;
                currentProcess.quantum += (currentProcess.quantum - exect_time);
            } else {
                is_completed = true;
                if (currentProcess.RemainingTime > 0) {
                    currentProcess.quantum += 2;
                    currentProcess.Calc_FcaiFactor(v1,v2);
                    ready.add(currentProcess);
                } else {
                    currentProcess.TurnaroundTime = CurrentTime - currentProcess.ArrivalTime;
                    currentProcess.WaitingTime = currentProcess.TurnaroundTime - currentProcess.BurstTime;
                    TotalWaitingTime += currentProcess.WaitingTime;
                    TotalTurnaroundTime += currentProcess.TurnaroundTime;
                    ExecutedCount++;
                    executionOrder.add(currentProcess.ProcessId);
                    System.out.printf("P%d      %-14d %d\n", currentProcess.ProcessId, currentProcess.WaitingTime, currentProcess.TurnaroundTime);
                }
            }

        }


        double AverageWaitingTime = (double) TotalWaitingTime / P_Size;
        double AverageTurnaroundTime = (double) TotalTurnaroundTime / P_Size;

        System.out.println("\n######## Averages ########");
        System.out.printf("Average Waiting Time: %.2f\n", AverageWaitingTime);
        System.out.printf("Average Turnaround Time: %.2f\n", AverageTurnaroundTime);


        System.out.println("\n######## Processes Execution Order ########");
        System.out.println("Processes Execution Order: " + executionOrder);

    }



}
