public class PlayerColorHelpers {
    public static PlayerColor GetOppositeColor(PlayerColor playerColor){
        return playerColor == PlayerColor.WHITE ? PlayerColor.WHITE : PlayerColor.BLACK;
    }
}