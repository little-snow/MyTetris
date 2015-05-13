package data;

import java.io.Serializable;

//ʵ�� Comparable �ӿ��Ա�� ArrayList<PerRank> ��������
public class PerRank implements Comparable<PerRank>, Serializable {
	private String name;
	private int score;

	public PerRank(String name, int score) {
		this.name = name;
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	//======= ��Ҫ ========
	@Override
	public int compareTo(PerRank player) {
		
		return player.score - this.score;
	}

}