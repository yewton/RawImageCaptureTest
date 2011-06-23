package jp.gr.java_conf.yewton.docomostar.device;

import com.docomostar.device.RawImageCapture;

public class RawImageCapture2 {
    RawImageCapture ric = null;
    RawImage ri = null;
    private static RawImageCapture2 instance = null;

    protected RawImageCapture2(int i) {
	ric = RawImageCapture.getRawImageCapture(i);
	ric.setColorSpace(RawImageCapture.COLORSPACE_YUV422_YUY2);
    }

    public static RawImageCapture2 getRawImageCapture(int i) {
	if (instance == null) {
	    instance = new RawImageCapture2(i);
	}
	return instance;
    }

    public String[] getAvailableColorSpaces() {
	return ric.getAvailableColorSpaces();
    }

    public int[] getAvailableFocusModes() {
	return ric.getAvailableFocusModes();
    }

    public int[][] getAvailableImageSizes() {
	return ric.getAvailableImageSizes();
    }

    public String getColorSpace() {
	return ric.getColorSpace();
    }

    public int getFocusMode() {
	return ric.getFocusMode();
    }

    public int[] getImageSize() {
	return ric.getImageSize();
    }

    public RawImage getRawImage() {
	try {
	    ri = null;
	    ri = new RawImage(ric.getRawImage(null), ric.getImageSize()[0],
		    ric.getImageSize()[1]);
	} catch (Exception e) {
	}
	return ri;
    }

    public byte[] getRawImageByte(byte[] buffer) {
	return ric.getRawImage(buffer);
    }

    public int getRawImageLength() {
	return ric.getRawImageLength();
    }

    public void setColorSpace(String colorSpace) {
	ric.setColorSpace(colorSpace);
    }

    public void setFocusMode(int mode) {
	ric.setFocusMode(mode);
    }

    public void setImageSize(int width, int height) {
	ric.setImageSize(width, height);
    }

    public void start() {
	ric.start();
    }

    public void stop() {
	ric.stop();
    }
}
