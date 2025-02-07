package chess;

import java.util.ArrayList;
import java.util.Collection;

public class RookMovesCalculator implements ChessMovesCalculator {
    @Override
    public Collection<ChessMove> calculateMoves(ChessBoard board, ChessPosition position) {
        Collection<ChessMove> validMoves = new ArrayList<>();
        ChessPiece piece = board.getPiece(position);
        if (piece == null) return validMoves;

        int[] rowMoves = {-1, 0, 1, 0};
        int[] colMoves = {0, 1, 0, -1};
        for (int i = 0; i < 4; i++) {
            int row = position.getRow();
            int col = position.getColumn();

            while (true) {
                row += rowMoves[i];
                col += colMoves[i];
                if (!onBoard(row, col)) {
                    break;
                }
                ChessPosition newPosition = new ChessPosition(row, col);
                ChessPiece pieceAtNewPosition = board.getPiece(newPosition);
                if (pieceAtNewPosition == null) {
                    validMoves.add(new ChessMove(position, newPosition, null));
                }
                else if (pieceAtNewPosition.getTeamColor() != piece.getTeamColor()) {
                    validMoves.add(new ChessMove(position, newPosition, null));
                    break;
                }
                else {
                    break;
                }
            }
        }
        return validMoves;
    }
    private boolean onBoard(int row, int col) {
        return row >= 1 && row <= 8 && col >= 1 && col <= 8;
    }
}
