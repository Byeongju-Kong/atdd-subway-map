package wooteco.subway.admin.dto.res;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import wooteco.subway.admin.domain.Line;
import wooteco.subway.admin.domain.Station;

public class LineResponse {
    private Long id;
    private String title;
    private String bgColor;
    private LocalTime startTime;
    private LocalTime endTime;
    private int intervalTime;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private List<Station> stations;

    public LineResponse() {
    }

    public LineResponse(Long id, String title, String bgColor, LocalTime startTime,
        LocalTime endTime, int intervalTime, LocalDateTime createdAt, LocalDateTime updatedAt,
        List<Station> stations) {
        this.id = id;
        this.bgColor = bgColor;
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.intervalTime = intervalTime;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.stations = stations;
    }

    public static LineResponse of(Line line) {
        return new LineResponse(line.getId(), line.getName(), line.getBgColor(),
            line.getStartTime(), line.getEndTime(), line.getIntervalTime(), line.getCreatedAt(),
            line.getUpdatedAt(), new ArrayList<>());
    }

    public static LineResponse of(Line line, List<Station> stations) {
        return new LineResponse(line.getId(), line.getName(), line.getBgColor(),
            line.getStartTime(), line.getEndTime(), line.getIntervalTime(), line.getCreatedAt(),
            line.getUpdatedAt(), stations);
    }

    public static List<LineResponse> listOf(List<Line> lines) {

        return lines.stream()
            .map(LineResponse::of)
            .collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBgColor() {
        return bgColor;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public int getIntervalTime() {
        return intervalTime;
    }

    public List<Station> getStations() {
        return stations;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}