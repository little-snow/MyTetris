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

	// 从文件中读取数据
	public List<PerRank> readRankList() {
		try (Scanner in = new Scanner(new FileInputStream(this.path), "UTF-8")) {
			rankList = new ArrayList<>();
			while (in.hasNextLine()) {
				String[] flag = in.nextLine().split("\\：");
				this.rankList.add(new PerRank(flag[0], Integer.parseInt(flag[1])));
			}
		} catch (FileNotFoundException e) {
		}
		return this.rankList;
	}

	//  往文件中更新数据,成功则返回 true
	public boolean writeRankList(List<PerRank> newRankList) {
		try (PrintWriter out = new PrintWriter(this.path, "UTF-8")) {
			for (PerRank e : newRankList) {
				out.println(e.getName() + "：" + e.getScore());
			}
			return true;
		} catch (FileNotFoundException e1) {
			return false;
		} catch (UnsupportedEncodingException e1) {
			return false;
		}
	}
}
