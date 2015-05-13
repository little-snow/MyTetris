package data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Data {

	// ÅÅÐÐ°ñÊý¾Ý
	private List<PerRank> rankList;
	// ÏÂ·½Î»ÖÃÊÇ·ñÓÐÂäÏÂµÄ·½¿é
	private static boolean[][] ground;
	// ÕýÔÚÏÂÂäµÄ·½¿é
	private static Action action;
	// ÏÂÒ»¸öÒªÏÂÂäµÄÍ¼°¸
	private int next;
	// µÈ¼¶
	private static int grade;
	// Éý¼¶ËùÐè·ÖÊý
	private int perScore;
	// µ±Ç°µÈ¼¶µÃ·Ö
	private static int nowScore;
	// ×Ü·ÖÊý
	private static int totalScore;
	//ÓÎÏ·ÊÇ·ñ¿ªÊ¼
	public  static boolean start;
	//ÓÎÏ·ÊÇ·ñÊ§°Ü
	public static boolean fail;
	
	//ÉèÖÃÏÂÂäËÙ¶È
	private static int delay;
	
	public Data() {
		// ³õÊ¼»¯ÏÂÒ»¸öÍ¼ÐÎ±àºÅ
		this.next = new Random().nextInt(7);
		// ³õÊ¼»¯ grade¡¢baseScore¡¢perScore
		grade = 1;
		perScore = 300;
		delay = 800;
	}

	public List<PerRank> getRankList() {
		return rankList;
	}

	public void setRankList(List<PerRank> rankList) {
		// ¶ÔÊý¾Ý½øÐÐÅÅÐò´¦Àí
		if (rankList == null) {
			rankList = new ArrayList<PerRank>();
			for (int i = 0; i < 5; i++)
				rankList.add(new PerRank(null, 0));
		}
		Collections.sort(rankList);
		this.rankList = rankList;
	}

	public boolean[][] getGround() {
		return ground;
	}

	public void setGround(boolean[][] ground) {
		Data.ground = ground;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		Data.action = action;
	}

	public int getNext() {
		return next;
	}

	public void setNext(int next) {
		this.next = next;
	}

	public int getGrade() {
		return grade;
	}

	// µ÷ÕûµÈ¼¶£¬µ±Ç°µÈ¼¶·ÖÊý¹éÁã
	public void setGrade(int grade) {
		Data.grade += grade;
		Data.nowScore = 0;
		Data.delay -=50;
	}

	public static int getDelay() {
		return delay;
	}

	public int getTotalScore() {
		return Data.totalScore;
	}

	// µ÷ÕûµÃ·Ö
	public void setTotalScore(int newScore) {
		Data.totalScore += newScore;
		Data.nowScore += newScore;
	}
	public int getNowScore() {
		return nowScore;
	}


	// »ñÈ¡Ã¿´ÎÉý¼¶ËùÐè·ÖÊý
	public int getPerScore() {
		return perScore;
	}
	public void setPerScore(int perScore) {
		this.perScore = perScore;
	}

	
	public void setStart(boolean start) {
		Data.start = start;
	}
	public static void initData(){
		for(int i = 0; i < ground.length;i++){
			for(int j = 0; j < ground[i].length;j++){
				ground[i][j] = false;
			}
		}
		Data.grade = 1;
		Data.nowScore = Data.totalScore = 0;
		Data.start = Data.fail = false;
		Data.action = null;
		Data.delay = 800;
	}
}
