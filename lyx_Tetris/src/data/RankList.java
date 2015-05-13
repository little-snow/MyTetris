package data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RankList {
	private final String path = "save/rankList.dat";
	private List<PerRank> rankList = null;

	// ���ļ��ж�ȡ����
	public List<PerRank> readRankList() {
		try (Scanner in = new Scanner(new FileInputStream(this.path), "UTF-8")) {
			rankList = new ArrayList<>();
			while (in.hasNextLine()) {
				String[] flag = in.nextLine().split("\\��");
				this.rankList.add(new PerRank(flag[0], Integer.parseInt(flag[1])));
			}
		} catch (FileNotFoundException e) {
		}
		return this.rankList;
	}

	//  ���ļ��и�������,�ɹ��򷵻� true
	public boolean writeRankList(List<PerRank> newRankList) {
		try (PrintWriter out = new PrintWriter(this.path, "UTF-8")) {
			for (PerRank e : newRankList) {
				out.println(e.getName() + "��" + e.getScore());
			}
			return true;
		} catch (FileNotFoundException e1) {
			return false;
		} catch (UnsupportedEncodingException e1) {
			return false;
		}
	}
}
