import java.util.*;

public class Main {
    public static void main(String[] Args) {
        /*
        List<Process> ProcessList = new ArrayList<>();
        ProcessList.add(new Process(1, 0, 10, 3));
        ProcessList.add(new Process(2, 0, 1, 1));
        ProcessList.add(new Process(3, 0, 2, 4));
        ProcessList.add(new Process(4, 0, 1, 5));
        ProcessList.add(new Process(5, 0, 5, 2));

       NonPreemptivePrioritySchedulerUsingContextSwitching PrioritySheduler = new NonPreemptivePrioritySchedulerUsingContextSwitching();

       PrioritySheduler.Schedule(ProcessList);

         */

        /*
        List<Process> ProcessList = new ArrayList<>();
        ProcessList.add(new Process(1, 0, 6));
        ProcessList.add(new Process(2, 0, 8));
        ProcessList.add(new Process(3, 0, 7));
        ProcessList.add(new Process(4, 0, 3));

        NonPreemptiveSJF SJF = new NonPreemptiveSJF();
        SJF.Schedule(ProcessList,5);
        */

        //Starvation Case

        /*
        List<Process> ProcessList = new ArrayList<>();
        ProcessList.add(new Process(1, 0, 2));
        ProcessList.add(new Process(2, 0, 2));
        ProcessList.add(new Process(3, 0, 2));
        ProcessList.add(new Process(4, 0, 2));
        ProcessList.add(new Process(5, 0, 2));
        ProcessList.add(new Process(6, 0, 2));
        ProcessList.add(new Process(7, 0, 2));
        ProcessList.add(new Process(8, 0, 2));
        ProcessList.add(new Process(9, 0, 2));
        ProcessList.add(new Process(10, 0, 2));
        ProcessList.add(new Process(11, 0, 2));
        ProcessList.add(new Process(12, 0, 2));

        ProcessList.add(new Process(13, 0, 3));

        NonPreemptiveSJF SJF = new NonPreemptiveSJF();
        SJF.Schedule(ProcessList,2);
        */
    /*
        List<Process> ProcessList = new ArrayList<>();
        ProcessList.add(new Process(1, 0, 8));
        ProcessList.add(new Process(2, 1, 4));
        ProcessList.add(new Process(3, 2, 9));
        ProcessList.add(new Process(4, 3, 5));

        SRTFScheduler SRTF = new SRTFScheduler();
        SRTF.Schedule(ProcessList);

     */

    }
}
