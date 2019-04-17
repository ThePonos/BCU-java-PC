package util.entity.data;

import util.Animable;
import util.anim.AnimU;

public interface MaskEntity {

	public int allAtk();

	public int getAbi();

	public int[] getAllProc(int ind);

	/** get the attack animation length */
	public int getAnimLen();

	public int getAtkCount();

	public int getAtkLoop();

	public MaskAtk getAtkModel(int ind);

	public int getDeathAnim();

	public int getHb();

	public int getHp();

	/** get the attack period */
	public int getItv();

	/** get the Enemy/Form this data represents */
	public Animable<AnimU> getPack();

	public int getPost();

	public int[] getProc(int ind);

	public int getRange();

	public MaskAtk getRepAtk();

	public int getShield();

	public int getSpeed();

	/** get waiting time */
	public int getTBA();

	public int getType();

	public int getWidth();

	public boolean isLD();

	public boolean isOmni();

	public boolean isRange();

	public int[][] rawAtkData();

	public int touchBase();

}