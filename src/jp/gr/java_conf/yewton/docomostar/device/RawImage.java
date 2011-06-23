package jp.gr.java_conf.yewton.docomostar.device;

import com.docomostar.ui.Graphics;
import com.docomostar.ui.Image;

public class RawImage {
    byte[] rawImage = null;
    public int width = 0;
    public int height = 0;

    public RawImage(byte[] rawImage, int width, int height) {
	this.rawImage = rawImage;
	this.width = width;
	this.height = height;
    }

    public Image getImage() {
	return null;
    }

    public int getPixel(int x, int y) {
	int i = (y * width + x & ~0x1) << 1;
	int c = ((int) rawImage[i] & 0xFF) - 16;
	int d = ((int) rawImage[i + 1] & 0xFF) - 128;
	int e = ((int) rawImage[i + 3] & 0xFF) - 128;
	int r = clip((298 * c + 409 * e + 128) >> 8);
	int g = clip((298 * c - 100 * d - 208 * e + 128) >> 8);
	int b = clip((298 * c + 516 * d + 128) >> 8);
	return Graphics.getColorOfRGB(r, g, b);
    }

    public int getRGBPixel(int x, int y) {
	int i = (y * width + x & ~0x1) << 1;
	int c = ((int) rawImage[i] & 0xFF) - 16;
	int d = ((int) rawImage[i + 1] & 0xFF) - 128;
	int e = ((int) rawImage[i + 3] & 0xFF) - 128;
	int r = clip((298 * c + 409 * e + 128) >> 8);
	int g = clip((298 * c - 100 * d - 208 * e + 128) >> 8);
	int b = clip((298 * c + 516 * d + 128) >> 8);
	return (r * 0x10000) + (g * 0x100) + b;
    }

    public int[] getRGBPixels() {
	int[] pixels = new int[width * height];
	for (int y = 0; y < height; y++) {
	    for (int x = 0; x < width; x++) {
		int i = (y * width + x & ~0x1) << 1;
		int c = ((int) rawImage[i] & 0xFF) - 16;
		int d = ((int) rawImage[i + 1] & 0xFF) - 128;
		int e = ((int) rawImage[i + 3] & 0xFF) - 128;
		int r = clip((298 * c + 409 * e + 128) >> 8);
		int g = clip((298 * c - 100 * d - 208 * e + 128) >> 8);
		int b = clip((298 * c + 516 * d + 128) >> 8);
		int a = (r * 0x10000) + (g * 0x100) + b;
		int idx = (y * width) + x;
		pixels[idx] = a;
	    }
	}
	return pixels;
    }

    int clip(int v) {
	if (0xFF < v) {
	    return 0xFF;
	} else if (v < 0) {
	    return 0;
	} else {
	    return v;
	}
    }
}
