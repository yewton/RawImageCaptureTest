package jp.gr.java_conf.yewton.rictest;

/*
 * Main.java
 *
 * DATE : 2011/03/14 08:18
 */
import jp.gr.java_conf.yewton.docomostar.device.RawImage;
import jp.gr.java_conf.yewton.docomostar.device.RawImageCapture2;
import jp.gr.java_conf.yewton.docomostar.ui.EasyDialog;

import com.docomostar.StarApplication;
import com.docomostar.StarApplicationManager;
import com.docomostar.device.Camera;
import com.docomostar.device.DeviceException;
import com.docomostar.device.RawImageCapture;

import com.docomostar.media.MediaImage;
import com.docomostar.ui.Canvas;
import com.docomostar.ui.Graphics;
import com.docomostar.ui.Frame;
import com.docomostar.ui.Display;
import com.docomostar.ui.Font;
import com.docomostar.ui.Image;

/**
 * Main
 *
 * @author NAME
 */
public class Main extends StarApplication {
    MainCanvas mc = null;
    public void started(int launchType) {
	mc = new MainCanvas();
	/*
	 * The program of StarApplication is written here.
	 */
	Display.setCurrent(mc);
    }

}