package Com;

import java.awt.*;
import java.util.Timer;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Board extends JPanel {
    
    private final int BOARD_WIDTH=20;
    private final int BOARD_HEIGHT=22;
    private final int PERIOD_INTERVAL=300;
    
    private Timer timer;
    private boolean isFallingFinished = false;
    private boolean isPaused = false;
    private int numLinesRemoved = 0;
    private int curX =0;
    private int curY =0;
    private JLabel statusbar;
    private Shape curPiece;
    private Shape.Tet[] board;

    public Board(Tetris parent) {
        initBoard(parent);
    }

    private void initBoard(Tetris parent) {

        setFocusable(true);
        statusbar = parent.getStatusbar();
    }

    private int squareWidth() {

        return (int) getSize().getWidth() / BOARD_WIDTH;
    }

    private int squareHeight(){

        return (int) getSize().getHeight() / BOARD_HEIGHT;
    }

    private Shape.Tet shapeAT(int x, int y) {
        return board[(y*BOARD_WIDTH) + x];
    }

    void start() {
        curPiece = new Shape();
        board = new Shape.Tet[BOARD_WIDTH*BOARD_HEIGHT];

        clearBoard();
        newPiece();
    }

    private void paused() {
        isPaused = !isPaused;

        if (isPaused) {

            statusbar.setText("Game Paused");
        }else {
            statusbar.setText(String.valueOf(numLinesRemoved));
        }

        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }

    private void doDrawing(Graphics g)  {
        var size = getSize();
        int boardTop = (int) size.getHeight() - BOARD_HEIGHT*squareHeight();

        for (int i = 0; i < BOARD_HEIGHT; i++) {
            for (int j = 0; j < BOARD_WIDTH; j++) {

                Shape.Tet shape = shapeAT(j, BOARD_HEIGHT - i - 1);
                if (shape != Shape.Tet.NoShape) {

                    drawSquare(g, j * squareWidth(), boardTop + i*squareHeight(), shape);
                }
            }
        }

        if (curPiece.getShape() != Shape.Tet.NoShape) {
            for (int i = 0; i < 4; i++) {
                int x = curX + curPiece.x(i);
                int y = curY - curPiece.y(i);

                drawSquare(g, x*squareWidth(), boardTop + (BOARD_HEIGHT -y -1) * squareHeight(), curPiece.getShape());

            }
        }
    }


}
