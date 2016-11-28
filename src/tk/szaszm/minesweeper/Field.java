package tk.szaszm.minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.ref.WeakReference;

/**
 * Created by marci on 2016.11.28..
 */
public class Field extends JComponent {
    private WeakReference<Field> topLeft;
    private WeakReference<Field> topCenter;
    private WeakReference<Field> topRight;
    private WeakReference<Field> middleLeft;
    private WeakReference<Field> middleRight;
    private WeakReference<Field> bottomLeft;
    private WeakReference<Field> bottomCenter;
    private WeakReference<Field> bottomRight;

    private int fieldX, fieldY;
    private boolean bomb;
    private FieldState fieldState;
    private BufferedImage image;
    private FieldGraphicsProvider fieldGraphicsProvider;

    public Field(int x, int y, FieldGraphicsProvider fieldGraphicsProvider) {
        fieldX = x;
        fieldY = y;
        bomb = false;
        fieldState = FieldState.UNREVEALED;
        this.fieldGraphicsProvider = fieldGraphicsProvider;
    }

    public Field getTopLeft() {
        return topLeft.get();
    }

    public Field getTopCenter() {
        return topCenter.get();
    }

    public Field getTopRight() {
        return topRight.get();
    }

    public Field getMiddleLeft() {
        return middleLeft.get();
    }

    public Field getMiddleRight() {
        return middleRight.get();
    }

    public Field getBottomLeft() {
        return bottomLeft.get();
    }

    public Field getBottomCenter() {
        return bottomCenter.get();
    }

    public Field getBottomRight() {
        return bottomRight.get();
    }

    public int getFieldX() {
        return fieldX;
    }

    public int getFieldY() {
        return fieldY;
    }

    public void setTopLeft(Field topLeft) {
        this.topLeft = new WeakReference<>(topLeft);
        updateIcon();
    }

    public void setTopCenter(Field topCenter) {
        this.topCenter = new WeakReference<>(topCenter);
        updateIcon();
    }

    public void setTopRight(Field topRight) {
        this.topRight = new WeakReference<>(topRight);
        updateIcon();
    }

    public void setMiddleLeft(Field middleLeft) {
        this.middleLeft = new WeakReference<>(middleLeft);
        updateIcon();
    }

    public void setMiddleRight(Field middleRight) {
        this.middleRight = new WeakReference<>(middleRight);
        updateIcon();
    }

    public void setBottomLeft(Field bottomLeft) {
        this.bottomLeft = new WeakReference<>(bottomLeft);
        updateIcon();
    }

    public void setBottomCenter(Field bottomCenter) {
        this.bottomCenter = new WeakReference<>(bottomCenter);
        updateIcon();
    }

    public void setBottomRight(Field bottomRight) {
        this.bottomRight = new WeakReference<>(bottomRight);
        updateIcon();
    }

    public boolean isBomb() {
        return bomb;
    }

    public void setBomb(boolean bomb) {
        this.bomb = bomb;
        updateIcon();
    }

    public FieldState getFieldState() {
        return fieldState;
    }

    public void setFieldState(FieldState fieldState) {
        this.fieldState = fieldState;
        updateIcon();
    }

    public int getNumberOfSurroundingBombs() {
        int cnt = 0;

        if(topLeft != null && topLeft.get() != null && topLeft.get().isBomb()) ++cnt;
        if(topCenter != null && topCenter.get() != null && topCenter.get().isBomb()) ++cnt;
        if(topRight != null && topRight.get() != null && topRight.get().isBomb()) ++cnt;
        if(middleLeft != null && middleLeft.get() != null && middleLeft.get().isBomb()) ++cnt;
        if(middleRight != null && middleRight.get() != null && middleRight.get().isBomb()) ++cnt;
        if(bottomLeft != null && bottomLeft.get() != null && bottomLeft.get().isBomb()) ++cnt;
        if(bottomCenter != null && bottomCenter.get() != null && bottomCenter.get().isBomb()) ++cnt;
        if(bottomRight != null && bottomRight.get() != null && bottomRight.get().isBomb()) ++cnt;

        return cnt;
    }

    private String getType() {
        if(getFieldState() == FieldState.UNREVEALED) return "unrevealed";
        if(getFieldState() == FieldState.MARKED) return "flag";
        if(isBomb()) return "bomb";
        return Integer.toString(getNumberOfSurroundingBombs());
    }

    private void updateIcon() {
        image = fieldGraphicsProvider.get(getType());
        repaint();
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        if (image != null) {
            graphics.drawImage(image, 0, 0, 30, 30, 0, 0, 114, 114, null);
        }
    }
}
