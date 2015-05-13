package body;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class BodyNext extends Body {
	private Image[] imgs = new Image[7];

	public BodyNext() {
		// �߿����Ͻ�
		super(369 + 9, 105 + 25);
		//���ط���ͼ����
		for (int i = 0; i < imgs.length; i++) {
			imgs[i] = new ImageIcon("images/icons/next" + i + ".png").getImage();
		}
	}

	@Override
	public void paint(Graphics g) {
		if(Body.data.getAction() != null)
		 g.drawImage(imgs[Body.data.getNext()], CORNER_X, CORNER_Y, null);
	}
}