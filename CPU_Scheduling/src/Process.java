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

    // fcai factor
    public Process(int processId, int burstTime, int arrivalTime, int priority, int quantum) {
        this.ProcessId = processId;
        this.ArrivalTime = arrivalTime;
        this.BurstTime = burstTime;
        this.Priority = priority;
        this.quantum = quantum;
        this.RemainingTime = burstTime;
    }
    public void Calc_FcaiFactor(double v1,double v2) {
        this.fcaiFactor =  (10-this.Priority) + Math.ceil(this.ArrivalTime/v1) + Math.ceil(this.RemainingTime/v2);
        return;
    }

    //PriorityScheduling
    public Process(int processId, int arrivalTime, int burstTime, int priority) {
        this.ProcessId = processId;
        this.ArrivalTime = arrivalTime;
        this.BurstTime = burstTime;
        this.Priority = priority;
        this.RemainingTime = burstTime;
    }


    public Process(int processId, int arrivalTime, int burstTime) {
        this.ProcessId = processId;
        this.ArrivalTime = arrivalTime;
        this.BurstTime = burstTime;
        this.RemainingTime = burstTime;
    }

}
