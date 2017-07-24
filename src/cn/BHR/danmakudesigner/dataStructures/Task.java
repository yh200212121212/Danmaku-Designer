package cn.BHR.danmakudesigner.dataStructures;

public final class Task {
	public int Type = 0; //Corresponds to the spinner's selected item id
	public String Target = ""; //Target Value
	public int Duration = 0;
	public int Timeout = 0;
	public int Interval = 0;
	
	@Override
	public Task clone() {
		Task task = new Task();
		task.Duration = this.Duration;
		task.Interval = this.Interval;
		task.Target = this.Target;
		task.Timeout = this.Timeout;
		task.Type = this.Type;
		return task;
	}
	
	public boolean isNew()
	{
		return Type == 0 && Target.equals("") && Duration == 0 && Timeout == 0 && Interval == 0;
	}
}
