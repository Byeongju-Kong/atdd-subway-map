package wooteco.subway.station;

public class Station {
    private Long id;
    private String name;

    public Station() {
    }

    public Station(Long id) {
        this(id, null);
    }

    public Station(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

