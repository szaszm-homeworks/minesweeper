package tk.szaszm.minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.lang.ref.WeakReference;

/**
 * Created by marci on 2016.11.28..
 */
public class Field extends JComponent {
    final static int SIZE = 30;

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
    private Board board;

    public Field(int x, int y, FieldGraphicsProvider fieldGraphicsProvider, Board board) {
        fieldX = x;
        fieldY = y;
        bomb = false;
        fieldState = FieldState.UNREVEALED;
        this.fieldGraphicsProvider = fieldGraphicsProvider;
        this.board = board;
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if(mouseEvent.getButton() == MouseEvent.BUTTON1) explore();
                else if(mouseEvent.getButton() == MouseEvent.BUTTON3) toggleMark();
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) { }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) { }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) { }

            @Override
            public void mouseExited(MouseEvent mouseEvent) { }
        });
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

    private int getNumberOfSurroundingMarkedFields() {
        int cnt = 0;

        if(topLeft != null && topLeft.get() != null && topLeft.get().getFieldState() == FieldState.MARKED) ++cnt;
        if(topCenter != null && topCenter.get() != null && topCenter.get().getFieldState() == FieldState.MARKED) ++cnt;
        if(topRight != null && topRight.get() != null && topRight.get().getFieldState() == FieldState.MARKED) ++cnt;
        if(middleLeft != null && middleLeft.get() != null && middleLeft.get().getFieldState() == FieldState.MARKED) ++cnt;
        if(middleRight != null && middleRight.get() != null && middleRight.get().getFieldState() == FieldState.MARKED) ++cnt;
        if(bottomLeft != null && bottomLeft.get() != null && bottomLeft.get().getFieldState() == FieldState.MARKED) ++cnt;
        if(bottomCenter != null && bottomCenter.get() != null && bottomCenter.get().getFieldState() == FieldState.MARKED) ++cnt;
        if(bottomRight != null && bottomRight.get() != null && bottomRight.get().getFieldState() == FieldState.MARKED) ++cnt;

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
            graphics.drawImage(image, 0, 0, SIZE, SIZE, 0, 0, image.getWidth(), image.getHeight(), null);
        }
    }

    @Override
    public Dimension getMinimumSize() {
        return new Dimension(SIZE, SIZE);
    }

    private void explore() {
        if(board.isEnded()) return;

        doExplore();

        board.check();
    }

    private void doExplore() {
        if(fieldState != FieldState.UNREVEALED) return;
        fieldState = FieldState.REVEALED;

        String type = getType();
        if(type.equals("0")) {
            if(topLeft != null && topLeft.get() != null) topLeft.get().doExplore();
            if(topCenter != null && topCenter.get() != null) topCenter.get().doExplore();
            if(topRight != null && topRight.get() != null) topRight.get().doExplore();
            if(middleLeft != null && middleLeft.get() != null) middleLeft.get().doExplore();
            if(middleRight != null && middleRight.get() != null) middleRight.get().doExplore();
            if(bottomLeft != null && bottomLeft.get() != null) bottomLeft.get().doExplore();
            if(bottomCenter != null && bottomCenter.get() != null) bottomCenter.get().doExplore();
            if(bottomRight != null && bottomRight.get() != null) bottomRight.get().doExplore();
        }


        updateIcon();
    }

    private void toggleMark() {
        if(fieldState == FieldState.UNREVEALED) fieldState = FieldState.MARKED;
        else if(fieldState == FieldState.MARKED) fieldState = FieldState.UNREVEALED;
        else if(!isBomb() && getNumberOfSurroundingBombs() == getNumberOfSurroundingMarkedFields()) {
            if(topLeft != null && topLeft.get() != null && topLeft.get().getFieldState() != FieldState.MARKED) topLeft.get().doExplore();
            if(topCenter != null && topCenter.get() != null && topCenter.get().getFieldState() != FieldState.MARKED) topCenter.get().doExplore();
            if(topRight != null && topRight.get() != null && topRight.get().getFieldState() != FieldState.MARKED) topRight.get().doExplore();
            if(middleLeft != null && middleLeft.get() != null && middleLeft.get().getFieldState() != FieldState.MARKED) middleLeft.get().doExplore();
            if(middleRight != null && middleRight.get() != null && middleRight.get().getFieldState() != FieldState.MARKED) middleRight.get().doExplore();
            if(bottomLeft != null && bottomLeft.get() != null && bottomLeft.get().getFieldState() != FieldState.MARKED) bottomLeft.get().doExplore();
            if(bottomCenter != null && bottomCenter.get() != null && bottomCenter.get().getFieldState() != FieldState.MARKED) bottomCenter.get().doExplore();
            if(bottomRight != null && bottomRight.get() != null && bottomRight.get().getFieldState() != FieldState.MARKED) bottomRight.get().doExplore();
        }
        
        updateIcon();
        board.check();
    }
}
