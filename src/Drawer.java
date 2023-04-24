public class Drawer {
    public static void DrawBoard(Piece[][] fields) {
        for(int y = 0; y < fields.length; y++) {
            StringBuilder line = new StringBuilder();
            for(int x = 0; x < fields[y].length; x++){
                var piece = fields[x][y];
                if(piece != null){
                    line.append(piece.getIcon());
                }
                else{
                    line.append(" ");
                }
            }
            System.out.println(line);
        }
    }
}
