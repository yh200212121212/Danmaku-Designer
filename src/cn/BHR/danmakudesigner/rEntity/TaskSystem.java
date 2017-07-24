package cn.BHR.danmakudesigner.rEntity;

import java.util.List;

import com.badlogic.gdx.math.Interpolation;

import cn.BHR.danmakudesigner.dataStructures.RandomVal;
import cn.BHR.danmakudesigner.dataStructures.Task;

public class TaskSystem {
	public static final int ACTIVE = 0, SELFROTATION = 1, BULLETSPEED = 2, CYCLE = 3, PROJCOUNT = 4, RANGE = 5, MIDDIR = 6, PROJDIR = 7;
	
	public CircularGroup Owner;
	public Task[] Tasks;
	
	protected boolean[] taskActivated;
	protected float[][] ai;
	
	public TaskSystem(CircularGroup owner, List<Task> tasks)
	{
		Owner = owner;
		Tasks = tasks.toArray(new Task[tasks.size()]);
		taskActivated = new boolean[Tasks.length];
		ai = new float[Tasks.length][4];
		for (int i=0; i<taskActivated.length; i++)
			taskActivated[i] = false;
	}
	
	public void Update()
	{
		for (int i=0; i<Tasks.length; i++) {
			Task task = Tasks[i];
			if ((task.Interval == -1 && task.Timeout == Owner.ExistTime) ||
					(Owner.ExistTime > task.Timeout && 
						((task.Interval <= 1 && task.Interval != -1) || (Owner.ExistTime - task.Timeout) % task.Interval == 1)))
			{
				taskActivated[i] = true;
				ai[i][0] = 0;
				if (task.Duration < 1) task.Duration = 1;
				switch (task.Type) {
				case ACTIVE:
					Owner.Active = Integer.parseInt(Tasks[i].Target);
					taskActivated[i] = false;
					break;
				case SELFROTATION:
					ai[i][1] = Owner.MainComponent.RotateSpeed;
					ai[i][2] = Float.parseFloat(task.Target);
					break;
				case BULLETSPEED:
					ai[i][1] = Owner.MainComponent.Velocity;
					ai[i][2] = RandomVal.Parse(task.Target).GetRand();
					break;
				case CYCLE:
					ai[i][1] = Owner.MainComponent.Cycle;
					ai[i][2] = RandomVal.Parse(task.Target).GetRand();
					break;
				case PROJCOUNT:
					ai[i][1] = Owner.MainComponent.CountProjs;
					ai[i][2] = RandomVal.Parse(task.Target).GetRand();
					break;
				case RANGE:
					ai[i][1] = Owner.MainComponent.DirRange;
					ai[i][2] = RandomVal.Parse(task.Target).GetRand();
					break;
				case MIDDIR:
					ai[i][1] = Owner.MainComponent.MidDir;
					ai[i][2] = RandomVal.Parse(task.Target).GetRand();
					break;
				case PROJDIR:
					ai[i][1] = task.Target.startsWith("%") ? 1f : -1f;
					ai[i][2] = RandomVal.Parse(task.Target.replace("%", "")).GetRand();
					ai[i][3]++;
					if (ai[i][1] < 0)
						task.Interval = Owner.MainComponent.Cycle;
					break;
				}
			}
		}
		for (int i=0; i<taskActivated.length; i++)
		{
			if (!taskActivated[i])
				return;
			switch (Tasks[i].Type) {
			case SELFROTATION:
				Owner.MainComponent.RotateSpeed = 
					Interpolation.linear.apply(ai[i][1], ai[i][2], ai[i][0]/Tasks[i].Duration);
				ai[i][0]++;
				if (ai[i][0] >= Tasks[i].Duration)
				{
					Owner.MainComponent.RotateSpeed = ai[i][2];
					taskActivated[i] = false;
				}
				break;
			case BULLETSPEED:
				Owner.MainComponent.Velocity = 
						Interpolation.linear.apply(ai[i][1], ai[i][2], ai[i][0]/Tasks[i].Duration);
				ai[i][0]++;
				if (ai[i][0] >= Tasks[i].Duration)
				{
					Owner.MainComponent.Velocity = ai[i][2];
					taskActivated[i] = false;
				}
				break;
			case CYCLE:
				Owner.MainComponent.Cycle = (int)Interpolation.linear.apply(ai[i][1], ai[i][2], ai[i][0]/Tasks[i].Duration);
				ai[i][0]++;
				if (ai[i][0] >= Tasks[i].Duration)
				{
					Owner.MainComponent.Cycle = (int)(ai[i][2] + 0.5f);
					taskActivated[i] = false;
				}
				break;
			case PROJCOUNT:
				Owner.MainComponent.CountProjs = (int)Interpolation.linear.apply(ai[i][1], ai[i][2], ai[i][0]/Tasks[i].Duration);
				ai[i][0]++;
				if (ai[i][0] >= Tasks[i].Duration)
				{
					Owner.MainComponent.CountProjs = (int)(ai[i][2] + 0.5f);
					taskActivated[i] = false;
				}
				break;
			case RANGE:
				Owner.MainComponent.DirRange = Interpolation.linear.apply(ai[i][1], ai[i][2], ai[i][0]/Tasks[i].Duration);
				ai[i][0]++;
				if (ai[i][0] >= Tasks[i].Duration)
				{
					Owner.MainComponent.DirRange = ai[i][2];
					taskActivated[i] = false;
				}
				break;
			case MIDDIR:
				Owner.MainComponent.MidDir = Interpolation.linear.apply(ai[i][1], ai[i][2], ai[i][0]/Tasks[i].Duration);
				ai[i][0]++;
				if (ai[i][0] >= Tasks[i].Duration)
				{
					Owner.MainComponent.MidDir = ai[i][2];
					taskActivated[i] = false;
				}
				break;
			case PROJDIR:
				if (ai[i][1] > 0)
				{
					for (Projectile proj : Owner.Projs) {
						proj.Direction += ai[i][2] / Tasks[i].Duration;
					}
				}
				else
				{
					for (Projectile proj : Owner.Projs) {
						if (proj.BatchID == (int)(ai[i][3] + 0.5f))
							proj.Direction += ai[i][2] / Tasks[i].Duration;
					}
				}
				ai[i][0]++;
				if (ai[i][0] >= Tasks[i].Duration)
					taskActivated[i] = false;
				break;
			default:
				break;
			}
		}
	}
}
