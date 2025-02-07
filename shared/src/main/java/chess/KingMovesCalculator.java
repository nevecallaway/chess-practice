package chess;

import java.util.ArrayList;
import java.util.Collection;

public class KingMovesCalculator implements ChessMovesCalculator {
    @Override
    public Collection<ChessMove> calculateMoves(ChessBoard board, ChessPosition position) {
        Collection<ChessMove> validMoves = new ArrayList<>();
        int[] rowMoves = {-1, -1, 0, 1, 1, 1, 0, -1};
        int[] colMoves = {0, 1, 1, 1, 0, -1, -1, -1};

        for(int i = 0; i < 8; i++) {
            int potentialRow = position.getRow() + rowMoves[i];
            int potentialCol = position.getColumn() + colMoves[i];
            if (onBoard(potentialRow, potentialCol)) {
                ChessPosition newPosition = new ChessPosition(potentialRow, potentialCol);
                ChessPiece pieceAtNewPosition = board.getPiece(newPosition);
                if (pieceAtNewPosition == null || pieceAtNewPosition.getTeamColor() != board.getPiece(position).getTeamColor()) {
                    validMoves.add(new ChessMove(position, newPosition, null));
                }
            }
        }
        return validMoves;
    }

    private boolean onBoard (int row, int col){
        return row >= 0 && row < 8 && col >= 0 && col < 8;
    }
}
