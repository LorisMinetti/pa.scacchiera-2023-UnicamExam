package it.unicam.cs.pa.scacchiera.list.util;

public enum Displacement {
        FrontRight(-1, +1),
        FrontLeft(-1, -1),
        BackRight(+1, +1),
        BackLeft(+1, -1),
        Front(-1,0),
        Back(+1, 0),
        Right(0, +1),
        Left(0, -1);

        private int rowDisplacement;
        private int columnDisplacement;

        Displacement(int rowDispl, int columnDispl) {
            rowDisplacement = rowDispl;
            columnDisplacement = columnDispl;
        }

        int getRowDisplacement() {
            return rowDisplacement;
        }

        int getColumnDisplacement() {
            return columnDisplacement;
        }
}
