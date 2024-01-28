package net.oriserver.aether.aether.chart;

import net.oriserver.aether.aether.Aether;
import net.oriserver.aether.aether.sqlite.SQLiteAPI;
import org.bukkit.plugin.java.JavaPlugin;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class ChartRankingDB extends SQLiteAPI {
    public ChartRankingDB(JavaPlugin plugin, String dbname) {
        super(plugin,dbname);
        String sql = "CREATE TABLE IF NOT EXISTS Chart_Ranking (" +
                "`stage_id` varchar NOT NULL," +
                "`player_uuid` varchar NOT NULL," +
                "`player_name` varchar NOT NULL," +
                "`time` BIGINT NOT NULL," +
                "`date` varchar NOT NULL," +
                "PRIMARY KEY (`player_uuid`, `stage_id`)" +
                ");";
        initialize(sql);
    }

    public int insertOrUpdateScoreIfTop5(int stage_id, String uuid,String player_name, Long time) {
        List<RankingData> top5 = getTop5Scores(stage_id);
        int position = -1;// 最適な位置を探索

        for(RankingData ti:top5){
            Aether.getPlugin().getLogger().info("time:"+ti.getTime());
        }

        for (int i = 0; i < top5.size(); i++) {

            if (time < top5.get(i).getTime()) {
                position = i;
                break;
            }
        }
        if (position == -1) {
            if (top5.size() < 5) {
                position = top5.size();
            } else {
                return -1; // 5位以内には入っていない
            }
        }
        // もし既存のランキングにuuidが存在すれば、更新が適切かどうか確認
        for (RankingData rank : top5) {
            if (uuid.equals(rank.getplayer_uuid())) {
                if (position <= top5.indexOf(rank) && time < rank.getTime()) {
                    insertOrUpdateScore(stage_id, uuid,player_name, time);
                    return position + 1;
                } else {
                    return -1;
                }
            }
        }
        // 新規スコアとして追加または更新
        insertOrUpdateScore(stage_id, uuid,player_name, time);
        if (top5.size() == 5) {deleteScore(stage_id, top5.get(4).getplayer_uuid());}
        return position + 1;
    }

    private void insertOrUpdateScore(int stage_id, String player_uuid,String player_name, Long time) {
        String checkSql = "SELECT 1 FROM Chart_Ranking WHERE stage_id = ? AND player_uuid = ?";
        List<Integer> checkResult = getDB(checkSql, Arrays.asList(stage_id, player_uuid), rs -> {
            List<Integer> results = new ArrayList<>();
            while (rs.next()) {
                results.add(rs.getInt(1));
            }
            return results;
        });
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String date = now.format(formatter);
        if (checkResult.isEmpty()) {
            String insertSql = "INSERT INTO Chart_Ranking(stage_id, player_uuid, player_name, time, date) VALUES(?, ?, ?, ?, ?)";
            setDB(insertSql, Arrays.asList(stage_id, player_uuid, player_name, time, date));
        } else {
            String updateSql = "UPDATE Chart_Ranking SET time = ?, player_name = ? WHERE stage_id = ? AND player_uuid = ?";
            setDB(updateSql, Arrays.asList(time,player_name, stage_id, player_uuid));
        }
    }

    private void deleteScore(int stage_id, String player_uuid) {
        setDB("DELETE FROM Chart_Ranking WHERE stage_id = ? AND player_uuid = ?", Arrays.asList(stage_id, player_uuid));
    }

    public List<RankingData> getTop5Scores(int stage_id) {
        String sql = "SELECT player_uuid, time FROM Chart_Ranking WHERE stage_id = ? ORDER BY time ASC LIMIT 5";
        return getDB(sql, Arrays.asList(stage_id), rs -> {
            List<RankingData> rankings = new ArrayList<>();
            while (rs.next()) {
                rankings.add(new RankingData(
                        rs.getString("player_uuid"),
                        rs.getLong("time")
                ));
            }
            return rankings;
        });
    }

    public List<RankingDataName> getTop5ScoreName(int stage_id) {
        String sql = "SELECT player_name, time FROM Chart_Ranking WHERE stage_id = ? ORDER BY time ASC LIMIT 5";
        return getDB(sql, Arrays.asList(stage_id), rs -> {
            List<RankingDataName> rankings = new ArrayList<>();
            while (rs.next()) {
                rankings.add(new RankingDataName(
                        rs.getString("player_name"),
                        rs.getLong("time")
                ));
            }
            return rankings;
        });
    }

    public static class RankingData {
        private final String player_uuid;
        private final Long time;
        public RankingData(String player_uuid, Long time) {
            this.player_uuid = player_uuid;
            this.time = time;
        }
        public String getplayer_uuid() {
            return player_uuid;
        }
        public Long getTime() {
            return time;
        }
    }
    public static class RankingDataName {
        private final String player_name;
        private final Long time;
        public RankingDataName(String player_name, Long time) {
            this.player_name = player_name;
            this.time = time;
        }
        public String getplayer_name() {
            return player_name;
        }
        public Long getTime() {
            return time;
        }
    }
}
