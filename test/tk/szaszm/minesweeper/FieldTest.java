package tk.szaszm.minesweeper;

import org.junit.*;
/**
 * Created by marci on 2016.12.01..
 */

public class FieldTest {
    private Field field;

    @Before
    public void setup() {
        field = new Field(0, 0, null, null);
        Field topLeft = new Field(0, 0, null, null);
        Field topCenter = new Field(0, 0, null, null);
        Field topRight = new Field(0, 0, null, null);
        field.setTopLeft(topLeft);
        field.setTopCenter(topCenter);
        field.setTopRight(topRight);
    }

    @Test
    public void testNumberOfSurroundingBombs() {
        Assert.assertEquals(0, field.getNumberOfSurroundingBombs());
        field.getTopLeft().setBomb(true);
        field.getTopRight().setBomb(true);
        Assert.assertEquals(2, field.getNumberOfSurroundingBombs());
    }

    @Test
    public void testNumberOfSurroundingMarkedFields() {
        Assert.assertEquals(0, field.getNumberOfSurroundingMarkedFields());
        field.getTopLeft().setFieldState(FieldState.MARKED);
        field.getTopRight().setFieldState(FieldState.MARKED);
        Assert.assertEquals(2, field.getNumberOfSurroundingMarkedFields());
    }
}
