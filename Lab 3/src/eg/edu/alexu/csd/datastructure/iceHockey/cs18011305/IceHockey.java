package eg.edu.alexu.csd.datastructure.iceHockey.cs18011305;

import java.awt.*;
import java.util.Arrays;

/**
 * @author Mark Nader Fathy
 */

public class IceHockey implements IPlayersFinder {
    private char [][] picture;
    private boolean [][] boolArray;
    private int retSize = 0;

    private int minX = -1;
    private int minY = -1;
    private int maxX = -1;
    private int maxY = -1;

    @Override
    public Point[] findPlayers(String[] photo, int team, int threshold) {
        retSize = 0;
        if (photo == null || photo.length == 0 || photo[0].length() == 0) {
            return new Point[0];
        }
        Point[] retArray = new Point[10000];
        picture = formingCharArray(photo);
        for (int i = 0; i < picture.length; i++) {
            for (int j = 0; j < picture[0].length; j++) {
                if (picture[i][j] == team + '0' && !boolArray[i][j]) {
                    minX = j * 2;
                    minY = i * 2;
                    maxX = j * 2 + 2;
                    maxY = i * 2 + 2;
                    int count = checkingSurroundings(picture, boolArray, i, j, team + '0');
                    if (count * 4 >= threshold) {
                        retArray[retSize++] = new Point(minX + ((maxX - minX)/2),minY + ((maxY - minY)/2));
                    }
                }
            }
        }
        // create new array with actual size and sort
        Point[] ret = new Point[retSize];
        System.arraycopy(retArray, 0, ret, 0, retSize);
        Arrays.sort(ret, (p1, p2) -> {
            if (p1.x != p2.x) {
                return Integer.compare(p1.x, p2.x);
            } else {
                return Integer.compare(p1.y, p2.y);
            }
        });
        return ret;
    }

    private int checkingSurroundings (char[][] picture, boolean[][] boolArray, int i, int j, int team) {
        if (i < 0 || j < 0 || i >= picture.length || j >= picture[0].length) {
            return 0;
        }

        if (picture[i][j] != team || boolArray[i][j]) {
            return 0;
        }

        boolArray[i][j] = true;

        minX = Math.min(minX, j * 2);
        minY = Math.min(minY, i * 2);
        maxX = Math.max(maxX, j * 2 + 2);
        maxY = Math.max(maxY, i * 2 + 2);

        int count = 1;
        count += checkingSurroundings(picture, boolArray, i + 1, j, team);
        count += checkingSurroundings(picture, boolArray, i, j + 1, team);
        count += checkingSurroundings(picture, boolArray, i - 1, j, team);
        count += checkingSurroundings(picture, boolArray, i, j - 1, team);
        return count;
    }

    private char[][] formingCharArray (String[] photo) {
        picture = new char[photo.length][photo[0].length()];
        boolArray = new boolean[photo.length][photo[0].length()];
        for (int i = 0; i < photo.length; i++) {
            for (int j = 0; j < photo[i].length(); j++) {
                picture[i][j] = photo[i].charAt(j);
                boolArray[i][j] = false;
            }
        }
        return picture;
    }
}
