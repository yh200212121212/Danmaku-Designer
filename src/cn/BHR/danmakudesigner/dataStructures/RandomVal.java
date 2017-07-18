package cn.BHR.danmakudesigner.dataStructures;

import com.badlogic.gdx.math.MathUtils;

public class RandomVal {
	public float MidValue;
	public float RandomRange;
	
	public float GetRand()
	{
		return MathUtils.random(MidValue - RandomRange, MidValue + RandomRange);
	}
	
	public static RandomVal Parse(String str)
	{
		RandomVal val = new RandomVal();
		String[] spl = str.split(",");
		val.MidValue = Float.parseFloat(spl[0]);
		val.RandomRange = Float.parseFloat(spl[1]);
		return val;
	}
}
