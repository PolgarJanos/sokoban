package sokoban;

/**
 * Representing a winner Player.
 */
@lombok.Data
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
@lombok.Builder
public class GameWinner {


    private String name;
    private Integer steps;


}
