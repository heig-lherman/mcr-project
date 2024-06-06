package heig.mcr.visitor.game.map;

import heig.mcr.visitor.game.Level;
import heig.mcr.visitor.game.actor.npc.Luke;
import heig.mcr.visitor.game.actor.npc.RandomGhost;
import heig.mcr.visitor.game.actor.npc.Sith;
import heig.mcr.visitor.game.actor.npc.Vader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.function.Predicate;

public final class MapParser {

    private MapParser() {
    }

    public static Level parse(String resourcePath) throws IOException {
        try (InputStream inputStream = MapParser.class.getResourceAsStream(resourcePath)) {
            return parse(inputStream);
        }
    }

    public static Level parse(InputStream inputStream) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            char[][] map = reader.lines()
                    .filter(Predicate.not(String::isBlank))
                    .map(String::toCharArray)
                    .toArray(char[][]::new);
            return parse(map);
        }
    }

    public static Level parse(char[][] map) {
        LevelBuilder builder = LevelBuilder.start(map[0].length, map.length);

        for (int y = 0; y < map.length; y++) {
            if (map[y].length != map[0].length) {
                throw new IllegalArgumentException("Map is not rectangular");
            }

            for (int x = 0; x < map[y].length; x++) {
                switch (map[y][x]) {
                    case 'P':
                        builder.addPlayer(x, y);
                        break;
                    case 'V':
                        builder.addGhost(x, y, new Vader());
                        break;
                    case 'S':
                        builder.addGhost(x, y, new Sith());
                        break;
                    case 'L':
                        builder.addGhost(x, y, new Luke());
                        break;
                    case '|':
                        builder.addWall(x, y);
                        break;
                    case '.':
                        builder.addPellet(x, y);
                        break;
                    case '+':
                        builder.addSuperPellet(x, y);
                        break;
                    case ' ':
                    default:
                        builder.addGround(x, y);
                        break;
                }
            }
        }

        return builder.build();
    }
}
