package ohtu;

public class TennisGame {

    private int score1 = 0;
    private int score2 = 0;
    private String player1Name;
    private String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName == "player1") {
            score1 += 1;
        } else {
            score2 += 1;
        }
    }

    public String getScore() {
        String score = "";

        if (score1 == score2) {
            score = sameScorePrints(score1);
        } else if (score1 >= 4 || score2 >= 4) {
            score = advantaceOrWin(score1, score2);
        } else {
            score = otherScenarios(score1, score2);
        }
        return score;
    }

    private String sameScorePrints(int bothScores) {
        String scorePrint = "";
        switch (bothScores) {
            case 0:
                scorePrint = "Love-All";
                break;
            case 1:
                scorePrint = "Fifteen-All";
                break;
            case 2:
                scorePrint = "Thirty-All";
                break;
            case 3:
                scorePrint = "Forty-All";
                break;
            default:
                scorePrint = "Deuce";
                break;
        }
        return scorePrint;
    }

    private String advantaceOrWin(int score1, int score2) {
        String scorePrint = "";
        int minusResult = score1 - score2;
        if (minusResult == 1) {
            scorePrint = "Advantage player1";
        } else if (minusResult == -1) {
            scorePrint = "Advantage player2";
        } else if (minusResult >= 2) {
            scorePrint = "Win for player1";
        } else {
            scorePrint = "Win for player2";
        }
        return scorePrint;
    }

    private String otherScenarios(int score1, int score2) {
        int tempScore = 0;
        String scorePrint = "";
        for (int i = 1; i < 3; i++) {
            if (i == 1) {
                tempScore = score1;
            } else {
                scorePrint += "-";
                tempScore = score2;
            }
            switch (tempScore) {
                case 0:
                    scorePrint += "Love";
                    break;
                case 1:
                    scorePrint += "Fifteen";
                    break;
                case 2:
                    scorePrint += "Thirty";
                    break;
                case 3:
                    scorePrint += "Forty";
                    break;
            }
        }
        return scorePrint;
    }

}
