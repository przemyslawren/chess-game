public class PlayerColorHelpers {
    public static PlayerColor GetOppositeColor(PlayerColor playerColor){
        return playerColor == PlayerColor.White ? PlayerColor.White : PlayerColor.Black;
    }
}