class Process {
    int ProcessId;
    int ArrivalTime;
    int BurstTime;
    int Priority;
    int quantum;
    int RemainingTime;
    int WaitingTime = 0;
    int TurnaroundTime = 0;
    int age = 0;
    double fcaiFactor = 0;
    String color;

    // fcai factor
    public Process(int processId, int arrivalTime, int burstTime, int priority, int quantum,String color) {
        this.ProcessId = processId;
        this.ArrivalTime = arrivalTime;
        this.BurstTime = burstTime;
        this.Priority = priority;
        this.quantum = quantum;
        this.RemainingTime = burstTime;
        this.color = color;
    }
    public void Calc_FcaiFactor(double v1,double v2) {
        this.fcaiFactor =  (10-this.Priority) + Math.ceil(this.ArrivalTime/v1) + Math.ceil(this.RemainingTime/v2);
        return;
    }

    //PriorityScheduling
    public Process(int processId, int arrivalTime, int burstTime, int priority,String color) {
        this.ProcessId = processId;
        this.ArrivalTime = arrivalTime;
        this.BurstTime = burstTime;
        this.Priority = priority;
        this.RemainingTime = burstTime;
        this.color = color;
    }


    public Process(int processId, int arrivalTime, int burstTime,String Color) {
        this.ProcessId = processId;
        this.ArrivalTime = arrivalTime;
        this.BurstTime = burstTime;
        this.RemainingTime = burstTime;
    }

    public String toString() {
        return Integer.toString(this.ProcessId);
    }

}
