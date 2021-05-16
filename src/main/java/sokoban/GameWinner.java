package sokoban;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZoneId;
import java.util.List;

@lombok.Data
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
@lombok.Builder
public class GameWinner {


    private String name;


}
