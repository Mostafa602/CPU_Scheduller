class Process {
    int ProcessId;
    int ArrivalTime;
    int BurstTime;
    int Priority;
    int RemainingTime;
    int WaitingTime = 0;
    int TurnaroundTime = 0;






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
