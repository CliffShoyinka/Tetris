package Com;

import java.util.Random;

public class Shape {

    protected enum Tet {
        NpShape, ZShape, SShape, LineShape, TShape, SquareShape, LShape, NoShape, MirroredShape
    }

    private Tet pieceShape;
    private int coords[][];
    private int[][][] coordsTable;

    public Shape() {
        initShape();
    }

    private void initShape() {
        coords = new int[4][2];

        coordsTable = new int[][][]{

        };

        setShape(Tet.NoShape);

    }

    private void setShape(Tet shape) {

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 2; j++) {
                coords[i][j] = coordsTable[shape.ordinal()][i][j];

            }
        }


    }

    public void setX(int index, int x) {
        coords[index][0] = x;
    }

    private void setY(int index, int y) {
        coords[index][1] = y;
    }

    public int x(int index) {
        return coords[index][0];
    }

    public int y(int index) {
        return coords[index][0];
    }


    public Tet getShape() {
        return pieceShape;
    }

    public void setRandomShape() {
        var r = new Random();
        int x = Math.abs(r.nextInt()) %7 +1;
    }
}