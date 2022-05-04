package wooteco.subway.service;

import java.util.List;
import java.util.Optional;
import wooteco.subway.dao.LineDao;
import wooteco.subway.domain.Line;
import wooteco.subway.exception.DuplicatedLineException;
import wooteco.subway.exception.LineNotFoundException;

public class LineService {

    public static Line save(Line line) {
        if (LineDao.exists(line)) {
            throw new DuplicatedLineException();
        }
        return LineDao.save(line);
    }

    public static List<Line> findAll() {
        return LineDao.findAll();
    }

    public static void deleteById(Long id) {
        Optional<Line> line = LineDao.findById(id);
        validateNull(line);
        LineDao.deleteById(id);
    }

    public static Line findLineById(Long id) {
        Optional<Line> line = LineDao.findById(id);
        validateNull(line);
        return line.get();
    }

    public static void update(Long id, Line updatingLine) {
        Optional<Line> line = LineDao.findById(id);
        validateNull(line);
        LineDao.update(id, updatingLine);
    }

    private static void validateNull(Optional<Line> line) {
        if (line.isEmpty()) {
            throw new LineNotFoundException();
        }
    }
}