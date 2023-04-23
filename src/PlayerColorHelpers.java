public class PlayerColorHelpers {
    public static PlayerColor GetOppositeColor(PlayerColor playerColor){
        return playerColor == PlayerColor.White ? PlayerColor.Black : PlayerColor.Black;
    }
}