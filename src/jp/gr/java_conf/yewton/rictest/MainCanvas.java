package jp.gr.java_conf.yewton.rictest;

import com.docomostar.StarApplication;
import com.docomostar.ui.Canvas;
import com.docomostar.ui.Display;
import com.docomostar.ui.Font;
import com.docomostar.ui.Graphics;

import jp.gr.java_conf.yewton.docomostar.device.RawImage;
import jp.gr.java_conf.yewton.docomostar.device.RawImageCapture2;
import jp.gr.java_conf.yewton.docomostar.ui.EasyDialog;

/**
 * MainCanvas
 */
class MainCanvas extends Canvas implements Runnable {
    private RawImageCapture2 ric = null;
    private boolean running = false;
    private RawImage ri = null;

    MainCanvas() {
	setSoftLabel(SOFT_KEY_1, "STOP");
	setSoftLabel(SOFT_KEY_2, "REC");
	setSoftLabel(SELECT_KEY, "");
	ric = RawImageCapture2.getRawImageCapture(0);
	setBackground(Graphics.getColorOfName(Graphics.WHITE));
	(new Thread(this)).start();
    }

    public void paint(Graphics g) {
	try {
	    g.lock();
	    g.clearRect(0, 0, Display.getWidth(), Display.getHeight());
	    paintImage(g);
	    paintInfo(g);
	    g.unlock(true);
	} catch (Exception e) {
	    EasyDialog.error(e.getMessage());
	    StarApplication.getThisStarApplication().terminate();
	}
    }

    private void paintInfo(Graphics g) {
	Font font = Font.getFont(Font.FACE_SYSTEM | Font.STYLE_PLAIN, 24);
	String msg = "RawImageCaptureTest";
	int boxWidth = 300;
	int boxHeight = 100;
	int dw = Display.getWidth();
	int dh = Display.getHeight();

	g.setColor(Graphics.getColorOfRGB(0xFF, 0xFF, 0xFF, 0xA0));
	g.fillRect((dw - boxWidth) / 2, (dh - boxHeight) / 2, boxWidth,
		boxHeight);
	g.setColor(Graphics.getColorOfName(Graphics.BLACK));
	g.setFont(font);
	g.drawString(msg, (dw - font.getBBoxWidth(msg)) / 2,
		(dh - font.getBBoxHeight(msg)) / 2);
    }

    private void paintImage(Graphics g) {
	ri = ric.getRawImage();
	if (ri == null)
	    return;
	int w = ri.width;
	int h = ri.height;
	g.setRGBPixels(0, 0, w, h, ri.getRGBPixels(), 0);
    }

    public void processEvent(int type, int param) {
	if (type == Display.KEY_RELEASED_EVENT) {
	    if (param == Display.KEY_SOFT1) {
		ric.stop();
		running = false;
	    } else if (param == Display.KEY_SOFT2) {
		ric.start();
		running = true;
	    }
	}
    }

    public void run() {
	while (true) {
	    try {
		Thread.sleep(100);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	    if (running) {
		repaint();
	    }
	}
    }
}