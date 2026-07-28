package org.springblade.basic.utils.qrcode;

import lombok.Getter;

import java.awt.*;

/**
 * @author xiaonannet
 */
@Getter
public class MatrixToLogoImageConfig {
    /**
     * logo默认边框颜色
     */
    public static final Color DEFAULT_BORDERCOLOR = Color.WHITE;
    /**
     * logo默认边框宽度
     */
    public static final int DEFAULT_BORDER = 5;
    /**
     * logo大小默认为照片的1/5
     */
    public static final int DEFAULT_LOGOPART = 5;

    private final int border;
    private final Color borderColor;
    private final int logoPart;

    public MatrixToLogoImageConfig() {
        this(DEFAULT_BORDERCOLOR, DEFAULT_LOGOPART);
    }

    public MatrixToLogoImageConfig(Color borderColor, int logoPart) {
        this(borderColor, logoPart, DEFAULT_BORDER);
    }

    public MatrixToLogoImageConfig(Color borderColor, int logoPart, int border) {
        this.borderColor = borderColor;
        this.logoPart = logoPart;
        this.border = border;
    }


}
