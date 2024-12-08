import java.util.*;

public class Main {
    public static void main(String[] Args) {

//        List<Process> ProcessList = new ArrayList<>();
//        ProcessList.add(new Process(1, 0, 10, 3,"0000"));
//        ProcessList.add(new Process(2, 0, 1, 1,"0000"));
//        ProcessList.add(new Process(3, 0, 2, 4,"0000"));
//
//       NonPreemptivePrioritySchedulerUsingContextSwitching PrioritySheduler = new NonPreemptivePrioritySchedulerUsingContextSwitching();
//       List<timeLine>t = PrioritySheduler.Schedule(ProcessList,0);
//
//       for(timeLine tz : t) {
//           System.out.println(tz.p.ProcessId+" "+tz.start+" "+tz.end);
//       }

//
//        List<Process> ProcessList = new ArrayList<>();
//        ProcessList.add(new Process(1, 0, 17, 4,4,"0000"));
//        ProcessList.add(new Process(2, 3, 6, 9,3,"0000"));
//        ProcessList.add(new Process(3, 4, 10, 3,5,"0000"));
//        ProcessList.add(new Process(4, 29, 4, 10,2,"0000"));
//        FcaiFactor fcai = new FcaiFactor();
//        List<timeLine>t = fcai.Schedule(ProcessList);
//
//       for(timeLine tz : t) {
//           System.out.println(tz.p.ProcessId+" "+tz.start+" "+tz.end);
//       }


//        List<Process> ProcessList = new ArrayList<>();
//        ProcessList.add(new Process(1, 0, 6,"0000"));
//        ProcessList.add(new Process(2, 0, 8,"0000"));
//        ProcessList.add(new Process(3, 0, 7,"0000"));
//        ProcessList.add(new Process(4, 0, 3,"0000"));
//
//
//        NonPreemptiveSJF SJF = new NonPreemptiveSJF();
//        List<timeLine>t = SJF.Schedule(ProcessList,1000);
//        for(timeLine tz : t) {
//           System.out.println(tz.p.ProcessId+" "+tz.start+" "+tz.end);
//       }


        List<Process> ProcessList = new ArrayList<>();
        ProcessList.add(new Process(1, 0, 8,"0000"));
        ProcessList.add(new Process(2, 1, 4,"0000"));
        ProcessList.add(new Process(3, 2, 9,"0000"));
        ProcessList.add(new Process(4, 3, 5,"0000"));

        SRTFScheduler SRTF = new SRTFScheduler();
        List<timeLine>t = SRTF.Schedule(ProcessList,1000,2);
        for(timeLine tz : t) {
            System.out.println(tz.p.ProcessId+" "+tz.start+" "+tz.end);
        }


    }
}
