import java.util.*;

public class Main {
    public static void main(String[] Args) {

//        List<Process> ProcessList = new ArrayList<>();
//        ProcessList.add(new Process(1, 0, 10, 3));
//        ProcessList.add(new Process(2, 0, 1, 1));
//        ProcessList.add(new Process(3, 0, 2, 4));
//        ProcessList.add(new Process(4, 0, 1, 5));
//        ProcessList.add(new Process(5, 0, 5, 2));
//
//       NonPreemptivePrioritySchedulerUsingContextSwitching PrioritySheduler = new NonPreemptivePrioritySchedulerUsingContextSwitching();
//
//       PrioritySheduler.Schedule(ProcessList,1);



//        List<Process> ProcessList = new ArrayList<>();
//        ProcessList.add(new Process(1, 17, 0, 4,4));
//        ProcessList.add(new Process(2, 6, 3, 9,3));
//        ProcessList.add(new Process(3, 10, 4, 3,5));
//        ProcessList.add(new Process(4, 4, 29, 10,2));
//        FcaiFactor fcai = new FcaiFactor();
//        fcai.Schedule(ProcessList);


//        List<Process> ProcessList = new ArrayList<>();
//        ProcessList.add(new Process(1, 0, 2));
//        ProcessList.add(new Process(2, 0, 10));
//        ProcessList.add(new Process(3, 1, 2));
//        ProcessList.add(new Process(4, 2, 2));
//        ProcessList.add(new Process(5, 3, 2));
//
//
//        NonPreemptiveSJF SJF = new NonPreemptiveSJF();
//        SJF.Schedule(ProcessList,2);


        //Starvation Case


//        List<Process> ProcessList = new ArrayList<>();
//        ProcessList.add(new Process(1, 0, 17));
//        ProcessList.add(new Process(2, 3, 6));
//        ProcessList.add(new Process(3, 4, 10));
//        ProcessList.add(new Process(4, 29, 4));
//
//        NonPreemptiveSJF SJF = new NonPreemptiveSJF();
//        SJF.Schedule(ProcessList,5);


        List<Process> ProcessList = new ArrayList<>();
        ProcessList.add(new Process(1, 0, 8));
        ProcessList.add(new Process(2, 1, 4));
        ProcessList.add(new Process(3, 2, 9));
        ProcessList.add(new Process(4, 3, 5));

        SRTFScheduler SRTF = new SRTFScheduler();
        SRTF.Schedule(ProcessList,5);



    }
}
